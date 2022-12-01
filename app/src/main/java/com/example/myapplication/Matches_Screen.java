package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Matches_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches_screen);
    }

    public void ButtonClick(View view) {    // a profile image was clicked
        Intent intent = new Intent(this, HomeActivity.class);       // CHANGE TO THE MATCHING SCREEN CLASS
        int index = Integer.parseInt(view.getTag().toString());
        intent.putExtra("index", index);   // put the profile's index as the extra
        startActivity(intent);
    }

    public void NavBarClick(View view) {    // a navbar button was clicked
        String tag = view.getTag().toString();
        Intent intent;
        if (tag.equals("home"))     // home was clicked
            intent = new Intent(this, HomeActivity.class);
//        else if (tag.equals("messages"))    // messages was clicked
//            intent = new Intent(this, );
        else if (tag.equals("matches")) // matches was clicked
            intent = new Intent(this, Matches_Screen.class);
        else
            intent = new Intent(this, HomeActivity.class);
//        else  // settings was clicked
//            intent = new Intent(this, );
        startActivity(intent);
    }
}