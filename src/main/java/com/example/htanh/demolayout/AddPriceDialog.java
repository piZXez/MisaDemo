package com.example.htanh.demolayout;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.htanh.demolayout.Activity.EditMenuActivity;

/***
 * Created by htanh on 1/23/2018.
 */

class AddPriceDialog extends Dialog {

    private EditMenuActivity editMenuActivity;
    private ImageButton ibtPriceDialogClose;
    private EditText etAddPriceDialog;
    private ImageButton btnDelete;
    private Button btnClear, btnDecrease, btnIncrease;
    private Button btnMinus, btnPlus, btnMinusPlus, btnComma, btnDone;
    private Button btnNumber0, btnNumber1, btnNumber2, btnNumber3, btnNumber4, btnNumber5, btnNumber6, btnNumber7, btnNumber8, btnNumber9, btnNumber000;
    private long value = 0;

    public AddPriceDialog(final Context context, final EditMenuActivity editMenuActivity) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.editMenuActivity = editMenuActivity;

        this.setContentView(R.layout.add_price_dialog_layout);

        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ibtPriceDialogClose = findViewById(R.id.button_pricedialog_close);
        etAddPriceDialog    = findViewById(R.id.edit_text_dialog);
        btnClear            = findViewById(R.id.dialog_clear_btn);
        btnDecrease         = findViewById(R.id.dialog_decrease_btn);
        btnIncrease         = findViewById(R.id.dialog_increase_btn);
        btnDelete           = findViewById(R.id.dialog_delete_btn);
        btnMinus            = findViewById(R.id.dialog_minus_btn);
        btnPlus             = findViewById(R.id.dialog_plus_btn);
        btnMinusPlus        = findViewById(R.id.dialog_minus_plus_btn);
        btnComma            = findViewById(R.id.dialog_comma_btn);
        btnDone             = findViewById(R.id.dialog_done_btn);
        btnNumber0          = findViewById(R.id.dialog_0_btn);
        btnNumber000        = findViewById(R.id.dialog_000_btn);
        btnNumber1          = findViewById(R.id.dialog_1_btn);
        btnNumber2          = findViewById(R.id.dialog_2_btn);
        btnNumber3          = findViewById(R.id.dialog_3_btn);
        btnNumber4          = findViewById(R.id.dialog_4_btn);
        btnNumber5          = findViewById(R.id.dialog_5_btn);
        btnNumber6          = findViewById(R.id.dialog_6_btn);
        btnNumber7          = findViewById(R.id.dialog_7_btn);
        btnNumber8          = findViewById(R.id.dialog_8_btn);
        btnNumber9          = findViewById(R.id.dialog_9_btn);

        etAddPriceDialog.setText(editMenuActivity.getTvPrice().getText().toString());

//        Button btnNumber
        btnNumberClicked1(0, btnNumber0);
        btnNumberClicked1(1, btnNumber1);
        btnNumberClicked1(2, btnNumber2);
        btnNumberClicked1(3, btnNumber3);
        btnNumberClicked1(4, btnNumber4);
        btnNumberClicked1(5, btnNumber5);
        btnNumberClicked1(6, btnNumber6);
        btnNumberClicked1(7, btnNumber7);
        btnNumberClicked1(8, btnNumber8);
        btnNumberClicked1(9, btnNumber9);

        //xóa hết
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = 0;
                etAddPriceDialog.setText("" + value);
                Toast.makeText(context, "" + value, Toast.LENGTH_SHORT).show();
            }
        });

        //xóa 1 ký tự
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = (value - value % 10)/10;
                etAddPriceDialog.setText("" + value);
                Toast.makeText(context, "" + value, Toast.LENGTH_SHORT).show();
            }
        });

        //tăng 1 đơn vị
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + 1;
                etAddPriceDialog.setText("" + value);
                Toast.makeText(context, "" + value, Toast.LENGTH_SHORT).show();
            }
        });

        //giảm một đơn vị
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value - 1;
                etAddPriceDialog.setText("" + value);
                Toast.makeText(context, "" + value, Toast.LENGTH_SHORT).show();
            }
        });

        //xong
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dở...
//                String a = etAddPriceDialog.getText().toString();
//                Long value = Long.parseLong(a);
//                Toast.makeText(context, ""+value, Toast.LENGTH_SHORT).show();
                editMenuActivity.getTvPrice().setText(etAddPriceDialog.getText().toString());
                dismiss();
            }
        });

        ibtPriceDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dở...
                dismiss();
            }
        });

    }


    private void btnNumberClicked1(final long number, Button btnNumber) {


//        final long value = 0;
//        final long newValue = value*10;
        btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringValue = "" + value;
                if (etAddPriceDialog.getText().toString().length() < 19  && stringValue.length() < 15){
                    value = value * 10 + number;

                    etAddPriceDialog.setText("" + value);
//                    if (etAddPriceDialog.getText().toString().equals("0")){
//                        etAddPriceDialog.setText("" + number);
//                    } else {
//                        if ((etAddPriceDialog.getText().toString().length() + 1) % 4 != 0){
//                            etAddPriceDialog.setText(etAddPriceDialog.getText().toString() + number);
//                        } else {
//                            etAddPriceDialog.setText(etAddPriceDialog.getText().toString()+ "." + number);
//                        }
//                    }
                }
            }
        });

    }

    private void btnNumberClicked2(final String number, Button btnNumber){

        btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public String getValue() {
        return etAddPriceDialog.getText().toString();
    }
}
