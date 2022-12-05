package com.example.myapplication;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Message> messagesArrayList;

    int ITEM_SEND = 1;
    int ITEM_RECIEVE=2;

    public MessagesAdapter(Context context, ArrayList<Message> messagesArrayList) {
        this.context = context;
        this.messagesArrayList = messagesArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==ITEM_SEND){
            View view = LayoutInflater.from(context).inflate(R.layout.senderchatlayout, parent, false);
            return new SenderViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.recieverchatlayout, parent, false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        Message messages = messagesArrayList.get(position);
        if (holder.getClass()==SenderViewHolder.class){
            SenderViewHolder viewHolder=(SenderViewHolder) holder;
            //viewHolder.textViewmessage.setText(messages.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return messagesArrayList.size();
    }

    @Override
    public int getItemViewType(int position){
        if (position == 1){
            return ITEM_SEND;
        } else {
            return ITEM_RECIEVE;
        }
    }

    class SenderViewHolder extends RecyclerView.ViewHolder{

        TextView textViewmessage;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewmessage=itemView.findViewById(R.id.sendermessage);
        }
    }

    class RecieverViewHolder extends RecyclerView.ViewHolder{

        TextView textViewmessage;

        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewmessage=itemView.findViewById(R.id.sendermessage);
        }
    }
}
