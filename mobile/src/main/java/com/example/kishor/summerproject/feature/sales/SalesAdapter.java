package com.example.kishor.summerproject.feature.sales;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.kishor.summerproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wongel on 7/14/17.
 */

public class SalesAdapter extends RecyclerView.Adapter<SalesVH> {
    private Context context;
    private List<Sales> phoneList = new ArrayList<>();

    public SalesAdapter(Context context, List<Sales> phoneList) {
        this.context = context;
        this.phoneList = phoneList;
    }

    @Override
    public SalesVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sales, parent, false);
        return new SalesVH(view);
    }

    @Override
    public void onBindViewHolder(final SalesVH holder, final int position) {
        Sales obj = phoneList.get(position);
        holder.txtProduct.setText(obj.getTitle());
        holder.txtQuantity.setText(obj.getQuantity());
        holder.txtDate.setText(obj.getDate());
    }


    @Override
    public int getItemCount() {
        return phoneList.size();
    }
}
