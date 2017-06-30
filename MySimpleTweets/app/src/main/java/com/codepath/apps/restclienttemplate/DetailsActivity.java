package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DetailsActivity extends AppCompatActivity {

    Tweet tweet;
    ImageView ivProfileImage;
    TextView tvUserName;
    TextView tvBody;
    TextView tvScreenName;
    TextView tvCreatedAt;
    Button btnReply;
    Button btnRT;
    Button btnFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvScreenName = (TextView) findViewById(R.id.tvScreenName);
        tvCreatedAt = (TextView) findViewById(R.id.tvCreatedAt);
        tvBody = (TextView) findViewById(R.id.tvBody);
        btnReply = (Button) findViewById(R.id.btnReply);
        btnRT = (Button) findViewById(R.id.btnRT);
        btnFav = (Button) findViewById(R.id.btnFav);

        tweet = getIntent().getParcelableExtra("tweet");
        tvUserName.setText(tweet.user.name);
        tvBody.setText(tweet.body);
        tvScreenName.setText("@" + tweet.user.screenName);
        tvCreatedAt.setText("• " + getRelativeTimeAgo(tweet.createdAt));


        Glide.with(this)
                .load(tweet.user.profileImageUrl)
                .bitmapTransform(new RoundedCornersTransformation(this, 15, 0))
                .into(ivProfileImage);


        btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailsActivity.this, ReplyActivity.class);
                i.putExtra("tweet", tweet);
                startActivity(i);
            }
        });


     /*

        btnRT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailsActivity.this, RetweetActivity.class);
                i.putExtra("tweet", tweet);
                startActivity(i);
            }
        });

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailsActivity.this, FavoriteActivity.class);
                i.putExtra("tweet", tweet);
                startActivity(i);
            }
        });



*/



    }

    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}






