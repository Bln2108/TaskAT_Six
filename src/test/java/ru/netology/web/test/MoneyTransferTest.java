package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV1;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.LoginPageV3;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
//    var loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        List<String> cardIdList = dashboardPage.getCardIdList();
        String firstCardId = cardIdList.get(0);
        int firstCardBalance = dashboardPage.getCardBalance(firstCardId);
        int secondCardBalance = dashboardPage.getCardBalance(cardIdList.get(1));

        DashboardPage dashboardPage1 = dashboardPage.transfer(firstCardId)
                .validTransfer(
                        10,
                        "5559000000000002"
                );
        List<String> cardIdList1 = dashboardPage1.getCardIdList();
        Assertions.assertEquals(
                firstCardBalance + 10,
                dashboardPage1.getCardBalance(cardIdList1.get(0))
        );
        Assertions.assertEquals(
                secondCardBalance - 10,
                dashboardPage1.getCardBalance(cardIdList1.get(1))
        );
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
//    var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        List<String> cardIdList = dashboardPage.getCardIdList();
        int firstCardBalance = dashboardPage.getCardBalance(cardIdList.get(0));
        String secondCardId = cardIdList.get(1);
        int secondCardBalance = dashboardPage.getCardBalance(secondCardId);

        DashboardPage dashboardPage1 = dashboardPage.transfer(secondCardId)
                .validTransfer(
                        50,
                        "5559000000000001"
                );
        List<String> cardIdList1 = dashboardPage1.getCardIdList();
        Assertions.assertEquals(
                firstCardBalance - 50,
                dashboardPage1.getCardBalance(cardIdList1.get(0))
        );
        Assertions.assertEquals(
                secondCardBalance + 50,
                dashboardPage1.getCardBalance(cardIdList1.get(1))
        );
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV3() {
        var loginPage = open("http://localhost:9999", LoginPageV3.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        List<String> cardIdList = dashboardPage.getCardIdList();
        String firstCardId = cardIdList.get(0);
        int firstCardBalance = dashboardPage.getCardBalance(firstCardId);
        int secondCardBalance = dashboardPage.getCardBalance(cardIdList.get(1));

        DashboardPage dashboardPage1 = dashboardPage.transfer(firstCardId)
                .validTransfer(
                        100,
                        "5559000000000001"
                );
        List<String> cardIdList1 = dashboardPage1.getCardIdList();
        Assertions.assertEquals(
                firstCardBalance,
                dashboardPage1.getCardBalance(cardIdList1.get(0))
        );
        Assertions.assertEquals(
                secondCardBalance,
                dashboardPage1.getCardBalance(cardIdList1.get(1))
        );
    }
}
