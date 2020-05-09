package com.example.crudbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateActivity extends AppCompatActivity {
    EditText xkode1,xharga1;
    EditText xnama1,xjumlah1;
    Button tblUpd1;
    DatabaseHelper BantuDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        BantuDb=new DatabaseHelper(this);

        xkode1=(EditText)findViewById(R.id.xkode1);
        xnama1=(EditText)findViewById(R.id.xnama1);
        xharga1=(EditText)findViewById(R.id.xharga1);
        xjumlah1=(EditText)findViewById(R.id.xjumlah1);

        Intent j=getIntent();

        String kd=j.getStringExtra("kode");

        xkode1.setText(kd);

        tblUpd1=(Button)findViewById(R.id.tblUpd);
        tblUpd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inKode=xkode1.getText().toString().trim();
                String inNama=xnama1.getText().toString().trim();
                String inHarga=xharga1.getText().toString().trim();
                String inJumlah=xjumlah1.getText().toString().trim();

                Boolean isEmpityFields = false;
                if (TextUtils.isEmpty(inKode)){
                    isEmpityFields=true;
                    xkode1.setError("Field Ini Tidak Boleh Kosong!!!");
                }
                if (TextUtils.isEmpty(inNama)){
                    isEmpityFields=true;
                    xnama1.setError("Field Ini Tidak Boleh Kosong!!!");
                }
                if (TextUtils.isEmpty(inHarga)){
                    isEmpityFields=true;
                    xharga1.setError("Field Ini Tidak Boleh Kosong!!!");
                }
                if (TextUtils.isEmpty(inJumlah)){
                    isEmpityFields=true;
                    xjumlah1.setError("Field Ini Tidak Boleh Kosong!!!");
                }

                if (!isEmpityFields){
                    boolean updated= BantuDb.updateData(xkode1.getText().toString(), xnama1.getText().toString(),xharga1.getText().toString(),xjumlah1.getText().toString());
                    if(updated == true){
                        Toast.makeText(updateActivity.this,"Data Terupdate",Toast.LENGTH_LONG).show();
                        Intent i =new Intent(getApplicationContext(),viewActivity.class);
                        startActivity(i);
                    } else
                        Toast.makeText(updateActivity.this,"Data Gagal Terupdate",Toast.LENGTH_LONG).show();
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
                i=new Intent(updateActivity.this,activityAbout.class);
                startActivity(i);
                break;
        }
    }
}