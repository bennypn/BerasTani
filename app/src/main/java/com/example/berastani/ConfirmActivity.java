package com.example.berastani;

import static com.example.berastani.HomepagePembeli.pera;
import static com.example.berastani.HomepagePembeli.pulen;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class ConfirmActivity extends AppCompatActivity {

    private TextView jenisBeras, count, total;
    private String berasPera, berasPulen;
    private Button btnBayar;
    private ImageView add,min, back;
    protected static Integer cntPera, cntPulen, totalPrice = 0;
    private Integer cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        jenisBeras = findViewById(R.id.jenisBeras);
        btnBayar = findViewById(R.id.bayar_btn);
        count = findViewById(R.id.count_txt);
        total = findViewById(R.id.total_txt);
        add = findViewById(R.id.add_item);
        min = findViewById(R.id.minus_item);
        back = findViewById(R.id.back_btn);

        berasPera = "Beras Petruk Pera";
        berasPulen = "Beras Rojolele Pulen";

        total.setText(totalPrice.toString());

        if(pera){
            jenisBeras.setText(berasPera);

        } else if(pulen) {
            jenisBeras.setText(berasPulen);

        } else {
            jenisBeras.setText("Error TV");
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnt++;
                if(pera){
                    totalPrice = cnt*1250;
                    cntPera = cnt;

                    total.setText(totalPrice.toString());
                    count.setText(cntPera.toString());
                } else if(pulen){
                    totalPrice = cnt*1500;
                    cntPulen = cnt;

                    total.setText(totalPrice.toString());
                    count.setText(cntPulen.toString());
                } else {
                    count.setText("Error TV");
                }
            }
        });

        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnt--;
                if(pera){
                    totalPrice = cnt*1250;
                    cntPera = cnt;

                    total.setText(totalPrice.toString());
                    count.setText(cntPera.toString());
                } else if(pulen){
                    totalPrice = cnt*1500;
                    cntPulen = cnt;

                    total.setText(totalPrice.toString());
                    count.setText(cntPulen.toString());
                } else {
                    count.setText("Error TV");
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ConfirmActivity.this, HomepagePembeli.class);
                startActivity(i);
            }
        });

        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                barcodeLauncher.launch(new ScanOptions());

                Intent i = new Intent(ConfirmActivity.this, TransactionActivity.class);
                startActivity(i);
            }
        });
    }

    // Register the launcher and result handler
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(ConfirmActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ConfirmActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                }
            });
}