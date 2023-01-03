package com.example.finalproject_sp.activites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.finalproject_sp.R;
import com.example.finalproject_sp.fragments.HomeFragment;
import com.example.finalproject_sp.adapters.viewpageradapter.ViewPagerAdapter2;
import com.example.finalproject_sp.databinding.ActivityAvailableJobsBinding;
import com.example.finalproject_sp.fragments.MoreDetailsFragment;
import com.example.finalproject_sp.fragments.OrdersStatusFragment;
import com.example.finalproject_sp.fragments.UpdateInfoFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class AvailableJobs extends AppCompatActivity  {

    ActivityAvailableJobsBinding binding;
    TabLayout tabLayout;
    TabLayoutMediator mediator;
    ViewPager2 pager2;
    ArrayList<Fragment> fragments;
    int[] icons;
    String[] names;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAvailableJobsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeViews_Vars();

        pager2.setUserInputEnabled(false);
        icons = new int[]{R.drawable.ic_logo, R.drawable.ic_orders, R.drawable.ic_user, R.drawable.ic_more};
        names = new String[]{"Home", "Orders", "User", "More"};

        mediator = new TabLayoutMediator(tabLayout, pager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(names[position]);
                tab.setIcon(icons[position]);
            }
        });
        mediator.attach();


    }

    void initializeViews_Vars() {
        tabLayout = binding.tabLayout;
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new OrdersStatusFragment());
        fragments.add(new UpdateInfoFragment());
        fragments.add(new MoreDetailsFragment());
        pager2 = binding.viewPager2;
        pager2.setAdapter(new ViewPagerAdapter2(this, fragments));
    }

}