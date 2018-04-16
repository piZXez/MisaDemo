package com.example.htanh.demolayout.Data;

import com.example.htanh.demolayout.Class.UnitCount;
import com.example.htanh.demolayout.R;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by htanh on 2/6/2018.
 */

public class UnitCountData {

    private List<UnitCount> unitCounts = new ArrayList<>();

    public List<UnitCount> getData() {

        unitCounts.add(new UnitCount(R.drawable.ic_checked, "Cái"));
        unitCounts.add(new UnitCount(R.drawable.ic_checked, "Chai"));
        unitCounts.add(new UnitCount(R.drawable.ic_checked, "Cốc"));
        unitCounts.add(new UnitCount(R.drawable.ic_checked, "Gói"));

        return unitCounts;
    }
}
