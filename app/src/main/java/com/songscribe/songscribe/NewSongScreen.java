package com.songscribe.songscribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class NewSongScreen extends ActionBarActivity {


    private static String songname = "Song Name";
    static String nameArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_song_screen);

        Button start = (Button) findViewById(R.id.songStart);
        final TextView txtArtist = (TextView)findViewById(R.id.artist);
        txtArtist.setText(nameArtist);
        
        final EditText sName = (EditText) findViewById(R.id.songName);



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                songname = sName.getText().toString();

                MainActivity.setNameSong(songname);


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public static String getSongName(){

        return songname;
    }

    public static void setNameArtist(String name){
        nameArtist = name;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_song_screen, menu);
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
