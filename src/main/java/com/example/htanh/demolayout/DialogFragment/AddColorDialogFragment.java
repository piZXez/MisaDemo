package com.example.htanh.demolayout.DialogFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.htanh.demolayout.Adapter.FoodBackgroundColorAdapter;
import com.example.htanh.demolayout.Data.FoodBackgroundColorData;
import com.example.htanh.demolayout.R;

/***
 * Created by htanh on 3/1/2018.
 */

public class AddColorDialogFragment extends android.support.v4.app.DialogFragment implements FoodBackgroundColorAdapter.IPickColor {

    TextView tvAddColorDialogCancel;
    FoodBackgroundColorAdapter adapter;
    FoodBackgroundColorData data = new FoodBackgroundColorData();
    RecyclerView recyclerViewColorList;

    //    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Dialog dialog=super.onCreateDialog(savedInstanceState);
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        return dialog;
//
//    }
    FoodBackgroundColorAdapter.IPickColor pickColor;

    public void setPickColor(FoodBackgroundColorAdapter.IPickColor pickColor) {
        this.pickColor = pickColor;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_icon_dialog_fragment, container, false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4, LinearLayoutManager.VERTICAL, false);
        tvAddColorDialogCancel = view.findViewById(R.id.text_add_icon_dialog_cancel);
        recyclerViewColorList = view.findViewById(R.id.recycler_view_icon_list);
        adapter = new FoodBackgroundColorAdapter(data.getData(), getActivity(), this, getContext(), this);     //4th parameter -> chọn màu
        recyclerViewColorList.setAdapter(adapter);
        recyclerViewColorList.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvAddColorDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        super.onResume();
    }

    @Override
    public void pickColor(String color) {
        if (this.pickColor != null) {
            this.pickColor.pickColor(color);
        }
    }
}
