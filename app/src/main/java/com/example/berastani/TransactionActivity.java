package com.example.berastani;

import static com.example.berastani.ConfirmActivity.cntPera;
import static com.example.berastani.ConfirmActivity.cntPulen;
import static com.example.berastani.ConfirmActivity.totalPrice;
import static com.example.berastani.HomepagePembeli.pera;
import static com.example.berastani.HomepagePembeli.pulen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TransactionActivity extends AppCompatActivity {

    private TextView total, jenis, jumlah;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        total = findViewById(R.id.totalTxt);
        jenis = findViewById(R.id.jenisBerastxt);
        jumlah = findViewById(R.id.JumlahBerastxt);
        back = findViewById(R.id.btnKeluar);

        total.setText(totalPrice.toString());

        if(pera){
            jenis.setText("Beras Petruk Pera");
            jumlah.setText(cntPera.toString());

        } else if(pulen) {
            jenis.setText("Beras Rojolele Pulen");
            jumlah.setText(cntPulen.toString());

        } else {
            jenis.setText("Error TV");
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TransactionActivity.this, HomepagePembeli.class);
                startActivity(i);
            }
        });
    }
}