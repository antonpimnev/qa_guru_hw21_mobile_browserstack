package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.id;

public class SelenideTest extends TestBase {

    @Test
    @Tag("android")
    void searchTestAndroid() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Java");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }
    @Test
    @Tag("ios")
    void loginTestIos() {
        step("Click button", () ->{
            $(id("Text Button")).click();
        });
        step("Write email", () ->{
            $(id("Text Input")).click();
            $(id("Text Input")).sendKeys("mail@gmail.com");
            $(id("Text Input")).pressEnter();
        });
        step("Check result", () ->{
            assertEquals("mail@gmail.com", $(id("Text Output")).getText());
        });
    }
}