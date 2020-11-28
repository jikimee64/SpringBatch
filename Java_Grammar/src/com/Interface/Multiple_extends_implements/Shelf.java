package com.Interface.Multiple_extends_implements;
import java.util.ArrayList;

public class Shelf {
    protected  ArrayList<String> shelf;

    public Shelf() {
        this.shelf = new ArrayList<String>();
    }

    public ArrayList<String> getShelf() {
        return shelf;
    }

    public int getCount() {
        return shelf.size();
    }

}
