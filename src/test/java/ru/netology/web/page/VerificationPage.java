package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id=code] input");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private final SelenideElement errorPopup = $(".notification__content");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(String verificationCode) {
        codeField.val(verificationCode);
        verifyButton.click();
        return new DashboardPage();
    }

    public void verifyCodeIsInvalid() {
        errorPopup.shouldHave(text("Неверно указан код! Попробуйте ещё раз."));
    }
}