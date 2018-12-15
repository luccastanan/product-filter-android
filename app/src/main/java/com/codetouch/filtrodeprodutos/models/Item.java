package com.codetouch.filtrodeprodutos.models;

public class Item {

    private boolean checked;
    private String title;

    public Item(String title) {
        this.title = title;
    }

    public Item(boolean checked, String title) {
        this.checked = checked;
        this.title = title;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Item && ((Item) obj).getTitle().equals(title);
    }

    @Override
    public String toString() {
        return "Item{" +
                "checked=" + checked +
                ", title='" + title + '\'' +
                '}';
    }
}
