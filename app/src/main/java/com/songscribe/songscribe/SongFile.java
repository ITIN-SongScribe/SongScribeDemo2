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

    private static String baseFile = "00.txt";
    private static String theFile;
    static int data_block = 100;
    private static int numSongs = 0;
    private static final int SONG_INFO = 6;
    private static final int MAX_SONGS = 4;

    public static void save(Context c, String saveData, String curArt){
        theFile = curArt+baseFile;
        if(isFull()){
            Toast.makeText(c,"You already have "+numSongs+" songs",Toast.LENGTH_LONG).show();
        }else {
            try {
                FileOutputStream saveFile = c.getApplicationContext().openFileOutput(theFile, c.getApplicationContext().MODE_WORLD_READABLE);

                OutputStreamWriter osw = new OutputStreamWriter(saveFile);

                try {
                    osw.write(saveData);
                    osw.flush();
                    osw.close();
                    Toast.makeText(c, "Save Complete", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static String load(Context c, String curArt){
        theFile = curArt+baseFile;
        String loaded = "";
        try {
            FileInputStream loadData = c.getApplicationContext().openFileInput(theFile);
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

                loaded = finalData;
            } catch (IOException e) {
                e.printStackTrace();
            }

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    numSongs = (loaded.split("\\|",MAX_SONGS).length)-1;

        return loaded;
    }

    public static String[] loadBuyNameAndArtist(Context c, String name, String artist){
        String[] allSongs = load(c,artist).split("\\|",MAX_SONGS);
        String s="";
        for(int i = 0; i < 4; i++){
            if(allSongs[i]!=null)s+="+"+allSongs[i]+"+";
            else s+="+Empty+";
        }

        for(int i = 0; i < 4; i++){
            String[] oneSong = allSongs[i].split(",",SONG_INFO);
            if(name.equalsIgnoreCase(oneSong[0]) && artist.equalsIgnoreCase(oneSong[1])) return oneSong;
        }
        return new String[]{"Default", "Dev","1","1","1","1"};
    }

    public static String[] loadBuyIndex(Context c, int index, String curArt){
        String[] allSongs = load(c,curArt).split("\\|",MAX_SONGS);
        String[] oneSong = allSongs[index].split(",",SONG_INFO);
        return oneSong;
    }

    public static String loadName(Context c, int i, String curArt){
        String[] allSongs = load(c,curArt).split("\\|",MAX_SONGS);
        if(i > (allSongs.length-1)) return "Empty";
        String[] oneSong = allSongs[i].split(",",SONG_INFO);
        if(oneSong.length < 2) return "Empty";
        return oneSong[0]+"-"+curArt;
    }

    private static boolean isFull(){
        if(numSongs >= 4){
            //numSongs = 4;
            return true;
        }
        return false;
    }
}