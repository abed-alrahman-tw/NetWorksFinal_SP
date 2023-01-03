package com.example.finalproject_sp.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.finalproject_sp.adapters.vpAdapter;
import com.example.finalproject_sp.databinding.ActivityOnBoardingBinding;
import com.example.finalproject_sp.interfaces.Constants;
import com.example.finalproject_sp.interfaces.SendPosition;

public class OnBoarding extends AppCompatActivity {

    ActivityOnBoardingBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewPager viewPager = binding.viewPager;
        sharedPreferences = getSharedPreferences(Constants.SHARED_KEY, 0);
        editor = sharedPreferences.edit();

        vpAdapter adapter = new vpAdapter(new SendPosition() {
            @Override
            public void clickButton(int position) {

                viewPager.setCurrentItem(position + 1);
                if (position == 2) {
                    startActivity(new Intent(OnBoarding.this, LogIn.class));
                    editor.putBoolean(Constants.IS_FIRST_TIME, true);
                    editor.commit();
                }

            }
        });
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0, false);
        viewPager.setNestedScrollingEnabled(false);
        viewPager.setHorizontalScrollBarEnabled(false);


    }
}