package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * Created by rdicker on 6/26/17.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {

    private  List<Tweet> mTweets;
    Context context;

    // pass in the Tweets array into the constructor
    public TweetAdapter(List<Tweet> tweets) {
        mTweets = tweets;
    }

    // inflate the layout and cache references into ViewHolder class for each row

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_tweet, parent, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }

    // bind the value of the tweet object to elements

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // get the data according to position
        Tweet tweet = mTweets.get(position);

        // populate the views according to this data
        holder.tvUsername.setText(tweet.user.name);
        holder.tvScreenName.setText("@" + tweet.user.screenName);
        holder.tvCreatedAt.setText("• " + getRelativeTimeAgo(tweet.createdAt));
        holder.tvBody.setText(tweet.body);






        Glide.with(context)
                .load(tweet.user.profileImageUrl)
                .bitmapTransform(new RoundedCornersTransformation(context, 15, 0))
                .into(holder.ivProfileImage);




        if (tweet.retweeted == false) {
            holder.btnRT.setText("retweet");
        }
        else {
            holder.btnRT.setText("unretweet");
        }




        if (tweet.faved == false) {
            holder.btnFav.setText("favorite");
        }
        else {
            holder.btnFav.setText("unfavorite");
        }


    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    // create the ViewHolder class

    public  class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivProfileImage;
        public TextView tvUsername;
        public TextView tvBody;
        public TextView tvScreenName;
        public TextView tvCreatedAt;
        public Button btnReply;

        Button btnRT;
        Button btnFav;
        TwitterClient client;
        // Tweet tweet;

        public RelativeLayout myTweet;

        public ViewHolder(View itemView) {
            super(itemView);

            // perform findViewByID lookups
            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUserName);
            tvScreenName = (TextView) itemView.findViewById(R.id.tvScreenName);
            tvCreatedAt = (TextView) itemView.findViewById(R.id.tvCreatedAt);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            btnReply = (Button) itemView.findViewById(R.id.btnReply);

            btnRT = (Button) itemView.findViewById(R.id.btnRT);
            btnFav = (Button) itemView.findViewById(R.id.btnFav);
            client = TwitterApp.getRestClient();
            // tweet = getIntent().getParcelableExtra("tweet");

            //int pos = getAdapterPosition();
            //tweet = mTweets.get(pos);


            myTweet = (RelativeLayout) itemView.findViewById(R.id.myTweet);

            btnReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent i = new Intent(context, ReplyActivity.class);
                    i.putExtra("tweet", mTweets.get(pos));
                    context.startActivity(i);
                }
            });

            ivProfileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent i = new Intent(context, ProfileActivity.class);
                    i.putExtra("tweet", mTweets.get(pos));
                    i.putExtra("onMyProfile", false);
                    context.startActivity(i);
                }
            });

            myTweet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent i = new Intent(context, DetailsActivity.class);
                    i.putExtra("tweet", mTweets.get(pos));
                    context.startActivity(i);
                }
            });











//            if (tweet.retweeted == false) {
//                btnRT.setText("retweet");
//            }
//            else {
//                btnRT.setText("unretweet");
//            }





            btnRT.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition();
                    final Tweet tweet = mTweets.get(pos);



                    if(tweet.retweeted == false) {
                        client.retweet(tweet, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                Log.d("TwitterClient", response.toString());
                                btnRT.setText("Unretweet");
                                tweet.retweeted = true;
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                Log.d("TwitterClient", responseString);
                                throwable.printStackTrace();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                                Log.d("TwitterClient", errorResponse.toString());
                                throwable.printStackTrace();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                Log.d("TwitterClient", errorResponse.toString());
                                throwable.printStackTrace();
                            }
                        });

                    }

                    if(tweet.retweeted == true) {
                        client.unRetweet(tweet, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                Log.d("TwitterClient", response.toString());
                                btnRT.setText("Retweet");
                                tweet.retweeted = false;
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                Log.d("TwitterClient", responseString);
                                throwable.printStackTrace();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                                Log.d("TwitterClient", errorResponse.toString());
                                throwable.printStackTrace();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                Log.d("TwitterClient", errorResponse.toString());
                                throwable.printStackTrace();
                            }
                        });

                    }




                }
            });





//            if (tweet.faved == false) {
//                btnFav.setText("favorite");
//            }
//            else {
//                btnFav.setText("unfavorite");
//            }




            btnFav.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition();
                    final Tweet tweet = mTweets.get(pos);

                    if (tweet.faved == false) {



                        client.favTweet(tweet, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                tweet.faved = true;
                                btnFav.setText("unfavorite");
                                Log.d("TwitterClient", response.toString());
                                //Toast.makeText(DetailsActivity.this, "Favorited", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                Log.d("TwitterClient", responseString);
                                throwable.printStackTrace();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                                Log.d("TwitterClient", errorResponse.toString());
                                throwable.printStackTrace();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                Log.d("TwitterClient", errorResponse.toString());
                                throwable.printStackTrace();
                            }
                        });

                    }




                    else {
                        client.unFavTweet(tweet, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                tweet.faved = false;
                                btnFav.setText("favorite");
                                Log.d("TwitterClient", response.toString());
                                //Toast.makeText(DetailsActivity.this, "Unfavorited", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                Log.d("TwitterClient", responseString);
                                throwable.printStackTrace();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                                Log.d("TwitterClient", errorResponse.toString());
                                throwable.printStackTrace();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                Log.d("TwitterClient", errorResponse.toString());
                                throwable.printStackTrace();
                            }
                        });
                    }
                }
            });

















        }
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

    // Clean all elements of the recycler
    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        mTweets.addAll(list);
        notifyDataSetChanged();
    }
}
