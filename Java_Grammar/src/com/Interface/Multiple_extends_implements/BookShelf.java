package com.Interface.Multiple_extends_implements;

public class BookShelf extends Shelf implements Queue {

    @Override
    public void enQueue(String title) {
        shelf.add(title);
    }

    @Override
    public String deQueue() {
        return shelf.remove(0);
    }

    @Override
    public int getSize() {
        //super.getCount(); //이것도 됨
        return getCount();
    }
}
