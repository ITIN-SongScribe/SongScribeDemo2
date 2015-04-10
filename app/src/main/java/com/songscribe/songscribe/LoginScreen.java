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


public class LoginScreen extends ActionBarActivity {

    // EditText mEdit;
    // TextView mText;

    private static String artist = "Artist";
    private static String rName [] = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Button login = (Button) findViewById(R.id.userLogin);
        Button random = (Button) findViewById(R.id.random);
        final EditText user = (EditText) findViewById(R.id.userName);

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random r = new Random();
                int number = r.nextInt(20);

                user.setText(rNameArray(number));

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // mText = (TextView)findViewById(R.id.welcome);
                // mText.setText("Welcome "+mEdit.getText().toString()+"!");

                artist = user.getText().toString();
                MainActivity.setNameArtist(artist);
                Album.setNameArtist(artist);
                NewSongScreen.setNameArtist(artist);
                SongSelection.setNameArtist(artist);

                Intent intent = new Intent(getApplicationContext(), SongSelection.class);
                startActivity(intent);
            }
        });
    }

    public static String rNameArray(int num){

        rName[0] = "Ned Steerin";
        rName[1] = "Shady Perry";
        rName[2] = "Micki MiMouse";
        rName[3] = "Justin Beaver";
        rName[4] = "Chustin Pimperlake";
        rName[5] = "Juno Earth";
        rName[6] = "P mama";
        rName[7] = "Dr. D-Money";
        rName[8] = "Spam Smith";
        rName[9] = "Taylor Quick";
        rName[10] = "My Cat Boone";
        rName[11] = "Reddish 6";
        rName[12] = "One Democratic";
        rName[13] = "Trip In Girl";
        rName[14] = "Meghan Craner";
        rName[15] = "Tessie Tay";
        rName[16] = "Meyonce";
        rName[17] = "Parry Overwood";
        rName[18] = "Think About Lizards";
        rName[19] = "Tim McBra";

        return rName[num];
    }
    public static String getArtist(){

        return artist;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_screen, menu);
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
