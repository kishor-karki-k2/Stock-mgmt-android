package com.example.kishor.summerproject.feature.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.kishor.summerproject.R;
import com.example.kishor.summerproject.feature.dashboard.DashboardActivity;


public class LoginPage extends Activity  {
    Button loginbutton;
    EditText Username,Password;
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginbutton=(Button)findViewById(R.id.log_btn);
        Username=(EditText)findViewById(R.id.username_login);
        Password=(EditText)findViewById(R.id.password_login);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Username.getText().toString().equals("Rimsha") && Password.getText().toString().equals("1132")) {
                    Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginPage.this, DashboardActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Loading Error!!!!", Toast.LENGTH_SHORT).show();
                }
            }

        });
        }
}
