/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.toan_itc.baoonline.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.toan_it.library.library.libs.image.ImageLoaderListener;
import com.toan_it.library.library.mvp.model.toan;
import com.toan_itc.baoonline.R;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

  public interface OnItemClickListener {
    void onUserItemClicked(toan toanModel);
  }
  private Context mContext;
  private List<toan> mUsersCollection;
  private final LayoutInflater layoutInflater;
  private ImageLoaderListener mImageLoaderListener;
  private OnItemClickListener onItemClickListener;

  @Inject
  public UsersAdapter(Context context,ImageLoaderListener imageLoaderListener) {
    mContext=context;
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.mUsersCollection = Collections.emptyList();
    this.mImageLoaderListener=imageLoaderListener;
  }

  @Override
  public int getItemCount() {
    return (this.mUsersCollection != null) ? this.mUsersCollection.size() : 0;
  }

  @Override
  public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_user, parent, false);
    return new UserViewHolder(view);
  }

  @Override
  public void onBindViewHolder(UserViewHolder holder, final int position) {
    final toan toanModel = this.mUsersCollection.get(position);
    holder.list_item_title_text_view.setText(toanModel.getTitle());
    holder.list_item_short_description_text_view.setText(toanModel.getShort_description());
    /*Picasso.with(mContext)
            .load(toanModel.getImage_preview_url())
            .fit().centerCrop()
            .into(holder.list_item_image_view);*/
    mImageLoaderListener.loadImage(toanModel.getImage_preview_url(),holder.list_item_image_view);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (UsersAdapter.this.onItemClickListener != null) {
          UsersAdapter.this.onItemClickListener.onUserItemClicked(toanModel);
        }
      }
    });
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  public void setUsersCollection(Collection<toan> usersCollection) {
    this.validateUsersCollection(usersCollection);
    this.mUsersCollection = (List<toan>) usersCollection;
    this.notifyDataSetChanged();
  }

  public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  private void validateUsersCollection(Collection<toan> usersCollection) {
    if (usersCollection == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class UserViewHolder extends RecyclerView.ViewHolder {
    //@BindView(R.id.list_item_title_text_view)
    TextView list_item_title_text_view;
    //@BindView(R.id.list_item_short_description_text_view)
    TextView list_item_short_description_text_view;
    //@BindView(R.id.list_item_image_view)
    SimpleDraweeView list_item_image_view;
    public UserViewHolder(View itemView) {
      super(itemView);
      list_item_title_text_view=(TextView)itemView.findViewById(R.id.list_item_title_text_view);
      list_item_short_description_text_view=(TextView)itemView.findViewById(R.id.list_item_short_description_text_view);
      list_item_image_view=(SimpleDraweeView) itemView.findViewById(R.id.list_item_image_view);
    }
  }
}
