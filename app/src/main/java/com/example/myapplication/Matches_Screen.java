package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Matches_Screen extends AppCompatActivity {
    int[] ids = new int[] {
            R.id.stevenLayout,
            R.id.marleyLayout,
            R.id.samLayout,
            R.id.paulLayout,
            R.id.jessLayout,
            R.id.alexLayout };

    // got these coords using code from this vid and getX/getY: https://www.youtube.com/watch?v=L3kBvM-fQ74
    int[][] coords = new int[][] {
            new int[] { 17, 193 },
            new int[] { 568, 193 },
            new int[] { 17, 688 },
            new int[] { 568, 688 },
            new int[] { 17, 1183 },
            new int[] { 568, 1183 } };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_matches_screen);
        //setContentView(R.layout.activity_matches_screen_tablelayout);
        setContentView(R.layout.activity_matches_screen_fixedlayout);

        ArrayList<Integer> matches = GrandpalsData.getInstance().getMatches();
        for (int i = 0; i < matches.size(); i++) {     // populate matches properly
            View v = findViewById(ids[matches.get(i)]);
            v.setX(coords[i][0]);   // set X based on our pre-defined locations
            v.setY(coords[i][1]);   // set Y based on our pre-defined locations
            v.setVisibility(View.VISIBLE);
        }
    }

    public void ButtonClick(View view) {    // a profile image was clicked
        Intent intent = new Intent(this, Matching_Screen.class);
        int index = Integer.parseInt(view.getTag().toString());
        intent.putExtra("index", index);   // put the profile's index as the extra
        startActivity(intent);
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
        else  // settings was clicked
            intent = new Intent(this, ProfileViewActivity.class);
        startActivity(intent);
    }
}