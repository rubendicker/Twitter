package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

   // EditText etCompose;
    String tweetText;
    TwitterClient client;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        client = TwitterApp.getRestClient();
      //  etCompose = (EditText) findViewById(R.id.etCompose);
        //tweetText = etCompose.getText().toString();
        // the line below makes my app crash
        // setContentView(R.layout.activity_timeline);

    }

    public void onSubmit(View v) {

        EditText etCompose = (EditText) findViewById(R.id.etCompose);

        tweetText = etCompose.getText().toString();

        client.sendTweet(tweetText, new JsonHttpResponseHandler() {
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




        // closes the activity and returns to first screen
        //this.finish();
    }

}
