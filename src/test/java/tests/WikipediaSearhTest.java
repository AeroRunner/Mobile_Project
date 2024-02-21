package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;


public class WikipediaSearhTest extends BaseTest {
    private static String appName = "Search Wikipedia";
    private static String requestText = "China";
    private static String articleTitle = "Country in East Asia";
    @Test
    @DisplayName("Проверка отображения статей после отправки запроса в поиск")
    void searchWikipediaMobileTest() {
        step("Отправляем запрос 'China' в поиске приложения Wikipedia ", () -> {
            $(accessibilityId(appName)).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(requestText);
        });

        step("Проверяем , что после запроса появились статьи", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));

    }

    @Test
    @DisplayName("Проверка наличия заголовка у статьи")
    void checkErrorTest() {
        step("Отправляем запрос 'China' в поиске приложения Wikipedia ", () -> {
            $(accessibilityId(appName)).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(requestText);
        });
        step("Проверяем наличие заголовка у статьи", () ->
                $(id("org.wikipedia.alpha:id/page_list_item_description"))
                        .shouldHave(text(articleTitle)));

    }

    @Test
    @DisplayName("Проверка отображения ошибки в открывшейся статье")
    void checkTittleInArticle() {
        step("Отправляем запрос 'China' в поиске приложения Wikipedia ", () -> {
            $(accessibilityId(appName)).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(requestText);
        });
        step("Кликаем на первую статью из поиска", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title")).first().click());

        step("Проверяем наличие ошибки после клика по статье", () ->
                $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldBe(visible));

    }
}
