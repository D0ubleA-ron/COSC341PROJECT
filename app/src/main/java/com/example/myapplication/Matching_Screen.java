package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Matching_Screen extends AppCompatActivity {
    int[] imageIds = new int[] {
            R.drawable.person_0,
            R.drawable.person_1,
            R.drawable.person_2,
            R.drawable.person_3,
            R.drawable.person_4,
            R.drawable.person_5,
            R.drawable.person_6 };

    int index = -1; // will be updated in onCreate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_screen);

        // unpack bundle and get corresponding profile info
        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("index");

        loadIndex();    // load info based on the match id
    }

    public void ButtonClick(View view) {    // accept/reject was clicked
        GrandpalsData data = GrandpalsData.getInstance();
        ArrayList<Integer> matches = data.getMatches();
        ArrayList<Integer> convos = data.getConvos();
        if (index != 6) {   // remove from matches page regardless of whether accept or reject was clicked
            matches.remove(Integer.valueOf(index));
            data.setMatches(matches);   // update the data
        }
        if (view.getTag().toString().equals("accept")) {    // add the id to the list of convos
            convos.remove(Integer.valueOf(index));  // i know this looks backwards but we do need to remove the index
            data.setConvos(convos);     // update the data
        } else {
            Toast.makeText(this, "add reject functionality", Toast.LENGTH_SHORT).show();
        }

        if (matches.size() > 0) {   // load new match
            index = matches.get(new Random().nextInt(matches.size()));
            loadIndex();
        }
        else {  // no more matches, so blank out the screen
            runOut();
        }
    }

    public void loadIndex() {
        // check if valid index (if not showing up on the convos page, we can load it)
        ArrayList<Integer> convos = GrandpalsData.getInstance().getConvos();
        ArrayList<Integer> matches = GrandpalsData.getInstance().getMatches();
        if (!convos.contains(index)) {
            if (matches.size() > 0) {   // load new match
                index = matches.get(new Random().nextInt(matches.size()));
            }
            else {  // no more matches, so blank out the screen
                runOut();
                return;
            }
        }

        // get resources
        Resources res = getResources();
        String name = res.getStringArray(R.array.names)[index];
        String task = res.getStringArray(R.array.tasks)[index];
        String age = res.getStringArray(R.array.ages)[index];
        String location = res.getStringArray(R.array.locations)[index];

        // update the matching screen's information
        ((TextView)findViewById(R.id.nameAgeTextView)).setText(name + ", " + age);
        ((TextView)findViewById(R.id.taskTextView)).setText(task);
        ((TextView)findViewById(R.id.locationTextView)).setText(location);
        ((ImageView)findViewById(R.id.profileImageView)).setImageDrawable(getDrawable(imageIds[index]));
    }

    public void runOut() {
        ((TextView)findViewById(R.id.nameAgeTextView)).setText("");
        ((TextView)findViewById(R.id.taskTextView)).setText("");
        ((TextView)findViewById(R.id.locationTextView)).setText("");
        ((ImageView)findViewById(R.id.profileImageView)).setVisibility(View.INVISIBLE);
        Toast.makeText(this, "You've run out of matches!", Toast.LENGTH_SHORT).show();
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