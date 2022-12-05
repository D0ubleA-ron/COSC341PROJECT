package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProfileViewActivity extends AppCompatActivity {

    public static final String FILENAME = "data.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load an appropriate page based on whether or not the profile has been created yet
        boolean profileCreated = GrandpalsData.getInstance().getCreatedStatus();
        if (profileCreated)
            setContentView(R.layout.activity_profile_view);
        else
            setContentView(R.layout.activity_profile_view_first_time);

        Button edit_button = (Button) findViewById(R.id.edit_button);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileViewActivity.this, ProfileEditActivity.class);
                startActivity(intent);
            }
        });
        if (profileCreated)
            update_display();
    }

    public void update_display() {
        TextView[] textViews = {(TextView) findViewById(R.id.view_name),
                (TextView) findViewById(R.id.view_age),
                (TextView) findViewById(R.id.view_interest)};
        ImageView[] images =   {(ImageView) findViewById(R.id.image_main),
                (ImageView) findViewById(R.id.image_1),
                (ImageView) findViewById(R.id.image_2),
                (ImageView) findViewById(R.id.image_3)};

        try {
            FileInputStream input = openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(input)));

            String data = "";
            String line;
            while ((line = reader.readLine()) != null) {
                data += line + '\n';
            }
            input.close();

            String[] str_arr = data.split("¶", -1);

            for (int i = 0; i < str_arr.length; i++) {
                if (i < 3) {
                    textViews[i].setText(str_arr[i]);
                } else {
                    images[i-3].setImageURI(Uri.parse(str_arr[i]));
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "GrandPals encountered an error, profile information not loaded at this time.", Toast.LENGTH_SHORT).show();
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
        else if (tag.equals("settings")) // settings was clicked
            intent = new Intent(this, ProfileViewActivity.class);
        else  // settings was clicked
            intent = new Intent(this, ProfileViewActivity.class);
        startActivity(intent);
    }
}