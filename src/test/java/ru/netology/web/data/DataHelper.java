package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {

        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static String getFirstCardNumber() {
        return "5559000000000001";
    }

    public static String getSecondCardNumber() {
        return "5559000000000002";
    }

    @Value
    public static class TransferInfo {
        int amount;
        String from;
    }

    public static TransferInfo getTransferFromFirstCard(int amount) {
        return new TransferInfo(
                amount,
                getFirstCardNumber()
        );
    }

    public static TransferInfo getTransferFromSecondCard(int amount) {
        return new TransferInfo(
                amount,
                getSecondCardNumber()
        );
    }
}
