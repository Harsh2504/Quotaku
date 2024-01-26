package com.example.quotaku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView recyclerView;
    List<Quote> quoteList;
    QuoteAdapter quoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.quotesrv);

        // Setting up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        quoteList = new ArrayList<>();
        quoteAdapter = new QuoteAdapter(quoteList);
        recyclerView.setAdapter(quoteAdapter);

        // Firebase initialization
        DatabaseReference quotesRef = FirebaseDatabase.getInstance().getReference("quotes");

        // Retrieve data from Firebase
        quotesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                quoteList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Quote quote = snapshot.getValue(Quote.class);
                    quoteList.add(quote);
                }

                quoteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddQuote.class);
                startActivity(intent);
            }
        });
    }
}
