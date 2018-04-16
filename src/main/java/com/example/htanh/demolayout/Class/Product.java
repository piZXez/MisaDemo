package com.example.htanh.demolayout.Class;

import java.io.Serializable;
import java.text.DecimalFormat;

/***
 * Created by htanh on 2/1/2018.
 */

public class Product implements Serializable {

    private int imgFoodMenu;
    private String foodName;
    private String foodPrice;
    private String unitCount;
    private String color;
    private String quantityString;
    private int imgInfo;
    private boolean stateOutOfStock;
    private long quantity;
    private long price;
    private long paySum;
    private DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

    public Product() {
        this.quantityString = decimalFormat.format(quantity);
    }

    public Product(String foodName, String foodPrice, long quantity, long paySum) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.quantity = quantity;
        this.paySum = paySum;
        this.quantityString = decimalFormat.format(quantity);
    }

    public Product(int imgFoodMenu, String foodName, String foodPrice, String unitCount, String color, boolean stateOutOfStock, int quantity) {
        this.imgFoodMenu = imgFoodMenu;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.unitCount = unitCount;
        this.color = color;
        this.stateOutOfStock = stateOutOfStock;
        this.quantity = quantity;
        this.price = Long.parseLong(foodPrice.replace(".", ""));
        this.paySum = this.price * this.quantity;
        this.quantityString = decimalFormat.format(quantity);
    }

    public Product(int imgFoodMenu, String foodName, String foodPrice) {
        this.imgFoodMenu = imgFoodMenu;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.quantityString = decimalFormat.format(quantity);
    }

    public Product(int imgFoodMenu, String foodName, String foodPrice, String unitCount, String color, boolean stateOutOfStock) {
        this.imgFoodMenu = imgFoodMenu;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.unitCount = unitCount;
        this.color = color;
        this.stateOutOfStock = stateOutOfStock;
        this.quantityString = decimalFormat.format(quantity);
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getPaySum() {
        return paySum;
    }

    public void setPaySum(long paySum) {
        this.paySum = paySum;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getQuantity() {
        return quantity;
    }


    public boolean isStateOutOfStock() {
        return stateOutOfStock;
    }

    public void setStateOutOfStock(boolean stateOutOfStock) {
        this.stateOutOfStock = stateOutOfStock;
    }

    public String getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(String unitCount) {
        this.unitCount = unitCount;
    }

    public int getImgFoodMenu() {
        return imgFoodMenu;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setImgFoodMenu(int imgFoodMenu) {
        this.imgFoodMenu = imgFoodMenu;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getImgInfo() {
        return imgInfo;
    }

    public void setImgInfo(int imgInfo) {
        this.imgInfo = imgInfo;
    }

    public String getQuantityString() {
        return quantityString;
    }

    public void setQuantityString(String quantityString) {
        this.quantityString = quantityString;
    }

    public DecimalFormat getDecimalFormat() {
        return decimalFormat;
    }

    public void setDecimalFormat(DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
    }
}