package com.codetouch.filtrodeprodutos;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utilities {

    public static String readProducts(Context context){
        StringBuilder content = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("gubee-teste.json")));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                content.append(mLine);
            }
        } catch (IOException e) {
            Log.e("Read file", e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("Close file", e.getMessage());
                }
            }
        }
        return content.toString();
    }

    public static String arrayConcat(String delimiter, String[] data){
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < data.length; i++){
            res.append(data[i]);
            if (i+1 < data.length)
                res.append(delimiter);
        }

        return res.toString();
    }
}
