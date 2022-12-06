package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Random;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void ButtonClick(View view) {    // an interest button was pressed
        Intent intent = new Intent(this, Matching_Screen.class);
        String text = ((Button)view).getText().toString().toLowerCase();
        int index = -1;
        if (text.equals("help"))
            index = new int[] {3, 4}[new Random().nextInt(2)];
        else if (text.equals("baking"))
            index = new int[] {0, 6}[new Random().nextInt(2)];
        else if (text.equals("conversation"))
            index = new int[] {1, 5}[new Random().nextInt(2)];
        else if (text.equals("technology"))
            index = new int[] {2, 4}[new Random().nextInt(2)];
        else
            index = GrandpalsData.getInstance().getMatches().get(new Random().nextInt(7));
        intent.putExtra("index", index);
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