package com.example.htanh.demolayout.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.htanh.demolayout.Activity.ChooseMenuActivity;
import com.example.htanh.demolayout.Class.Product;
import com.example.htanh.demolayout.R;
import com.example.htanh.demolayout.DialogFragment.SellingQuantityDialogFragment;

import java.text.DecimalFormat;
import java.util.List;

/***
 * Created by htanh on 3/13/2018.
 */

public class FoodSellingAdapter extends RecyclerView.Adapter<FoodSellingAdapter.ViewHolder> {

    private IGetPaySum getPaySum;
    private List<IGetPaySum> getPaySumsList;

    private ChooseMenuActivity activity;
    private List<Product> foodList;
    private Context context;
    private long l = 0;
    private String value = "";


    public FoodSellingAdapter(List<Product> foodList, Context context, ChooseMenuActivity activity) {
        this.foodList = foodList;
        this.context = context;
        this.activity = activity;
    }

    public FoodSellingAdapter(List<Product> foodList) {
        this.foodList = foodList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_selling, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.quantity = foodList.get(position).getQuantity();
        if (foodList.get(position).getFoodPrice() != null) {
            holder.price = foodList.get(position).getPrice();
        }
        holder.paySum = foodList.get(position).getPaySum();
        holder.quantitySelector(foodList.get(position).getImgFoodMenu(), context.getResources().getDrawable(R.drawable.selling_food_selected_bg));
        holder.tvFoodName.setText(foodList.get(position).getFoodName());
        holder.tvFoodPrice.setText(foodList.get(position).getFoodPrice());
        holder.gradientDrawable.setColor(Color.parseColor(foodList.get(position).getColor()));

        holder.tvFoodQuantity.setOnClickListener(new View.OnClickListener() {           //show dialog edit số lượng
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fm = activity.getSupportFragmentManager();
                //khởi tạo dialog số lượng
                SellingQuantityDialogFragment sellingQuantityDialogFragment = SellingQuantityDialogFragment.newInstance(holder.tvFoodQuantity.getText().toString());
                sellingQuantityDialogFragment.check = 1;
                sellingQuantityDialogFragment.setQuantityValue(new SellingQuantityDialogFragment.IPassQuantityValue() {
                    @Override
                    public void passQuantityValue(String valuePrint, String valueString) {                //truyền giá trị số lượng từ dialog về

                        holder.quantity = Long.parseLong(valueString);
                        updateFoodList(holder, position, getPaySum);
                    }
                });
                sellingQuantityDialogFragment.show(fm, "1");
            }
        });

        holder.rlFoodItem.setOnClickListener(new View.OnClickListener() {               //tăng số lượng món đặt lên 1 đơn vị
            @Override
            public void onClick(View v) {
                holder.quantity = foodList.get(position).getQuantity() + 1;
                updateFoodList(holder, position, getPaySum);
//                Toast.makeText(context, "" + foodList.get(position).getPaySum(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.ivIncreaseQuantity.setOnClickListener(new View.OnClickListener() {      //tăng số lượng món đặt lên 1 đơn vị
            @Override
            public void onClick(View v) {
                holder.quantity = foodList.get(position).getQuantity() + 1;
                updateFoodList(holder, position, getPaySum);

//                Toast.makeText(context, "" + foodList.get(position).getPaySum(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.ivDecreaseQuantity.setOnClickListener(new View.OnClickListener() {      //giảm số lượng món đặt đi 1 đơn vị
            @Override
            public void onClick(View v) {
                holder.quantity = foodList.get(position).getQuantity() - 1;
                updateFoodList(holder, position, getPaySum);

//                Toast.makeText(context, "" + foodList.get(position).getPaySum(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.ivFoodMenu.setOnClickListener(new View.OnClickListener() {              //đặt món ở lần click đầu hoặc hủy đặt món
            @Override
            public void onClick(View v) {
                holder.quantity = 0;
                updateFoodList(holder, position, getPaySum);
//                Toast.makeText(context, "" + foodList.get(position).getPaySum(), Toast.LENGTH_SHORT).show();

            }
        });

        holder.tvFoodQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                holder.tvFoodQuantity.removeTextChangedListener(this);
                holder.quantityPrint = holder.decimalFormat.format(Long.parseLong(s.toString().replace(".", "")));
                holder.quantityPrint = holder.quantityPrint.replace(",", ".");
                holder.tvFoodQuantity.setText(holder.quantityPrint);
                holder.tvFoodQuantity.addTextChangedListener(this);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void getPaySum(IGetPaySum getPaySum) {                                 //DỞ.....................
        this.getPaySum = getPaySum;
    }

    //update list sau khi click làm thay đổi quantity
    private void updateFoodList(ViewHolder holder, int position, IGetPaySum getPaySum) {
        foodList.get(position).setQuantity(holder.quantity);
        String quantityString = foodList.get(position).getDecimalFormat().format(holder.quantity);
        quantityString = quantityString.replace(",", ".");
        foodList.get(position).setQuantityString(quantityString);
        holder.quantitySelector(foodList.get(position).getImgFoodMenu(), context.getResources().getDrawable(R.drawable.selling_food_selected_bg));
        foodList.get(position).setPaySum(holder.paySum);
        getPaySum.getPaySum(foodList.get(position).getPaySum());
    }

    public List<Product> getFoodList() {
        return foodList;
    }

//    @Override
//    public void passQuantityValue(String value, boolean isClick) {
//        this.value = value;
//    }

//    @Override
//    public void passQuantityValue(String value, boolean isClick) {
//
//    }

//    void setQuantity(String value) {
//    }

//    @Override
//    public void passQuantityValue(String value) {
//        this.value = value;
//    }

    class ViewHolder extends RecyclerView.ViewHolder {


        RelativeLayout rlFoodItem;
        LinearLayout llEditQuantity;
        GradientDrawable gradientDrawable;
        ImageView ivFoodMenu;
        ImageView ivDecreaseQuantity;
        ImageView ivIncreaseQuantity;
        TextView tvFoodQuantity;
        TextView tvFoodName;
        TextView tvFoodPrice;
        long quantity;
        long price;
        long paySum;
        String quantityPrint;
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        ViewHolder(View itemView) {
            super(itemView);

//            quantity = 0;
            llEditQuantity = itemView.findViewById(R.id.linear_layout_edit_quantity);
            rlFoodItem = itemView.findViewById(R.id.food_selling_item_layout);
            ivFoodMenu = itemView.findViewById(R.id.image_food_selling);
            ivDecreaseQuantity = itemView.findViewById(R.id.image_decrease_quantity);
            ivIncreaseQuantity = itemView.findViewById(R.id.image_increase_quantity);
            tvFoodQuantity = itemView.findViewById(R.id.text_food_selling_quantity);
            tvFoodName = itemView.findViewById(R.id.text_food_selling_name);
            tvFoodPrice = itemView.findViewById(R.id.text_selling_price);
            gradientDrawable = (GradientDrawable) ivFoodMenu.getResources().getDrawable(R.drawable.image_food_menu_bg);
        }

        @SuppressLint({"SetTextI18n", "ResourceAsColor"})
        void quantitySelector(int imgRes, Drawable drawable) {           //thay đổi icon món ăn thành dấu tích khi được chọn
//            foodList.get(position).setQuantity(quantity);
            if (quantity == 0) {
                //rlFoodItem.setB.. dở..
                rlFoodItem.setBackgroundResource(0);
                llEditQuantity.setVisibility(View.GONE);
                ivFoodMenu.setBackground(gradientDrawable);
                ivFoodMenu.setImageResource(imgRes);
            } else {
                llEditQuantity.setVisibility(View.VISIBLE);
                rlFoodItem.setBackgroundResource(R.drawable.item_food_selling_bg);
                tvFoodQuantity.setText("" + quantity);
                ivFoodMenu.setImageResource(0);
                ivFoodMenu.setBackground(drawable);
//                Toast.makeText(activity, "" + paySum, Toast.LENGTH_SHORT).show();
            }
            paySum = quantity * price;
        }
    }

    public interface IGetPaySum {
        void getPaySum(long paySum);
    }
}
