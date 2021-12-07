package com.example.persistancedonnees;

public class Livre implements ILivre {

    private String title;
    private int isbn;

    public Livre(int isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }


    @Override
    public int getIsbn() {
        return this.isbn;
    }

    @Override
    public String getTitre() {
        return this.title;
    }



}
