package com.codetouch.filtrodeprodutos.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;

import com.codetouch.filtrodeprodutos.R;
import com.codetouch.filtrodeprodutos.models.Item;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.MyViewHolder> {

    private FilterInteraction onFilter;
    private List<Item> filterList;

    public FilterAdapter(List<Item> filterList, FilterInteraction onFilter) {
        this.filterList = filterList;
        this.onFilter = onFilter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Item item = filterList.get(position);
        holder.ctvTitle.setText(item.getTitle());
        holder.ctvTitle.setOnCheckedChangeListener(null);
        holder.ctvTitle.setChecked(item.isChecked());
        holder.ctvTitle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setChecked(isChecked);
                refreshFilter();
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox ctvTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            ctvTitle = itemView.findViewById(R.id.ctvTitle);
        }
    }

    private void refreshFilter() {
        List<Item> filtred = new ArrayList<>();
        for (Item item : filterList) {
            if (item.isChecked())
                filtred.add(item);
        }
        onFilter.filter(filtred);
    }

    public interface FilterInteraction {
        public void filter(List<Item> filterList);
    }
}
