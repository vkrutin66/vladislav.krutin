package net.test;

import com.test.actions.Actions;
import com.test.base.BaseTest;
import com.test.other.Book;
import com.test.pages.Pages;
import com.test.util.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class FirstTest extends BaseTest {

    @Test
    public void test1(){

        Actions.mainActions().openMainPage();

        Actions.mainActions().search("Java");

        ArrayList<Book> books = Actions.mainActions().getBooksList();

        boolean isBookInside = Actions.mainActions().isBookInsideList(books, "Head First Java, 2nd Edition");

        Assert.assertTrue(isBookInside, "book on amazon");

    }

}
