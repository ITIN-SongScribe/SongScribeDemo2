package com.songscribe.songscribe;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;

import com.songscribe.songscribe.util.LinkedList;


public class SoundManager {


    //int[] soundsGuitar = {R.raw.guitar1, R.raw.guitar2, R.raw.guitar3};
    static int[] soundsBass = {R.raw.bass1,R.raw.bass2,R.raw.bass3,R.raw.bass4,R.raw.bass5,R.raw.bass6,R.raw.bass7,R.raw.bass8};
    static int[] soundsDrums = {R.raw.drum1,R.raw.drum2,R.raw.drum3,R.raw.drum4,R.raw.drum5,R.raw.drum6,R.raw.drum7,R.raw.drum8};
    static int[] soundsSong = {R.raw.lead1,R.raw.lead2,R.raw.lead3,R.raw.lead4,R.raw.lead5,R.raw.lead6,R.raw.lead7,R.raw.lead8};


    static int indexBass = 0, indexDrums = 0,indexSong = 0;
    static int[][] soundsAll = {soundsDrums,soundsBass,soundsSong};

    //int[] playing = new int[1];


    static boolean playing = false;
    static boolean paused = false;


    static LinkedList<Integer> listPlaying = new LinkedList<Integer>("Playing List");
    static LinkedList<Integer> listBass = new LinkedList<Integer>("Bass List");
    static LinkedList<Integer> listDrums = new LinkedList<Integer>("Drums List");
    static LinkedList<Integer> listSong = new LinkedList<Integer>("Song List");
    static LinkedList<Integer> listUserSong = new LinkedList<Integer>("User Song List");


    //static int[] userSongArray = new int[3];

    //static int[] listBass = new int[3];
    //static int[] listDrums = new int [3];
    //static int[] listSong = new int [3];

    static int setbass =0;
    static int setdrums=0;
    static int setsong=0;


    static boolean init = false;
    static long duration = 0;

    static SoundPool p;

    private static void preInit(Context c){
        populateBassList(c);
        populateDrumsList(c);
        populateSongList(c);
    }
    public static void populateBassList(Context c){
        listBass.clear();
        int i = 0;
        while(i < soundsBass.length){
            listBass.insertAtBack(loadSound(c,soundsBass[i]));
            i++;
        }

        /*listBass.insertAtBack(loadSound(c,soundsBass[0]));
        listBass.insertAtBack(loadSound(c,soundsBass[1]));
        listBass.insertAtBack(loadSound(c,soundsBass[2]));*/
    }
    public static void populateDrumsList(Context c){
        listDrums.clear();

        int i = 0;
        while(i < soundsDrums.length){
            listDrums.insertAtBack(loadSound(c,soundsDrums[i]));
            i++;
        }
        /*
        listDrums.insertAtBack(loadSound(c,soundsDrums[0]));
        listDrums.insertAtBack(loadSound(c,soundsDrums[1]));
        listDrums.insertAtBack(loadSound(c,soundsDrums[2]));*/
    }
    public static void populateSongList(Context c){
        listSong.clear();

        int i = 0;
        while(i < soundsSong.length){
            listSong.insertAtBack(loadSound(c,soundsSong[i]));
            i++;
        }
        /*
        listSong.insertAtBack(loadSound(c,soundsSong[0]));
        listSong.insertAtBack(loadSound(c,soundsSong[1]));
        listSong.insertAtBack(loadSound(c,soundsSong[2]));*/
    }


    public static boolean isPlaying(){
        return  playing;
    }
    public static void setPlaying(boolean b){
        playing = b;
    }
    public static boolean isPaused(){
        return  paused;
    }
    public static void setPaused(boolean b){
        paused = b;
    }

    public SoundManager(Context c) {

        ///System.out.println(playing.length);
        //sm = new SoundManager(this, 1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            p = new SoundPool.Builder().setMaxStreams(10).build();
        else p = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);

        preInit(c);
//TODO
        if (!init) {

            setbass = 0;
            setdrums = 0;
            setsong = 0;
            init = true;
        }


        listUserSong.insertAtBack(listBass.get(setbass));
        listUserSong.insertAtBack(listDrums.get(setdrums));
        listUserSong.insertAtBack(listSong.get(setsong));
    }



    private static long getSoundDuration(Context c, int rawId){
        MediaPlayer player = MediaPlayer.create(c, rawId);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        int duration = player.getDuration();
        return duration;
    }


    public static void setSongStuff(String[] s){
        init = true;
        setbass = Integer.parseInt(s[2]);
        setdrums = Integer.parseInt(s[3]);
        setsong = Integer.parseInt(s[4]);



    }
    public static void setAll(int b, int d, int s){
        setbass = b;
        setdrums = d;
        setsong = s;
    }
    public static void setBass(int b, int d, int s){
        setbass = b;

    }
    public static void setDrums(int b, int d, int s){

        setdrums = d;

    }
    public static void setSong(int b, int d, int s){

        setsong = s;
    }


    public static int getBass(){

        return indexBass;
    }
    public static int getDrums(){

        return indexDrums;
    }
    public static int getLead(){

        return indexSong;
    }


    public static int lengthBass(){
        return soundsBass.length;
    }
    public static int lengthSong(){
        return soundsSong.length;
    }
    public static int lengthDrums(){
        return soundsDrums.length;
    }
    public static int playSound(int loadedSound, int type, int index, int loops, Context c) throws InterruptedException {
        //listUserSong.clear();
        if(type!=-1){
            if(index != -1){
                if(getSoundDuration(c,soundsAll[type][index]) > duration) duration = getSoundDuration(c,soundsAll[type][index]);
            }
            //listUserSong.insertAtBack(loadedSound);
            ////userSongArray[type] = loadedSound;
        }

        if(loops > 0){
            Thread.sleep(9000);
        }

        return  p.play(loadedSound, 1, 1, 1, 0, 1);
    }

    public static int buttonChords(Context c){
        if(indexBass >= lengthBass()-1)indexBass=0;
        else indexBass++;

        stopAll();
        listPlaying.clear();
        if(listBass.isEmpty()) populateBassList(c);
        try {
           listPlaying.insertAtBack(playSound(listBass.get(indexBass), 1, indexBass, 0, c));
        }
        catch(InterruptedException e){

        }
        return indexBass+1;

    }
    public static int buttonDrums(Context c){
        if(indexDrums >= lengthDrums()-1)indexDrums=0;
        else indexDrums++;

        stopAll();
        listPlaying.clear();
        if(listDrums.isEmpty()) populateDrumsList(c);
        try {
            listPlaying.insertAtBack(playSound(listDrums.get(indexDrums), 1, indexDrums, 0,c));
        }
        catch(InterruptedException e){

        }
        return indexDrums+1;

    }
    public static int buttonLead(Context c){
        if(indexSong >= lengthSong()-1)indexSong=0;
        else indexSong++;

        stopAll();
        listPlaying.clear();
        if(listSong.isEmpty()) populateSongList(c);
        try {
            listPlaying.insertAtBack(playSound(listSong.get(indexSong), 1, indexSong, 0,c));
        }
        catch(InterruptedException e){

        }
        return indexSong+1;

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

    public static int loadSound(Context c, int rawSound){
        return p.load(c,rawSound,1);
    }

    public void stopSound(int playingSound){
        p.stop(playingSound);
        listPlaying.clear();
    }

    public static void stopAll(){
        for(int i =0; i < listUserSong.lengthIs(); i++){
            p.stop(listUserSong.get(i));
        }
        stopUserSong();
        listPlaying.clear();
    }

    public void playUserSong(Context c){
        stopAll();
        listPlaying.clear();
        listUserSong.clear();

        listUserSong.insertAtBack(listBass.get(indexBass));
        listUserSong.insertAtBack(listDrums.get(indexDrums));
        listUserSong.insertAtBack(listSong.get(indexSong));
        int loops = 0;
        for(int i =0; i < listUserSong.lengthIs(); i++){


            //if(i >= userSongArray.length-1) loops = 0;
            try{
                listPlaying.insertAtBack(playSound(listUserSong.get(i), -1, -1, 0,c));
            }catch(InterruptedException e){

            }
        }

    }

    public static void playUserSongFromSave(Context c, String[] s){
        stopAll();
        listPlaying.clear();
        listUserSong.clear();

        listUserSong.insertAtBack(listBass.get(Integer.parseInt(s[2])));
        listUserSong.insertAtBack(listDrums.get(Integer.parseInt(s[3])));
        listUserSong.insertAtBack(listSong.get(Integer.parseInt(s[4])));
        int loops = 0;
        for(int i =0; i < listUserSong.lengthIs(); i++){


            //if(i >= userSongArray.length-1) loops = 0;
            try{
                listPlaying.insertAtBack(playSound(listUserSong.get(i), -1, -1, 0,c));
            }catch(InterruptedException e){

            }
        }

    }
    public static void playUserSongFromSaveBuyIndex(Context c, String[] s){
        listPlaying.clear();
        int[] song = new int[3];
        song[0] = Integer.parseInt(s[2]);
        song[1] = Integer.parseInt(s[3]);
        song[2] = Integer.parseInt(s[4]);

        int loops = 0;
        for(int i =0; i < song.length; i++){


            //if(i >= userSongArray.length-1) loops = 0;
            try{
                listPlaying.insertAtBack(playSound(song[i], -1, -1, 0,c));
            }catch(InterruptedException e){

            }
        }

    }

    public void pauseSound(int playingSound){
        p.pause(playingSound);
    }

    public static void stopUserSong(){
        for(int i = 0; i < listPlaying.lengthIs();i++)  p.stop(listPlaying.get(i));
        listPlaying.clear();
    }

    public void pauseUserSong(){
        for(int i = 0; i < listPlaying.lengthIs();i++)  pauseSound(listPlaying.get(i));
    }


}
