package com.toan_itc.baoonline.ui.datafragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.mvp.model.news.Data;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 10/07/2016
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolders> {

    private List<Data> itemList;

    public RecyclerViewAdapter(List<Data> itemList) {
        this.itemList = itemList;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);
        return  new RecyclerViewHolders(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        Data news=itemList.get(position);
            holder.id.setText(String.valueOf(news.getId()));
        holder.title.setText(news.getTitle());
        holder.url.setText(news.getUrl());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView title;
        public TextView url;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            id=(TextView)itemView.findViewById(R.id.id);
            title=(TextView)itemView.findViewById(R.id.title);
            url=(TextView)itemView.findViewById(R.id.url);
        }
    }
}