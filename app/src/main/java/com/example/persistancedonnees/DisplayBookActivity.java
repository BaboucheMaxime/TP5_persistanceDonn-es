package com.example.persistancedonnees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_book);

        Intent intent = getIntent();
        String isbn = intent.getStringExtra("ISBN");
        String title = intent.getStringExtra("TITLE");

        // Capture the layout's TextView and set the string as its text
        TextView tv_isbn = findViewById(R.id.tv_isbn);
        tv_isbn.setText(isbn);

        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText(title);
    }
}