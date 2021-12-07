package com.example.persistancedonnees;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by zemmari on 14/11/16.
 */
public class BDLivres {

    private final static int VERSION_BDD = 1;
    private SQLiteDatabase bdd;
    private MaBaseSQLite maBaseSQLite;

    public BDLivres(Context context){
        //On crée la BDD et sa table :
        maBaseSQLite = new MaBaseSQLite(context,"livres",null,VERSION_BDD);
    }

    public void open(){
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        bdd.close();
    }

    public SQLiteDatabase getBdd(){
        return bdd;
    }

    public long insertLivre(Livre Livre){
        //Création d'un ContentValues (fonctionne comme une HashMap)

        ContentValues values = new ContentValues();
        values.put("isbn", Livre.getIsbn());
        values.put("title", Livre.getTitre());

        //on insère l'objet dans la BDD via le ContentValues
        return getBdd().insert("livres",null,values);
    }

    public Livre getLivreByTitre(String titre){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD

        bdd = maBaseSQLite.getReadableDatabase();
        Cursor cursor = bdd.query("livres", new String[] {"isbn", "title"},
                "title" + "=?", new String[] { titre },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursorToLivre(cursor);
    }

    public Livre cursorToLivre(Cursor cursor){

        if (cursor.getCount() == 0) {
            return null;
        }
        else {
            Livre livre = new Livre(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1));
            return livre;
        }
    }
}