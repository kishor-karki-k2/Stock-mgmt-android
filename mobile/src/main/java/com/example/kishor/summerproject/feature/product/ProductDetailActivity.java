package com.example.kishor.summerproject.feature.product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kishor.summerproject.R;
import com.example.kishor.summerproject.feature.addSales.SalesDialogFragment;
import com.example.kishor.summerproject.feature.category.Product;
import com.example.kishor.summerproject.feature.home.Category;
import com.example.kishor.summerproject.feature.uploadProduct.UploadDialogFragment;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmObject;

public class ProductDetailActivity extends AppCompatActivity {
    private int productID;
    private Realm realm;
    private Product product;
    private ImageView imgProduct;
    private TextView txtProductTitle;
    private TextView txtDescription;
    private LinearLayout lytAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        productID = getIntent().getIntExtra("ProductId", 0);
        initToolbar();
        findViews();
        eventHandling();
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    private void findViews() {
        imgProduct = findViewById(R.id.imgProduct);
        txtProductTitle = findViewById(R.id.txtProductTitle);
        lytAddToCart = findViewById(R.id.lytAddToCart);
        txtDescription = findViewById(R.id.txtDescription);
        getData();
    }

    private void eventHandling() {
        lytAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalesDialogFragment salesDialogFragment = new SalesDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("ProductId", productID);
                salesDialogFragment.setArguments(bundle);
                salesDialogFragment.show(getFragmentManager().beginTransaction(), null);

            }
        });
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
        RealmObject object = realm.where(Product.class).equalTo("id", productID).findFirst();
        Product product = (Product) realm.copyFromRealm(object);
        realm.commitTransaction();
        if (product != null) {
            getSupportActionBar().setTitle(product.getTitle());
            txtProductTitle.setText(product.getTitle());
            txtDescription.setText(product.getDescription());
            int image = R.drawable.no_image;
            if (product.getImage() != 0)
                image = product.getImage();
            Picasso.with(this).load(image).into(imgProduct);
        }
    }
}
