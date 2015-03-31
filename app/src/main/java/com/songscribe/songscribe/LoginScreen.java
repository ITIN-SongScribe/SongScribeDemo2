package com.songscribe.songscribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginScreen extends ActionBarActivity {

    // EditText mEdit;
    // TextView mText;

    private static String artist = "Artist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Button login = (Button) findViewById(R.id.userLogin);
        final EditText user = (EditText) findViewById(R.id.userName);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // mText = (TextView)findViewById(R.id.welcome);
                // mText.setText("Welcome "+mEdit.getText().toString()+"!");

                artist = user.getText().toString();

                Intent intent = new Intent(getApplicationContext(), SongSelection.class);
                startActivity(intent);
            }
        });
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