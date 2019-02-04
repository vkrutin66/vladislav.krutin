package com.test.pages;

public class Pages {

    private static LoginPage loginPage;
    private static SearchForm searchForm;
    private static SearchResult searchResult;

    public static LoginPage loginPage() {
        if (loginPage == null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public static SearchForm searchForm() {
        if (searchForm == null){
            searchForm = new SearchForm();
        }
        return searchForm;
    }

    public static SearchResult searchResult() {
        if (searchResult == null){
            searchResult = new SearchResult();
        }
        return searchResult;
    }

}
