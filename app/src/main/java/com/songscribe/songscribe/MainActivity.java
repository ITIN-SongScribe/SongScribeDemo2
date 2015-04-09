package com.songscribe.songscribe;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {


    static String nameArtist, nameSong;

    static boolean init = false;


    static SoundManager player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //System.out.println(playing.length);
        //sm = new SoundManager(this, 1);

        if(!init){
            player.setAll(0,0,0);
            nameArtist = LoginScreen.getArtist();
            nameSong = NewSongScreen.getSongName();
            player = new SoundManager(getBaseContext());
        }


        final Button btnPlay=(Button)findViewById(R.id.PLAY);
        final Button btnChords=(Button)findViewById(R.id.button3);
        final Button btnDrums=(Button)findViewById(R.id.button4);
        final Button btnLead=(Button)findViewById(R.id.button5);
        final Button btnStop=(Button)findViewById(R.id.button2);
        final Button save = (Button)findViewById(R.id.SAVE);
        final Button toRecordPage = (Button)findViewById(R.id.toRecordPage);

        final TextView tvArtist = (TextView)findViewById(R.id.artist);
        tvArtist.setText(nameArtist);
        final TextView tvSong = (TextView)findViewById(R.id.textView);




        final TextView txtArtist = (TextView)findViewById(R.id.artist);
        txtArtist.setText(nameArtist);
        final TextView txtSongName = (TextView)findViewById(R.id.textView);
        txtSongName.setText(nameSong);



        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (player.isPlaying()) {
                    player.setPlaying(false);
                    player.setPaused(true);
                    btnPlay.setText("Play");
                    player.pauseUserSong();
                    //loopSong = false;
                } else {
                    if(!player.isPaused())player.stopAll();
                    player.setPaused(false);
                    player.setPlaying(true);
                    btnPlay.setText("Pause");
                    player.playUserSong(getBaseContext());
                    //loopSong = true;
                }

            }
        });

        btnChords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnChords.setText("Bass: "+player.buttonChords(getBaseContext()));
            }
        });

        btnDrums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDrums.setText("Drum: "+player.buttonDrums(getBaseContext()));
            }
        });

        btnLead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLead.setText("Lead: "+player.buttonLead(getBaseContext()));
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setPlaying(false);

                player.setPaused(false);

                btnPlay.setText("Play");

                //loopSong = false;

                player.stopAll();

            }

        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setPlaying(false);
                player.setPaused(false);

                btnPlay.setText("Play");

               // loopSong = false;

                player.stopAll();

                String nameSong = tvSong.getText().toString();
                String testArtist = LoginScreen.getArtist();

                String saveData = nameSong+","+testArtist+","+player.getBass()+","+player.getDrums()+","+player.getLead()+"|";
                saveData+=SongFile.load(getBaseContext(),testArtist);
                SongFile.save(getBaseContext(), saveData, testArtist);

                Intent intent = new Intent(getApplicationContext(), SongSelection.class);
                startActivity(intent);

            }

        });

        toRecordPage.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VoiceRecord.class);
                startActivity(intent);
            }
        });

    }



    public static void setNameArtist(String name){
        nameArtist = name;
    }
    public static void setNameSong(String name){
        nameSong = name;
    }
    public static void setSongStuff(String[] s) {
        init = true;
        player.setSongStuff(s);
        setNameSong(s[0]);
        setNameArtist(s[1]);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
