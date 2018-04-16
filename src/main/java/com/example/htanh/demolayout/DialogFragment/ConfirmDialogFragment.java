package com.example.htanh.demolayout.DialogFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.htanh.demolayout.R;

/***
 * Created by htanh on 3/8/2018.
 */

public class ConfirmDialogFragment extends DialogFragment {

    TextView tvConfirmDialog;
    ImageButton ibtConfirmDialogClose;
    Button btnConfirmDialogAccept, btnConfirmDialogDecline;
    IActionAccept actionAccept;
    String foodName = "";
    String caller = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.delete_confirm_dialog_fragment, container, false);


        tvConfirmDialog = view.findViewById(R.id.text_confirmdialog);
        ibtConfirmDialogClose = view.findViewById(R.id.button_confirmdialog_close);
        btnConfirmDialogAccept = view.findViewById(R.id.button_confirmdialog_accept);
        btnConfirmDialogDecline = view.findViewById(R.id.button_confirmdialog_decline);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switch (caller){
            case "EditMenuActivity":
                foodName = getArguments().getString("FoodName");
                tvConfirmDialog.setText(Html.fromHtml("Bạn có chắc chắn muốn xóa món '" + foodName + "' không?"));
                break;
            case "SellAdapter":
                tvConfirmDialog.setText("Bạn có chắc chắn muốn hủy các món đã chọn không?");
                break;

        }

        ibtConfirmDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnConfirmDialogDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionAccept.actionAccept(false);
                dismiss();
            }
        });

        btnConfirmDialogAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionAccept.actionAccept(true);
                dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            actionAccept = (IActionAccept) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public void getCaller(String caller) {
        this.caller = caller;
    }

    //bắt sự kiện khi ấn accept
    public interface IActionAccept {
        void actionAccept(boolean isAccept);
    }

    public void getActionAccept(IActionAccept actionAccept){
        this.actionAccept = actionAccept;
    }

    public interface IGetCaller {
        void getCaller(String caller);
    }
}
