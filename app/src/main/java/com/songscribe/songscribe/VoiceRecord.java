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

    Button _start;
    Button _stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_record);

        _start = (Button) findViewById(R.id.startRecord);
        _stop = (Button) findViewById(R.id.stopRecord);

        final String nameSong;
        nameSong = NewSongScreen.getSongName();
        final TextView txtArtist = (TextView)findViewById(R.id.textView4);
        txtArtist.setText(nameSong);

        OUTPUT_FILE= Environment.getExternalStorageDirectory()+"/audiorecorder.3gpp";
    }
    public void buttonTapped(View view){
        switch(view.getId()){
            case R.id.startRecord:
                try {
                    beginRecording();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.stopRecord:
                try {
                    stopRecording();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.startPlayback:
                try {
                    playRecording();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.stopPlayback:
                try {
                    stopPlayback();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
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
