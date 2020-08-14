package com.waldo.notesbites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomepageActivity extends AppCompatActivity {
    public static String PERSONNAME  = "PERSONNAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        Intent intent = getIntent();
        PERSONNAME = (String) intent.getExtras().get(PERSONNAME);

        TextView welcome_text = findViewById(R.id.welcome_text);
        welcome_text.setText("welcome " + PERSONNAME);


    }
}
