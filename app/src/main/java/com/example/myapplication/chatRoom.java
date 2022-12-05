package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class chatRoom extends AppCompatActivity {

    EditText message;
    ImageButton send;

    CardView sendmessage;
    androidx.appcompat.widget.Toolbar toolbarchatroom;
    ImageView userImage;
    TextView userName;

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
                    message.setText(null);
                }
            }
        });

    }
}