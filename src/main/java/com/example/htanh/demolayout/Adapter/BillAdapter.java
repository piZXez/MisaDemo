package com.example.htanh.demolayout.Adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.htanh.demolayout.Class.Product;
import com.example.htanh.demolayout.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/***
 * Created by htanh on 3/29/2018.
 */

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {

    private List<Product>  productList = new ArrayList<>();
    private DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

    public BillAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.tvName.setText(productList.get(position).getFoodName());
//        holder.tvQuantity.setText("" + productList.get(position).getQuantity());
        holder.tvPrice.setText(productList.get(position).getFoodPrice());
        String paySum = decimalFormat.format(productList.get(position).getPaySum());
        String quantity = decimalFormat.format(productList.get(position).getQuantity());
        quantity = quantity.replace(",", ".");
        paySum = paySum.replace(",", ".");
        holder.tvQuantity.setText(quantity);
        holder.tvPaySum.setText(paySum);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvQuantity;
        TextView tvPrice;
        TextView tvPaySum;

        ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.text_bill_item_name);
            tvQuantity = itemView.findViewById(R.id.text_bill_item_quantity);
            tvPrice = itemView.findViewById(R.id.text_bill_item_price);
            tvPaySum = itemView.findViewById(R.id.text_bill_item_paysum);
        }

    }
}
