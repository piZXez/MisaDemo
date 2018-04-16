package com.example.htanh.demolayout.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.htanh.demolayout.Class.FoodBackgroundColor;
import com.example.htanh.demolayout.R;

import java.util.List;

/**
 *
 * Created by htanh on 3/1/2018.
 */

public class FoodBackgroundColorAdapter extends RecyclerView.Adapter<FoodBackgroundColorAdapter.ViewHolder> {

    private List<FoodBackgroundColor> colorList;
    private ViewGroup parent;
    private Activity activity;
    private Context context;
    private DialogFragment dialogFragment;
    private IPickColor pickColor;

//    SharedPreferences sharedPreferences = activity.getBaseContext().getSharedPreferences("Color", Context.MODE_PRIVATE);   //LỖI TÙM LUM
//    private SharedPreferences.Editor editor = context.getSharedPreferences("Color", context.MODE_PRIVATE).edit();

    public FoodBackgroundColorAdapter(List<FoodBackgroundColor> colorList, Activity activity, DialogFragment dialogFragment, Context context, IPickColor pickColor) {
        this.colorList = colorList;
        this.context = context;
        this.activity = activity;
        this.dialogFragment = dialogFragment;
        this.pickColor = pickColor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_background_color, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

            GradientDrawable gradientDrawable = (GradientDrawable) parent.getResources().getDrawable(R.drawable.food_color_bg);    //khởi tạo gradient drawable để lưu background color drawable
            gradientDrawable.setColor(Color.parseColor(colorList.get(position).getColor()));            //set color cho drawable theo item trong data
            holder.tvFoodBackgroundColor.setBackground(gradientDrawable);                           //set holder background theo drawable
            holder.tvFoodBackgroundColor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pickColor.pickColor(colorList.get(position).getColor());    //get màu
                    dialogFragment.dismiss();
//                    Toast.makeText(activity, colorList.get(position).getColor(), Toast.LENGTH_SHORT).show();
                }
            });
//        }
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public interface IPickColor{
        void pickColor(String color);    //interface đổi màu
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFoodBackgroundColor;
        ViewHolder(View itemView) {
            super(itemView);
            tvFoodBackgroundColor = itemView.findViewById(R.id.image_item_food_background_color);
        }
    }
}