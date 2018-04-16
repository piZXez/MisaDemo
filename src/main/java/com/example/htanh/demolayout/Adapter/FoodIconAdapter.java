package com.example.htanh.demolayout.Adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.htanh.demolayout.Class.FoodIcon;
import com.example.htanh.demolayout.DialogFragment.AddIconDialogFragment;
import com.example.htanh.demolayout.R;

import java.util.List;

/**
 *
 * Created by htanh on 3/9/2018.
 */

public class FoodIconAdapter extends RecyclerView.Adapter<FoodIconAdapter.ViewHolder> {

    private List<FoodIcon> iconList;
    private AddIconDialogFragment addIconDialogFragment;
    private IPickIcon pickIcon;

    public FoodIconAdapter(List<FoodIcon> iconList, AddIconDialogFragment addIconDialogFragment, IPickIcon pickIcon) {
        this.iconList = iconList;
        this.addIconDialogFragment = addIconDialogFragment;
        this.pickIcon = pickIcon;
    }

    @Override
    public FoodIconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_icon, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FoodIconAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.ivFoodIcon.setImageResource(iconList.get(position).getIcon());
        holder.ivFoodIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickIcon.pickIcon(iconList.get(position).getIcon());
                addIconDialogFragment.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return iconList.size();
    }


    public interface IPickIcon{
        void pickIcon(int icon);    //interface chọn icon
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivFoodIcon;
        ViewHolder(View itemView) {
            super(itemView);
            ivFoodIcon = itemView.findViewById(R.id.image_item_food_icon);
        }
    }
}
