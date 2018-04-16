package com.example.htanh.demolayout.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.htanh.demolayout.Class.UnitCount;
import com.example.htanh.demolayout.R;
import com.example.htanh.demolayout.Adapter.UnitCountAdapter;
import com.example.htanh.demolayout.Data.UnitCountData;

import java.util.ArrayList;
import java.util.List;

public class UnitCountActivity extends AppCompatActivity {

    ImageButton btnMenuBack;
    UnitCountAdapter adapter;
    ListView listUnitCount;
    Button btnDone;
    List<UnitCount> unitCounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_count);

        mapped();

        btnMenuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UnitCountActivity.this, EditMenuActivity.class);
                intent.putExtra("Unit name", adapter.getN());
                setResult(111, intent);
                finish();
            }
        });

        unitCounts = new UnitCountData().getData();
        adapter = new UnitCountAdapter(unitCounts, this);
        listUnitCount = findViewById(R.id.list_unit_count);

        Intent intent = getIntent();
        adapter.setN(intent.getStringExtra("Menu unit name"));

        listUnitCount.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void mapped() {
        btnMenuBack = findViewById(R.id.button_menu_back);
        btnDone = findViewById(R.id.button_done_add_unit);
    }

}
