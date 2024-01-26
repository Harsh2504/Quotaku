package com.example.quotaku;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddQuote extends AppCompatActivity {


    EditText etQuote, etcharacter, etanime;
    Button btnAddQuote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);

        //binding views
        etQuote = findViewById(R.id.quote);
        etcharacter = findViewById(R.id.character);
        etanime = findViewById(R.id.animenm);
        btnAddQuote = findViewById(R.id.addbutton);

        //listener
        btnAddQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quote = etQuote.getText().toString();
                String character = etcharacter.getText().toString();
                String anime = etanime.getText().toString();

                //validation
                if (quote.isEmpty()){
                    etQuote.setError("Quote is required");
                    etQuote.requestFocus();
                    return;
                }
                if (character.isEmpty()){
                    etcharacter.setError("Character is required");
                    etcharacter.requestFocus();
                    return;
                }
                if (anime.isEmpty()){
                    etanime.setError("Anime is required");
                    etanime.requestFocus();
                    return;
                }

                //add quote to firebase
                addQuote(quote, character, anime);
                Log.d(TAG, "onClick: "+quote+" "+character+" "+anime);
            }
        });

    }

    private void addQuote(String quote, String character, String anime) {
        //create hashmap to store data
        HashMap<String, Object> map = new HashMap<>();
        map.put("quote", quote);
        map.put("character", character);
        map.put("anime", anime);

        //instantiate firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference quotesRef = database.getReference("quotes");
        quotesRef.setValue("Inside Quotes Function");
        //write data to firebase
        String key = quotesRef.push().getKey();
        map.put("key", key);
        quotesRef.child(key).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddQuote.this, "Quote Added", Toast.LENGTH_SHORT).show();
                //clear edittext
                etQuote.setText("");
                etcharacter.setText("");
                etanime.setText("");
            }
        });

    }
}