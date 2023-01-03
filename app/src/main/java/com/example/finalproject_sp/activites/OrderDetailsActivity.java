package com.example.finalproject_sp.activites;

import static com.android.volley.Request.Method.POST;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.finalproject_sp.adapters.orderadapter.Order;
import com.example.finalproject_sp.databinding.ActivityOrderDetailsBinding;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OrderDetailsActivity extends AppCompatActivity {
    ActivityOrderDetailsBinding binding;
    Context context = this;
    SharedPreferences preferences;
    String token;
    String image;
    Order order;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getDataFromIntent();
        initializeData();

        binding.orderNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AvailableJobs.class));

            }
        });
        binding.phoneEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(binding.phoneEt.getText().toString());
            }
        });
        binding.locationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetailsActivity.this, MapActivity.class);
                intent.putExtra("lat", order.getLat());
                intent.putExtra("long", order.getLongitude());
                startActivity(intent);
            }
        });

    }

    void post() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("order_id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(POST, "https://studentucas.awamr.com/api/create/Offer",
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    String message = response.getString("message");
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    Log.e("error", e.getMessage());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("Authorization", token);

                return map;
            }
        };

        queue.add(request);
    }

    void getDataFromIntent() {

        Intent intent = getIntent();

        Gson gson = new Gson();
        order = gson.fromJson(intent.getStringExtra("order"), Order.class);
        id = Integer.parseInt(order.getOrderNumber());
        preferences = context.getSharedPreferences("shared", 0);
        token = preferences.getString("token", "");
        image = intent.getStringExtra("image");
    }

    void initializeData() {

        binding.customerName.setText(order.getCustomerName());
        binding.dateTvShow.setText(order.getTime());
        binding.locationDetails.setText(order.getLocationDetails());
        binding.jobName.setText(order.getServiceType());
        Glide.with(this).load(image).into(binding.problemImg);
    }

    public void dialPhoneNumber(String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
