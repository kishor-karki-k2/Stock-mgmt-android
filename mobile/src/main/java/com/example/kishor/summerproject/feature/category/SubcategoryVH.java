package com.example.kishor.summerproject.feature.category;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kishor.summerproject.R;


/**
 * Created by sujan on 7/18/17.
 */

public class SubcategoryVH extends RecyclerView.ViewHolder {
    TextView txtCategoryTitle;
    ImageView subimage;

    public SubcategoryVH(View itemView) {
        super(itemView);
        txtCategoryTitle = itemView.findViewById(R.id.txtCategoryTitle);
        subimage=itemView.findViewById(R.id.subimage);
    }
}
