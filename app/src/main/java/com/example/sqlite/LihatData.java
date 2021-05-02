package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class LihatData extends AppCompatActivity {
    static TextView tnama,ttelfon;
    String nm,tlp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);
        tnama = (TextView) findViewById(R.id.tvNamaKontak);
        ttelfon = (TextView) findViewById(R.id.tvNomorTelepon);
        nm = getIntent().getStringExtra("nama");
        tlp = getIntent().getStringExtra("telpon");
        tnama.setText(nm);
        ttelfon.setText(tlp);
    }
}