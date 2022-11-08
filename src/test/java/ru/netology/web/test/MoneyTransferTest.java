package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPageV2;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var firstCardBalance = dashboardPage.getFirstCardBalance();
        var secondCardBalance = dashboardPage.getSecondCardBalance();

        var firstCardPage = dashboardPage.validFirstCard();
        var amount = 10;
        var dashboardPage1 =
                firstCardPage.validTransfer(DataHelper.getTransferFromSecondCard(amount));
        Assertions.assertEquals(
                firstCardBalance + amount,
                dashboardPage1.getFirstCardBalance()
        );
        Assertions.assertEquals(
                secondCardBalance - amount,
                dashboardPage1.getSecondCardBalance()
        );
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var firstCardBalance = dashboardPage.getFirstCardBalance();
        var secondCardBalance = dashboardPage.getSecondCardBalance();

        var secondCardPage = dashboardPage.validSecondCard();
        var amount = 5000;
        var dashboardPage1 =
                secondCardPage.validTransfer(DataHelper.getTransferFromFirstCard(amount));
        Assertions.assertEquals(
                firstCardBalance - amount,
                dashboardPage1.getFirstCardBalance()
        );
        Assertions.assertEquals(
                secondCardBalance + amount,
                dashboardPage1.getSecondCardBalance()
        );
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV3() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var firstCardBalance = dashboardPage.getFirstCardBalance();
        var secondCardBalance = dashboardPage.getSecondCardBalance();

        var firstCardPage = dashboardPage.validFirstCard();
        var dashboardPage1 =
                firstCardPage.validTransfer(DataHelper.getTransferFromFirstCard(100));
        Assertions.assertEquals(
                firstCardBalance,
                dashboardPage1.getFirstCardBalance()
        );
        Assertions.assertEquals(
                secondCardBalance,
                dashboardPage1.getSecondCardBalance()
        );
    }
}
