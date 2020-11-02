
package com.sefvi.seamarket.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.Model.PurchaseAreaModel;
import com.sefvi.seamarket.R;

import java.util.ArrayList;

public class PurchaseAreaAdapter extends RecyclerView.Adapter<PurchaseAreaAdapter.ViewHolder>{
    ArrayList<PurchaseAreaModel> purchaseAreaModels;
    Context context;

    public PurchaseAreaAdapter(ArrayList<PurchaseAreaModel> purchaseAreaModels, Context context) {
        this.purchaseAreaModels = purchaseAreaModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_purchase_area,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtaddress.setText(purchaseAreaModels.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return purchaseAreaModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtaddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtaddress = (TextView) itemView.findViewById(R.id.purchasearea_address);
        }
    }

}