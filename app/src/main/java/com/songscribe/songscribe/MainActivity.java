package com.songscribe.songscribe;

import android.content.Context;
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
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends ActionBarActivity {
    private static boolean isPlaying = false;
    private static boolean loopSong = false;

    int[] soundsGuitar = {R.raw.guitar1, R.raw.guitar2, R.raw.guitar3};
    int[] soundsBass = {R.raw.bass1,R.raw.bass2,R.raw.bass_variation};
    int[] soundsDrums = {R.raw.drums1,R.raw.drums2,R.raw.drums3};
    int[] soundsSong = {R.raw.softgeet1, R.raw.softgeet2,R.raw.softgeet3};

    int[][] soundsAll = {soundsDrums,soundsBass,soundsSong};

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


    boolean playingDrums = false;
    boolean playingChords = false;

    boolean paused = false;

    long duration = 0;

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

        final Button btnPlay=(Button)findViewById(R.id.button1);
        final Button btnChords=(Button)findViewById(R.id.button3);
        final Button btnDrums=(Button)findViewById(R.id.button4);
        final Button btnLead=(Button)findViewById(R.id.button5);
        final Button btnStop=(Button)findViewById(R.id.button2);

        final Button save = (Button)findViewById(R.id.SAVE);

        final TextView tvArtist = (TextView)findViewById(R.id.textView2);
        final TextView tvSong = (TextView)findViewById(R.id.textView3);

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

                btnChords.setText("Bass: "+(indexBass+1));

                stopAll();
                playing = new int[1];
                try {
                    playing[0] = playSound(listBass[indexBass], 1, indexBass, 0);
                }
                catch(InterruptedException e){

                }



            }
        });

        btnDrums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(indexDrums >= soundsDrums.length-1)indexDrums=0;
                else indexDrums++;

                btnDrums.setText("Drum: "+(indexDrums+1));

                stopAll();
                playing = new int[1];
                try {
                    playing[0] = playSound(listDrums[indexDrums], 0, indexDrums, 0);
                }catch(InterruptedException e){

                }


            }
        });

        btnLead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(indexSong >= soundsSong.length-1)indexSong=0;
                else indexSong++;

                btnLead.setText("Other: "+(indexSong+1));

                stopAll();
                playing = new int[1];

                try{
                    playing[0] = playSound(listSong[indexSong],2, indexSong,0);
                }catch(InterruptedException e){

                }



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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPlaying = false;

                paused = false;

                btnPlay.setText("Play");

                loopSong = false;

                stopAll();

                String nameSong = tvSong.getText().toString();
                String nameArtist = tvArtist.getText().toString();

                String saveData = nameSong+","+nameArtist+","+indexBass+","+indexDrums+","+indexSong+"|";
                saveData+=SongFile.load(getBaseContext());
                SongFile.save(getBaseContext(), saveData);
                SongFile.load(getBaseContext());
            }

        });
    }

    private long getSoundDuration(int rawId){
        MediaPlayer player = MediaPlayer.create(this, rawId);
        int duration = player.getDuration();
        return duration;
    }

    public int playSound(int loadedSound, int type, int index, int loops) throws InterruptedException {

        if(type!=-1){
            if(index != -1){
                if(getSoundDuration(soundsAll[type][index]) > duration) duration = getSoundDuration(soundsAll[type][index]);
            }
            userSongArray[type] = loadedSound;
        }

        if(loops > 0){
            Thread.sleep(9000);
        }

        return  p.play(loadedSound, 1, 1, 1, 0, 1);
    }

    /*public int[] playLoopingSound(int loadedSound, int type, int loops, int[] pl){

        int lenPlay = pl.length;
        int[] temp = new int[lenPlay+1];

        if (loops < 0) loops = 0;
        for(int i = 0; i < lenPlay; i ++){
            temp[i] = pl[i];
            if(type!=-1) userSongArray[type] = loadedSound;
        }
        temp[lenPlay] =  p.play(loadedSound, 1, 1, 1, loops, 1);

        return temp;
    }*/

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
        int loops = 0;
        for(int s: userSongArray){

            i++;
            if(i >= userSongArray.length-1) loops = 4;
            try{
                playing[i] = playSound(s, -1, -1, loops);
            }catch(InterruptedException e){

            }
        }

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
