package com.test.actions;

import com.test.base.BaseActions;
import com.test.other.Book;
import com.test.pages.Pages;
import com.test.util.Constants;
import org.testng.Reporter;

import java.util.ArrayList;

import static com.test.base.BaseTest.driver;

public class MainActions extends BaseActions {

    public void openMainPage(){
        Reporter.log("Opening main page: " + Constants.BASE_URL);
        driver.get(Constants.BASE_URL);
    }

    public void clearSession() {
        driver.manage().deleteAllCookies();
    }

    public void openPage(String url){
        Reporter.log("Opening page: " + url);
        driver.get(url);
    }

    public void search(String value){
        Pages.searchForm().typeSearchField(value);
        Pages.searchForm().submitSearchForm();
    }

    public ArrayList<Book> getBooksList(){

        ArrayList<Book> books = new ArrayList<Book>();

        for (int i = 0; i < Pages.searchResult().getItemsNum(); i++){

            Book book = new Book();

            if( Pages.searchResult().isBookNamePresent(i) ){
                book.setName(Pages.searchResult().getBookName(i));
            } else {
                book.setName("Unknown");
            }
            if(Pages.searchResult().isAuthorPresent(i)){
                book.setAuthor(Pages.searchResult().getAuthor(i));
            } else {
                book.setAuthor("Unknown");
            }
            if(Pages.searchResult().isPricePresent(i)){
                book.setPrice(Pages.searchResult().getPrice(i));
            } else {
                book.setPrice(0.0f);
            }
            book.setBestseller(false);
            if(Pages.searchResult().isStarsPresent(i)){
                book.setRate(Pages.searchResult().getStars(i));
                if(Pages.searchResult().getStars(i) == 5){
                    book.setBestseller(true);
                }
            } else {
                book.setRate(0.0f);
            }

            books.add(book);
        }
        return books;
    }

    public boolean isBookInsideList(ArrayList<Book> books, String bookName){
        for(int j = 0; j < books.size() ; j++) {
            if( books.get(j).getName().equals(bookName)){
                return true;
            }
        }
        return false;
    }

}
