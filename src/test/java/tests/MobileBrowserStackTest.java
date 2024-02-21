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


public class MobileBrowserStackTest extends BaseTest {
    @Test
    @DisplayName("Checking search results")
    void searchWikipediaMobileTest() {
        step("Sending a request 'China' to app 'Wikipedia' ", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("China");
        });

        step("Check that the application showed search results", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));

        step("Checking the title of the first article from the search results", () ->
                $(id("org.wikipedia.alpha:id/page_list_item_description"))
                        .shouldHave(text("Country in East Asia")));

        step("Click on the first article", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title")).first().click());

        step("Checking that the application has generated an error", () ->
                $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldBe(visible));
    }
}
