package com.example.htanh.demolayout.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.htanh.demolayout.Activity.ChooseMenuActivity;
import com.example.htanh.demolayout.Adapter.SellAdapter;
import com.example.htanh.demolayout.Class.Product;
import com.example.htanh.demolayout.Class.SellProduct;
import com.example.htanh.demolayout.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellingFragment extends Fragment {

    TextView tvHintSellingFragment;
    SellAdapter adapter;
    List<SellProduct> sellList = new ArrayList<>();
    String tableQuantity;
    
    RecyclerView rvSellList;

    public SellingFragment() {
        // Required empty public constructor
    }

    public static SellingFragment newInstance(String tableQuantity) {

        Bundle args = new Bundle();

        SellingFragment fragment = new SellingFragment();
        args.putString("TableQuantity", tableQuantity);
        fragment.setArguments(args);
        fragment.tableQuantity = fragment.getArguments().getString("TableQuantity");
        fragment.sellList.add(new SellProduct(fragment.tableQuantity));
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_selling, container, false);
        // Inflate the layout for this fragment

        rvSellList = view.findViewById(R.id.recycler_view_sell_list);
        tvHintSellingFragment = view.findViewById(R.id.text_hint_selling_fragment);

//        if (sellList.isEmpty()){
//            tvHintSellingFragment.setVisibility(View.VISIBLE);
//        } else {
//            tvHintSellingFragment.setVisibility(View.GONE);
//        }

        if (!sellList.isEmpty()){
            tvHintSellingFragment.setVisibility(View.GONE);
        }

        tvHintSellingFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChooseMenuActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        adapter = new SellAdapter(this, sellList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        rvSellList.setAdapter(adapter);
        rvSellList.setLayoutManager(linearLayoutManager);

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    public void addData(List<Product> productList, String tableQuantity, String customerQuantity, String paySum) {
//        rvSellList.setVisibility(View.VISIBLE);
        tvHintSellingFragment.setVisibility(View.GONE);
        adapter.getData().add(new SellProduct(productList, tableQuantity, customerQuantity, paySum));
        adapter.notifyDataSetChanged();
    }

    public void addData(SellProduct sellProduct){
        tvHintSellingFragment.setVisibility(View.GONE);
        adapter.getData().add(sellProduct);
        adapter.notifyDataSetChanged();
    }

    public void editData(int position, SellProduct sellProduct){
        adapter.getData().remove(position);
        adapter.getData().add(position, sellProduct);
        adapter.notifyDataSetChanged();
    }

    public TextView getTvHintSellingFragment(){
        return tvHintSellingFragment;
    }
}