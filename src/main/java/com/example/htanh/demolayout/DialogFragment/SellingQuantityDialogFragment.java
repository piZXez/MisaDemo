package com.example.htanh.demolayout.DialogFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.htanh.demolayout.R;

import java.text.DecimalFormat;

/***
 * Created by htanh on 3/13/2018.
 */

public class SellingQuantityDialogFragment extends DialogFragment {

    IPassQuantityValue passQuantityValue;

    TextView tvQuantityDialogName;
    ImageButton ibtSellingDialogClose;
    EditText etSellingDialog;
    ImageButton btnDelete;
    Button btnClear, btnDecrease, btnIncrease;
    Button btnDone;
    Button btnNumber0, btnNumber1, btnNumber2, btnNumber3, btnNumber4, btnNumber5, btnNumber6, btnNumber7, btnNumber8, btnNumber9;
    String valueString = "";
    String valuePrint;
    long value = 0;
    public int check;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

    public SellingQuantityDialogFragment() {
    }

    public static SellingQuantityDialogFragment newInstance(String quantity) {      //???

        Bundle args = new Bundle();

        final SellingQuantityDialogFragment fragment = new SellingQuantityDialogFragment();
        args.putString("Quantity", quantity);
        fragment.setArguments(args);
        fragment.valueString = fragment.getArguments().getString("Quantity").replace(".", "");
        fragment.value = Long.parseLong(fragment.getArguments().getString("Quantity").replace(".", ""));
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_quantity_dialog_fragment, container, false);

        tvQuantityDialogName = view.findViewById(R.id.text_quantity_dialog_name);
        ibtSellingDialogClose = view.findViewById(R.id.button_quantity_dialog_close);
        etSellingDialog = view.findViewById(R.id.edit_text_dialog);
        btnClear = view.findViewById(R.id.dialog_clear_btn);
        btnDecrease = view.findViewById(R.id.dialog_decrease_btn);
        btnIncrease = view.findViewById(R.id.dialog_increase_btn);
        btnDelete = view.findViewById(R.id.dialog_delete_btn);
        btnDone = view.findViewById(R.id.dialog_done_btn);
        btnNumber0 = view.findViewById(R.id.dialog_0_btn);
        btnNumber1 = view.findViewById(R.id.dialog_1_btn);
        btnNumber2 = view.findViewById(R.id.dialog_2_btn);
        btnNumber3 = view.findViewById(R.id.dialog_3_btn);
        btnNumber4 = view.findViewById(R.id.dialog_4_btn);
        btnNumber5 = view.findViewById(R.id.dialog_5_btn);
        btnNumber6 = view.findViewById(R.id.dialog_6_btn);
        btnNumber7 = view.findViewById(R.id.dialog_7_btn);
        btnNumber8 = view.findViewById(R.id.dialog_8_btn);
        btnNumber9 = view.findViewById(R.id.dialog_9_btn);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        if (getArguments().getString("Quantity") == null) {
//            value = 0;
//        } else {
//        }
//        valueString = getArguments().getString("Quantity");
//        value = Long.parseLong(valueString);

        //        Button btnNumber
        switch (check) {
            case 1:
                tvQuantityDialogName.setText("Nhập số lượng");
                break;
            case 2:
                tvQuantityDialogName.setText("Nhập số bàn");
                break;
            case 3:
                tvQuantityDialogName.setText("Nhập số người");
                break;
        }

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

        valuePrint = decimalFormat.format(value);
        valuePrint = valuePrint.replace(",", ".");
        etSellingDialog.setText(valuePrint);
        etSellingDialog.setSelection(0, valuePrint.length());


        //xóa hết
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = 0;
                valueString = "" + value;
                etSellingDialog.setText(valueString);
//                Toast.makeText(getContext(), "" + value, Toast.LENGTH_SHORT).show();
            }
        });

        //xóa 1 ký tự
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = (value - value % 10) / 10;
                if (etSellingDialog.getText().toString().equals("") || etSellingDialog.getText().toString().length() == 1) {
                    etSellingDialog.setText("");
                } else {
                    valueString = "" + value;
                    etSellingDialog.setText(valueString);
                }
//                Toast.makeText(getContext(), "" + value, Toast.LENGTH_SHORT).show();
            }
        });

        //tăng 1 đơn vị
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSellingDialog.getText().toString().length() < 11 && value < 99999999) {
                    value = value + 1;
                }
                valueString = "" + value;
                etSellingDialog.setText(valueString);
//                Toast.makeText(getContext(), "" + value, Toast.LENGTH_SHORT).show();
            }
        });

        //giảm một đơn vị
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value > 0) {
                    value = value - 1;
                    valueString = "" + value;
                }
                etSellingDialog.setText(valueString);
//                Toast.makeText(getContext(), "" + value, Toast.LENGTH_SHORT).show();
            }
        });

        //xong
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etSellingDialog.getText().toString().equals("")) {
                    passQuantityValue.passQuantityValue(valuePrint, valueString);
                } else {
                    passQuantityValue.passQuantityValue("0", "0");
                }
//                isClick = true;
//                Toast.makeText(getContext(), "" + isClick, Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        //đóng dialog
        ibtSellingDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dở...
                dismiss();
            }
        });

        etSellingDialog.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                etSellingDialog.removeTextChangedListener(this);
                if (!s.toString().equals("")) {
                    valuePrint = decimalFormat.format(Long.parseLong(s.toString()));
                } else {
                    valuePrint = "";
                }
                valuePrint = valuePrint.replace(",", ".");
                etSellingDialog.setText(valuePrint);
//                Toast.makeText(getContext(), "" + valueString.toString(), Toast.LENGTH_SHORT).show();
                etSellingDialog.addTextChangedListener(this);     //dở...
            }
        });

    }


    private void btnNumberClicked(final int number, Button btnNumber) {
        btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSellingDialog.getText().toString().length() < 11 && valueString.length() < 8) {
                    value = value * 10 + number;

//                    valuePrint = decimalFormat.format(value);
//                    valuePrint = valuePrint.replace(",", ".");
                    valueString = "" + value;
                    etSellingDialog.setText(valueString);
//                    Toast.makeText(getContext(), "" + valuePrint, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        try {
//            passQuantityValue = (IPassQuantityValue) context;
//        } catch (ClassCastException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void setQuantityValue(IPassQuantityValue passQuantityValue) {
        this.passQuantityValue = passQuantityValue;
    }

    //    public void setDialogName(String name) {
//        this.tvQuantityDialogName.setText(name);
//    }
    public interface IPassQuantityValue {
        void passQuantityValue(String valuePrint, String valueString);
    }

}
