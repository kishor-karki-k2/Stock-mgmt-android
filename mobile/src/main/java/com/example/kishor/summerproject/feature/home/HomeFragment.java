package com.example.kishor.summerproject.feature.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kishor.summerproject.R;
import com.example.kishor.summerproject.feature.category.SubCategoryActivity;
import com.example.kishor.summerproject.feature.sales.Sales;
import com.example.kishor.summerproject.listener.OnClickListener;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private View mainView;
    private CategoryAdapter adapter;
    private Realm realm;
    private ArrayList<Category> categoryList = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.activity_main, container, false);
        findViews();
        initRecyclerview();
        return mainView;
    }

    private void findViews() {
        recyclerView = mainView.findViewById(R.id.recyclerView);
    }

    private void setData() {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Category> realmList = realm.where(Category.class).findAll();
        List<Category> sales = new ArrayList<>();
        sales = realm.copyFromRealm(realmList);
        realm.commitTransaction();
        if (sales != null && sales.size() > 0) {
            categoryList.clear();
            categoryList.addAll(sales);
            adapter.notifyDataSetChanged();
        }
    }

    private void initRecyclerview() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CategoryAdapter(categoryList, new OnClickListener() {

            @Override
            public void onClick(int position) {
                getContext().startActivity(new Intent(getContext(), SubCategoryActivity.class).putExtra("CategoryID", categoryList.get(position).getId()));
            }
        });
        recyclerView.setAdapter(adapter);
        setData();

    }

}

