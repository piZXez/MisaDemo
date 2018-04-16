package com.example.htanh.demolayout.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.htanh.demolayout.Class.UnitCount;
import com.example.htanh.demolayout.R;
import com.example.htanh.demolayout.Data.UnitCountData;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by htanh on 2/6/2018.
 */

public class UnitCountAdapter extends BaseAdapter {

    private List<UnitCount> unitCounts = new ArrayList<>();
    private Context context;
    public String n ;

    public UnitCountAdapter(Context context) {
        this.unitCounts = new UnitCountData().getData();
        this.context = context;
    }

    public UnitCountAdapter(List<UnitCount> unitCounts, Context context) {
        this.unitCounts = unitCounts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return unitCounts.size();
    }

    @Override
    public Object getItem(int position) {
        return unitCounts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setN(String stringExtra) {
        n = stringExtra;
    }

    public class ViewHolder {
        ImageView ivUnitCheck;
        TextView tvUnitName;
        ImageButton btnEdit;
        LinearLayout llItemLayout;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_unit_count, parent, false);

            holder = new ViewHolder();
            holder.ivUnitCheck  = convertView.findViewById(R.id.icon_check);
            holder.tvUnitName   = convertView.findViewById(R.id.text_unit_name);
            holder.btnEdit      = convertView.findViewById(R.id.button_edit);
            holder.llItemLayout = convertView.findViewById(R.id.item_layout);

        }

        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ivUnitCheck.setImageResource(unitCounts.get(position).getIvCheck());
        holder.ivUnitCheck.setVisibility(View.INVISIBLE);
        holder.tvUnitName.setText(unitCounts.get(position).getTxtUnitName());

        //set check với vị trí tương ứng, uncheck các vị trí còn lại
        if (holder.tvUnitName.getText().toString().equals(n)) {
            holder.ivUnitCheck.setVisibility(View.VISIBLE);
        }else {
            holder.ivUnitCheck.setVisibility(View.INVISIBLE);
        }

        //lấy vị trí của item
        holder.tvUnitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = holder.tvUnitName.getText().toString();
                notifyDataSetChanged();
            }
        });

        holder.llItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = holder.tvUnitName.getText().toString();
                notifyDataSetChanged();
            }
        });
        convertView.setTag(holder);

        return convertView;
    }

    public String getN (){
        return n;
    }
}
