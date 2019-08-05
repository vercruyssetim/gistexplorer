package com.cegeka.gistexplorer.progresstracker.domain;

import java.time.LocalDate;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Progress {
    private String username;
    private List<String> completedItems = newArrayList();
    private List<String> uncompletedItems = newArrayList();
    private List<String> uncertainItems = newArrayList();
    private int totalItems = 0;
    private LocalDate lastUpdate;
    private String url;

    public Progress(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getCompletedItems() {
        return completedItems;
    }

    public void addToCompletedItems(String item) {
        completedItems.add(item);
    }

    public List<String> getUncompletedItems() {
        return uncompletedItems;
    }

    public void addToUncompletedItems(String item) {
        uncompletedItems.add(item);
    }

    public List<String> getUncertainItems() {
        return uncertainItems;
    }

    public void addToUncertainItems(String item) {
        uncertainItems.add(item);
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getNumberOfCompletedItems() {
        return completedItems.size();
    }

    public void calculateProgress() {
        this.totalItems = completedItems.size() + uncompletedItems.size() + uncertainItems.size();
    }

    public int getPercentage() {
        return (int) (((double) getNumberOfCompletedItems() / (double) getTotalItems()) * 100);
    }

    public String getStyle() {
        return "width:" + getPercentage() + "%";
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
