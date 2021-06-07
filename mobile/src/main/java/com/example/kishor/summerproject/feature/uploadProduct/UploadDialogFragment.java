package com.example.kishor.summerproject.feature.uploadProduct;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.kishor.summerproject.R;
import com.example.kishor.summerproject.feature.category.Product;
import com.example.kishor.summerproject.feature.home.Category;
import com.example.kishor.summerproject.utils.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class UploadDialogFragment extends DialogFragment {
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog originalDialog;
    private View mainView;
    private Realm realm;
    private ArrayList<Category> categoryList = new ArrayList<>();
    private ArrayList<Product> productList = new ArrayList<>();
    private Spinner spnCategory;
    private Spinner spnProduct;
    private Product product;
    private ArrayAdapter<String> categoryAdapter;
    private ArrayAdapter<String> productAdapter;
    private Button btnSubmit;
    private EditText edtQuantity;
    private Button btnCancel;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        alertDialogBuilder = new AlertDialog.Builder(getActivity());
        mainView = View.inflate(getActivity(), R.layout.upload_product_dialog, null);
        alertDialogBuilder.setView(mainView);
        originalDialog = alertDialogBuilder.create();
        originalDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        originalDialog.getWindow().setDimAmount(0.7f);
        originalDialog.setCancelable(false);
        originalDialog.setCanceledOnTouchOutside(false);
        setData();
        findViews();
        eventHandling();
        return originalDialog;
    }

    private void eventHandling() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                originalDialog.dismiss();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edtQuantity.getText())) {
                    if (product != null) {
                        product.setQuantity(edtQuantity.getText().toString().trim());
                        updateData();
                    } else {
                        CustomDialog.showMessagePop(getActivity(), "Please select category and product.");
                    }
                }
            }
        });
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    productList.clear();
                    productList.addAll(categoryList.get(position - 1).getProductArrayList());
                    productAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spn_filter_layout, R.id.filter, getProductSpinnerList("Select Product", productList));
                    spnProduct.setAdapter(productAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    edtQuantity.setText(productList.get(position - 1).getQuantity());
                    product = productList.get(position - 1);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateData() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() { // must be in transaction for this to work
            @Override
            public void execute(Realm realm) {
                // increment index
                //...
                realm.copyToRealmOrUpdate(product); // using insert API
            }
        }, new Realm.Transaction.OnSuccess() {

            @Override
            public void onSuccess() {
                originalDialog.dismiss();
            }
        });

    }

    private void findViews() {
        spnCategory = mainView.findViewById(R.id.spnCategory);
        spnProduct = mainView.findViewById(R.id.spnProduct);
        btnSubmit = mainView.findViewById(R.id.btnSubmit);
        edtQuantity = mainView.findViewById(R.id.edtQuantity);
        btnCancel = mainView.findViewById(R.id.btnCancel);
        setSpinner();
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edtQuantity.getWindowToken(), 0);
    }

    private void setSpinner() {
        categoryAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spn_filter_layout, R.id.filter, getCategorySpinnerList("Select Category", categoryList));
        spnCategory.setAdapter(categoryAdapter);
        productAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spn_filter_layout, R.id.filter, getProductSpinnerList("Select Product", productList));
        spnProduct.setAdapter(productAdapter);
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
        }
    }

    private String[] getProductSpinnerList(String title, List<Product> productList) {
        List<String> list = new ArrayList<>();
        if (title != null)
            list.add(title);
        for (Product specialist : productList)
            list.add(specialist.getTitle());
        String[] l = new String[list.size()];
        l = list.toArray(l);
        return l;
    }

    private String[] getCategorySpinnerList(String title, ArrayList<Category> categoryList) {
        List<String> list = new ArrayList<>();
        if (title != null)
            list.add(title);
        for (Category specialist : categoryList)
            list.add(specialist.getTitle());
        String[] l = new String[list.size()];
        l = list.toArray(l);
        return l;
    }
}
