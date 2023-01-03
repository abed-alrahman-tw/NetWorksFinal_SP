package com.example.finalproject_sp.adapters.orderstatusadapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_sp.databinding.OrderStautsItemBinding;

import java.util.ArrayList;

public class OrderStatusAdapter extends RecyclerView.Adapter<OStatusViewHolder> {
    ArrayList<OrderStatus> orders;

    public OrderStatusAdapter(ArrayList<OrderStatus> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OStatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderStautsItemBinding binding = OrderStautsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);


        return new OStatusViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OStatusViewHolder holder, int position) {


        OrderStatus order = orders.get(position);
        holder.jobName.setText(order.getJobName());
        holder.orderNumber.append(order.getOrderNumber());
        holder.orderDateTv.setText(order.getOrderDate());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
