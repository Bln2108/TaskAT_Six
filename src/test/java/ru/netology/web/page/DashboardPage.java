package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private static final String BALANCE_BEGIN = "баланс: ";
    private static final String BALANCE_END = " р.";

    private final ElementsCollection cards = $$("li div");

    public DashboardPage() {
        $("h1").shouldHave(Condition.text("Ваши карты"));
    }

    public int getCardBalance(int index) {
        String text = cards.get(index).text();
        int begin = text.indexOf(BALANCE_BEGIN);
        int end = text.indexOf(BALANCE_END);

        return Integer.parseInt(text.substring(begin + BALANCE_BEGIN.length(), end));
    }

    public CardPage validCard(int index) {
        cards.get(index)
                .find(By.cssSelector("button"))
                .click();

        return new CardPage();
    }
}
