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

        var firstCardBalance = dashboardPage.getCardBalance(0);
        var secondCardBalance = dashboardPage.getCardBalance(1);

        var cardPage = dashboardPage.validCard(0);
        var amount = 10;
        var dashboardPage1 =
                cardPage.validTransfer(DataHelper.getTransferInfo(
                        amount,
                        DataHelper.getSecondCardNumber()
                ));
        Assertions.assertEquals(
                firstCardBalance + amount,
                dashboardPage1.getCardBalance(0)
        );
        Assertions.assertEquals(
                secondCardBalance - amount,
                dashboardPage1.getCardBalance(1)
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

        var firstCardBalance = dashboardPage.getCardBalance(0);
        var secondCardBalance = dashboardPage.getCardBalance(1);

        var cardPage = dashboardPage.validCard(1);
        var amount = 50;
        var dashboardPage1 =
                cardPage.validTransfer(DataHelper.getTransferInfo(
                        amount,
                        DataHelper.getFirstCardNumber()
                ));
        Assertions.assertEquals(
                firstCardBalance - amount,
                dashboardPage1.getCardBalance(0)
        );
        Assertions.assertEquals(
                secondCardBalance + amount,
                dashboardPage1.getCardBalance(1)
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

        var firstCardBalance = dashboardPage.getCardBalance(0);
        var secondCardBalance = dashboardPage.getCardBalance(1);

        var cardPage = dashboardPage.validCard(0);
        var dashboardPage1 =
                cardPage.validTransfer(DataHelper.getTransferInfo(
                        100,
                        DataHelper.getFirstCardNumber()
                ));
        Assertions.assertEquals(
                firstCardBalance,
                dashboardPage1.getCardBalance(0)
        );
        Assertions.assertEquals(
                secondCardBalance,
                dashboardPage1.getCardBalance(1)
        );
    }
}
