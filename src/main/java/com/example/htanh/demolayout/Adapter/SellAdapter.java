package com.example.htanh.demolayout.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.htanh.demolayout.Activity.ChooseMenuActivity;
import com.example.htanh.demolayout.Activity.PayMentActivity;
import com.example.htanh.demolayout.Class.Product;
import com.example.htanh.demolayout.Class.SellProduct;
import com.example.htanh.demolayout.DialogFragment.ConfirmDialogFragment;
import com.example.htanh.demolayout.Fragment.SellingFragment;
import com.example.htanh.demolayout.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/***
 * Created by htanh on 4/2/2018.
 */

public class SellAdapter extends RecyclerView.Adapter<SellAdapter.ViewHolder>{
    public SellAdapter(List<SellProduct> sellList) {
        this.sellList = sellList;
    }

    private List<SellProduct> sellList = new ArrayList<>();
    private SellingFragment sellingFragment;

    public SellAdapter(SellingFragment sellingFragment, List<SellProduct> sellList) {
        this.sellingFragment = sellingFragment;
        this.sellList = sellList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvSellItemTable.setText(sellList.get(position).getTableQuantity());
        holder.tvSellItemCustomer.setText(sellList.get(position).getCustomerQuantity());
        holder.tvSellItemPaySum.setText(sellList.get(position).getPaySum());

        if (sellList.get(position).getCustomerQuantity().equals("")){
            holder.ivCustomerIcon.setVisibility(View.GONE);
        } else {
            holder.ivCustomerIcon.setVisibility(View.VISIBLE);
        }

        final List<Product> productList;
        productList = sellList.get(position).getProductList();
        StringBuilder productListStringBuilder = new StringBuilder();
        StringBuilder nameStringBuilder = new StringBuilder();
        StringBuilder quantityStringBuilder = new StringBuilder();
        for (int i = 0; i < productList.size(); i++) {
            StringBuilder s = new StringBuilder();
            s.append("<small><font color='teal'>(").append(productList.get(i).getQuantityString()).append(")</font></small>");
            nameStringBuilder.replace(0, nameStringBuilder.length(), productList.get(i).getFoodName());                                                          //string tên món
            //string số lượng
            //xóa dấu phẩy cuối
            if (i < productList.size() - 1) {
                quantityStringBuilder.replace(0, quantityStringBuilder.length(), String.valueOf(s.append(", ")));
            } else {
                quantityStringBuilder.replace(0, quantityStringBuilder.length(), String.valueOf(s));
            }
            productListStringBuilder.append(nameStringBuilder).append(" ").append(quantityStringBuilder);
        }

        holder.tvSellItemList.setText(Html.fromHtml(String.valueOf(productListStringBuilder)));

        holder.tvSellItemTable.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                sellList.remove(position);
                notifyDataSetChanged();
                if (sellList.isEmpty()) {
                    sellingFragment.getTvHintSellingFragment().setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        holder.llSellListDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = sellingFragment.getActivity().getSupportFragmentManager();
                ConfirmDialogFragment confirmDialogFragment = new ConfirmDialogFragment();
                confirmDialogFragment.getCaller("SellAdapter");
                confirmDialogFragment.show(fm, "5");
                confirmDialogFragment.getActionAccept(new ConfirmDialogFragment.IActionAccept() {
                    @Override
                    public void actionAccept(boolean isAccept) {
                        if (isAccept){
                            sellList.remove(position);
                            notifyDataSetChanged();
                            if (sellList.isEmpty()) {
                                sellingFragment.getTvHintSellingFragment().setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
            }
        });

        holder.llSellListPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sellingFragment.getContext(), PayMentActivity.class);
                intent.putExtra("SellProduct", sellList.get(position));
//                intent.putExtra("BillList", (Serializable) productList);
//                intent.putExtra("PaySum", holder.tvSellItemPaySum.getText().toString());
//                intent.putExtra("TableQuantity", holder.tvSellItemTable.getText().toString());
                intent.putExtra("Caller", "SellAdapter");
                sellingFragment.getActivity().startActivityForResult(intent, 2);
            }
        });

        holder.rlSellListInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sellingFragment.getContext(), ChooseMenuActivity.class);
                intent.putExtra("SellList", sellList.get(position));            //dở........
//                intent.putExtra("PaySum", holder.tvSellItemPaySum.getText().toString());
//                intent.putExtra("TableQuantity", holder.tvSellItemTable.getText().toString());
                intent.putExtra("SellIndex", position);
                sellingFragment.getActivity().startActivityForResult(intent, 2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sellList.size();
    }

    public List<SellProduct> getData() {
        return sellList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSellItemTable, tvSellItemCustomer;
        TextView tvSellItemList, tvSellItemPaySum;
        LinearLayout llSellListDelete, llSellListPay;
        ImageView ivCustomerIcon;
        RelativeLayout rlSellListInfo;

        ViewHolder(View itemView) {
            super(itemView);

            tvSellItemTable = itemView.findViewById(R.id.text_sell_item_table);
            tvSellItemCustomer = itemView.findViewById(R.id.text_sell_item_customer);
            tvSellItemList = itemView.findViewById(R.id.text_sell_item_list);
            tvSellItemPaySum = itemView.findViewById(R.id.text_sell_item_pay_sum);
            ivCustomerIcon = itemView.findViewById(R.id.image_customer_icon);
            llSellListDelete = itemView.findViewById(R.id.button_sell_list_delete);
            llSellListPay = itemView.findViewById(R.id.button_sell_list_pay);
            rlSellListInfo = itemView.findViewById(R.id.relative_layout_sell_list_info);
        }
    }
}
