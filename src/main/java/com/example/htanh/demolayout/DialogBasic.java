package com.example.htanh.demolayout;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by htanh on 1/23/2018.
 */

public class DialogBasic extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Cài đặt các thuộc tính
        builder.setTitle("Xác nhận !");
        builder.setMessage("Bạn thực sự muốn thoát khỏi chương trình ?");
        // Cài đặt button Cancel- Hiển thị Toast
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        // Cài đặt button Yes Dismiss ẩn Dialog
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        return builder.create();
    }
}
