package com.example.htanh.demolayout.Class;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by htanh on 2/6/2018.
 */

public class UnitCount {

    private int ivCheck;
    private String txtUnitName;
    private int ivEdit;

    public UnitCount(int ivCheck, String txtUnitName) {
        this.ivCheck = ivCheck;
        this.txtUnitName = txtUnitName;
    }

    public int getIvCheck() {
        return ivCheck;
    }

    public void setIvCheck(int ivCheck) {
        this.ivCheck = ivCheck;
    }

    public String getTxtUnitName() {
        return txtUnitName;
    }

    public void setTxtUnitName(String txtUnitName) {
        this.txtUnitName = txtUnitName;
    }

    public int getIvEdit() {
        return ivEdit;
    }

    public void setIvEdit(int ivEdit) {
        this.ivEdit = ivEdit;
    }
}
