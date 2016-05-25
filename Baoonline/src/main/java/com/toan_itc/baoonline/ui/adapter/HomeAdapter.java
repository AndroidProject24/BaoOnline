package com.toan_itc.baoonline.ui.adapter;

/**
 * Created by Toan.IT
 * Date: 22/05/2016
 */
public class HomeAdapter{// extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    /*private BaseActivity context;
    private List<SongHome> mSongList=new ArrayList<>();
    public interface OnItemClickListener {
        void onItemClick(SongHome song);
    }
    protected OnItemClickListener onItemClickListener;
    public HomeAdapter(@NonNull BaseActivity context, List<SongHome> mSongList, OnItemClickListener onItemClickListener, RecyclerView mrecyclerView, LinearLayoutManager linearLayoutManager, MainPresenter mainPresenter, String url) {
       try {
           if (mSongList == null) {
               throw new IllegalArgumentException("articles cannot be null");
           }
           this.context = context;
           this.mSongList = mSongList;
           this.onItemClickListener = onItemClickListener;
           mrecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
               @Override
               public void onLoadMore(int current_page) {
                   Timber.e("onLoadMore="+current_page);
                   if (url != null && !url.equalsIgnoreCase("")) {
                       mainPresenter.loadmore_home(context,url, HomeAdapter.this);
                   } else {
                       mainPresenter.not_url();
                   }
               }
           });

       }catch (Exception e){
           e.printStackTrace();
       }
    }
    public void addAll(List<SongHome> list) {
        try {
            if(list!=null&&list.size()>0) {
                int startIndex = mSongList.size();
                mSongList.addAll(startIndex, list);
                notifyItemRangeInserted(startIndex, list.size());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_home, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        try {
            SongHome song=mSongList.get(position);
            if(song!=null) {
                viewHolder.title_song.setText(song.getName());
                viewHolder.title_singer.setText(song.getSinger());
                Glide.with(context)
                        .load(mSongList.get(position).getImage())
                        .crossFade()
                        .centerCrop()
                        .error(R.drawable.image_not_available)
                        .placeholder(R.drawable.image_not_available)
                        .into(viewHolder.image_song);

                viewHolder.image_song.setOnKeyListener((v, keyCode, event) -> {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        if (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER) {
                            onItemClickListener.onItemClick(song);
                        }

                    }
                    return false;
                }
                );
                viewHolder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(song)
                );
            }
            }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return mSongList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.title_song)
        TextView title_song;
        @BindView(R.id.title_singer)
        TextView title_singer;
        @BindView(R.id.image_song)
        ImageView image_song;
        @BindView(R.id.layout_main)
        LinearLayout layout_main;
        public ViewHolder(View itemView) {
            super(itemView);
            try {
                ButterKnife.bind(this, itemView);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }*/
}