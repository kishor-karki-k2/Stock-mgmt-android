package com.example.kishor.summerproject.feature.dashboard;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kishor.summerproject.feature.auth.LoginPage;
import com.example.kishor.summerproject.R;
import com.example.kishor.summerproject.feature.aboutus.AboutUsFragment;
import com.example.kishor.summerproject.feature.home.HomeFragment;
import com.example.kishor.summerproject.feature.sales.SalesReportActivity;
import com.example.kishor.summerproject.feature.uploadProduct.UploadDialogFragment;
import com.example.kishor.summerproject.utils.GlobalUtils;

public class DashboardActivity extends AppCompatActivity {
    private NavigationView navigationview;
    private DrawerLayout drawer;
    private ImageView imgprofile;
    private TextView txtname, txtEmail;
    private Toolbar toolbar;
    private String[] activityTitle;
    private View navHeader;
    private int navItemindex;
    private FloatingActionButton fabButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initToolbar();
        findViews();


        drawer = (DrawerLayout) findViewById(R.id.drawable_layout);
        navigationview = (NavigationView) findViewById(R.id.nav_view);

        //Navigation Header
        navHeader = navigationview.getHeaderView(0);
        imgprofile = (ImageView) navHeader.findViewById(R.id.imgProfile);
        txtname = (TextView) navHeader.findViewById(R.id.txtName);
        txtEmail = (TextView) navHeader.findViewById(R.id.email);
        activityTitle = getResources().getStringArray(R.array.nav_item_activity_titles);
        setUpNavigationview();
        addFragment(new HomeFragment(), R.id.container, false);
    }

    private void initToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public void ToolbarTitle() {
        getSupportActionBar().setTitle(activityTitle[navItemindex]);
    }


    public void setUpNavigationview() {
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.drawableHome:
                        addFragment(new HomeFragment(), R.id.container, false);

                        navItemindex = 0;
                        break;

                    case R.id.nav_about_us:
                        addFragment(new AboutUsFragment(), R.id.container, false);
                        navItemindex = 1;
                        break;


                    case R.id.nav_logout:
                        navItemindex = 2;
                        Intent logoutintent = new Intent(DashboardActivity.this, LoginPage.class);
                        startActivity(logoutintent);
                        break;
                    case R.id.nav_sales_report:
                        GlobalUtils.navigateActivity(DashboardActivity.this, false, SalesReportActivity.class);
                        break;

                    default:
                        navItemindex = 0;
                }
                drawer.closeDrawer(GravityCompat.START);
                ToolbarTitle();
                return true;

            }


        });


        ToolbarTitle();

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                drawerView.bringToFront();
                drawerView.requestLayout();
            }
        };


        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }


    private void addFragment(Fragment fragment, int id, boolean addToBackstack) {
        FragmentTransaction trasaction = getSupportFragmentManager().beginTransaction().replace(id, fragment);
        if (addToBackstack)
            trasaction.addToBackStack(null);
        trasaction.commit();
    }
    private void findViews() {

        fabButton = findViewById(R.id.fabButton);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadDialogFragment newFragment = new UploadDialogFragment();
                newFragment.show(getFragmentManager().beginTransaction(), null);
            }
        });
    }

}
