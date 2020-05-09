package com.example.crudbarang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class viewActivity extends AppCompatActivity {

    DatabaseHelper BantuDb;
    ImageView add,sort;
    TextView totalB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        BantuDb=new DatabaseHelper(this);

        add=(ImageView)findViewById(R.id.iconAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        sort=(ImageView)findViewById(R.id.iconSort);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseHelper db=new DatabaseHelper(viewActivity.this);
                final ArrayList<HashMap<String, String>> userList = db.GetSortASC();
                final ListView lv = (ListView) findViewById(R.id.listview1);
                ListAdapter adapter = new SimpleAdapter(viewActivity.this, userList, R.layout.row_list,new String[]{"kode","nama_brg"}, new int[]{R.id.rowKode, R.id.rowNama});
                lv.setAdapter(adapter);
            }
        });

        final DatabaseHelper db=new DatabaseHelper(this);
        final ArrayList<HashMap<String, String>> userList = db.GetUsers();
        final ListView lv = (ListView) findViewById(R.id.listview1);
        ListAdapter adapter = new SimpleAdapter(viewActivity.this, userList, R.layout.row_list,new String[]{"kode","nama_brg"}, new int[]{R.id.rowKode, R.id.rowNama});
        lv.setAdapter(adapter);

        totalB=(TextView)findViewById(R.id.ttlBrg);
        totalB.setText(String.valueOf(userList.size()));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                TextView rs1=findViewById(R.id.rowKode);
                Toast.makeText(getApplicationContext(),rs1.getText(),Toast.LENGTH_SHORT).show();
                final CharSequence[] items = { "Update Data", "Hapus Data","Detail Data" };
                AlertDialog.Builder builder = new AlertDialog.Builder(viewActivity.this);
                builder.setTitle("Option");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @SuppressLint("ResourceType")
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                TextView rs=findViewById(R.id.rowKode);
                                Intent i=new Intent(getApplicationContext(), updateActivity.class);
                                i.putExtra("kode", rs.getText());
                                startActivity(i);
                                break;
                            case 1:
                                TextView kode1=findViewById(R.id.rowKode);
                                long isDeleted= db.deleteData(kode1.getText().toString());
                                if (isDeleted==1){
                                    Toast.makeText(viewActivity.this,"Data Terhapus",Toast.LENGTH_LONG).show();
                                    finish();
                                    startActivity(getIntent());
                                }else {
                                    Toast.makeText(viewActivity.this,"Data Tidak Terhapus",Toast.LENGTH_LONG).show();
                                    finish();
                                    startActivity(getIntent());
                                }
                                break;
                            case 2:
                                Intent d = new Intent(getApplicationContext(),detailActivity.class);
                                startActivity(d);
                                break;
                        }
                        dialog.dismiss();
                    }
                }).show();
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
                i=new Intent(viewActivity.this,activityAbout.class);
                startActivity(i);
                break;
        }
    }
}