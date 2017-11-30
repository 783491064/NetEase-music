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
import com.example.administrator.neteasemusic.ui.cnmusic.RecentPlayListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：bjc on 2017/11/29 14:31
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：
 */
public class RecentPlayAdapter extends RecyclerView.Adapter<RecentPlayAdapter.RecentViewHolder> {
    private Context mContext;
    private List<Song> songs;
    private OnItemClickListener songClickListener;
    public OnItemClickListener getOnItemClickListener() {
        return songClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.songClickListener = songClickListener;
    }



    public RecentPlayAdapter(Context context) {
        this.mContext=context;
        this.songs=new ArrayList<>();
    }

    public void setData(List<Song> songs){
        this.songs=songs;
        notifyDataSetChanged();
    }

    @Override
    public RecentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.recycler_recently_listitem, parent, false);
        return new RecentViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final RecentViewHolder holder, int position) {
        final Song song = songs.get(position);
        holder.title.setText(Html.fromHtml(song.getTitle()));
        if(TextUtils.isEmpty(song.getArtistName())){
            holder.detail.setText(R.string.music_unknown);
        }else{
            holder.detail.setText(song.getArtistName());
        }
        Glide.with(mContext)
                .load(song.getCoverUrl())
                .placeholder(R.drawable.cover)
                .into(holder.cover);

        holder.setting.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(songClickListener!=null&&song.isStatus()){
                    songClickListener.onItemSettingClick(holder.setting, song, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class RecentViewHolder extends RecyclerView.ViewHolder{
        public View songItem;
        public TextView title, detail;
        public ImageView cover;
        public AppCompatImageView setting;
        public RecentViewHolder(View itemView) {
            super(itemView);
            songItem = itemView.findViewById(R.id.song_item);
            title = (TextView) itemView.findViewById(R.id.song_title);
            detail = (TextView) itemView.findViewById(R.id.song_detail);
            cover = (ImageView) itemView.findViewById(R.id.song_cover);
            setting = (AppCompatImageView) itemView.findViewById(R.id.song_setting);
            songItem.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    Song song=songs.get(position);
                    if(songClickListener!=null){
                        songClickListener.onItemClick(song,position);
                    }
                }
            });
        }

    }
}
