package com.example.books;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText queryEditText;
    Button queryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queryEditText = findViewById(R.id.queryEditText);
        queryButton = findViewById(R.id.queryButton);

        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = queryEditText.getText().toString();
                if (query.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a query", Toast.LENGTH_SHORT).show();
                }
                else {
                    String queryURL = "https://www.googleapis.com/books/v1/volumes?q=" + query + "&maxResults=5";
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("queryURL", queryURL);
                    startActivity(intent);
                }
            }
        });

    }
}
