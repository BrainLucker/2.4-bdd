package ru.netology.web.data;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataHelper {

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static CardNumbers getCardNumbersFor(AuthInfo authInfo) {
        return new CardNumbers(List.of("5559 0000 0000 0001", "5559 0000 0000 0002"));
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    @Value
    public static class CardNumbers {
        private List<String> cardNumbers;
    }
}