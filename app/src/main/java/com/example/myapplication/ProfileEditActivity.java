package com.example.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class ProfileEditActivity extends AppCompatActivity {
    public static final String FILENAME = "data.txt";
    Uri[] uris = new Uri[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        Context context = this;
        ImageButton main_image = (ImageButton) findViewById(R.id.main_image);
        ImageButton image_1 = (ImageButton) findViewById(R.id.image_1);
        ImageButton image_2 = (ImageButton) findViewById(R.id.image_2);
        ImageButton image_3 = (ImageButton) findViewById(R.id.image_3);
        Button confirm_button = (Button) findViewById(R.id.confirm_button);

        ActivityResultLauncher<Intent> mainActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        Uri selected_image = data.getData();
                        uris[0] = selected_image;
                        main_image.setImageURI(selected_image);
                    }
                });
        ActivityResultLauncher<Intent> activityResultLauncher1 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        Uri selected_image = data.getData();
                        uris[1] = selected_image;
                        image_1.setImageURI(selected_image);
                    }
                });
        ActivityResultLauncher<Intent> activityResultLauncher2 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        Uri selected_image = data.getData();
                        uris[2] = selected_image;
                        image_2.setImageURI(selected_image);
                    }
                });
        ActivityResultLauncher<Intent> activityResultLauncher3 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        Uri selected_image = data.getData();
                        uris[3] = selected_image;
                        image_3.setImageURI(selected_image);
                    }
                });

        main_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                mainActivityResultLauncher.launch(intent);
            }
        });
        image_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher1.launch(intent);
            }
        });
        image_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher2.launch(intent);
            }
        });
        image_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher3.launch(intent);
            }
        });

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String age = ((EditText)findViewById(R.id.edit_age)).getText().toString();
                String name = ((EditText)findViewById(R.id.edit_name)).getText().toString();
                if (isNumeric(age) && name.length() > 0) {
                    Intent intent = new Intent(ProfileEditActivity.this, ProfileViewActivity.class);
                    save();
                    startActivity(intent);
                } else if (!isNumeric(age)) {
                    Toast.makeText(context, "Please enter a valid age", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Please enter a valid name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void save() {
        EditText name = (EditText) findViewById(R.id.edit_name);
        EditText age = (EditText) findViewById(R.id.edit_age);
        EditText interest = (EditText) findViewById(R.id.edit_interest);

        try {
            FileOutputStream output = openFileOutput(FILENAME, Context.MODE_PRIVATE);//create save file

            output.write((name.getText().toString() + '¶').getBytes());
            output.write((age.getText().toString() + '¶').getBytes());
            output.write((interest.getText().toString() + '¶').getBytes());

            if (uris[0] != null) output.write((uris[0].toString() + '¶').getBytes());
            if (uris[1] != null) output.write((uris[1].toString() + '¶').getBytes());//----------
            if (uris[2] != null) output.write((uris[2].toString() + '¶').getBytes());
            if (uris[3] != null) output.write(uris[3].toString().getBytes());

            output.close();

        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) return false;
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
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