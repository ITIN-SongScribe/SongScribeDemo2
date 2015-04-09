package com.songscribe.songscribe;

import android.content.Context;
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
    private static boolean isPlaying = false;
    private static boolean loopSong = false;

    int[] soundsGuitar = {R.raw.guitar1, R.raw.guitar2, R.raw.guitar3};
    static int[] soundsBass = {R.raw.bass1,R.raw.bass2,R.raw.bass_variation};
    static int[] soundsDrums = {R.raw.drums1,R.raw.drums2,R.raw.drums3};
    static int[] soundsSong = {R.raw.softgeet1, R.raw.softgeet2,R.raw.softgeet3};

    int[][] soundsAll = {soundsDrums,soundsBass,soundsSong};

    int[] playing = new int[1];

    int indexBass = 0;
    int indexDrums = 0;
    int indexSong = 0;

    static String nameArtist, nameSong;

    int idBass = -1;
    int idDrums = -1;
    int idSong = -1;

    int test1,test2,test3;

    static int[] userSongArray = new int[3];

    static int[] listBass = new int[3];
    static int[] listDrums = new int [3];
    static int[] listSong = new int [3];

    static int setbass;
    static int setdrums;
    static int setsong;

    boolean playingDrums = false;
    boolean playingChords = false;

    boolean paused = false;

    static boolean init = false;
    long duration = 0;

    SoundPool p;

    private void preInit(){
        listBass[0] = loadSound(soundsBass[0]);
        listBass[1] = loadSound(soundsBass[1]);
        listBass[2] = loadSound(soundsBass[2]);

        listDrums[0] = loadSound(soundsDrums[0]);
        listDrums[1] = loadSound(soundsDrums[1]);
        listDrums[2] = loadSound(soundsDrums[2]);

        listSong[0] = loadSound(soundsSong[0]);
        listSong[1] = loadSound(soundsSong[1]);
        listSong[2] = loadSound(soundsSong[2]);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        System.out.println(playing.length);
        //sm = new SoundManager(this, 1);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) p = new SoundPool.Builder().setMaxStreams(10).build();
        else p = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);


        preInit();
        if(!init){

            setbass=0;
            setdrums=0;
            setsong=0;
            nameArtist = LoginScreen.getArtist();
            nameSong = NewSongScreen.getSongName();
        }


        userSongArray[0]=listBass[setbass];
        userSongArray[1]=listDrums[setdrums];
        userSongArray[2]=listSong[setsong];

        final Button btnPlay=(Button)findViewById(R.id.PLAY);
        final Button btnChords=(Button)findViewById(R.id.button3);
        final Button btnDrums=(Button)findViewById(R.id.button4);
        final Button btnLead=(Button)findViewById(R.id.button5);
        final Button btnStop=(Button)findViewById(R.id.button2);

        final Button save = (Button)findViewById(R.id.SAVE);

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

                btnLead.setText("Chord: "+(indexSong+1));

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
                String testArtist = LoginScreen.getArtist();

                String saveData = nameSong+","+testArtist+","+indexBass+","+indexDrums+","+indexSong+"|";
                saveData+=SongFile.load(getBaseContext(),testArtist);
                SongFile.save(getBaseContext(), saveData, testArtist);

                Intent intent = new Intent(getApplicationContext(), SongSelection.class);
                startActivity(intent);

            }

        });
    }

    private long getSoundDuration(int rawId){
        MediaPlayer player = MediaPlayer.create(this, rawId);
        int duration = player.getDuration();
        return duration;
    }

    public static void setNameArtist(String name){
        nameArtist = name;
    }
    public static void setNameSong(String name){
        nameSong = name;
    }
    public static void setSongStuff(String[] s){
        init = true;


        setbass = Integer.parseInt(s[2]);
        setdrums = Integer.parseInt(s[3]);
        setsong = Integer.parseInt(s[4]);
        setNameSong(s[0]);
        setNameArtist(s[1]);


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
            //if(i >= userSongArray.length-1) loops = 0;
            try{
                playing[i] = playSound(s, -1, -1, 0);
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
