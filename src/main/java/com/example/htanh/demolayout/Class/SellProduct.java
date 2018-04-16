package com.example.htanh.demolayout.Class;

import java.io.Serializable;
import java.util.List;

/***
 * Created by htanh on 4/2/2018.
 */

public class SellProduct implements Serializable {
    private List<Product> productList;
    private String tableQuantity;
    private String customerQuantity;
    private String paySum;



    public SellProduct() {

    }

    public SellProduct(String tableQuantity) {
        this.tableQuantity = tableQuantity;
    }

    public SellProduct(List<Product> productList, String tableQuantity, String customerQuantity, String paySum) {

        this.productList = productList;
        this.tableQuantity = tableQuantity;
        this.customerQuantity = customerQuantity;
        this.paySum = paySum;
    }

    public SellProduct(List<Product> productList, String tableQuantity, String customerQuantity) {
        this.productList = productList;
        this.tableQuantity = tableQuantity;
        this.customerQuantity = customerQuantity;
    }

    public SellProduct(String tableQuantity, String customerQuantity) {
        this.tableQuantity = tableQuantity;
        this.customerQuantity = customerQuantity;
    }

    public String getPaySum() {
        return paySum;
    }

    public void setPaySum(String paySum) {
        this.paySum = paySum;
    }

    public String getTableQuantity() {
        return tableQuantity;
    }

    public void setTableQuantity(String tableQuantity) {
        this.tableQuantity = tableQuantity;
    }

    public String getCustomerQuantity() {
        return customerQuantity;
    }

    public void setCustomerQuantity(String customerQuantity) {
        this.customerQuantity = customerQuantity;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
