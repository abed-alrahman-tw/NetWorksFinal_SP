package com.example.finalproject_sp.adapters.orderadapter;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_sp.databinding.RecyclerItemBinding;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    TextView customerName, serviceType, orderNumber, dateTv, location;
    Button detailsBtn;
    ImageView imageView, customerPic;

    public OrderViewHolder(@NonNull RecyclerItemBinding binding) {
        super(binding.getRoot());

        customerName = binding.customerName;
        serviceType = binding.serviceTv;
        orderNumber = binding.orderTv;
        detailsBtn = binding.detailsButton;
        imageView = binding.img;
        customerPic = binding.customerImage;
        dateTv = binding.dateTv;
        location = binding.locationTv;

    }
}
