package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ReplyActivity extends AppCompatActivity {

    // EditText etCompose;
    String tweetText;
    TwitterClient client;
    private final int REQUEST_CODE = 20;
    private TextView mTextView;
    EditText etReply;
    Tweet replyTweet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        client = TwitterApp.getRestClient();
        etReply = (EditText) findViewById(R.id.etReply);
        tweetText = etReply.getText().toString();



        mTextView = (TextView) findViewById(R.id.tvCounter);
        etReply.addTextChangedListener(tvCounter);


        replyTweet = getIntent().getParcelableExtra("tweet");

        etReply.setText("@" + replyTweet.user.screenName + " ");


        // the line below makes my app crash
        // setContentView(R.layout.activity_timeline);}
    }


    private final TextWatcher tvCounter = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            mTextView.setText(String.valueOf(140 - s.length()));
        }

        public void afterTextChanged(Editable s) {
        }
    };

    public void onSubmit(View v) {

        etReply = (EditText) findViewById(R.id.etReply);

        tweetText = etReply.getText().toString();

        client.replyTweet(tweetText, replyTweet, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.d("TwitterClient", response.toString());

                try {
                    Tweet tweet = Tweet.fromJSON(response);
                    Intent i = new Intent();
                    i.putExtra("tweet", tweet);
                    setResult(20, i);
                    //startActivityForResult(i, REQUEST_CODE);

                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


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
