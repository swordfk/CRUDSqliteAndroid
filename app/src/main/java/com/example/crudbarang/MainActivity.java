package com.example.crudbarang;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    EditText xkode,xharga;
    EditText xnama,xjumlah;
    Button tblAdd;
    Button tblView;
    DatabaseHelper BantuDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BantuDb=new DatabaseHelper(this);

        xkode=(EditText)findViewById(R.id.xkode);
        xnama=(EditText)findViewById(R.id.xnama);
        xharga=(EditText)findViewById(R.id.xharga);
        xjumlah=(EditText)findViewById(R.id.xjumlah);

        tblAdd=(Button)findViewById(R.id.tblAdd);
        tblView=(Button)findViewById(R.id.tblView);
        tblView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),viewActivity.class);
                startActivity(i);
            }
        });
        tblAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inKode=xkode.getText().toString().trim();
                String inNama=xnama.getText().toString().trim();
                String inHarga=xharga.getText().toString().trim();
                String inJumlah=xjumlah.getText().toString().trim();

                Boolean isEmpityFields = false;
                if (TextUtils.isEmpty(inKode)){
                    isEmpityFields=true;
                    xkode.setError("Field Ini Tidak Boleh Kosong!!!");
                }
                if (TextUtils.isEmpty(inNama)){
                    isEmpityFields=true;
                    xnama.setError("Field Ini Tidak Boleh Kosong!!!");
                }
                if (TextUtils.isEmpty(inHarga)){
                    isEmpityFields=true;
                    xharga.setError("Field Ini Tidak Boleh Kosong!!!");
                }
                if (TextUtils.isEmpty(inJumlah)){
                    isEmpityFields=true;
                    xjumlah.setError("Field Ini Tidak Boleh Kosong!!!");
                }

                if (!isEmpityFields){
                    boolean isInserted =  BantuDb.insertData(
                            inKode,
                            inNama,
                            inHarga,
                            inJumlah
                    );

                    if(isInserted == true)
                        Toast.makeText(MainActivity.this,"Data Tersimpan",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this,"Data Gagal Tersimpan",Toast.LENGTH_LONG).show();

                    xkode.setText("");
                    xnama.setText("");
                    xharga.setText("");
                    xjumlah.setText("");
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        Intent i;
        switch (selectedMode) {
            case R.id.action_about:
                i=new Intent(MainActivity.this,activityAbout.class);
                startActivity(i);
                break;
        }
    }
}