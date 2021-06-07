package com.example.kishor.summerproject.feature.category;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.kishor.summerproject.R;
import com.example.kishor.summerproject.feature.home.Category;
import com.example.kishor.summerproject.feature.sales.Sales;
import com.example.kishor.summerproject.feature.uploadProduct.UploadDialogFragment;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class SubCategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SubcategoryAdapter adapter;
    private FloatingActionButton fabButton;
    private int categoryID;
    private Realm realm;
    private ArrayList<Product> productArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);
        categoryID = getIntent().getIntExtra("CategoryID", 0);
        initToolbar();
        findViews();
        initRecyclerView();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    private void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
        fabButton = findViewById(R.id.fabButton);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadDialogFragment newFragment = new UploadDialogFragment();
                newFragment.show(getFragmentManager().beginTransaction(), null);
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new SubcategoryAdapter(productArrayList);
        recyclerView.setAdapter(adapter);
        getData();

    }

    private void getData() {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmObject realmList = realm.where(Category.class).equalTo("id", categoryID).findFirst();
        Category sales = new Category();
        sales = (Category) realm.copyFromRealm(realmList);
        realm.commitTransaction();
        if (sales != null) {
            productArrayList.clear();
            productArrayList.addAll(sales.getProductArrayList());
            getSupportActionBar().setTitle(sales.getTitle());
            adapter.notifyDataSetChanged();
        }
    }
}
