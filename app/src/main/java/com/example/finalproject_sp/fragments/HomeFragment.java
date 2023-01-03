package com.example.finalproject_sp.fragments;

import static com.android.volley.Request.Method.POST;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalproject_sp.activites.OrderDetailsActivity;
import com.example.finalproject_sp.adapters.orderadapter.Order;
import com.example.finalproject_sp.adapters.orderadapter.OrdersAdapter;
import com.example.finalproject_sp.databinding.FragmentHomeBinding;
import com.example.finalproject_sp.interfaces.Constants;
import com.example.finalproject_sp.interfaces.OnClick;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment implements OnClick {


    FragmentHomeBinding binding;
    RecyclerView recycler;
    ArrayList<Order> orders;
    String token;
    OrdersAdapter adapter;
    SharedPreferences preferences;
    Context context;
    LinearLayoutManager lm;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(context), container, false);
        initializeViews_Vars();
        post();
        return binding.getRoot();
    }

    void initializeViews_Vars() {
        recycler = binding.recycler;
        lm = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        orders = new ArrayList<>();
        adapter = new OrdersAdapter(orders, context, this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(lm);
    }

    void post() {

        showAnimation();

        preferences = context.getSharedPreferences(Constants.SHARED_KEY, 0);
        token = preferences.getString(Constants.TOKEN, "");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderBy", "ASC");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(POST, "https://studentucas.awamr.com/api/home/deliver",
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray;
                JSONObject dataObject;

                String image;
                String customerName;
                String serviceType;
                String orderNumber;
                String location;
                String date;
                String lat, longitude;

                try {
                    if (response.getBoolean("success")) {

                        String message = response.getString("message");
                        Log.e("onResponse: ", message);
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        jsonArray = response.getJSONArray("data");

                        //fromArray
                        for (int i = 0; i < jsonArray.length(); i++) {

                            dataObject = jsonArray.getJSONObject(i);
                            location = dataObject.getString("details_address");
                            lat = dataObject.getString("lat");
                            longitude = dataObject.getString("long");
                            date = dataObject.getString("created_at");
                            JSONObject workObject = dataObject.getJSONObject("work");
                            serviceType = workObject.getString("name");
                            JSONObject photoObject = dataObject.getJSONObject("photo_order_home");
                            image = photoObject.getString("photo");
                            orderNumber = photoObject.getString("order_id");
                            JSONObject userObject = dataObject.getJSONObject("user");
                            customerName = userObject.getString("name");
                            orders.add(new Order(image, customerName, serviceType, orderNumber, location, date, lat, longitude));
                            adapter.notifyItemInserted(orders.size() - 1);

                        }
                    }
                    hideAnimation();

                } catch (JSONException e) {
                    Log.e("error", e.getMessage());

                    hideAnimation();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideAnimation();
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

    @Override
    public void onClickListener(int id) {

        Intent intent = new Intent(context, OrderDetailsActivity.class);
        Gson gson = new Gson();
        Order order = null;

        for (int i = 0; i < orders.size(); i++) {
            if (Integer.parseInt(orders.get(i).getOrderNumber()) == id) {
                order = orders.get(i);
            }
        }

        String orderString = gson.toJson(order);
        intent.putExtra("order", orderString);
        intent.putExtra("image", order.getImage());
        startActivity(intent);
    }

    void showAnimation() {
        binding.recycler.setVisibility(View.INVISIBLE);
        binding.animationView.setVisibility(View.VISIBLE);
    }

    void hideAnimation() {
        binding.recycler.setVisibility(View.VISIBLE);
        binding.animationView.setVisibility(View.INVISIBLE);
    }


}