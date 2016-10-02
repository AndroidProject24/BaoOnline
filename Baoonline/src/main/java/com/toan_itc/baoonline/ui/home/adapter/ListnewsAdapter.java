package com.toan_itc.baoonline.ui.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.interfaces.OnItemClickListener;
import com.toan_itc.data.libs.image.ImageLoaderListener;
import com.toan_itc.data.model.rss.RssFeedItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Toan.IT
 * Date: 22/05/2016
 */
public class ListnewsAdapter extends RecyclerView.Adapter<ListnewsAdapter.ViewHolder> {
    private Context mContext;
    private List<RssFeedItem> mRssFeedItems=new ArrayList<>();
    private ImageLoaderListener imageLoaderListener;
    private OnItemClickListener onItemClickListener;
    public ListnewsAdapter(@NonNull Context context, List<RssFeedItem> mRssFeedItems, ImageLoaderListener imageLoaderListener, OnItemClickListener onItemClickListener) {
       try {
           this.mContext = context;
           this.mRssFeedItems = Preconditions.checkNotNull(mRssFeedItems,"articles cannot be null");
           this.imageLoaderListener=imageLoaderListener;
           this.onItemClickListener = onItemClickListener;
       }catch (Exception e){
           e.printStackTrace();
       }
    }
    public void addAll(List<RssFeedItem> list) {
        try {
            if(list!=null&&list.size()>0) {
                int startIndex = mRssFeedItems.size();
                mRssFeedItems.addAll(startIndex, list);
                notifyItemRangeInserted(startIndex, list.size());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateList(List<RssFeedItem> rssFeedItemList) {
        this.mRssFeedItems.clear();
        this.mRssFeedItems.addAll(rssFeedItemList);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_adapter, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        try {
	        RssFeedItem rssFeedItem=mRssFeedItems.get(position);
            if(rssFeedItem!=null) {
                viewHolder.txt_title.setText(rssFeedItem.getTitle());
                viewHolder.txt_description.setText(rssFeedItem.getArticle());
                viewHolder.txt_time.setText(rssFeedItem.getPubDate());
                imageLoaderListener.loadController(rssFeedItem.getImage(),viewHolder.img_news,300,250,null);
                viewHolder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(rssFeedItem));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mRssFeedItems!=null?mRssFeedItems.size():0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_title)
        TextView txt_title;
        @BindView(R.id.txt_description)
        TextView txt_description;
        @BindView(R.id.txt_time)
        TextView txt_time;
        @BindView(R.id.img_news)
        SimpleDraweeView img_news;
        private ViewHolder(View itemView) {
            super(itemView);
            try {
                ButterKnife.bind(this, itemView);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}