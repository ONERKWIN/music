package com.example.administrator.music.Model;

/**
 * Created by Administrator on 2016/8/11.
 */
public class Lyric {
    private String contextLyric;//一定时间段内的歌词
    private int playTime;//当前歌词显示时间

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public String getContextLyric() {
        return contextLyric;
    }

    public void setContextLyric(String contextLyric) {
        this.contextLyric = contextLyric;
    }
}
