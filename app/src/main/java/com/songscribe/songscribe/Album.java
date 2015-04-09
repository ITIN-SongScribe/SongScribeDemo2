package com.songscribe.songscribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Album extends ActionBarActivity {
    Button song1;
    Button song2;
    Button song3;
    Button song4;
    static String nameArtist;
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
        txtSongName.setText(NewSongScreen.getSongName());
        setUp();
        // final String txtAlbumArtist = txtArtist + "'s Album!";


          song1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                if(song1.getText().toString().equalsIgnoreCase("new song")){
                    Intent intent = new Intent(getApplicationContext(),NewSongScreen.class);
                    startActivity(intent);
                }else{
                    //String[] s = song1.getText().toString().split("-");
                    //MainActivity.setSongStuff(SongFile.loadBuyNameAndArtist(getBaseContext(), s[0], s[1]));
                    MainActivity.setSongStuff(SongFile.loadBuyIndex(getBaseContext(),0,nameArtist));
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
              }
          });
        song2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(song2.getText().toString().equalsIgnoreCase("new song")){
                    Intent intent = new Intent(getApplicationContext(),NewSongScreen.class);
                    startActivity(intent);
                }else{
                    //String[] s = song2.getText().toString().split("-");
                    //MainActivity.setSongStuff(SongFile.loadBuyNameAndArtist(getBaseContext(),s[0],s[1]));
                    MainActivity.setSongStuff(SongFile.loadBuyIndex(getBaseContext(),1,nameArtist));
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        song3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(song3.getText().toString().equalsIgnoreCase("new song")){
                    Intent intent = new Intent(getApplicationContext(),NewSongScreen.class);
                    startActivity(intent);
                }else{
                    //String[] s = song3.getText().toString().split("-");
                    //MainActivity.setSongStuff(SongFile.loadBuyNameAndArtist(getBaseContext(),s[0],s[1]));
                    MainActivity.setSongStuff(SongFile.loadBuyIndex(getBaseContext(),2,nameArtist));
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        song4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(song4.getText().toString().equalsIgnoreCase("new song")){
                    Intent intent = new Intent(getApplicationContext(),NewSongScreen.class);
                    startActivity(intent);
                }else{
                    //String[] s = song4.getText().toString().split("-");
                    //MainActivity.setSongStuff(SongFile.loadBuyNameAndArtist(getBaseContext(),s[0],s[1]));
                    MainActivity.setSongStuff(SongFile.loadBuyIndex(getBaseContext(),3,nameArtist));
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void setUp(){
        System.out.println("Setup Start");
        int i = 0;
        song1.setText(SongFile.loadName(getBaseContext(),i++,nameArtist).toString());
        song2.setText(SongFile.loadName(getBaseContext(),i++,nameArtist).toString());
        song3.setText(SongFile.loadName(getBaseContext(),i++,nameArtist).toString());
        song4.setText(SongFile.loadName(getBaseContext(),i++,nameArtist).toString());
        System.out.println("Setup End");
    }

    public static void setNameArtist(String name){
        nameArtist = name;
    }
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
