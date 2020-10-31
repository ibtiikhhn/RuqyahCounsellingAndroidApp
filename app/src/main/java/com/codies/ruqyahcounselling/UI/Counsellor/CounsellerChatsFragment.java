package com.codies.ruqyahcounselling.UI.Counsellor;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codies.ruqyahcounselling.Models.Chat;
import com.codies.ruqyahcounselling.Models.ChatList;
import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;
import com.codies.ruqyahcounselling.Utils.ChatClickListener;
import com.codies.ruqyahcounselling.Utils.CounsellorChatsAdapter;
import com.codies.ruqyahcounselling.Utils.OnItemClickListener;
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
public class CounsellerChatsFragment extends Fragment implements ChatClickListener {

    public static final String TAG = "YE";
    RecyclerView recyclerView;
    CounsellorChatsAdapter chatsAdapter;
    List<String> patientlist;
    List<Patient> patients;
    List<ChatList> chatLists;
    static CounsellerChatsFragment chatsFragment;

    DatabaseReference databaseReference;
    public static CounsellerChatsFragment newInstance() {
        if (chatsFragment == null) {
            chatsFragment = new CounsellerChatsFragment();
        }
        return chatsFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container ,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_counseller_chats, container, false);
        patientlist = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        patients = new ArrayList<>();
        recyclerView = view.findViewById(R.id.chatsRVV);
        chatsAdapter = new CounsellorChatsAdapter(getContext(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(chatsAdapter);
        chatLists = new ArrayList<>();
        getChatlist();
        return view;
    }

    public void getUsers() {
        databaseReference.child("Chats").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getSender().equals("admin")) {
                        patientlist.add(chat.getReceiver());
                    }
                    if (chat.getReceiver().equals("admin")) {
                        patientlist.add(chat.getSender());
                    }
                    Log.i(TAG, "onDataChange: " + chat.toString());
                }
                Log.i(TAG, "onDataChange: "+patientlist.size());
                readChats();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void readChats() {


        databaseReference.child("patients").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                patients.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Patient patient = snapshot.getValue(Patient.class);
                    for (ChatList chatList : chatLists) {
                        if (patient.getUserId().equals(chatList.getId())) {
                            patients.add(patient);
                        }
                    }
                }
                chatsAdapter.setList(patients);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getChatlist() {
        databaseReference.child("ChatList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chatLists.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChatList chatList = snapshot.getValue(ChatList.class);
                    chatLists.add(chatList);
                }
                readChats();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onClick(String itemId,String name,String profileUrl) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra("id", itemId);
        intent.putExtra("name", name);
        intent.putExtra("image", profileUrl);
        startActivity(intent);
    }
}
