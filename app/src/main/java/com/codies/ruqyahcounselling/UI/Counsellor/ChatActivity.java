package com.codies.ruqyahcounselling.UI.Counsellor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codies.ruqyahcounselling.Models.Chat;
import com.codies.ruqyahcounselling.R;
import com.codies.ruqyahcounselling.Utils.MessageAdapterUser;
import com.codies.ruqyahcounselling.Utils.SharedPrefs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    public static final String TAG = "HOLLO";
    RecyclerView recyclerView;
    EditText text;
    ImageButton send;
    MessageAdapterUser messageAdapterUser;
    List<Chat> chatList;
    SharedPrefs sharedPrefs;
    String userId;
    CircularImageView profileIMG;
    TextView name;
    AppCompatImageView imageView;
    DatabaseReference reference;
    String imageURL;
    String personName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        send = findViewById(R.id.sendEt);
        text = findViewById(R.id.messageET);
        sharedPrefs = SharedPrefs.getInstance(this);
        profileIMG = findViewById(R.id.toolbarIMG);
        name = findViewById(R.id.toolbarName);
        imageView = findViewById(R.id.backBT);
        Intent intent = getIntent();
        imageURL = intent.getStringExtra("image");
        personName = intent.getStringExtra("name");
        userId = intent.getStringExtra("id");
        name.setText(personName);
        Glide.with(getApplicationContext()).load(imageURL).into(profileIMG);

        Log.i(TAG, "onCreate: " + userId);
        Log.i(TAG, "onCreate: ");
        chatList = new ArrayList<>();
        messageAdapterUser = new MessageAdapterUser(this,"admin");
        recyclerView = findViewById(R.id.chatRV);
        reference = FirebaseDatabase.getInstance().getReference();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(messageAdapterUser);
        readMessages();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messagee = text.getText().toString();
                if (!messagee.isEmpty()) {
                    sendMessage(messagee);
                } else {
                    Toast.makeText(ChatActivity.this, "Can't send empty message", Toast.LENGTH_SHORT).show();
                }

                text.setText("");
            }
        });


    }

    public void sendMessage(String message) {
        Chat chat = new Chat("admin", userId, message);
        reference.child("Chats").push().setValue(chat);
    }

    public void readMessages() {
        reference.child("Chats").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chatList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    Log.i(TAG, "onDataChange: " + chat.toString());
                    if(chat.getReceiver().equals(userId) && chat.getSender().equals("admin") ||
                    chat.getReceiver().equals("admin") && chat.getSender().equals(userId)){
                        chatList.add(chat);
                    }
                }
                messageAdapterUser.setList(chatList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
