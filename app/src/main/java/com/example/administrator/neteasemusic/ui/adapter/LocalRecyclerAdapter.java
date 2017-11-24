package com.example.administrator.neteasemusic.ui.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.data.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：bjc on 2017/11/23 15:36
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：本地音乐 RecyclerView 的适配器；
 */
public class LocalRecyclerAdapter extends RecyclerView.Adapter<LocalRecyclerAdapter.LocalMusicViewHolder> {
    private Context context;
    private List<Song> songs=new ArrayList<>();
    private OnItemClickListener ItemClickListener;

    public LocalRecyclerAdapter(Context context) {
        this.context = context;
    }

    /**
     * 给适配器设置数据
     */
    public void setData(List<Song> songs) {
        this.songs = songs;
        notifyDataSetChanged();
    }

    /**
     * 设置条目的点击事件监听器
     */
    public void setOnItemClickListener(OnItemClickListener ItemClickListener) {
        this.ItemClickListener = ItemClickListener;
    }


    @Override
    public LocalMusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_localmusic_item, parent, false);
        return new LocalMusicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LocalMusicViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.title.setText(Html.fromHtml(song.getTitle()));
        if (TextUtils.isEmpty(song.getArtistName())) {
            holder.detail.setText("unknown");
        } else {
            holder.detail.setText(song.getArtistName());
        }
        Glide.with(context)
                .load(song.getCoverUrl())
                .placeholder(R.drawable.cover)
                .into(holder.cover);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class LocalMusicViewHolder extends RecyclerView.ViewHolder {

        private final View musicLayout;
        private final TextView title;
        private final TextView detail;
        private final ImageView cover;
        private final AppCompatImageView setting;
        private final ImageView playstate;

        public LocalMusicViewHolder(View itemView) {
            super(itemView);
            musicLayout = itemView.findViewById(R.id.local_song_item);
            title = (TextView) itemView.findViewById(R.id.local_song_title);
            detail = (TextView) itemView.findViewById(R.id.local_song_detail);
            cover = (ImageView) itemView.findViewById(R.id.local_song_cover);
            setting = (AppCompatImageView) itemView.findViewById(R.id.local_song_setting);
            playstate = (ImageView) itemView.findViewById(R.id.play_state);

            //条目上的设置按钮点击了
            setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Song song = songs.get(getAdapterPosition());
                    if (ItemClickListener != null && song.isStatus()) {
                        ItemClickListener.onItemSettingClick(setting, song, getAdapterPosition());
                    }
                }
            });
            //点击整个条目；
            musicLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Song song = songs.get(getAdapterPosition());
                    if (ItemClickListener != null && song.isStatus()) {
                        ItemClickListener.onItemClick(song, getAdapterPosition());
                    }
                }
            });
        }
    }
}
