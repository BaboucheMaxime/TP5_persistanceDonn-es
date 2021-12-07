package com.example.persistancedonnees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BDLivres mydb;
    private EditText et_addisbn;
    private EditText et_addtitle;
    private EditText et_searchtitle;
    private Button bt_addbook;
    private Button bt_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_addisbn = findViewById(R.id.id_addisbn);
        et_addtitle = findViewById(R.id.id_addtitle);
        et_searchtitle = findViewById(R.id.id_searchtitle);
        bt_addbook = findViewById(R.id.btn_addbook);
        bt_search = findViewById(R.id.btn_search);

        mydb = new BDLivres(this);

    }

    public void addBook(View v) {

        int isbn = Integer.parseInt(et_addisbn.getText().toString());
        String title = et_addtitle.getText().toString();;
        
        Livre livre = new Livre(isbn, title);
        String message = title + " ajouté en DB";

        mydb.open();
        mydb.insertLivre(livre);

        Toast toast = Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_SHORT);
        toast.show();

    }

    public void searchForBook(View v) {

        String title = et_searchtitle.getText().toString();
        Livre livre = mydb.getLivreByTitre(title);

        if (livre == null) {
            String message =  title + " n'existe pas";
            Toast toast = Toast.makeText(getApplicationContext(),
                    message,
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            mydb.close();
            int isbn = livre.getIsbn();

            Intent i = new Intent(this, DisplayBookActivity.class);
            i.putExtra("ISBN", String.valueOf(isbn));
            i.putExtra("TITLE", title);
            startActivity(i);
        }
    }
}