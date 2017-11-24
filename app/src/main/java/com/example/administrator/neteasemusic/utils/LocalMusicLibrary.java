package com.example.administrator.neteasemusic.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.administrator.neteasemusic.data.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：bjc on 2017/11/23 14:11
 * 邮箱：783491064@qq.com
 * QQ ：783491064
 * 类描述：获取本地所有歌曲的工具类
 */
public class LocalMusicLibrary {
    /**
     * 查所有歌曲封装成集合后返回；
     * @param context
     * @return
     */
    public static List<Song> getAllSong(Context context) {
        List<Song> songs = new ArrayList<>();
        String selectionStatement = "is_music=1 AND title != ''";
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "title", "artist", "album", "duration", "track", "artist_id", "album_id", "_data"}, selectionStatement, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if ((cursor != null) && (cursor.moveToFirst())) {
            do {
                Song song = getSongFromCursor(cursor);
                if (song.isStatus()) {
                    songs.add(song);
                }
            } while (cursor.moveToNext());
        }
        return songs;
    }

    /**
     * 通过cursor 去封装每一首歌曲；
     * @param cursor
     * @return
     */
    private static Song getSongFromCursor(Cursor cursor) {
        //获取歌曲数据
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
        String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
        String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
        int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
        int trackNumber = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TRACK));
        long artistId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST_ID));
        long albumId = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
        String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
         //封装歌曲数据到歌曲实体类
        Song song=new Song();
        song.setId(-id);
        song.setTitle(title);
        song.setAlbumName(album);
        song.setArtistName(artist);
        song.setDuration(duration);
        song.setTrackNumber(trackNumber);
        song.setArtistId(artistId);
        song.setAlbumId(albumId);
        String cover = getAlbumArtUri(albumId).toString();
        song.setCoverUrl(cover);
        song.setPath(url);
        song.setUrl(url);
        //该歌曲在本地存储有路径 设置歌曲本地歌曲为真
        if(FileUtils.existFile(url)){
            song.setStatus(true);
        }
        return song;
    }

    /**
     * 获取封面照片
     * @param paramInt
     * @return
     * 解析
     * ContentUris是content URI的一个辅助类。
     * ContentUris有两个方法很有用，具体如下所示：
     * public static Uri withAppendedId（Uri contentUri, long id），这个方法负责把id和contentUri连接成一个新的Uri。比如在我们这个例子当中是这么使用 的：ContentUris.withAppendedId （Diary. DiaryColumns.CONTENT_URI, rowId）。如果rowId为100的话，那么现在的这个Uri的内容就是：
     * content://com.ex09_2_contentprovider.diarycontentprovider/diaries/100。
     * public static long parseId（Uri contentUri），这个方法负责把content URI 后边的id解析出来，比如现在这个content URI 是content://com.ex09_2_contentprovider.diarycontentprovider/diaries/100，那 么这个函数的返回值就是100。
     */
    private static Uri getAlbumArtUri(long paramInt) {
        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"),paramInt);
    }
}
