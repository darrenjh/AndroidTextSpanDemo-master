package com.yang.demo;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextPaint;
import android.util.SparseArray;

import org.xml.sax.XMLReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 下划线
 * Created by yang on 2019/07/23.
 */
public class LineSpanable {

    private static final String FILL_TAG = "&nbsp;<thickline>&nbsp;";//fix bugs -----多个空连续的情况下
    private static final String FILL_TAG_NAME = "thickline";
    private List<LineReplacementSpan> mSpans;
    private int textColor;//字体颜色
    private Context mContext;

    public LineSpanable(Context context) {
        this.mContext = context;
        this.mSpans = new ArrayList<>();
        this.textColor = Color.parseColor("#73C047");
    }

    public Spanned init(final String textContent, final String... args) {
        final SparseArray<String> sparseArray = new SparseArray<>();
        String covertText = textContent;
        int index = 0;
        List<String> strings = Arrays.asList(args);
        for (int i = 0; i < textContent.length(); i++) {
            char c = textContent.charAt(i);
            if (strings.contains(String.valueOf(c))) {
                covertText = covertText.replace(args[i], FILL_TAG);
                sparseArray.put(index, args[i]);
                index++;
            }
        }
        Spanned spanned = Html.fromHtml(covertText, null, new Html.TagHandler() {
            int index = 0;

            @Override
            public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
                if (tag.equalsIgnoreCase(FILL_TAG_NAME) && opening) {
                    String lineText = sparseArray.get(index);
                    TextPaint mTextPaint = new TextPaint();
                    mTextPaint.setTextSize(16 * mContext.getResources().getDisplayMetrics().scaledDensity);
                    mTextPaint.setColor(textColor);
                    LineReplacementSpan span = new LineReplacementSpan(mTextPaint, 80, "" + lineText);
                    span.id = index++;
                    mSpans.add(span);
                    output.setSpan(span, output.length() - 1, output.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        });
        return spanned;
    }
}
