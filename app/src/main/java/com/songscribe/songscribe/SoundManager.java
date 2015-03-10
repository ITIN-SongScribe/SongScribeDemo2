package com.songscribe.songscribe;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.util.HashMap;


public class SoundManager {
    private  SoundPool mSoundPool;
    private  HashMap<Integer, Integer> mSoundPoolMap;
    private AudioManager mAudioManager;
    private Context mContext;
    private int size = -1;

    public SoundManager(Context theContext, int sounds) {
        mContext = theContext;

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        mSoundPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .build();
        else
            mSoundPool = new SoundPool(sounds, AudioManager.STREAM_MUSIC, 0);
        mSoundPoolMap = new HashMap<Integer, Integer>();
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    public void addSound(int index, int SoundID) {
        size++;
        mSoundPoolMap.put(index, mSoundPool.load(mContext, SoundID, 1));
    }

    public void playSound(int index) {
        float streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
        streamVolume = streamVolume / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING);

        //mSoundPool.play((Integer) mSoundPoolMap.get(index), streamVolume, streamVolume, 1, 0, 1f);
        mSoundPool.play(index,streamVolume,streamVolume,1,0,1);


    }

    public int initSound(Context it,int index){
        return mSoundPool.load(it, index, 1);
    }

    public void playLoopedSound(int index) {
        float streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        mSoundPool.play((Integer) mSoundPoolMap.get(index), streamVolume, streamVolume, 1, -1, 1f);
    }

    public int getSize(){
        return size;
    }
}