package com.example.htanh.demolayout.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.htanh.demolayout.Class.SellProduct;
import com.example.htanh.demolayout.Fragment.FoodMenuFragment;
import com.example.htanh.demolayout.Class.Product;
import com.example.htanh.demolayout.R;
import com.example.htanh.demolayout.Fragment.ReportFragment;
import com.example.htanh.demolayout.Fragment.SellingFragment;

import java.util.List;

public class MainAppActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FoodMenuFragment foodMenuFragment = new FoodMenuFragment();
    SellingFragment sellingFragment = new SellingFragment();
    ReportFragment reportFragment = new ReportFragment();
    TextView tvToolbarTitle;
    ImageButton btnBarAdd;
    String unitName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnBarAdd = findViewById(R.id.button_menu_bar_add);
        tvToolbarTitle = findViewById(R.id.text_toolbar_title);

        replaceFragment(sellingFragment);

        init();
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//    }

    private void init() {
        Intent intent = new Intent(MainAppActivity.this, ChooseMenuActivity.class);
        startActivityForResult(intent, 1);

        btnBarAdd.setVisibility(View.VISIBLE);
        btnBarAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAppActivity.this, ChooseMenuActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode){
            case 1:
                foodMenuFragment.addData((Product) data.getSerializableExtra("AddFood"));
                break;
            case 2:
                foodMenuFragment.editData(data.getIntExtra("Index", 0), (Product) data.getSerializableExtra("EditFood"));
                break;
            case 3:
                foodMenuFragment.deleteData(data.getIntExtra("DeleteFood", 0));
                break;
            case 4:
                sellingFragment.addData((SellProduct) data.getSerializableExtra("SellProduct"));
//                sellingFragment.addData((List<Product>) data.getSerializableExtra("SellList"), data.getStringExtra("TableQuantity"), data.getStringExtra("CustomerQuantity"), data.getStringExtra("PaySum"));
//                sellingFragment.addData((SellProduct) data.getSerializableExtra(""));
                break;
        }
    }

    //    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_food_menu:
                tvToolbarTitle.setText(R.string.title_fragment_food_menu);

                btnBarAdd.setVisibility(View.VISIBLE);
                btnBarAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainAppActivity.this, EditMenuActivity.class);
                        intent.putExtra("Activity", 1);
//                        editMenuActivity.getBtnEditFoodDelete().setVisibility(View.GONE);
//                        editMenuActivity.getBtnEditFoodSave().setVisibility(View.GONE);
                        startActivityForResult(intent, 0);
                    }
                });

                replaceFragment(foodMenuFragment);
                break;

            case R.id.nav_selling:
                tvToolbarTitle.setText(R.string.title_fragment_selling);

                btnBarAdd.setVisibility(View.VISIBLE);
                btnBarAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainAppActivity.this, ChooseMenuActivity.class);
                        startActivityForResult(intent, 1);
                    }
                });

                replaceFragment(sellingFragment);
                break;

            case R.id.nav_report:
                tvToolbarTitle.setText(R.string.title_fragment_report);

                btnBarAdd.setVisibility(View.INVISIBLE);
                replaceFragment(reportFragment);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment newFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_food_menu, newFragment);
        fragmentTransaction.commit();
    }

    public void addFragment(Fragment newFragment){
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout_food_menu);
        if(fragment == null || fragment.equals(newFragment)){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frame_layout_food_menu, newFragment);
            fragmentTransaction.commit();
        }
    }
}
