package com.songscribe.songscribe;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Album extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        Button song1 = (Button) findViewById(R.id.button3);

        final TextView txtArtist = (TextView)findViewById(R.id.artistname);
        txtArtist.setText(LoginScreen.getArtist());
        final TextView txtSongName = (TextView)findViewById(R.id.button3);
        txtSongName.setText(NewSongScreen.getSongName());
        // final String txtAlbumArtist = txtArtist + "'s Album!";


          song1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  SongFile.load(getBaseContext());


              }
          });
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
