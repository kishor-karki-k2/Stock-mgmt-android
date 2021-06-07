package com.example.kishor.summerproject.feature.addSales;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kishor.summerproject.R;
import com.example.kishor.summerproject.feature.category.Product;
import com.example.kishor.summerproject.feature.home.Category;
import com.example.kishor.summerproject.feature.sales.Sales;
import com.example.kishor.summerproject.utils.CustomDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class SalesDialogFragment extends DialogFragment {
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog originalDialog;
    private View mainView;
    private Realm realm;
    private Product product;
    private Sales sales;
    private Button btnSubmit;
    private EditText edtQuantity;
    private EditText edtSalesQuantity;
    private LinearLayout lytSalesDate;
    private TextView txtProductTitle;
    private TextView txtScheduleDate;
    private Button btnCancel;
    private String date;
    private int productID;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        alertDialogBuilder = new AlertDialog.Builder(getActivity());
        mainView = View.inflate(getActivity(), R.layout.add_sales_dialog, null);
        alertDialogBuilder.setView(mainView);
        originalDialog = alertDialogBuilder.create();
        originalDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        originalDialog.getWindow().setDimAmount(0.7f);
        originalDialog.setCancelable(false);
        originalDialog.setCanceledOnTouchOutside(false);
        findViews();
        eventHandling();
        return originalDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productID = getArguments().getInt("ProductId");
        getData();
    }

    private void eventHandling() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                originalDialog.dismiss();
            }
        });
        lytSalesDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int salesQuantity;
                if (!TextUtils.isEmpty(edtSalesQuantity.getText())) {
                    salesQuantity = Integer.parseInt(edtSalesQuantity.getText().toString().trim());
                } else
                    salesQuantity = 0;
                int productQuantity = Integer.parseInt(product.getQuantity());
                if (salesQuantity != 0) {
                    if (salesQuantity > productQuantity) {
                        CustomDialog.showMessagePop(getActivity(), "Sales quantity should not be greater than product quantity");
                    } else {
                        if (product != null) {
                            if (date != null) {
                                Random r = new Random();
                                int id = r.nextInt(2000);
                                sales = new Sales();
                                sales.setTitle(product.getTitle());
                                sales.setQuantity(edtSalesQuantity.getText().toString().trim());
                                sales.setId(id);
                                int quantity = Integer.parseInt(product.getQuantity()) - Integer.parseInt(sales.getQuantity());
                                product.setQuantity(String.valueOf(quantity));
                                sales.setDate(date);
                                updateData();
                            } else
                                CustomDialog.showMessagePop(getActivity(), "Please select sale date.");
                        }
                    }
                } else {
                    CustomDialog.showMessagePop(getActivity(), "Sales quantity should not be empty");
                }
            }
        });

    }

    private void showDateDialog() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        int monthUpdate = month + 1;
                        date = year + "/" + monthUpdate + "/" + day;
                        txtScheduleDate.setText(date);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private void updateData() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() { // must be in transaction for this to work
            @Override
            public void execute(Realm realm) {
                // increment index
                //...
                realm.copyToRealmOrUpdate(product); // using insert API
                realm.copyToRealmOrUpdate(sales); // using insert API
            }
        }, new Realm.Transaction.OnSuccess() {

            @Override
            public void onSuccess() {
                originalDialog.dismiss();
            }
        });

    }

    private void findViews() {
        btnSubmit = mainView.findViewById(R.id.btnSales);
        edtQuantity = mainView.findViewById(R.id.edtQuantity);
        edtSalesQuantity = mainView.findViewById(R.id.edtSalesQuantity);
        txtProductTitle = mainView.findViewById(R.id.txtProductTitle);
        txtScheduleDate = mainView.findViewById(R.id.txtScheduleDate);
        lytSalesDate = mainView.findViewById(R.id.lytSalesDate);
        btnCancel = mainView.findViewById(R.id.btnCancel);
        if (product != null) {
            txtProductTitle.setText(product.getTitle());
            edtQuantity.setText(product.getQuantity());
        }
    }

    private void getData() {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmObject object = realm.where(Product.class).equalTo("id", productID).findFirst();
        product = (Product) realm.copyFromRealm(object);
        realm.commitTransaction();

    }

}
