package com.example.kishor.summerproject.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kishor.summerproject.R;

public class CustomDialog {


    public static void showMessagePop(Context context, String msg) {
        final android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(context);
        final View view = View.inflate(context, R.layout.error_message_layout, null);
        TextView errorText = (TextView) view.findViewById(R.id.error_text);
        Button buttonOk = (Button) view.findViewById(R.id.btnOk);
        errorText.setText(msg);
        alertDialogBuilder.setView(view);
        final Dialog originalDialog = alertDialogBuilder.create();
        originalDialog.getWindow().setDimAmount(0.7f);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                originalDialog.dismiss();
            }
        });
        originalDialog.setCanceledOnTouchOutside(false);
        originalDialog.show();
    }

}
