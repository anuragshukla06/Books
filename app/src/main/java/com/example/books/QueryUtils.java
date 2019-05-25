package com.example.books;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QueryUtils {

    public static ArrayList<BookItem> getArrayList(String JSONStringResponse) {

        ArrayList<BookItem> bookItems = new ArrayList<BookItem>();

        try {
            JSONObject responseObject = new JSONObject(JSONStringResponse);
            JSONArray itemsJSONArray = responseObject.getJSONArray("items");
            for(int i=0; i<itemsJSONArray.length(); i++) {

                JSONObject bookJSONRoot = itemsJSONArray.getJSONObject(i);
                JSONObject volumeInfo = bookJSONRoot.getJSONObject("volumeInfo");
                JSONObject saleInfo = bookJSONRoot.getJSONObject("saleInfo");
                //TODO: Add author name
                BookItem bookItem = new BookItem(volumeInfo.getString("title"),
                        volumeInfo.getString("description"),
                        volumeInfo.getString("previewLink"),
                        volumeInfo.getString("infoLink"),
                        saleInfo.getString("buyLink")
                        );

                bookItems.add(bookItem);

            }
            return bookItems;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error", "error parsing response to json");
            return null;
        }

    }

}
