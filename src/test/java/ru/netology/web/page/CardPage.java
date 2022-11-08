package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class CardPage {
    private final SelenideElement amountField = $("[data-test-id=amount] input");
    private final SelenideElement fromField = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public CardPage() {
        $("h1").shouldHave(Condition.text("Пополнение карты"));
    }

    public DashboardPage validTransfer(DataHelper.TransferInfo transferInfo) {
        amountField.sendKeys(Integer.toString(transferInfo.getAmount()));
        fromField.sendKeys(transferInfo.getFrom());
        transferButton.click();
        return new DashboardPage();
    }
}