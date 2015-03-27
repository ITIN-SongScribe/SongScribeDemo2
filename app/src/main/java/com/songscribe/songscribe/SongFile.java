package com.songscribe.songscribe;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Cotty on 3/27/2015.
 */
public class SongFile {

    static int data_block = 100;

    public static void save(Context c, String saveData){
        try {
            FileOutputStream saveFile =c.getApplicationContext(). openFileOutput("savedSongs.txt", c.getApplicationContext().MODE_WORLD_READABLE);

            OutputStreamWriter osw = new OutputStreamWriter(saveFile);

            try {
                osw.write(saveData);
                osw.flush();
                osw.close();
                Toast.makeText(c,"Save Complete",Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String load(Context c){
        String loaded = "";
        try {
            FileInputStream loadData = c.getApplicationContext().openFileInput("savedSongs.txt");
            InputStreamReader isr = new InputStreamReader(loadData);
            char[] data = new char[data_block];
            String finalData = "";
            int size;

            try {
                while((size = isr.read(data))>0){
                    String read_data = String.copyValueOf(data,0,size);
                    finalData+=read_data;
                    data = new char[data_block];
                }
                Toast.makeText(c, "Song Data: " + finalData, Toast.LENGTH_LONG).show();
                loaded = finalData;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return loaded;
    }
}
