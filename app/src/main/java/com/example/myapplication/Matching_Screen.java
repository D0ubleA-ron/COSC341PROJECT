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

public class Matching_Screen extends AppCompatActivity {
    int[] imageIds = new int[] {
            R.drawable.person_0,
            R.drawable.person_1,
            R.drawable.person_2,
            R.drawable.person_3,
            R.drawable.person_4,
            R.drawable.person_5,
            R.drawable.person_6 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_screen);

        // unpack bundle and get corresponding profile info
        Bundle bundle = getIntent().getExtras();
        int index = bundle.getInt("index");
        Resources res = getResources();
        String name = res.getStringArray(R.array.names)[index];
        String task = res.getStringArray(R.array.tasks)[index];
        String age = res.getStringArray(R.array.ages)[index];
        String location = res.getStringArray(R.array.locations)[index];

        // update the matching screen's information
        ((TextView)findViewById(R.id.nameAgeTextView)).setText(name + ", " + age);
        ((TextView)findViewById(R.id.taskTextView)).setText(task);
        ((TextView)findViewById(R.id.locationTextView)).setText(location);
        updateImage(index);
    }

    public void updateImage(int index) {    // add rounding/border code
        Drawable img = getDrawable(imageIds[index]);

        ((ImageView)findViewById(R.id.profileImageView)).setImageDrawable(img);
    }

    public void ButtonClick(View view) {    // accept/reject was clicked
        if (view.getTag().toString().equals("accept")) {
            Toast.makeText(this, "add accept functionality", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "add reject functionality", Toast.LENGTH_SHORT).show();
        }
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