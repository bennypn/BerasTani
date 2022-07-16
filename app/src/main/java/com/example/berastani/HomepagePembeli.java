package com.example.berastani;

import static com.example.berastani.LoginActivity.newname;
import static com.example.berastani.LoginActivity.usernm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomepagePembeli extends AppCompatActivity {

    private TextView username;
    private Button btnPulen, btnPera;
    protected static boolean pera, pulen;
    protected static Integer stkPera, stkPulen;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_pembeli);
        pulen = false;
        pera = false;

        username = findViewById(R.id.usertxt);
        btnPera = findViewById(R.id.pera_btn);
        btnPulen = findViewById(R.id.pulen_btn);



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                newname = snapshot.child("user").child(usernm).child("nama").getValue(String.class);
                username.setText(newname);

                stkPera = snapshot.child("stock").child("pera").getValue(Integer.class);
                stkPulen = snapshot.child("stock").child("pulen").getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnPulen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pulen = true;
                pera = false;
                if(stkPulen>0){
                    Intent i = new Intent(HomepagePembeli.this, ConfirmActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "STOCK KOSONG !!",
                            Toast.LENGTH_LONG)
                            .show();
                }

            }
        });

        btnPera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pera = true;
                pulen = false;
                if(stkPera>0){
                    Intent i = new Intent(HomepagePembeli.this, ConfirmActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "STOCK KOSONG !!",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
}