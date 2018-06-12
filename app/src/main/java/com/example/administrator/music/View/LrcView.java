package com.example.administrator.music.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.administrator.music.Model.Lyric;

import java.util.List;
//歌词显示
/**
 * Created by Administrator on 2016/8/11.
 */
public class LrcView extends TextView {

    private Paint currentPaint;
    private Paint otherPaint;
    private int index=0;
    private int width;
    private int height;
    private List<Lyric> lyrics;
    private int textHeight=60;

    public LrcView(Context context) {
        super(context);
        init();
    }
    public LrcView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public LrcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setFocusable(true);		//设置可对焦

        //高亮部分
        currentPaint = new Paint();
        currentPaint.setAntiAlias(true);	//设置抗锯齿，让文字美观饱满
        currentPaint.setTextAlign(Paint.Align.CENTER);//设置文本对齐方式

        //非高亮部分
        otherPaint = new Paint();
        otherPaint.setAntiAlias(true);
        otherPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setLyrics(List<Lyric> lyrics) {
        this.lyrics = lyrics;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        currentPaint.setColor(Color.argb(210, 251, 248, 29));
        otherPaint.setColor(Color.argb(140, 255, 255, 255));

        currentPaint.setTextSize(60);
        currentPaint.setTypeface(Typeface.SERIF);

        otherPaint.setTextSize(30);
        otherPaint.setTypeface(Typeface.DEFAULT);
        try {
            setText("");
            canvas.drawText(lyrics.get(index).getContextLyric(), width / 2, height / 2, currentPaint);

            float tempY = height / 2;
            //画出本句之前的句子
            for(int i = index - 1; i >= 0; i--) {
                //向上推移
                tempY = tempY - textHeight;
                canvas.drawText(lyrics.get(i).getContextLyric(), width / 2, tempY, otherPaint);
            }
            tempY = height / 2;
            //画出本句之后的句子
            for(int i = index + 1; i < lyrics.size(); i++) {
                //往下推移
                tempY = tempY + textHeight;
                canvas.drawText(lyrics.get(i).getContextLyric(), width / 2, tempY, otherPaint);
            }
        } catch (Exception e) {
            setText("...木有歌词文件，赶紧去下载...");
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width=w;
        this.height=h;
    }
}
