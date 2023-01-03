package com.example.finalproject_sp.adapters.orderadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject_sp.R;
import com.example.finalproject_sp.databinding.RecyclerItemBinding;
import com.example.finalproject_sp.interfaces.OnClick;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    ArrayList<Order> orders;
    Context context;
    OnClick onClick;

    public OrdersAdapter(ArrayList<Order> orders, Context context, OnClick onClick) {
        this.orders = orders;
        this.onClick = onClick;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerItemBinding binding = RecyclerItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new OrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        Order order = orders.get(position);
        Glide.with(context).load(order.image).into(holder.imageView);
        holder.orderNumber.setText(order.getOrderNumber());
        holder.serviceType.setText(order.getServiceType());
        holder.customerName.setText(order.getCustomerName());
        holder.customerPic.setImageResource(R.drawable.customer_pic);
        holder.location.setText(order.locationDetails);
        String date = order.time;
        date = date.substring(0, date.indexOf("T"));
        holder.dateTv.setText(date);

        holder.detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClickListener(Integer.parseInt(order.getOrderNumber()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

}
