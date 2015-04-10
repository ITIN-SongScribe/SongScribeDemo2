package com.songscribe.songscribe;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;


public class VoiceRecord extends ActionBarActivity {

    private MediaPlayer mediaPlayer;
    private MediaRecorder recorder;
    private String OUTPUT_FILE;
    static String nameArtist;

    static boolean isRecording = false;
    static boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_record);

        final String nameSong;
        nameSong = Lyrics.getSongName();
        final TextView txtArtist = (TextView)findViewById(R.id.textView4);
        txtArtist.setText(nameSong);

        OUTPUT_FILE= Environment.getExternalStorageDirectory()+"/audiorecorder.3gpp";

        final Button record=(Button)findViewById(R.id.recordButton);
        final Button playback=(Button)findViewById(R.id.playbackButton);



        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isRecording) {
                    try {
                        stopRecording();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    record.setText("Record!");
                }
                else {
                    try {
                        beginRecording();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    record.setText("Stop Recording");
                }
                isRecording=changeState(isRecording);

            }
        });

        playback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isPlaying) {
                    try {
                        stopPlayback();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    playback.setText("Play");
                }
                else {
                    try {
                        playRecording();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    playback.setText("Stop");
                }
                isPlaying=changeState(isPlaying);

            }
        });


        /*switch(view.getId()){
            case R.id.recordButton:
                if(isRecording == 0) {
                    try {
                        beginRecording();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                else if(isRecording == 1)
                {
                    try {
                        stopRecording();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                isRecording=changeState(isRecording);
                break;

            case R.id.playbackButton:
                if(isPlaying == 0)
                {
                    try {
                        playRecording();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(isPlaying == 1) {
                    try {
                        stopPlayback();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                isPlaying=changeState(isPlaying);
                break;
        }*/

    }
    private static boolean changeState(boolean position)
    {
        position = !position;
        return position;

    }
    private void stopPlayback(){
        if(mediaPlayer != null)
            mediaPlayer.stop();

    }
    private void playRecording() throws Exception{
        ditchMediaPlayer();
        mediaPlayer=new MediaPlayer();
        mediaPlayer.setDataSource(OUTPUT_FILE);
        mediaPlayer.prepare();
        mediaPlayer.start();

    }
    private void stopRecording(){
        if(recorder != null)
            recorder.stop();
    }
    private void beginRecording() throws Exception{
        ditchMediaRecorder();
        File outFile = new File(OUTPUT_FILE);

        if(outFile.exists())
            outFile.delete();

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(OUTPUT_FILE);
        recorder.prepare();
        recorder.start();

    }
    private void ditchMediaRecorder(){
        if(recorder != null)
            recorder.release();
    }
    private void ditchMediaPlayer(){
        if(mediaPlayer != null)
        {
            try{
                mediaPlayer.release();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    public static void setNameArtist(String name){
        nameArtist = name;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_voice_record, menu);
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
