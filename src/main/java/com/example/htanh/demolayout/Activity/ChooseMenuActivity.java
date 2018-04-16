package com.example.htanh.demolayout.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.htanh.demolayout.Class.SellProduct;
import com.example.htanh.demolayout.Data.FoodMenuData;
import com.example.htanh.demolayout.Adapter.FoodSellingAdapter;
import com.example.htanh.demolayout.Class.Product;
import com.example.htanh.demolayout.R;
import com.example.htanh.demolayout.DialogFragment.SellingQuantityDialogFragment;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ChooseMenuActivity extends AppCompatActivity {

    FoodSellingAdapter adapter;

    Button btnSellingSave, btnPay;
    ImageButton btnMainBack;
    ImageView ivSellingCancel;
    RecyclerView rvFoodSellingList;
    TextView tvTableQuantity, tvCustomerQuantity;
    TextView tvPaySum;
    TextView tvToolbarSave;
    SellProduct sellProduct;     //danh sách món trong ChooseMenuActivity
    long tableQuantity = 0, customerQuantity = 0;
    long allPaySum = 0;
    private int index;
    String valuePrint;

    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
    private List<Product> foodList = new ArrayList<>();

    FragmentManager fm = getSupportFragmentManager();
    int foodSellingQuantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_menu);

        btnMainBack = findViewById(R.id.button_menu_back);
        btnSellingSave = findViewById(R.id.button_seling_save);
        btnPay = findViewById(R.id.button_pay);
        ivSellingCancel = findViewById(R.id.image_selling_cancel);
        rvFoodSellingList = findViewById(R.id.recycler_view_selling_food_list);
        tvToolbarSave = findViewById(R.id.text_toolbar_save);
        tvTableQuantity = findViewById(R.id.text_table_quantity);
        tvCustomerQuantity = findViewById(R.id.text_customer_quantity);
        tvPaySum = findViewById(R.id.text_pay_sum);

        //lấy thông tin từ selling fragment hiển thị lên màn hình chọn món


        Serializable sellList = getIntent().getSerializableExtra("SellList");
        if (foodList.size() == 0) {
            foodList = new FoodMenuData().setData();
        } else {
            foodList = new ArrayList<>();
            foodList = new FoodMenuData().setData();
        }
        if (sellList == null) {
//            sellProduct = new SellProduct();
            sellProduct = new SellProduct(foodList, null, null);
            tvPaySum.setText("0");
        } else {
//            sellProduct = new SellProduct(foodList, null, null);
            sellProduct = (SellProduct) sellList;
            tvTableQuantity.setText(sellProduct.getTableQuantity());
            tvCustomerQuantity.setText(sellProduct.getCustomerQuantity());
            tvPaySum.setText(sellProduct.getPaySum());
            index = getIntent().getIntExtra("SellIndex", 0);
            foodList = ((SellProduct) sellList).getProductList();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvFoodSellingList.getContext(), linearLayoutManager.getOrientation());
        adapter = new FoodSellingAdapter(foodList, this, this);

        rvFoodSellingList.setAdapter(adapter);
        rvFoodSellingList.setLayoutManager(linearLayoutManager);
//        rvFoodSellingList.addItemDecoration(dividerItemDecoration);

        //show dialog thêm số lượng bàn
        tvTableQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellingQuantityDialogFragment sellingQuantityDialogFragment;
                if (!tvTableQuantity.getText().toString().equals("")) {
                    sellingQuantityDialogFragment = SellingQuantityDialogFragment.newInstance(tvTableQuantity.getText().toString());
                } else {
                    sellingQuantityDialogFragment = new SellingQuantityDialogFragment();
                }

                sellingQuantityDialogFragment.check = 2;
                sellingQuantityDialogFragment.setQuantityValue(new SellingQuantityDialogFragment.IPassQuantityValue() {
                    @Override
                    public void passQuantityValue(String valuePrint, String valueString) {
                        if (!valuePrint.equals("") && !valuePrint.equals("0")) {
                            tableQuantity = Long.parseLong(valueString);
                            tvTableQuantity.setText(valuePrint);
                        } else {
                            tableQuantity = 0;
                            tvTableQuantity.setText("");
                        }
                    }
                });
                sellingQuantityDialogFragment.show(fm, "1");
            }
        });

        //show dialog thêm số lượng khách hàng
        tvCustomerQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellingQuantityDialogFragment sellingQuantityDialogFragment;
                if (!tvCustomerQuantity.getText().toString().equals("")) {
                    sellingQuantityDialogFragment = SellingQuantityDialogFragment.newInstance(tvCustomerQuantity.getText().toString());
                } else {
                    sellingQuantityDialogFragment = new SellingQuantityDialogFragment();
                }

                sellingQuantityDialogFragment.check = 3;
                sellingQuantityDialogFragment.setQuantityValue(new SellingQuantityDialogFragment.IPassQuantityValue() {
                    @Override
                    public void passQuantityValue(String valuePrint, String valueString) {
                        if (!valuePrint.equals("") && !valuePrint.equals("0")) {
                            customerQuantity = Long.parseLong(valueString);
                            tvCustomerQuantity.setText(valuePrint);
                        } else {
                            customerQuantity = 0;
                            tvCustomerQuantity.setText("");
                        }
                    }
                });
                sellingQuantityDialogFragment.show(fm, "1");  //dở...
            }
        });

//        tvPaySum.setText("" + adapter.getPaySum());
//        for (int i = 0; i < adapter.getFoodList().size(); i++){
//
//            adapter.getPaySum(i, new IGetPaySum() {
//                @Override
//                public void getPaySum(long paySum) {
//                    allPaySum = allPaySum + paySum;
//                    Toast.makeText(ChooseMenuActivity.this, "" + allPaySum, Toast.LENGTH_SHORT).show();
//                }
//            });
//        }


        //get tổng số tiền thanh toán
        adapter.getPaySum(new FoodSellingAdapter.IGetPaySum() {                       //DỞ....
            @Override
            public void getPaySum(long paySum) {
                long l = 0;
                for (int i = 0; i < adapter.getFoodList().size(); i++) {
                    l = l + adapter.getFoodList().get(i).getPaySum();
                }
                tvPaySum.setText("" + l);
            }
        });

        tvPaySum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvPaySum.removeTextChangedListener(this);
                String paySum = decimalFormat.format(Long.parseLong(s.toString()));
                paySum = paySum.replace(",", ".");
                tvPaySum.setText(paySum);
                tvPaySum.addTextChangedListener(this);
            }
        });

        btnMainBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivSellingCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSellingSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ChooseMenuActivity.this, MainAppActivity.class);

                passInfo(intent, "finish");
//                intent.putExtra("SellList", (Serializable) sellList);
//                intent.putExtra("TableQuantity", tvTableQuantity.getText().toString());
//                intent.putExtra("CustomerQuantity", tvCustomerQuantity.getText().toString());
//                intent.putExtra("PaySum", tvPaySum.getText().toString());
            }
        });
        tvToolbarSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseMenuActivity.this, PayMentActivity.class);

                passInfo(intent, "start");
//                Intent intent = new Intent(ChooseMenuActivity.this, PayMentActivity.class);
//                intent.putExtra("SellProduct", (Serializable) sellProduct);
////                intent.putExtra("BillList", (Serializable) adapter.getFoodList());
////                intent.putExtra("PaySum", tvPaySum.getText().toString());
////                intent.putExtra("TableQuantity", tvTableQuantity.getText().toString());
//                intent.putExtra("Caller", "ChooseMenuActivity");
//                startActivityForResult(intent, 1);
            }
        });


        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseMenuActivity.this, PayMentActivity.class);

                passInfo(intent, "start");
//                Intent intent = new Intent(ChooseMenuActivity.this, PayMentActivity.class);
//                intent.putExtra("BillList", (Serializable) adapter.getFoodList());
//                intent.putExtra("PaySum", tvPaySum.getText().toString());
//                intent.putExtra("TableQuantity", tvTableQuantity.getText().toString());
//                intent.putExtra("Caller", "ChooseMenuActivity");
//                startActivityForResult(intent, 1);
            }
        });
    }

    //pass thông tin danh sách các món đã chọn cho các màn hình khác
    public void passInfo(Intent intent, String state) {
        List<Product> sellList = new ArrayList<>();
        SellProduct sellProductSaved = new SellProduct();    //danh sách các món đã chọn

        for (int i = 0; i < sellProduct.getProductList().size(); i++) {
            if (sellProduct.getProductList().get(i).getQuantity() != 0) {
                sellList.add(sellProduct.getProductList().get(i));
//                        sellProductSaved.getProductList().add(sellList.get(i));
            }
        }
        if (sellList.isEmpty()) {
            Toast.makeText(ChooseMenuActivity.this, "Vui lòng chọn món", Toast.LENGTH_SHORT).show();
        } else {
//                Bundle bundle = new Bundle();
//                bundle.putString("TableQuantity", tvTableQuantity.getText().toString());
//                intent.putExtras(bundle);
            sellProductSaved.setProductList(sellList);
            sellProductSaved.setTableQuantity(tvTableQuantity.getText().toString());
            sellProductSaved.setCustomerQuantity(tvCustomerQuantity.getText().toString());
            sellProductSaved.setPaySum(tvPaySum.getText().toString());
            intent.putExtra("SellProduct", sellProductSaved);
            intent.putExtra("Caller", "ChooseMenuActivity");
            setResult(4, intent);
            if (state.equals("finish")) {
                finish();
            } else {
                startActivityForResult(intent, 1);
            }
        }
    }

}
