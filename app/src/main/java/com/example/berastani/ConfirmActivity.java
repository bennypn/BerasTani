package com.example.berastani;

import static com.example.berastani.HomepagePembeli.pera;
import static com.example.berastani.HomepagePembeli.pulen;
import static com.example.berastani.HomepagePembeli.stkPera;
import static com.example.berastani.HomepagePembeli.stkPulen;
import static com.example.berastani.LoginActivity.newname;
import static com.example.berastani.LoginActivity.usernm;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    private Integer newCount=0;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

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

        ScanOptions options = new ScanOptions();



        berasPera = "Beras Petruk Pera";
        berasPulen = "Beras Rojolele Pulen";

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                stkPera = snapshot.child("stock").child("pera").getValue(Integer.class);
                stkPulen = snapshot.child("stock").child("pulen").getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                if(pera && stkPera>cntPera){
                    newCount = stkPera-cntPera;
                    options.setOrientationLocked(false);
                    options.setPrompt("Scan a barcode");
                    options.setCameraId(0);  // Use a specific camera of the device
                    options.setBeepEnabled(true);
                    options.setBarcodeImageEnabled(true);
                    barcodeLauncher.launch(options);

                } else if(pulen && stkPulen>cntPulen){
                    newCount = stkPulen-cntPulen;
                    options.setOrientationLocked(false);
                    options.setPrompt("Scan a barcode");
                    options.setCameraId(0);  // Use a specific camera of the device
                    options.setBeepEnabled(true);
                    options.setBarcodeImageEnabled(true);
                    barcodeLauncher.launch(options);

                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "STOCK TIDAK CUKUP !!",
                            Toast.LENGTH_LONG)
                            .show();
                }

            }
        });
    }

    // Register the launcher and result handler
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(ConfirmActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                //    Toast.makeText(ConfirmActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                    if(pera){

                        myRef = database.getReference("transaction").child("pulen");
                        myRef.setValue(0);
                        myRef = database.getReference("transaction").child("pera");
                        myRef.setValue(cntPera);
                        myRef = database.getReference("transaction").child("status");
                        myRef.setValue(1);
                        myRef = database.getReference("stock").child("pera");
                        myRef.setValue(newCount);

                        Intent i = new Intent(ConfirmActivity.this, LoadingActivity.class);
                        startActivity(i);

                    } else if(pulen){

                        myRef = database.getReference("transaction").child("pulen");
                        myRef.setValue(cntPulen);
                        myRef = database.getReference("transaction").child("pera");
                        myRef.setValue(0);
                        myRef = database.getReference("transaction").child("status");
                        myRef.setValue(1);
                        myRef = database.getReference("stock").child("pulen");
                        myRef.setValue(newCount);

                        Intent i = new Intent(ConfirmActivity.this, LoadingActivity.class);
                        startActivity(i);
                    } else {
                        total.setText("Error TV");
                    }
                }
            });
}