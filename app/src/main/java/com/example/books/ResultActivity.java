package com.example.books;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<BookItem>> {

    String queryURL;
    ListView bookListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bookListView = findViewById(R.id.bookList);

        Intent intent = getIntent();
        queryURL = intent.getStringExtra("queryURL");

        getSupportLoaderManager().initLoader(0, null, this).forceLoad();


    }


    @NonNull
    @Override
    public Loader<ArrayList<BookItem>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new BooksLoader(this, queryURL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<BookItem>> loader, ArrayList<BookItem> bookItems) {
        BookAdapter bookAdapter = new BookAdapter(getApplicationContext(), bookItems);
        bookListView.setAdapter(bookAdapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<BookItem>> loader) {

    }
}
