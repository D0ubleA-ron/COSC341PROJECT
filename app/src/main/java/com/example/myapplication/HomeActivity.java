package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void ButtonClick(View view) {    // an interest button was pressed
        Intent intent = new Intent(this, HomeActivity.class);   // CHANGE TO THE MATCHING SCREEN CLASS
        intent.putExtra("index", 6);
        //intent = new Intent(this, Matches_Screen.class);
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
            intent = new Intent(this, Matches_Screen.class);
//        else  // settings was clicked
//            intent = new Intent(this, );
        startActivity(intent);
    }
}