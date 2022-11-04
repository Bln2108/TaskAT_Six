package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private static final String BALANCE_BEGIN = "баланс: ";
    private static final String BALANCE_END = " р.";

    private final ElementsCollection cards = $$("li div");

    public DashboardPage() {
        $("h1").shouldHave(Condition.text("Ваши карты"));
    }

    public List<String> getCardIdList() {
        return cards.stream()
                .map(element ->
                        element.getAttribute("data-test-id"))
                .collect(Collectors.toList());
    }

    public int getCardBalance(String cardId) {
        String text = cards.find(Condition.attribute("data-test-id", cardId)).text();
        int begin = text.indexOf(BALANCE_BEGIN);
        int end = text.indexOf(BALANCE_END);

        return Integer.parseInt(text.substring(begin + BALANCE_BEGIN.length(), end));
    }

    public CardPage transfer(String cardId) {
        cards.find(Condition.attribute("data-test-id", cardId))
                .find(By.cssSelector("button"))
                .click();

        return new CardPage();
    }
}
