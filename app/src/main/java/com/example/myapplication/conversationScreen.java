package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

    public void ConvoClick(View view) {
        int id = Integer.parseInt(view.getTag().toString());
        Toast.makeText(this, "id: " + id + ", implement this function", Toast.LENGTH_SHORT).show();
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
        else if (tag.equals("settings")) // settings was clicked
            intent = new Intent(this, ProfileViewActivity.class);
        else
            intent = new Intent(this, HomeActivity.class);
//        else  // settings was clicked
//            intent = new Intent(this, );
        startActivity(intent);
    }
}