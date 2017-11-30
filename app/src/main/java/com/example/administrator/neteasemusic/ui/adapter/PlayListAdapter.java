package com.example.administrator.neteasemusic.ui.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.neteasemusic.R;
import com.example.administrator.neteasemusic.data.Song;
import com.example.administrator.neteasemusic.service.MusicPlayerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：bjc on 2017/11/30 11:53
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：播放列表的适配器
 */
public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.PlayListHodler> {
    private Context mcontext;
    private List<Song> songs;
    private int currentlyPlayingPosition;
    private OnItemClickListener songClickListener;

    public PlayListAdapter(Context context) {
        this.mcontext = context;
        songs=new ArrayList<>();
    }
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * 设置每个条目的歌曲的监听器；
     * @param songClickListener
     */
    public void setSongClickListener(OnItemClickListener songClickListener) {
        this.songClickListener = songClickListener;
    }

    /**
     * 设置适配器的数据并刷新；
     * @param songs
     */
    public void setSongs(List<Song> songs) {
        this.songs = songs;
        notifyDataSetChanged();
    }
    @Override
    public PlayListHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.fragment_playqueue_item,parent,false);
        return new PlayListHodler(view);
    }

    @Override
    public void onBindViewHolder(final PlayListHodler holder, int position) {
        final Song song = songs.get(position);
        holder.MusicName.setText(Html.fromHtml(song.getTitle()));
        //唱片名称
        if(TextUtils.isEmpty(song.getArtistName())){
            holder.Artist.setVisibility(View.GONE);
        }else{
            holder.Artist.setVisibility(View.VISIBLE);
            holder.Artist.setText(song.getArtistName());
        }
        //正在播放 的歌曲条目颜色改变；
        if(MusicPlayerManager.get().getPlayingSong()!=null&&song.getId()==MusicPlayerManager.get().getPlayingSong().getId()){
            holder.MusicName.setTextColor(mcontext.getResources().getColor(R.color.theme_color_primary));
        }else{
            holder.MusicName.setTextColor(mcontext.getResources().getColor(R.color.black_normal));
        }
        if (MusicPlayerManager.get().getPlayingSong().getId() == song.getId()) {
            holder.playstate.setVisibility(View.VISIBLE);
            holder.playstate.setImageResource(R.drawable.song_play_icon);
//            holder.playstate.setImageTintList(R.color.theme_color_primary);

            currentlyPlayingPosition = position;
        } else {
            holder.playstate.setVisibility(View.GONE);
        }
        //条目点击的时间
        holder.musiclayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songClickListener != null && song.isStatus()) {
                    songClickListener.onItemClick(song, holder.getAdapterPosition());
                }
            }
        });
        //右侧设置按钮点击；
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (songClickListener != null && song.isStatus()) {
                    songClickListener.onItemSettingClick(holder.delete, song, holder.getAdapterPosition());
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class PlayListHodler extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{
        ImageView delete;
        TextView MusicName, Artist;
        ImageView playstate;
        RelativeLayout musiclayout;

        public PlayListHodler(View itemView) {
            super(itemView);
            this.playstate = (ImageView) itemView.findViewById(R.id.play_state);
            this.delete = (ImageView) itemView.findViewById(R.id.play_list_delete);
            this.MusicName = (TextView) itemView.findViewById(R.id.play_list_musicname);
            this.Artist = (TextView) itemView.findViewById(R.id.play_list_artist);
            this.musiclayout = (RelativeLayout) itemView.findViewById(R.id.musiclayout);
            this.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

        }

        @Override
        public void onItemSelected() {

        }

        @Override
        public void onItemClear() {

        }
    }
}
