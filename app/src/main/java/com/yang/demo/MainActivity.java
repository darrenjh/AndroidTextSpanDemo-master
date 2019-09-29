package com.yang.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextSpan1();
        initTextSpan2();
    }

    private void initTextSpan1() {
        TextView textView = findViewById(R.id.tv_span1);
        String textContent = "Han was reading alone when his mother came into ";
        SpannableStringBuilder mSpannableStringBuilder = new SpannableStringBuilder(textContent);
        ThickLineTextSpan mThickLineTextSpan = new ThickLineTextSpan(this);
        mSpannableStringBuilder.setSpan(mThickLineTextSpan, 0, textContent.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(mSpannableStringBuilder);
    }

    private void initTextSpan2() {
        TextView textView = findViewById(R.id.tv_span2);
        String textContent = "Han was reading alone when his mother, his brother and his sister came into ,";
        LineSpanable lineSpanable = new LineSpanable(this);

        Spanned spanned = lineSpanable.init(textContent, "was reading", "alone", "his");
//        SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder("");
//        spannableStringBuilder.append(spanned);
//        textView.setText(spannableStringBuilder);
        textView.setText(spanned);
    }

}
