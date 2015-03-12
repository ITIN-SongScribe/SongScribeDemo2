package com.songscribe.songscribe;

import android.content.Context;
import android.media.AudioManager;
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
    private static boolean isPlaying = false;
    private static boolean loopSong = false;

    int[] soundsBass = {R.raw.guitar1, R.raw.guitar2, R.raw.guitar3};
    int[] soundsDrums = {R.raw.drums1,R.raw.drums2,R.raw.drums3};
    int[] soundsSong = {R.raw.song3, R.raw.song3, R.raw.song3};

    int[] playing = new int[1];

    int indexBass = 0;
    int indexDrums = 0;
    int indexSong = 0;

    int idBass = -1;
    int idDrums = -1;
    int idSong = -1;

    int test1,test2,test3;

    int[] userSongArray = new int[3];

    int[] listBass = new int[3];
    int[] listDrums = new int [3];
    int[] listSong = new int [3];
    boolean paused = false;

    SoundPool p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context test = this;

        System.out.println(playing.length);
        //sm = new SoundManager(this, 1);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) p = new SoundPool.Builder().setMaxStreams(10).build();
        else p = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);

        listBass[0] = loadSound(soundsBass[0]);
        listBass[1] = loadSound(soundsBass[1]);
        listBass[2] = loadSound(soundsBass[2]);

        listDrums[0] = loadSound(soundsDrums[0]);
        listDrums[1] = loadSound(soundsDrums[1]);
        listDrums[2] = loadSound(soundsDrums[2]);

        listSong[0] = loadSound(soundsSong[0]);
        listSong[1] = loadSound(soundsSong[1]);
        listSong[2] = loadSound(soundsSong[2]);

        userSongArray[0]=listBass[0];
        userSongArray[1]=listDrums[0];
        userSongArray[2]=listSong[0];

        /*
        test1 = p.load(this,R.raw.drums1,1);
        test2 = p.load(this,R.raw.drums2,1);
        test3 = p.load(this,R.raw.drums3,1);
        */

        final Button btnPlay=(Button)findViewById(R.id.button1);
        final Button btnChords=(Button)findViewById(R.id.button3);
        final Button btnDrums=(Button)findViewById(R.id.button4);
        final Button btnLead=(Button)findViewById(R.id.button5);
        final Button btnStop=(Button)findViewById(R.id.button2);
        final TextView txtArtist = (TextView)findViewById(R.id.textView2);
        txtArtist.setText(SongSelection.getArtist());

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isPlaying) {
                    isPlaying = false;
                    paused = true;
                    btnPlay.setText("Play");
                    pauseUserSong();
                    loopSong = false;
                } else {
                    if(!paused)stopAll();
                    paused = false;
                    isPlaying = true;
                    btnPlay.setText("Pause");
                    playUserSong();
                    loopSong = true;
                }

                System.out.println(playing.length);
            }
        });

        btnChords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(indexBass >= soundsBass.length-1)indexBass=0;
                else indexBass++;

                stopAll();
                playing = new int[1];
                playing[0] = playSound(listBass[indexBass],2);


            }
        });
/*
        btnLead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(indexSong >= soundsSong.length-1)indexSong=0;
                else indexSong++;

                stopAll();

                playing = playSound(listSong[indexSong],1);


            }
        });
*/
        btnDrums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(indexDrums >= soundsDrums.length-1)indexDrums=0;
                else indexDrums++;

                stopAll();
                playing = new int[1];           playing[0] = playSound(listDrums[indexDrums],0);


            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPlaying = false;

                paused = false;

                btnPlay.setText("Play");

                loopSong = false;

                stopAll();

            }

        });
    }

    public int playSound(int loadedSound, int type){
        /*
        int[] temp = new int[playing.length+1];
        int i=0;
        for(int s: playing)  temp[i++]=s;
        temp[i] = p.play(loadedSound, 1, 1, 1, 0, 1);
        if(type!=-1) userSongArray[type] = loadedSound;
        return temp;*/

        if(type!=-1) userSongArray[type] = loadedSound;
        return  p.play(loadedSound, 1, 1, 1, 0, 1);
    }

    public int[] playLoopingSound(int loadedSound, int type, int loops, int[] pl){

        int lenPlay = pl.length;
        int[] temp = new int[lenPlay+1];

        if (loops < 0) loops = 0;
        for(int i = 0; i < lenPlay; i ++){
            temp[i] = pl[i];
            if(type!=-1) userSongArray[type] = loadedSound;
        }
        temp[lenPlay] =  p.play(loadedSound, 1, 1, 1, loops, 1);
        //for(int s: playing)  temp[i++]=s;

        //temp[i] =

        return temp;
    }

    public int loadSound(int rawSound){
        return p.load(this,rawSound,1);
    }

    public void stopSound(int playingSound){
        p.stop(playingSound);
        playing = new int[0];
    }

    public void stopAll(){
        for(int s: userSongArray)  p.stop(s);
        stopUserSong();
        playing = new int[0];
    }

    public void playUserSong(){
        playing = new int[3];
        int i = -1;
        for(int s: userSongArray){
            i++;
            playing[i] = playSound(s, -1);
        }

        //for(int s: userSongArray)  playing = playLoopingSound(s,-1, 5, playing);
    }
    public void pauseSound(int playingSound){
        p.pause(playingSound);
    }

    public void stopUserSong(){
        for(int s: playing)  p.stop(s);
        playing = new int[0];
    }

    public void pauseUserSong(){
        for(int s: playing)  pauseSound(s);
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
