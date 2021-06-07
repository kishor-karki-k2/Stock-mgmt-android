package com.example.kishor.summerproject.feature.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kishor.summerproject.R;
import com.example.kishor.summerproject.listener.OnClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wongel on 7/14/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryVH> {
    private OnClickListener listener;
    private List<Category> categoryList = new ArrayList<>();

    public CategoryAdapter(List<Category> categoryList, OnClickListener listener) {
        this.categoryList = categoryList;
        this.listener = listener;
    }

    @Override
    public CategoryVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_category, parent, false);
        return new CategoryVH(view);
    }

    @Override
    public void onBindViewHolder(final CategoryVH holder, final int position) {
        Category category = categoryList.get(position);
        holder.txtCategory.setText(category.getTitle());
        Picasso.with(holder.itemView.getContext()).load(category.getImage()).into(holder.imgCategory);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
