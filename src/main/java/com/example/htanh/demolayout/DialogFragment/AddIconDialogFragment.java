package com.example.htanh.demolayout.DialogFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.htanh.demolayout.Adapter.FoodIconAdapter;
import com.example.htanh.demolayout.Data.FoodIconData;
import com.example.htanh.demolayout.R;

/***
 * Created by htanh on 3/9/2018.
 */

public class AddIconDialogFragment extends DialogFragment implements FoodIconAdapter.IPickIcon {

    TextView tvAddIconDialogCancel;
    RecyclerView recyclerViewIconList;
    FoodIconData data = new FoodIconData();
    FoodIconAdapter adapter;
    FoodIconAdapter.IPickIcon pickIcon;

    public void setPickIcon(FoodIconAdapter.IPickIcon pickIcon) {
        this.pickIcon = pickIcon;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_icon_dialog_fragment, container, false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5, LinearLayoutManager.VERTICAL, false);
        tvAddIconDialogCancel = view.findViewById(R.id.text_add_icon_dialog_cancel);
        recyclerViewIconList = view.findViewById(R.id.recycler_view_icon_list);

        adapter = new FoodIconAdapter(data.getData(), this, this);
        recyclerViewIconList.setAdapter(adapter);
        recyclerViewIconList.setLayoutManager(gridLayoutManager);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvAddIconDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    public void pickIcon(int icon) {
        if (this.pickIcon != null) {
            this.pickIcon.pickIcon(icon);
        }
    }
}
