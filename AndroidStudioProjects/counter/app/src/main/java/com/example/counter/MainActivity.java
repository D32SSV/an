package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int count = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = 0 ;

        EditText preferredCountEditText = findViewById(R.id.preferredCountEditText);
        preferredCountEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                int preferredCount = Integer.parseInt(preferredCountEditText.getText().toString());
                new Thread(() -> {
                    while (count < preferredCount) {
                        count++;
                    }
                    // Play a sound alert when the preferred number of counts is reached
                    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.alert_sound);
                    mediaPlayer.start();
                }).start();
                return true;
            }
            return false;
        });



        TextView countTextView = findViewById(R.id.countTextView) ;
        countTextView.setOnClickListener(view -> count++);
    }
}