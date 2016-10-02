package com.toan_itc.baoonline.ui.home.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * @author Artur Vasilov
 */
public class TagsViewHolder extends RecyclerView.ViewHolder {
/*

    private final TextView mTagName;
    private final ImageView mFavouriteIcon;
*/

    public TagsViewHolder(View itemView) {
        super(itemView);
       /* mTagName = Views.findById(itemView, R.id.tagText);
        mFavouriteIcon = Views.findById(itemView, R.id.tagImage);*/
    }

  /*  public void bind(@NonNull Tag tag, int position, @NonNull View.OnClickListener onFavouriteListener) {
        mTagName.setText(tag.getName());
        mFavouriteIcon.setImageResource(tag.isFavourite() ? R.drawable.ic_favorite : R.drawable.ic_not_favourite);
        mFavouriteIcon.setTag(position);
        mFavouriteIcon.setOnClickListener(onFavouriteListener);
    }*/
}
