package com.songscribe.songscribe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Album extends ActionBarActivity {
    Button song1;
    Button song2;
    Button song3;
    Button song4;
//    final Button logoutButton=(Button)findViewById(R.id.Logout);
    static String nameArtist;
    static String nameSong;
    static String nameArtist1;
    static String nameSong1;
    static String nameArtist2;
    static String nameSong2;
    static String nameArtist3;
    static String nameSong3;
    static String nameArtist4;
    static String nameSong4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_album);

        song1 = (Button) findViewById(R.id.button3);
        song2 = (Button) findViewById(R.id.button4);
        song3 = (Button) findViewById(R.id.button5);
        song4 = (Button) findViewById(R.id.SAVE);



        final TextView txtArtist = (TextView)findViewById(R.id.artistname);
        txtArtist.setText(nameArtist);
        final TextView txtSongName = (TextView)findViewById(R.id.button3);
        txtSongName.setText(Lyrics.getSongName());
        setUp();
        // final String txtAlbumArtist = txtArtist + "'s Album!";

//        logoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
//                startActivity(intent);
//            }
//        });

          song1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  //String[] s = song1.getText().toString().split("-");
//MainActivity.setSongStuff(SongFile.loadBuyNameAndArtist(getBaseContext(), s[0], s[1]));
/*
MainActivity.setSongStuff(SongFile.loadBuyIndex(getBaseContext(),0,nameArtist));
Intent intent = new Intent(getApplicationContext(), MainActivity.class);
startActivity(intent);*/
                  if(song1.getText().toString().equalsIgnoreCase("empty")){
                    Intent intent = new Intent(getApplicationContext(),SongSelection.class);
                    startActivity(intent);
                    MainActivity.getPlayer().stopAll();
                }else {
                      /*try {
                          MainActivity.getPlayer().playUserSongFromSave(6, getBaseContext(), SongFile.loadBuyIndex(getBaseContext(), 0, nameArtist));
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }*/
                      try {
                          MediaPlayer mp = new MediaPlayer();
                          mp.setDataSource("/mnt/sdcard/fSong1.3gpp");
                          mp.prepare();
                          mp.start();
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                  }
              }
          });
        song2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(song2.getText().toString().equalsIgnoreCase("empty")){
                    Intent intent = new Intent(getApplicationContext(),SongSelection.class);
                    startActivity(intent);
                    MainActivity.getPlayer().stopAll();
                }else{
                    //String[] s = song2.getText().toString().split("-");
                    //MainActivity.setSongStuff(SongFile.loadBuyNameAndArtist(getBaseContext(),s[0],s[1]));
                    try {
                        MediaPlayer mp = new MediaPlayer();
                        mp.setDataSource("/mnt/sdcard/fSong2.3gpp");
                        mp.prepare();
                        mp.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    /*
                    MainActivity.setSongStuff(SongFile.loadBuyIndex(getBaseContext(),1,nameArtist));
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);*/
                }
            }
        });
        song3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(song3.getText().toString().equalsIgnoreCase("empty")){
                    Intent intent = new Intent(getApplicationContext(),SongSelection.class);
                    startActivity(intent);
                    MainActivity.getPlayer().stopAll();
                }else{
                    //String[] s = song3.getText().toString().split("-");
                    //MainActivity.setSongStuff(SongFile.loadBuyNameAndArtist(getBaseContext(),s[0],s[1]));
                    try {
                        MediaPlayer mp = new MediaPlayer();
                        mp.setDataSource("/mnt/sdcard/fSong3.3gpp");
                        mp.prepare();
                        mp.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    /*
                    MainActivity.setSongStuff(SongFile.loadBuyIndex(getBaseContext(),2,nameArtist));
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);*/
                }
            }
        });
        song4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(song4.getText().toString().equalsIgnoreCase("empty")){
                    Intent intent = new Intent(getApplicationContext(),SongSelection.class);
                    startActivity(intent);
                    MainActivity.getPlayer().stopAll();
                }else{
                    //String[] s = song4.getText().toString().split("-");
                    //MainActivity.setSongStuff(SongFile.loadBuyNameAndArtist(getBaseContext(),s[0],s[1]));
                    try {
                        MediaPlayer mp = new MediaPlayer();
                        mp.setDataSource("/mnt/sdcard/fSong4.3gpp");
                        mp.prepare();
                        mp.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    /*
                    MainActivity.setSongStuff(SongFile.loadBuyIndex(getBaseContext(),3,nameArtist));
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);*/
                }
            }
        });


    }
    private void setUp(){

        int i = 0;
        song1.setText(SongFile.loadName(getBaseContext(),i++,nameArtist).toString());
        song2.setText(SongFile.loadName(getBaseContext(),i++,nameArtist).toString());
        song3.setText(SongFile.loadName(getBaseContext(),i++,nameArtist).toString());
        song4.setText(SongFile.loadName(getBaseContext(),i++,nameArtist).toString());

    }

    /*private void playRecording() throws Exception {
        ditchMediaPlayer();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(OUTPUT_FILE);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }*/
    public static void setNameArtist(String name){
        nameArtist = name;
    }
    public static void setNameSong(String song) { nameSong = song; }

  /* public static void setNameArtist1(String name1){
        nameArtist1 = name1;
    }
    public static void setNameSong1(String song1) { nameSong1 = song1; }
    public static void setNameArtist2(String name2){
        nameArtist2 = name2;
    }
    public static void setNameSong2(String song2) { nameSong2 = song2; }
    public static void setNameArtist3(String name3){
        nameArtist3 = name3;
    }
    public static void setNameSong3(String song3) { nameSong3 = song3; }
    public static void setNameArtist4(String name4){
        nameArtist4 = name4;
    }
    public static void setNameSong4(String song4) { nameSong4 = song4; }
    */
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_album, menu);
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
