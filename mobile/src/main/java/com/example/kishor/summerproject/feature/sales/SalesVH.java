package com.example.kishor.summerproject.feature.sales;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kishor.summerproject.R;

/**
 * Created by sujan on 7/18/17.
 */

public class SalesVH extends RecyclerView.ViewHolder {
    TextView txtProduct;
    TextView txtQuantity;
    TextView txtDate;

    public SalesVH(View itemView) {
        super(itemView);
        txtProduct = itemView.findViewById(R.id.txtProduct);
        txtQuantity = itemView.findViewById(R.id.txtQuantity);
        txtDate = itemView.findViewById(R.id.txtDate);
    }
}
