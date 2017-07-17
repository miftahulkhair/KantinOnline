package com.example.mmift.kantinonline.Model;

/**
 * Created by mmift on 4/14/2017.
 */

public class Menu {

    private String mListMenu, mURL;
    private int mHargaMenu;

    //getter setter
    public String getmListMenu() {
        return mListMenu;
    }

    public int getmHargaMenu() {
        return mHargaMenu;
    }

    public String getmURL() {
        return mURL;
    }

    public void setmListMenu(String mListMenu) {
        this.mListMenu = mListMenu;
    }

    public void setmHargaMenu(int mHargaMenu) {
        this.mHargaMenu = mHargaMenu;
    }

    public void setmURL(String mURL) {
        this.mURL = mURL;
    }

    public Menu() {
    }

    //konstruktor menu
    public Menu(String vListMenu, int vHargaMenu, String url) {
        mListMenu = vListMenu;
        mHargaMenu = vHargaMenu;
        mURL = url;
    }
}
