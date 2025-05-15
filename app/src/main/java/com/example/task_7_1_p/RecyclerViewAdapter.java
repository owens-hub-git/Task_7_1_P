package com.example.task_7_1_p;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task_7_1_p.model.Item;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Item> itemList;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    private OnItemClickListener listener;

    public RecyclerViewAdapter(List<Item> itemList, Context context, OnItemClickListener listener) {
        this.itemList = itemList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_row,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        Item item = itemList.get(position);

        holder.item_rowTextView.setText(item.getIssue());
        holder.postTypeTextView.setText("Post Type: " + item.getPostType());

        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onItemClick(item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_rowTextView;
        TextView postTypeTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_rowTextView = itemView.findViewById(R.id.item_rowTextView);
            postTypeTextView = itemView.findViewById(R.id.postTypeTextView);

        }


    }


}
