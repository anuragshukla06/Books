package com.example.books;

public class BookItem {

    private String mTitle;
    private String mDescription;
    private String mPreviewLink;
    private String mInfoLink;
    private String mBuyLink;

    BookItem(String title, String description, String previewLink, String infoLink, String buyLink) {
        mTitle = title;
        mDescription = description;
        mPreviewLink = previewLink;
        mInfoLink = infoLink;
        mBuyLink = buyLink;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getPreviewLink() {
        return mPreviewLink;
    }

    public String getInfoLink() {
        return mInfoLink;
    }

    public String getBuyLink() {
        return mBuyLink;
    }
}
