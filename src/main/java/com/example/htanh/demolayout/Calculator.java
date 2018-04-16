package com.example.htanh.demolayout;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/***
 * Created by htanh on 1/22/2018.
 */

//đang làm dở
public class Calculator extends DialogFragment {

    Button btnCalculator7;
    Button btnCalculator8;
    Button btnCalculator9;
    Button btnCalculatorClear;
    EditText etCalculator;
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.activity_calculator, container);
//    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View customLayout = inflater.inflate(R.layout.activity_calculator, null);

        builder.setView(customLayout);
        builder.setTitle("Giá bán");

        return builder.create();
    }


//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        btnCalculator7     = view.findViewById(R.id.dialog_7_btn);
//        btnCalculator8     = view.findViewById(R.id.dialog_8_btn);
//        btnCalculator9     = view.findViewById(R.id.dialog_9_btn);
//        btnCalculatorClear = view.findViewById(R.id.dialog_clear_btn);
//        etCalculator       = view.findViewById(R.id.dialog_edt);
//
//
//        btnCalculator7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                long currentNumber = new Long(etCalculator.getText().toString());
//                currentNumber = (currentNumber*10 + 7);
//                etCalculator.setText(Long.toString(currentNumber));
//            }
//        });
//
//        btnCalculator8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                etCalculator.setText(etCalculator.getText().toString() + "8");
//            }
//        });
//        btnCalculator9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                etCalculator.setText(etCalculator.getText().toString() + "9");
//            }
//        });
//    }
}

