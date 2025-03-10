package com.example.magasklad.Models;

import java.util.ArrayList;

public class Pagination<T> {
    private final int maxItemsOnPage = 2;
    private int totalNumberPages;
    private int currentPage;
    private int currentNumberItems;
    private ArrayList<T> currentItems;

    public Pagination(ArrayList<T> currentItems, int currentPage) {
        this.totalNumberPages = getTotalNumberPages(currentItems);
        this.currentPage = currentPage;
        this.currentItems = currentPage > totalNumberPages ? new ArrayList<>() : getItemsForCurrentPage(currentItems);
        currentNumberItems = currentItems.size();
    }

    private int getTotalNumberPages(ArrayList<T> currentItems) {
        int page = currentItems.size() / maxItemsOnPage;
        if (currentItems.size() % maxItemsOnPage > 0)
            page++;

        return page;
    }

    private ArrayList<T> getItemsForCurrentPage(ArrayList<T> allItems) {

        ArrayList<T> arrayList = new ArrayList<>();

        int startIndex = maxItemsOnPage * currentPage - maxItemsOnPage;
        int endIndex = startIndex + maxItemsOnPage;

        if (endIndex >= allItems.size()) {
            endIndex = allItems.size() - 1;
        }

        for (int i = startIndex; i <= endIndex; i++) {
            if (arrayList.size() == maxItemsOnPage) {
                break;
            }
            arrayList.add(allItems.get(i));
        }

        return arrayList;
    }

    public int getTotalNumberPages() {
        return totalNumberPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getCurrentNumberItems() {
        return currentNumberItems;
    }

    public ArrayList<T> getCurrentItems() {
        return currentItems;
    }
}