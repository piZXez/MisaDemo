package com.example.htanh.demolayout.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.htanh.demolayout.Activity.EditMenuActivity;
import com.example.htanh.demolayout.Class.Product;
import com.example.htanh.demolayout.R;

import java.util.List;

/**
 *
 * Created by htanh on 2/1/2018.
 */

public class FoodMenuAdapter extends BaseAdapter {

    private List<Product> foodsList;
    //    private FoodMenuData data = new FoodMenuData();
    private Activity activity;

    public FoodMenuAdapter(Activity activity, List<Product> foodsList) {
        this.activity = activity;
        this.foodsList = foodsList;
    }

    @Override
    public int getCount() {
        return foodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        RelativeLayout rlFoodItem;
        GradientDrawable gradientDrawable;
        ImageView ivFoodMenu;
        ImageView ivFoodInfo;
        TextView tvFoodName;
        TextView tvFoodPrice;
        TextView tvFoodState;
    }

    public List<Product> getData() {
        return foodsList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            convertView = inflater.inflate(R.layout.item_food_menu, parent, false);

            holder = new ViewHolder();

            holder.rlFoodItem = convertView.findViewById(R.id.food_item_layout);
            holder.ivFoodMenu = convertView.findViewById(R.id.image_food_menu);
            holder.ivFoodInfo = convertView.findViewById(R.id.image_food_info);
            holder.tvFoodName = convertView.findViewById(R.id.text_food_name);
            holder.tvFoodPrice = convertView.findViewById(R.id.text_food_price);
            holder.tvFoodState = convertView.findViewById(R.id.text_food_state);
            holder.gradientDrawable = (GradientDrawable) holder.ivFoodMenu.getResources().getDrawable(R.drawable.image_food_menu_bg);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvFoodName.setText(foodsList.get(position).getFoodName());
        holder.tvFoodPrice.setText(foodsList.get(position).getFoodPrice());
        holder.gradientDrawable.setColor(Color.parseColor(foodsList.get(position).getColor()));
        holder.ivFoodMenu.setBackground(holder.gradientDrawable);
//        GradientDrawable gradientDrawable = (GradientDrawable) holder.ivFoodMenu.getResources().getDrawable(R.drawable.image_food_menu_bg);
//        gradientDrawable.setColor(Color.parseColor(foodsList.get(position).getColor()));
//        holder.ivFoodMenu.setBackground(gradientDrawable);

        if (foodsList.get(position).isStateOutOfStock()){
            holder.tvFoodState.setVisibility(View.VISIBLE);
        } else {
            holder.tvFoodState.setVisibility(View.GONE);
        }

        try {
            holder.ivFoodMenu.setImageResource(foodsList.get(position).getImgFoodMenu());
        } catch (Exception e) {
            e.printStackTrace();
        }


        holder.rlFoodItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //truyền food info và index sang Edit activity
                Intent intent = new Intent(activity, EditMenuActivity.class);
                intent.putExtra("FoodInfo", foodsList.get(position));
                intent.putExtra("FoodIndex", position);
                activity.startActivityForResult(intent, 1);
            }
        });

        return convertView;
    }
}
