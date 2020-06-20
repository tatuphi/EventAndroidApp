package com.example.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.myapplication.R;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.myapplication.Tab1Fragment;
import com.example.myapplication.Tab2Fragment;
import com.example.myapplication.Tab3Fragment;
import com.example.myapplication.Tab4Fragment;
import com.example.myapplication.TabAdapter;
import com.example.myapplication.model.Notification.BadgeNumber;
import com.example.myapplication.util.SharedPrefManager;
import com.example.myapplication.util.api.BaseApiService;
import com.example.myapplication.util.api.UtilsApi;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.pager) ViewPager viewPager;
    @BindView(R.id.tablayout) TabLayout tabLayout;

    private TabAdapter adapter;

    CircleImageView circleImageView;
    TextView fullname;

    TextView textCartItemCount;
    int mCartItemCount;

    Context mContext;
    BaseApiService mApiService;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);
        adapter = new TabAdapter(getSupportFragmentManager());


        View header = navigationView.getHeaderView(0);
        fullname = (TextView) header.findViewById(R.id.profile_fullname);
        fullname.setText(sharedPrefManager.getSPName());

        circleImageView = (CircleImageView) header.findViewById(R.id.profile_image);
        Picasso.get().load(sharedPrefManager.getSPUrlavatar()).into(circleImageView);

        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);


//        add fragments
        adapter.addFragment(new Tab1Fragment(), "All");
        adapter.addFragment(new Tab2Fragment(), "Recent");
        adapter.addFragment(new Tab3Fragment(), "Past");
        adapter.addFragment(new Tab4Fragment(), "Self");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_item_chat:
                                startActivity(new Intent(mContext, Chat.class));
                                break;
                            case R.id.nav_item_qrCode:
                                startActivity(new Intent(mContext, QRCode.class));
                                break;
                            case R.id.nav_item_settings:
                                startActivity(new Intent(mContext, settings.class));
                                break;
                            case R.id.nav_item_changePassword:
                                startActivity(new Intent(mContext, ChangePassword.class));
                                break;
                        }
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    }
                });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_notification);

        View actionView = menuItem.getActionView();
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_notification:
                startActivity(new Intent(mContext, Notification.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setupBadge() {
        mApiService.getBadgeNumber().enqueue(new Callback<BadgeNumber>() {
            @Override
            public void onResponse(Call<BadgeNumber> call, Response<BadgeNumber> response) {
                if (response.isSuccessful())
                {
                    mCartItemCount = response.body().getResult();
                    Log.e("test","onFailure: ERROR 600 > " + mCartItemCount);
                    if (mCartItemCount == 0) {
                        if (textCartItemCount.getVisibility() != View.GONE) {
                            textCartItemCount.setVisibility(View.GONE);
                        }
                    } else {
                        textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                        if (textCartItemCount.getVisibility() != View.VISIBLE) {
                            textCartItemCount.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<BadgeNumber> call, Throwable t) {

            }
        });
    }


    public void get_profile(View v)
    {
        Intent register = new Intent(HomeActivity.this, profile.class);
        startActivity(register);
    }

}