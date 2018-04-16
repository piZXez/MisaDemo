package com.example.htanh.demolayout.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.htanh.demolayout.DialogFragment.AddColorDialogFragment;
import com.example.htanh.demolayout.DialogFragment.AddIconDialogFragment;
import com.example.htanh.demolayout.DialogFragment.AddPriceDialogFragment;
import com.example.htanh.demolayout.DialogFragment.ConfirmDialogFragment;
import com.example.htanh.demolayout.Adapter.FoodBackgroundColorAdapter;
import com.example.htanh.demolayout.Adapter.FoodIconAdapter;
import com.example.htanh.demolayout.Class.Product;
import com.example.htanh.demolayout.R;

import java.io.Serializable;

public class EditMenuActivity extends AppCompatActivity implements AddPriceDialogFragment.IPassValueToActivity, ConfirmDialogFragment.IActionAccept {

    ImageButton btnMenuBack;

    TextView tvPrice, tvUnitCount, tvFoodName, tvUnit;
    TextView tvToolbar, tvToolbarSave;
    ImageView ivPrice, ivUnitCount, ivFoodBackgroundColor, ivFoodIcon;
    AddPriceDialogFragment addPriceDialogFragment;
    AddColorDialogFragment addColorDialogFragment;
    AddIconDialogFragment addIconDialogFragment;
    ConfirmDialogFragment confirmDialogFragment;
    EditText etAddFoodName;
    Button btnAddFoodSave, btnEditFoodSave, btnEditFoodDelete;
    CheckBox cbFoodState;
    LinearLayout llFoodState;
    FragmentManager fm = getSupportFragmentManager();
    Product product;
    GradientDrawable gradientDrawable;
    int iconBg;
    String colorBg;     //giá trị màu nền
    int index;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);

        gradientDrawable = (GradientDrawable) getResources().getDrawable(R.drawable.food_color_bg);

        tvToolbarSave = findViewById(R.id.text_toolbar_save);
        tvToolbar = findViewById(R.id.text_toolbar);

        tvPrice = findViewById(R.id.text_menu_price);
        tvUnitCount = findViewById(R.id.text_unit_count);
        tvUnit = findViewById(R.id.text_unit);
        tvFoodName = findViewById(R.id.text_food_name);

        ivPrice = findViewById(R.id.image_price);
        ivUnitCount = findViewById(R.id.image_unit_count);
        ivFoodBackgroundColor = findViewById(R.id.image_food_background);
        ivFoodIcon = findViewById(R.id.image_food_icon);

        btnAddFoodSave = findViewById(R.id.button_add_food_save);
        btnEditFoodSave = findViewById(R.id.button_edit_food_save);
        btnEditFoodDelete = findViewById(R.id.button_edit_food_delete);

        etAddFoodName = findViewById(R.id.edit_text_add_food_name);

        cbFoodState = findViewById(R.id.checkbox_food_state);

        llFoodState = findViewById(R.id.linear_layout_food_state);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("Color", String.valueOf(Color.BLACK));
//        editor.commit();


        //lấy thông tin food từ FoodMenuFragment hiển thị lên màn hình sửa
        Serializable foodInfo = getIntent().getSerializableExtra("FoodInfo");
        if (foodInfo == null) {
            product = new Product();
        } else {
            product = (Product) foodInfo;
            index = getIntent().getIntExtra("FoodIndex", 0);
        }

        //đổi màu text
        tvFoodName.setText(Html.fromHtml("Tên món <font color='red'>*</font>"));
        tvUnit.setText(Html.fromHtml("Đơn vị tính <font color='red'>*</font>"));

        //thêm món
        if (getIntent().getIntExtra("Activity", 0) == 1) {
            toAddFoodInfo();

        } else {
            //sửa món
            getSelectedFoodInfo(product);
        }

        //hiển thị màn hình máy tính nhập giá bán
        tvPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddPriceDialogFragment();
            }
        });

        //hiển thị màn hình máy tính nhập giá bán
        ivPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddPriceDialogFragment();
            }
        });

        //hiển thị màn hình nhập đơn vị tính
        tvUnitCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toUnitCountActivity();
            }
        });

        //hiển thị màn hình nhập đơn vị tính
        ivUnitCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toUnitCountActivity();
            }
        });

        ivFoodBackgroundColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddColorDialogFragment();
//                ivFoodBackgroundColor.setBackground(gradientDrawable);
//                ivFoodIcon.setBackground(gradientDrawable);
            }
        });

        ivFoodIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddIconDialogFragment();
            }
        });

        btnMenuBack = findViewById(R.id.button_menu_back);
        //quay lại màn hình MainAppActivity
        btnMenuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditMenuActivity.this, MainAppActivity.class);
                setResult(0, intent);
                finish();
            }
        });


//        tvToolbarSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addFood(etAddFoodName.getText().toString(), tvPrice.getText().toString(), tvUnitCount.getText().toString());
//            }
//        });


    }

    //show màn hình thêm món
    private void toAddFoodInfo() {
        tvToolbar.setText("Thêm món");
        tvUnitCount.setText("Chai");

        colorBg = "#0087e6";
        iconBg = R.drawable.ic_default;
        gradientDrawable.setColor(Color.parseColor(colorBg));
        ivFoodIcon.setBackground(gradientDrawable);
        ivFoodIcon.setImageResource(iconBg);
        ivFoodBackgroundColor.setBackground(gradientDrawable);

        btnEditFoodSave.setVisibility(View.GONE);
        btnEditFoodDelete.setVisibility(View.GONE);
        llFoodState.setVisibility(View.GONE);

        //lưu món vừa thêm hoặc sửa
        btnAddFoodSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setFoodName(etAddFoodName.getText().toString());
                product.setFoodPrice(tvPrice.getText().toString());
                product.setUnitCount(tvUnitCount.getText().toString());
                product.setColor(colorBg);
                product.setImgFoodMenu(iconBg);
                addFood(product);
            }
        });
        tvToolbarSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setFoodName(etAddFoodName.getText().toString());
                product.setFoodPrice(tvPrice.getText().toString());
                product.setUnitCount(tvUnitCount.getText().toString());
                product.setColor(colorBg);
                product.setImgFoodMenu(iconBg);
                addFood(product);
            }
        });
    }

    //show màn hình sửa món
    private void getSelectedFoodInfo(final Product product) {

        tvToolbar.setText("Sửa món");
        tvPrice.setText(product.getFoodPrice());
        tvUnitCount.setText(product.getUnitCount());
        etAddFoodName.setText(product.getFoodName());
        if (product.isStateOutOfStock()){
            cbFoodState.setChecked(true);
        } else {
            cbFoodState.setChecked(false);
        }

        colorBg = product.getColor();
        iconBg = product.getImgFoodMenu();
        gradientDrawable.setColor(Color.parseColor(colorBg));
        ivFoodBackgroundColor.setBackground(gradientDrawable);
        ivFoodIcon.setImageResource(iconBg);
        ivFoodIcon.setBackground(gradientDrawable);

        btnAddFoodSave.setVisibility(View.GONE);

        etAddFoodName.setText(product.getFoodName());
        if (product.getFoodPrice() == null) {
            tvPrice.setText("0");
        } else {
            tvPrice.setText(product.getFoodPrice());
        }

        tvToolbarSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setFoodName(etAddFoodName.getText().toString());
                product.setFoodPrice(tvPrice.getText().toString());
                product.setUnitCount(tvUnitCount.getText().toString());
                product.setColor(colorBg);
                product.setImgFoodMenu(iconBg);
                if (cbFoodState.isChecked()){
                    product.setStateOutOfStock(true);
                } else {
                    product.setStateOutOfStock(false);
                }
                editFood(product, index);
            }
        });
        btnEditFoodSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setFoodName(etAddFoodName.getText().toString());
                product.setFoodPrice(tvPrice.getText().toString());
                product.setUnitCount(tvUnitCount.getText().toString());
                product.setColor(colorBg);
                product.setImgFoodMenu(iconBg);
                if (cbFoodState.isChecked()){
                    product.setStateOutOfStock(true);
                } else {
                    product.setStateOutOfStock(false);
                }
                editFood(product, index);
            }
        });

        btnEditFoodDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toConfirmDialogFragment();
//                deleteFood(index);
            }
        });
    }

    //thêm món ăn mới
    private void addFood(Product product) {
        if (etAddFoodName.getText().toString().equals("")) {
            Toast.makeText(EditMenuActivity.this, "Tên món không được để trống", Toast.LENGTH_SHORT).show();
        } else {
            //dở..
            Intent intent = new Intent(EditMenuActivity.this, MainAppActivity.class);
            intent.putExtra("AddFood", product);
            setResult(1, intent);
            finish();
        }
    }

    //sửa món
    private void editFood(Product product, int index) {
//        product.setFoodName(tvFoodName.getText().toString());
//        product.setFoodPrice(tvPrice.getText().toString());
//        product.setUnitCount(tvUnitCount.getText().toString());
        Intent intent = new Intent(EditMenuActivity.this, MainAppActivity.class);
        intent.putExtra("EditFood", product);
        intent.putExtra("Index", index);
        setResult(2, intent);
        finish();
    }

    //xóa món
    private void deleteFood(int index) {
        Intent intent = new Intent(EditMenuActivity.this, MainAppActivity.class);
        intent.putExtra("DeleteFood", index);
        setResult(3, intent);
        finish();
    }

    private void toAddPriceDialogFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("Price", tvPrice.getText().toString());
        addPriceDialogFragment = new AddPriceDialogFragment();
        addPriceDialogFragment.setArguments(bundle);      //truyền giá trị food price vào dialog
        addPriceDialogFragment.show(fm, "1");
    }

    private void toAddColorDialogFragment() {
        addColorDialogFragment = new AddColorDialogFragment();
        addColorDialogFragment.setPickColor(new FoodBackgroundColorAdapter.IPickColor() {

            //chọn màu/đổi màu
            @Override
            public void pickColor(String color) {
                gradientDrawable = (GradientDrawable) getResources().getDrawable(R.drawable.food_color_bg);
                colorBg = color;
                gradientDrawable.setColor(Color.parseColor(color));        //add food bg color
                ivFoodIcon.setBackground(gradientDrawable);
                ivFoodBackgroundColor.setBackground(gradientDrawable);
            }
        });
        addColorDialogFragment.show(fm, "2");
    }

    private void toAddIconDialogFragment() {
        addIconDialogFragment = new AddIconDialogFragment();
        addIconDialogFragment.setPickIcon(new FoodIconAdapter.IPickIcon() {
            @Override
            public void pickIcon(int icon) {
                iconBg = icon;
                ivFoodIcon.setImageResource(iconBg);                       //add food bg icon
            }
        });
        addIconDialogFragment.show(fm, "3");
    }

    private void toConfirmDialogFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("FoodName", product.getFoodName());
        confirmDialogFragment = new ConfirmDialogFragment();
        confirmDialogFragment.setArguments(bundle);
        confirmDialogFragment.getCaller("EditMenuActivity");
        confirmDialogFragment.show(fm, "4");
    }

//    private void toAddPriceDialog() {
//        addPriceDialog.setTitle("Giá bán");
//        addPriceDialog.show();
//    }

    private void toUnitCountActivity() {
        Intent intent = new Intent(EditMenuActivity.this, UnitCountActivity.class);
        intent.putExtra("Menu unit name", tvUnitCount.getText().toString());
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == 0){
//            btnEditFoodDelete.setVisibility(View.GONE);
//            btnEditFoodSave.setVisibility(View.GONE);
//        } else if(requestCode == 1){
//            btnAddFoodSave.setVisibility(View.GONE);
//        }

        //nhận dữ liệu từ UnitCountActivity
        if (data != null) {
            switch (resultCode) {
                case 111:
                    tvUnitCount.setText(data.getStringExtra("Unit name"));
                    break;
            }
        }
    }

    public void setTvPrice(String s) {
        tvPrice.setText(s);
    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    public void onAttachFragment(Fragment fragment) {
//        super.onAttachFragment(fragment);
//
//        try {
//            listener = (PassValueToDialog) fragment.getContext();
//        } catch (ClassCastException e) {
//            e.printStackTrace();
//        }
//    }


    public TextView getTvPrice() {
        return tvPrice;
    }

    @Override
    public void passValueToActivity(String value) {
        tvPrice.setText(value);        //nhận giá trị từ add price dialog
    }

    @Override
    public void actionAccept(boolean isAccept) {
        if (isAccept){
            deleteFood(index);         //xóa món ăn khi nhận đc accept từ delete confirm dialog
        }
    }
}

