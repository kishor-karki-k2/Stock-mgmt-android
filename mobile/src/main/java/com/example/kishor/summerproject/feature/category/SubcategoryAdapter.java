package com.example.kishor.summerproject.feature.category;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kishor.summerproject.R;
import com.example.kishor.summerproject.feature.product.ProductDetailActivity;
import com.example.kishor.summerproject.listener.OnClickListener;
import com.example.kishor.summerproject.utils.GlobalUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wongel on 7/14/17.
 */

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryVH> {
    private ArrayList<Product> subcategoryList = new ArrayList<>();

    public SubcategoryAdapter(ArrayList<Product> subcategoryList) {
        this.subcategoryList = subcategoryList;

    }

    @Override
    public SubcategoryVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sub_category, parent, false);
        return new SubcategoryVH(view);
    }

    @Override
    public void onBindViewHolder(final SubcategoryVH holder, final int position) {
        Product category = subcategoryList.get(position);
        holder.txtCategoryTitle.setText(category.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("ProductId", subcategoryList.get(position).getId());
                GlobalUtils.navigateActivitywithData(holder.itemView.getContext(), false, ProductDetailActivity.class, map, 0, 0);
            }
        });
    }


    @Override
    public int getItemCount() {
        return subcategoryList.size();
    }
}
