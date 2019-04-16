package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {

    @Test
    public void testCheckSearchText()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String title = "Java (programming language)";
        String description = "Object-oriented programming language";
        SearchPageObject.waitForElementByTitleAndDescription(title, description);
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );

    }

    @Test
    public void testAmountOfNotEmptySearchByMyQuery()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_query = "Kotlin";
        SearchPageObject.typeSearchLine(search_query);
        List<WebElement> articles_titles = SearchPageObject.waitForAllArticlesTitlesPresent();
        int amount_of_search_results = articles_titles.size();

        assertTrue(
                "Found less then three search results",
                amount_of_search_results >= 3
        );

        SearchPageObject.assertAllArticlesTitlesContainsSearchText(search_query, articles_titles);
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "zxcbvbnmm,lkjh";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }

    @Test
    public void testSearchArticles()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_title = "Python";
        SearchPageObject.typeSearchLine(search_title);
        List<WebElement> articles_titles = SearchPageObject.waitForAllArticlesTitlesPresent();
        SearchPageObject.assertAllArticlesTitlesContainsSearchText(search_title, articles_titles);
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForAllArticlesTitlesToDisappear();
    }

    @Test
    public void testSearchArticleAndAssertTitle()
    {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Python");
        SearchPageObject.waitForSearchResult("Python (programming language)");

        AssertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Title not present on the page."
        );
    }

    private void AssertElementPresent(By by, String error_message)
    {
        List<WebElement> elements = driver.findElements(by);
        assertTrue(
                error_message,
                elements.size() != 0
        );
    }

}
