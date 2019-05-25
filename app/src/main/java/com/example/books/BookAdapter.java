package com.example.books;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class BookAdapter extends ArrayAdapter {
    public BookAdapter(Context context,  ArrayList<BookItem> bookItems) {
        super(context, 0, bookItems);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.book_item, parent, false);
        }

        final BookItem bookItem = (BookItem) getItem(position);

        TextView titleTextView = itemView.findViewById(R.id.titleTextView);
        TextView decriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        Button previewButton = itemView.findViewById(R.id.previewButton);
        Button infoButton = itemView.findViewById(R.id.infoButton);
        Button buyButton = itemView.findViewById(R.id.buyButton);

        titleTextView.setText(bookItem.getTitle());
        decriptionTextView.setText(bookItem.getDescription());

        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirect(bookItem.getPreviewLink());
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirect(bookItem.getInfoLink());
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirect(bookItem.getBuyLink());
            }
        });

        return itemView;

    }

    private void redirect(String urlString) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
        getContext().startActivity(intent);
    }
}
