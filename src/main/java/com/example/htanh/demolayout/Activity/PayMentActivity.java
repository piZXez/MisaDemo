package com.example.htanh.demolayout.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.htanh.demolayout.Adapter.BillAdapter;
import com.example.htanh.demolayout.Class.Product;
import com.example.htanh.demolayout.Class.SellProduct;
import com.example.htanh.demolayout.Data.FoodMenuData;
import com.example.htanh.demolayout.R;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PayMentActivity extends AppCompatActivity {

    private BillAdapter adapter;
    private RecyclerView rvBillList;
    private SellProduct sellProduct;
    private List<Product> productList = new ArrayList<>();
    private List<Product> billList = new ArrayList<>();

    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

    LinearLayout llTablePayment;
    TextView tvPaySum, tvCustomerPay, tvPaymentChange;
    TextView tvBillTable;
    TextView tvToolbarDone;
    ImageButton btnPaymentBack;
    Button btnPaymentDone;
    long paySum, customerPay, paymentChange;
    String paySumSring, customerPayString, paymentChangeString;
    String caller;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        rvBillList = findViewById(R.id.recycler_view_bill_list);
        llTablePayment = findViewById(R.id.linear_layout_table_payment);

        tvBillTable = findViewById(R.id.text_bill_table);
        tvPaySum = findViewById(R.id.text_pay_sum);
        tvCustomerPay = findViewById(R.id.text_customer_pay);
        tvPaymentChange = findViewById(R.id.text_payment_change);
        tvToolbarDone = findViewById(R.id.text_toolbar_done);
        btnPaymentBack = findViewById(R.id.button_menu_back);
        btnPaymentDone = findViewById(R.id.button_payment_done);

        Intent intent = getIntent();
        caller = intent.getStringExtra("Caller");

        Serializable sellInfo = intent.getSerializableExtra("SellProduct");
        if (sellInfo == null) {
            sellProduct = new SellProduct();
        } else {
            sellProduct = (SellProduct) sellInfo;
        }


        if (sellProduct.getTableQuantity() == null || sellProduct.getTableQuantity().isEmpty()) {    /////////////////////////////////
            llTablePayment.setVisibility(View.GONE);
        } else {
            llTablePayment.setVisibility(View.VISIBLE);
            tvBillTable.setText(sellProduct.getTableQuantity());
//            tvBillTable.setText(intent.getStringExtra("TableQuantity"));
            for (int i = 0; i < sellProduct.getProductList().size(); i++) {
                if (sellProduct.getProductList().get(i).getQuantity() != 0) {
                    billList.add(sellProduct.getProductList().get(i));
                }
            }
        }

        tvPaySum.setText(sellProduct.getPaySum());
//        if (!sellProduct.getPaySum().isEmpty() || sellProduct.getPaySum() != null) {
//        }
//        tvPaySum.setText(intent.getStringExtra("PaySum"));
        tvCustomerPay.setText("100.000");
        adapter = new BillAdapter(billList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvBillList.getContext(), linearLayoutManager.getOrientation());

        rvBillList.setAdapter(adapter);
        rvBillList.setLayoutManager(linearLayoutManager);
        rvBillList.addItemDecoration(dividerItemDecoration);

//        tvCustomerPay.setText(intent.getStringExtra("PaySum"));
        paySumSring = tvPaySum.getText().toString().replace(".", "");
        customerPayString = tvCustomerPay.getText().toString().replace(".", "");

        if (!paySumSring.equals("")) {
            paySum = Long.parseLong(paySumSring);
        } else {
            paySum = 0;
        }
        if (!customerPayString.equals("")) {
            customerPay = Long.parseLong(customerPayString);
        } else {
            customerPay = 0;
        }
        paymentChange = customerPay - paySum;
        paymentChangeString = "" + paymentChange;
//        String s = decimalFormat.format(Long.parseLong(paymentChangeString));
//        s = s.replace(",", ".");
        tvPaymentChange.setText(decimalFormat.format(Long.parseLong(paymentChangeString)).replace(",", "."));

        tvToolbarDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnPaymentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnPaymentDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
