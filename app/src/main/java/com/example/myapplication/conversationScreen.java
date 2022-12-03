package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class conversationScreen extends AppCompatActivity {
    int[] ids = new int[] {
            R.id.stevenLayout,
            R.id.marleyLayout,
            R.id.samLayout,
            R.id.paulLayout,
            R.id.jessLayout,
            R.id.alexLayout,
            R.id.joeLayout };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_conversation_screen);
        setContentView(R.layout.activity_conversation_screen_fixedlayout);

        ArrayList<Integer> convos = GrandpalsData.getInstance().getConvos();
        for (int i = 0; i < convos.size(); i++) {
            View v = findViewById(ids[convos.get(i)]);
            v.setVisibility(View.GONE);

        }
    }

    public void NavBarClick(View view) {    // a navbar button was clicked
        String tag = view.getTag().toString();
        Intent intent;
        if (tag.equals("home"))     // home was clicked
            intent = new Intent(this, HomeActivity.class);
        else if (tag.equals("messages"))    // messages was clicked
            intent = new Intent(this, conversationScreen.class);
        else if (tag.equals("matches")) // matches was clicked
            intent = new Intent(this, Matches_Screen.class);
        else
            intent = new Intent(this, HomeActivity.class);
//        else  // settings was clicked
//            intent = new Intent(this, );
        startActivity(intent);
    }
}