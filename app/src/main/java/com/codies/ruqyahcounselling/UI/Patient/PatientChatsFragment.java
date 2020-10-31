package com.codies.ruqyahcounselling.UI.Patient;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.codies.ruqyahcounselling.Models.Chat;
import com.codies.ruqyahcounselling.Models.ChatList;
import com.codies.ruqyahcounselling.R;
import com.codies.ruqyahcounselling.Utils.MessageAdapterUser;
import com.codies.ruqyahcounselling.Utils.SharedPrefs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientChatsFragment extends Fragment {

    static PatientChatsFragment chatsFragment;
    public static final String TAG = "HOLLO";
    RecyclerView recyclerView;
    EditText text;
    ImageButton send;
    MessageAdapterUser messageAdapterUser;
    List<Chat> chatList;
    SharedPrefs sharedPrefs;
    String userId;
    FirebaseDatabase database;
    DatabaseReference reference;

    public static PatientChatsFragment newInstance() {
        if (chatsFragment == null) {
            chatsFragment = new PatientChatsFragment();
        }
        return chatsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefs = SharedPrefs.getInstance(getContext());
        userId = sharedPrefs.getUserId();
        messageAdapterUser = new MessageAdapterUser(getContext(),userId);
        chatList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient_chats, container, false);
        send = view.findViewById(R.id.sendEt);
        text = view.findViewById(R.id.messageET);

        Log.i(TAG, "onCreate: " + userId);
        Log.i(TAG, "onCreate: ");
        recyclerView = view.findViewById(R.id.chatRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setAdapter(messageAdapterUser);
        recyclerView.setLayoutManager(linearLayoutManager);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messagee = text.getText().toString();
                if (!messagee.isEmpty()) {
                    sendMessage(messagee);
                } else {
                    Toast.makeText(getContext(), "Can't send empty message", Toast.LENGTH_SHORT).show();
                }

                text.setText("");
            }
        });
        readMessages();
        Log.i(TAG, "onCreateView: "+"data caleed");
        return view;
    }

    public void sendMessage(String message) {
        final Chat chat = new Chat(userId, "admin", message);
        reference.child("Chats").push().setValue(chat);
        reference.child("ChatList").child(userId).child("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    reference.child("ChatList").child(userId).child("id").setValue(userId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
                Log.i(TAG, "onDataChange: data set");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
