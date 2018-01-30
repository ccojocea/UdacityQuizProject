package com.example.android.ccojocea.javaquiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/**
 * Created by ccojo on 1/30/2018.
 */

public class AboutActivity extends AppCompatActivity{

    TextView tvClickable1, tvClickable2, tvClickable3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        Spanned clickableLink1 = Html.fromHtml(getString(R.string.logo_cc_2_link));
        Spanned clickableLink2 = Html.fromHtml(getString(R.string.logo_cc_5_link));
        Spanned clickableLink3 = Html.fromHtml(getString(R.string.logo_cc_7_link));

        tvClickable1 = findViewById(R.id.about_clickable_link_1);
        tvClickable2 = findViewById(R.id.about_clickable_link_2);
        tvClickable3 = findViewById(R.id.about_clickable_link_3);

        tvClickable1.setText(clickableLink1);
        tvClickable2.setText(clickableLink2);
        tvClickable3.setText(clickableLink3);

        tvClickable1.setTextColor(ContextCompat.getColor(this, R.color.colorSecondaryDark));

        tvClickable1.setMovementMethod(LinkMovementMethod.getInstance());
        tvClickable2.setMovementMethod(LinkMovementMethod.getInstance());
        tvClickable3.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
