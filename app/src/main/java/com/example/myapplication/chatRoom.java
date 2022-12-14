package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class chatRoom extends AppCompatActivity {
    int[] imageIds = new int[] {
            R.drawable.person_0,
            R.drawable.person_1,
            R.drawable.person_2,
            R.drawable.person_3,
            R.drawable.person_4,
            R.drawable.person_5,
            R.drawable.person_6 };

    EditText message;
    ImageButton send;

    CardView sendmessage;
    androidx.appcompat.widget.Toolbar toolbarchatroom;
    ImageView userImage;
    TextView userName;
    TextView senderText;

    private String enteredMessage;

    ImageButton back;

    RecyclerView messagerecyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        message = findViewById(R.id.getmessage);
        sendmessage = findViewById(R.id.cardViewOfSendMessage);
        send = findViewById(R.id.imageViewSendMessage);
        toolbarchatroom = findViewById(R.id.toolBarChatRoom);
        userName = findViewById(R.id.user_name);
        userImage = findViewById(R.id.chatRoomUserProfilepic);
        back = findViewById(R.id.backToConversationScreen);
        senderText = findViewById(R.id.textView31);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        userName.setText("Ved");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enteredMessage = message.getText().toString();

                if(enteredMessage.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter Something", Toast.LENGTH_SHORT).show();
                } else {
                    senderText.setText(enteredMessage);
                    senderText.setBackground(ContextCompat.getDrawable(chatRoom.this, R.drawable.senderchatdrawable));
                    message.setText(null);
                }
            }
        });

        // set up initial profile info/images
        int id = getIntent().getExtras().getInt("id");
        userName.setText(getResources().getStringArray(R.array.names)[id]);
        userImage.setImageDrawable(getDrawable(imageIds[id]));
    }
}