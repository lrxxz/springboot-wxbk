package org.lrx.entity;

public class UniversitySearch {
    private Integer searchId;
    private String searchContent;

    @Override
    public String toString() {
        return "UniversitySearch{" +
                "searchId=" + searchId +
                ", searchContent='" + searchContent + '\'' +
                '}';
    }

    public UniversitySearch(Integer searchId, String searchContent) {
        this.searchId = searchId;
        this.searchContent = searchContent;
    }

    public UniversitySearch() {
    }

    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
}
