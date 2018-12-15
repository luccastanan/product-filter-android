package com.codetouch.filtrodeprodutos.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codetouch.filtrodeprodutos.R;
import com.codetouch.filtrodeprodutos.Utilities;
import com.codetouch.filtrodeprodutos.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private List<Product> products;

    public ProductAdapter(List<Product> products){
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = products.get(position);
        holder.txvName.setText(product.getProductName());
        holder.txvDescription.setText(product.getDescription());
        holder.txvMarket.setText(TextUtils.join(", ", product.getTargetMarket()));
        holder.txvTechnologies.setText(TextUtils.join(", ", product.getStack()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txvName, txvDescription, txvMarket, txvTechnologies;
        public MyViewHolder(View itemView) {
            super(itemView);
            txvName = itemView.findViewById(R.id.txvName);
            txvDescription = itemView.findViewById(R.id.txvDescription);
            txvMarket= itemView.findViewById(R.id.txvMarket);
            txvTechnologies = itemView.findViewById(R.id.txvTechnologies);
        }
    }
}
