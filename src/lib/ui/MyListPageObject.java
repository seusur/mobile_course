package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyListPageObject extends MainPageObject {

    public static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS START */
    public static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    public static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    /* TEMPLATES METHODS END */

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find folder by name " + name_of_folder,
                15
        );
    }

    public WebElement waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        return this.waitForElementPresent(
            By.xpath(article_xpath),
            "Cannot find saved article title " + article_title,
            10
        );
    }

    public void openArticleByTitle(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title).click();
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Cannot delete saved article title " + article_title,
                10
        );
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find saved article title " + article_title
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }
}
