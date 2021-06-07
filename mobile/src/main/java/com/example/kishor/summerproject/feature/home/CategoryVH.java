package com.example.kishor.summerproject.feature.home;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kishor.summerproject.R;


/**
 * Created by sujan on 7/18/17.
 */

public class CategoryVH extends RecyclerView.ViewHolder {
    TextView txtCategory;
    ImageView imgCategory;

    public CategoryVH(View itemView) {
        super(itemView);
        txtCategory = itemView.findViewById(R.id.txtCategory);
        imgCategory = itemView.findViewById(R.id.imgCategory);


    }
}
