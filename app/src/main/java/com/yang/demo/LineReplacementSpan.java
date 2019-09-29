package com.yang.demo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ReplacementSpan;

/**
 * Created by yang on 2019/09/18.
 * 自定义下划线
 */
public class LineReplacementSpan extends ReplacementSpan {
    private String mText;
    //字的画笔
    private final Paint mTextPaint;
    //填空的宽度
    private int textWidth;

    public int id = 0;//回调中的对应Span的ID

    public LineReplacementSpan(Paint textPaint, int textWidth, String text) {
        this.textWidth = textWidth;
        this.mTextPaint = textPaint;
        this.mText = text;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return textWidth;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Paint linePaint = new Paint();
        linePaint.setColor(Color.parseColor("#FFCA2A"));
        linePaint.setStrokeWidth(10);
        canvas.drawLine(x, y, x + textWidth, y, linePaint);

        CharSequence ellipsize = TextUtils.ellipsize(mText, (TextPaint) paint, textWidth, TextUtils.TruncateAt.END);
        int width = (int) paint.measureText(ellipsize, 0, ellipsize.length());
        width = (textWidth - width) / 2;
        canvas.drawText(ellipsize, 0, ellipsize.length(), x + width, (float) y, mTextPaint);
    }


}