package com.songscribe.songscribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {


    static String nameArtist, nameSong, saveDataT;

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
        //player.setAll(0,0,0);

        final Button btnChords=(Button)findViewById(R.id.button3);
        final Button btnDrums=(Button)findViewById(R.id.button4);
        final Button btnLead=(Button)findViewById(R.id.button5);
        final Button btnMore=(Button)findViewById(R.id.button6);
        final Button btnPlay=(Button)findViewById(R.id.PLAY);
        final Button btnStop=(Button)findViewById(R.id.button2);
        final Button save = (Button)findViewById(R.id.SAVE);
        final Button logoutButton=(Button)findViewById(R.id.Logout);


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


                //if (player.isPlaying()) {
                    //player.setPlaying(false);
                   // player.setPaused(true);
                   // btnPlay.setText("Play");
                   // player.pauseUserSong();
                    //loopSong = false;
               // } else {
                    if(player.isPlaying()) player.stopAll();
                    player.setPaused(false);
                    player.setPlaying(true);
                    btnPlay.setText("Play");
                    player.playUserSong(getBaseContext());
                    //loopSong = true;
              //  }

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

        /*
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLead.setText("Lead: "+player.buttonLead(getBaseContext()));
            }
        });
        */



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

                String testArtist = LoginScreen.getArtist();

                String saveDataTemp = testArtist+","+player.getBass()+","+player.getDrums()+","+player.getLead()+"|";
                saveDataTemp+=SongFile.load(getBaseContext(),testArtist);
                SongFile.save(getBaseContext(), saveDataTemp, testArtist);
                setData(saveDataTemp);

                Intent intent = new Intent(getApplicationContext(), Lyrics.class);
                startActivity(intent);

            }

        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(intent);
            }
        });
    }

    public static void setData(String dataTemp) { saveDataT = dataTemp; }
    public static void setNameArtist(String name){
        nameArtist = name;
    }
    public static void setNameSong(String song){
        nameSong = song;
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
    public static SoundManager getPlayer(){return player;}

}
