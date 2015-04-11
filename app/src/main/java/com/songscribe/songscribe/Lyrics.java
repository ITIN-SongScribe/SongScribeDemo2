package com.songscribe.songscribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;


public class Lyrics extends ActionBarActivity {

    private static String songname = "Song Name";
    private static String saveDataT;
    private static String rSong [] = new String[20];
    private static String lyricsT = "Lyrics";
    static String nameArtist;
    static String nameSong;
    //static String lyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);

        Button save = (Button) findViewById(R.id.userLogin);
        Button random = (Button) findViewById(R.id.rSongName);
        Button play = (Button) findViewById(R.id.playback);

        final EditText song = (EditText) findViewById(R.id.songName);
        final EditText lyricsField = (EditText) findViewById(R.id.lyrics);

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random r = new Random();
                int number = r.nextInt(20);

                song.setText(rSongArray(number));

            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*try {
                    MainActivity.getPlayer().playUserSongFromSave(6, getBaseContext(), SongFile.loadBuyIndex(getBaseContext(), 0, nameArtist));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // mText = (TextView)findViewById(R.id.welcome);
                // mText.setText("Welcome "+mEdit.getText().toString()+"!");

                songname = song.getText().toString();
                MainActivity.setNameSong(songname);
                Album.setNameSong(songname);

                lyricsT = lyricsField.getText().toString();
                VoiceRecord.setLyrics(lyricsT);

                nameSong = songname;

                String testArtist = LoginScreen.getArtist();

                String saveDataT2 = nameSong+","+saveDataT;
                saveDataT2+=SongFile.load(getBaseContext(),testArtist);
                SongFile.save(getBaseContext(), saveDataT2, testArtist);

                //lyricsT = lyricsField.getText().toString();
                // VoiceRecord.setLyricsT(lyrics);
                /*
                Intent intent = new Intent(getApplicationContext(), VoiceRecord.class);
                startActivity(intent);
                */

                Intent intent = new Intent(getApplicationContext(), VoiceRecord.class);
                startActivity(intent);
            }
        });
    }

    public static String rSongArray(int num){

        rSong[0] = "Downtown Flunk";
        rSong[1] = "Salt";
        rSong[2] = "Thinking Internally";
        rSong[3] = "Filled Space";
        rSong[4] = "Play With Me";
        rSong[5] = "Take Me To School";
        rSong[6] = "Shake It On";
        rSong[7] = "I'm Not The Only Pun";
        rSong[8] = "Decades";
        rSong[9] = "All About That Treble";
        rSong[10] = "Envious";
        rSong[11] = "Amphibians";
        rSong[12] = "Hey Crude";
        rSong[13] = "Smells Like Adult Spirit";
        rSong[14] = "We Are The Losers";
        rSong[15] = "Yesteryear";
        rSong[16] = "Bohemian Flapsody";
        rSong[17] = "Staying Dead";
        rSong[18] = "Living On A Dare";
        rSong[19] = "Hotel Nebraska";


        return rSong[num];
    }

    public static void setNameArtist(String name){
        nameArtist = name;
    }
    //public static void setNameSong(String song) { nameSong = song; }
    //public static void setLyrics(String lyrics) { lyrics = lyricsT; }

    public static String getSongName(){

        return songname;
    }

    public static void setData(String dataTemp) { saveDataT = dataTemp; }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lyrics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
