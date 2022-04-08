package ru.netology.web.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import io.cucumber.java.ru.Когда;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.VerificationPage;
import ru.netology.web.page.DashboardPage;

import static org.junit.Assert.assertEquals;

public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void loginWithNameAndPasswordAndVerify(String login, String password) {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1000x800";
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        verificationPage = loginPage.validLogin(login, password);
        var verificationCode = DataHelper.getVerificationCodeFor(new DataHelper.AuthInfo(login, password)).getCode();
        dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.verifyIsDashboardPage();
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою \"{int}\" карту с главной страницы")
    public void transferMoneyToOwnCard(String amount, String numberOfCardSender, int idOfCardRecipient) {
        dashboardPage.moneyTransfer(amount, numberOfCardSender, idOfCardRecipient);
    }

    @Тогда("баланс его \"{int}\" карты из списка на главной странице должен стать {string} рублей")
    public void verify(int id, String expectedBalance) {
        assertEquals(Integer.parseInt(expectedBalance.replace (" ", "")), dashboardPage.getCardBalance(id));
    }
}