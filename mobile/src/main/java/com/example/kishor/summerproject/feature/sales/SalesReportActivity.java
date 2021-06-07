package com.example.kishor.summerproject.feature.sales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.kishor.summerproject.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class SalesReportActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private SalesAdapter adapter;
    private Realm realm;
    private ArrayList<Sales> salesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_report);
        initToolbar();
        findViews();
        initRecycleView();
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sales Report");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    private void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    public void initRecycleView() {
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new SalesAdapter(this, salesList);
        recyclerView.setAdapter(adapter);
        getData();
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

    private void getData() {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Sales> realmList = realm.where(Sales.class).findAll();
        List<Sales> sales = new ArrayList<>();
        sales = realm.copyFromRealm(realmList);
        realm.commitTransaction();
        if (sales != null && sales.size() > 0) {
            salesList.clear();
            salesList.addAll(sales);
            adapter.notifyDataSetChanged();
        }
    }

}
