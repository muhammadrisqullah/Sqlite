package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sqlite.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class EditData extends AppCompatActivity {
    private Button simpanBtn;
    private TextInputEditText tnama,tTelpon;
    String nm, tlp, id;
    DBController controller = new DBController(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        tnama = (TextInputEditText)findViewById(R.id.edNama);
        tTelpon = (TextInputEditText)findViewById(R.id.edTelpon);
        simpanBtn = (Button)findViewById(R.id.buttonSave);
        id = getIntent().getStringExtra("id");
        nm = getIntent().getStringExtra("nm");
        tlp = getIntent().getStringExtra("tlp");
        tnama.setText(nm);
        tTelpon.setText(tlp);
        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tnama.getText().toString().equals("")||tTelpon.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"data belum komplit!",Toast.LENGTH_SHORT).show();
                }else {
                    nm= tnama.getText().toString();
                    tlp= tTelpon.getText().toString();
                    HashMap<String,String> qvalues = new HashMap<>();
                    qvalues.put("id",id);
                    qvalues.put("nama",nm);
                    qvalues.put("telpon",tlp);
                    controller.editData(qvalues);
                    callhome();
                    Toast.makeText(getApplicationContext(),"Data Berhasil di ubah..",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void  callhome() {
        Intent intent = new Intent(EditData.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}