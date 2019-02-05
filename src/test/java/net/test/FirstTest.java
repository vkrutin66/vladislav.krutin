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

        Pages.searchForm().search("Java");
        Pages.searchForm().submitSearchForm();

        ArrayList<Book> books = new ArrayList<Book>();

        for (int i = 0; i < Pages.searchResult().getItemsNum(); i++){

            Book book = new Book();
            book.setName(Pages.searchResult().getName(i));
            book.setAuthor(Pages.searchResult().getAuthor(i));
            book.setPrice(Pages.searchResult().getPrice(i));
            book.setRate(Pages.searchResult().getStars(i));

            books.add(book);
        }

        boolean isBookInside = false;
        for(int j = 0; j < books.size() ; j++) {
            if( books.get(j).getName().equals("Head First Java, 2nd Edition")){
                isBookInside = true;
            }
        }

        Assert.assertTrue(isBookInside, "book on amazon");

    }

}
