package com.example.berastani;

import static com.example.berastani.LoginActivity.usertext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomepagePembeli extends AppCompatActivity {

    private TextView username;
    private Button btnPulen, btnPera;
    protected static boolean pera, pulen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_pembeli);
        pulen = false;
        pera = false;

        username = findViewById(R.id.usertxt);
        btnPera = findViewById(R.id.pera_btn);
        btnPulen = findViewById(R.id.pulen_btn);

        username.setText(usertext);

        btnPulen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pulen = true;
                Intent i = new Intent(HomepagePembeli.this, ConfirmActivity.class);
                startActivity(i);
            }
        });

        btnPera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pera = true;
                Intent i = new Intent(HomepagePembeli.this, ConfirmActivity.class);
                startActivity(i);
            }
        });
    }
}