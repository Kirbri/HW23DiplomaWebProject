package avito.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class FavoritePage {
    private final SelenideElement titleH1 = $("h1"),
            itemContentCount = $("[data-marker='favorites-rubricator/item-0/selected']");

    @Step("Проверить текстовый заголовок страницы H1")
    public FavoritePage checkPageTitleTextH1() {
        titleH1.shouldHave(text("Избранное"));
        return this;
    }

    @Step("Проверить, что одно объявление добавлено в избранное")
    public FavoritePage checkTheNumberOfFavoriteAnnouncements() {
        itemContentCount.shouldHave(exactText("Все1"));
        return this;
    }
}