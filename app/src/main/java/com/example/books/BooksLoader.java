package com.example.books;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class BooksLoader extends AsyncTaskLoader<ArrayList<BookItem>> {

    private String mQueryStringURL;

    BooksLoader(@NonNull Context context, String queryStringURL) {
        super(context);
        mQueryStringURL = queryStringURL;
    }

    @Nullable
    @Override
    public ArrayList<BookItem> loadInBackground() {

        URL url = makeURL(mQueryStringURL);
        String JSONStringResponse = makeConnection(url);
        ArrayList<BookItem> bookItems = QueryUtils.getArrayList(JSONStringResponse);

        return  bookItems;

    }

    private URL makeURL(String stringURL) {

        try {
            URL url = new URL(stringURL);
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String makeConnection(URL url) {

        if (url == null) {
            return null;
        }
        else {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(1000);
                httpURLConnection.setReadTimeout(1000);
                httpURLConnection.setRequestMethod("GET");

                int responseCode = httpURLConnection.getResponseCode();

                if (responseCode == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    String response = getResponse(inputStream);
                    return response;
                } else{
                    Log.e("Error", "The response code was" + responseCode);
                    return null;
                }


            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Error", "Can't load the data");
                return null;
            }
        }
    }

    private String getResponse(InputStream inputStream) {

        StringBuilder builder = new StringBuilder();

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            Log.i("empty", String.valueOf(reader.toString().length()));
            String data = reader.readLine();

            while (data != null) {
                builder.append(data);
                data = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error", "error in reading inputStram");
        }

        return builder.toString();


    }

}
