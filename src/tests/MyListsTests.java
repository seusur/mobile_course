package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);

    }

    @Test
    public void testSaveTwoArticlesToMyList()
    {
        String first_search_article = "Java (programming language)";
        String second_search_article = "Python (programming language)";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring(first_search_article);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        // second article search

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Python");
        SearchPageObject.clickByArticleWithSubstring(second_search_article);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToAlreadyExistList(name_of_folder);
        ArticlePageObject.closeArticle();

        // after search two articles

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();
        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);



        MyListPageObject.swipeByArticleToDelete(first_search_article);
        MyListPageObject.openArticleByTitle(second_search_article);

        String title_after_deleted_first_article = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after deleted first article",
                second_search_article,
                title_after_deleted_first_article
        );

    }

}
