package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "//*[ .//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}'] and .//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
            SEARCH_ARTICLES_TITLES = "org.wikipedia:id/page_list_item_title";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS START */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getSearchResultByTitleAndDescriptionXpath(String title, String description)
    {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS END */

    public void initSearchInput()
    {
        this.waitForElementAndClick(
                By.id(SEARCH_INIT_ELEMENT),
                "Cannot find Search Wikipedia input.",
                5
        );
        this.waitForElementPresent(
                By.id(SEARCH_INIT_ELEMENT),
                "Cannot find search input after clicking search init element.",
                5
        );
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find X to cancel search",
                5
        );
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "X is still present on the page",
                5
        );
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find and click X cancel search button",
                5
        );
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(
                By.xpath(SEARCH_INPUT),
                search_line,
                "Cannot find and type into search input",
                5
        );
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                By.xpath(search_result_xpath),
                "Cannot find search result with substring " + substring,
                5
        );
    }

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_xpath = getSearchResultByTitleAndDescriptionXpath(title, description);
        this.waitForElementPresent(
                By.xpath(search_result_xpath),
                "Cannot find search result with title " + title + " and description " + description,
                5
        );
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                By.xpath(search_result_xpath),
                "Cannot find search result with substring " + substring,
                5
        );
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request",
                15
        );

        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result element.",
                15
        );
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We supposed not to find any results."
        );
    }

    public List<WebElement> waitForAllArticlesTitlesPresent()
    {
        return this.waitForAllElementsPresent(
                By.id(SEARCH_ARTICLES_TITLES),
                "No articles found.",
                15
        );
    }

    public void waitForAllArticlesTitlesToDisappear()
    {
        this.waitForElementNotPresent(
                By.id(SEARCH_ARTICLES_TITLES),
                "Articles titles are still present on the page.",
                5
        );
    }

    public void assertAllArticlesTitlesContainsSearchText(String search_text, List<WebElement> articles_titles)
    {
        for (WebElement article : articles_titles) {
            String title_text = article.getText();
            System.out.println("Current title text is " + title_text);
            Assert.assertTrue(
                    search_text + " not found in " + title_text,
                    title_text.contains(search_text)
            );
        }
    }
}
