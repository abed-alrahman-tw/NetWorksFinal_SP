package com.example.finalproject_sp.adapters.orderstatusadapter;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_sp.databinding.OrderStautsItemBinding;
import com.example.finalproject_sp.databinding.RecyclerItemBinding;

public class OStatusViewHolder extends RecyclerView.ViewHolder {

    TextView orderDateTv,  jobName , orderNumber ;


    public OStatusViewHolder(@NonNull OrderStautsItemBinding binding) {
        super(binding.getRoot());

        orderDateTv = binding.orderDateTv;
        jobName = binding.jobNameTv;
        orderNumber = binding.orderNumTv;

    }
}
