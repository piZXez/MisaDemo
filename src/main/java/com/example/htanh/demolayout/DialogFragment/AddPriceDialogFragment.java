package com.example.htanh.demolayout.DialogFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.htanh.demolayout.R;

import java.text.DecimalFormat;


/**
 *
 * Created by htanh on 2/27/2018.
 */

public class AddPriceDialogFragment extends DialogFragment {

    IPassValueToActivity listener;

    ImageButton ibtPriceDialogClose;
    EditText etAddPriceDialog;
    ImageButton btnDelete;
    Button btnClear, btnDecrease, btnIncrease;
    Button btnMinus, btnPlus, btnMinusPlus, btnComma, btnDone;
    Button btnNumber0, btnNumber1, btnNumber2, btnNumber3, btnNumber4, btnNumber5, btnNumber6, btnNumber7, btnNumber8, btnNumber9, btnNumber000;
    String valueString = "";
    long value = 0;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");


    public AddPriceDialogFragment() {
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_price_dialog_layout, container, false);

        ibtPriceDialogClose = view.findViewById(R.id.button_pricedialog_close);
        etAddPriceDialog    = view.findViewById(R.id.edit_text_dialog);
        btnClear            = view.findViewById(R.id.dialog_clear_btn);
        btnDecrease         = view.findViewById(R.id.dialog_decrease_btn);
        btnIncrease         = view.findViewById(R.id.dialog_increase_btn);
        btnDelete           = view.findViewById(R.id.dialog_delete_btn);
        btnMinus            = view.findViewById(R.id.dialog_minus_btn);
        btnPlus             = view.findViewById(R.id.dialog_plus_btn);
        btnMinusPlus        = view.findViewById(R.id.dialog_minus_plus_btn);
        btnComma            = view.findViewById(R.id.dialog_comma_btn);
        btnDone             = view.findViewById(R.id.dialog_done_btn);
        btnNumber0          = view.findViewById(R.id.dialog_0_btn);
        btnNumber000        = view.findViewById(R.id.dialog_000_btn);
        btnNumber1          = view.findViewById(R.id.dialog_1_btn);
        btnNumber2          = view.findViewById(R.id.dialog_2_btn);
        btnNumber3          = view.findViewById(R.id.dialog_3_btn);
        btnNumber4          = view.findViewById(R.id.dialog_4_btn);
        btnNumber5          = view.findViewById(R.id.dialog_5_btn);
        btnNumber6          = view.findViewById(R.id.dialog_6_btn);
        btnNumber7          = view.findViewById(R.id.dialog_7_btn);
        btnNumber8          = view.findViewById(R.id.dialog_8_btn);
        btnNumber9          = view.findViewById(R.id.dialog_9_btn);



        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        etAddPriceDialog.setText(editMenuActivity.tvPrice.getText().toString());..
//
        //get giá trị giá bán từ edit activity về dialog
        value = Long.parseLong(getArguments().getString("Price").replace(".", ""));
//        valueString = "" + value;
        valueString = decimalFormat.format(value);
        valueString = valueString.replace(",", ".");

        //        Button btnNumber
        btnNumberClicked(0, btnNumber0);
        btnNumberClicked(1, btnNumber1);
        btnNumberClicked(2, btnNumber2);
        btnNumberClicked(3, btnNumber3);
        btnNumberClicked(4, btnNumber4);
        btnNumberClicked(5, btnNumber5);
        btnNumberClicked(6, btnNumber6);
        btnNumberClicked(7, btnNumber7);
        btnNumberClicked(8, btnNumber8);
        btnNumberClicked(9, btnNumber9);
        etAddPriceDialog.setText(valueString);
        etAddPriceDialog.setSelection(0, valueString.length());

        //xóa hết
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = 0;
                etAddPriceDialog.setText("" + value);
            }
        });

        //xóa 1 ký tự
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = (value - value % 10)/10;
                etAddPriceDialog.setText("" + value);
            }
        });

        //tăng 1 đơn vị
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + 1;
                etAddPriceDialog.setText("" + value);
            }
        });

        //giảm một đơn vị
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value - 1;
                etAddPriceDialog.setText("" + value);
            }
        });

        //xong
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dở...
//                String a = etAddPriceDialog.getText().toString();
//                Long value = Long.parseLong(a);
//                Toast.makeText(getContext(), ""+value, Toast.LENGTH_SHORT).show();
//                listener2.passValueToDialog(etAddPriceDialog.setText();
                listener.passValueToActivity(etAddPriceDialog.getText().toString());  //get giá trị từ dialog
                dismiss();
            }
        });

        //đóng dialog
        ibtPriceDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dở...
                dismiss();
            }
        });

        etAddPriceDialog.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                etAddPriceDialog.removeTextChangedListener(this);
                valueString = decimalFormat.format(Long.parseLong(s.toString()));
                valueString = valueString.replace(",", ".");
                etAddPriceDialog.setText(valueString);
                etAddPriceDialog.addTextChangedListener(this);
            }
        });
    }

    private void btnNumberClicked(final int number, Button btnNumber) {
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

    //??
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (IPassValueToActivity) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public String getValue() {
        return etAddPriceDialog.getText().toString();
    }


    //interface - truyền giá trị từ dialog sang activity
    public interface IPassValueToActivity{
        void passValueToActivity(String value);
    }
}
