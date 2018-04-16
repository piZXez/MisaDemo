package com.example.htanh.demolayout.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.htanh.demolayout.Adapter.FoodMenuAdapter;
import com.example.htanh.demolayout.Data.FoodMenuData;
import com.example.htanh.demolayout.Class.Product;
import com.example.htanh.demolayout.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodMenuFragment extends Fragment {

    FoodMenuData foodMenuData = new FoodMenuData();
    List<Product> foodList = new ArrayList<>();
    FoodMenuAdapter adapter;
    ListView foodMenu;

    public FoodMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_food_menu, container, false);
        // Inflate the layout for this fragment

        if (foodList.isEmpty()) {
            foodList = foodMenuData.setData();
        }
        adapter = new FoodMenuAdapter(getActivity(), foodList);
        foodMenu = view.findViewById(R.id.list_food_menu);
        foodMenu.setAdapter(adapter);

        return view;
    }

    public void addData(Product product) {
        adapter.getData().add(product);
        adapter.notifyDataSetChanged();
    }

    public void editData(int position, Product product) {
        //dở..
        adapter.getData().remove(position);
        adapter.getData().add(position, product);
        adapter.notifyDataSetChanged();
    }

    public void deleteData(int position) {
        adapter.getData().remove(position);
        adapter.notifyDataSetChanged();
    }
}
