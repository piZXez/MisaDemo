package com.example.htanh.demolayout.Data;

import com.example.htanh.demolayout.Class.Product;
import com.example.htanh.demolayout.R;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by htanh on 2/2/2018.
 */

public class FoodMenuData {

    private List<Product> foodList = new ArrayList<>();

    public List<Product> setData() {
        foodList.add(new Product(R.drawable.ic_default, "Bánh giò nóng có giò", "15.000", "Cái", "#227bb6", false, 0));
        foodList.add(new Product(R.drawable.ic_default, "Bánh giò nóng không giò", "10.000", "Cái", "#227bb6", false, 0));
        foodList.add(new Product(R.drawable.ic_add, "Sữa đậu nành", "6.000", "Cốc", "#1f30a3", true, 0));
        foodList.add(new Product(R.drawable.ic_back_white, "Sữa ngô (chai)", "12.000", "Chai", "#782005", false, 0));
        foodList.add(new Product(R.drawable.ic_back_keyboard, "Sữa ngô (cốc)", "8.000", "Cốc", "#782005", false, 0));
        foodList.add(new Product(R.drawable.ic_default, "Xôi đỗ đen ruốc", "10.000", "Gói", "#1f30a3", true, 0));
        foodList.add(new Product(R.drawable.ic_chevron_right, "Xôi đỗ đen vừng", "7.000", "Gói", "#1f30a3", false, 0));
        foodList.add(new Product(R.drawable.ic_checked, "Xôi đỗ xanh ruốc", "10.000", "Gói", "#5bc6ab", false, 0));
        foodList.add(new Product(R.drawable.ic_menu_send, "Xôi đỗ xang vừng", "7.000", "Gói", "#5bc6ab", false, 0));
        foodList.add(new Product(R.drawable.ic_checked, "Xôi gấc ruốc", "10.000", "Gói", "#e19549", false, 0));
        foodList.add(new Product(R.drawable.ic_default, "Xôi gấc vừng", "7.000", "Gói", "#5bc6ab", false, 0));
        foodList.add(new Product(R.drawable.ic_default, "Xôi lạc ruốc", "10.000", "Gói", "#782005", false, 0));
        foodList.add(new Product(R.drawable.ic_chevron_right, "Xôi lạc vừng", "10.000", "Gói", "#782005", false, 0));
        foodList.add(new Product(R.drawable.ic_back_keyboard, "Xôi ngô", "10.000", "Gói", "#5bc6ab", false, 0));
        foodList.add(new Product(R.drawable.ic_back_keyboard, "Xôi xéo", "10.000", "Gói", "#5bc6ab", false, 0));

//        foodList.add(new Product(R.drawable.ic_default, "món 3", "10000"));
//        foodList.add(new Product(R.drawable.ic_default, "món 3", "10000"));
//        foodList.add(new Product(R.drawable.ic_default, "món 3", "10000"));
//        foodList.add(new Product(R.drawable.ic_default, "món 3", "10000"));
//        foodList.add(new Product(R.drawable.ic_default, "món 3", "10000"));
//        foodList.add(new Product(R.drawable.ic_default, "món 3", "10000"));
//        foodList.add(new Product(R.drawable.ic_default, "món 3", "10000"));
//        foodList.add(new Product(R.drawable.ic_default, "món 3", "10000"));
//        foodList.add(new Product(R.drawable.ic_default, "món 3", "10000"));
//        foodList.add(new Product(R.drawable.ic_default, "món 3", "10000"));
//        foodList.add(new Product(R.drawable.ic_default, "món 3", "10000"));
//        foodList.add(new Product(R.drawable.ic_default, "món 3", "10000"));
        return foodList;
    }
}
