package com.example.crudbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class detailActivity extends AppCompatActivity {
    DatabaseHelper BantuDb;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        BantuDb=new DatabaseHelper(this);
        back=(ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),viewActivity.class);
                startActivity(i);
            }
        });

        final DatabaseHelper db=new DatabaseHelper(this);
        final ArrayList<HashMap<String, String>> userList = db.GetDetails();
        final ListView lv = (ListView) findViewById(R.id.listview2);
        ListAdapter adapter = new SimpleAdapter(detailActivity.this, userList, R.layout.detail_list,new String[]{"kode","nama_brg","hrg_brg","jml_brg"}, new int[]{R.id.rowKode1, R.id.rowNama1,R.id.rowHarga1,R.id.rowJumlah1});
        lv.setAdapter(adapter);
    }
}
