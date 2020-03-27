package com.example.music_player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    AudioManager audioManager;
    MediaPlayer player;

    public void play_song(View view){
        player.start();
    }

    public void pause_song(View view){
        player.pause();
    }

    public void stop_song(View view){
        player.stop();
        player = MediaPlayer.create(this,R.raw.song);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = MediaPlayer.create(this,R.raw.song);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar seekvol = findViewById(R.id.seekVol);
        seekvol.setMax(maxVol);
        seekvol.setProgress(curVol);

        seekvol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar seekprog = findViewById(R.id.seekProg);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekprog.setProgress(player.getCurrentPosition() );
            }
        },0,900);


    seekprog.setMax(player.getDuration());
    seekprog.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            player.seekTo(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    });
    }
}
