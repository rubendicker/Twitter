package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by rdicker on 7/3/17.
 */

public class TweetsPagerAdapter extends FragmentPagerAdapter {


    private String tabTitles[] = new String[] {"Home", "Mentions"};
    private Context context;

    private HomeTimelineFragment timelineFragment;
    private MentionsTimelineFragment mentionsFragment;



    public TweetsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

        timelineFragment = new HomeTimelineFragment();
        mentionsFragment = new MentionsTimelineFragment();
    }

    // return the total # of fragments
    @Override
    public int getCount() {
        return 2;
    }

    // return the fragment to use depending on the position

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {

            return timelineFragment;

            //return new HomeTimelineFragment();


        }
        else if (position == 1) {
            //return new MentionsTimelineFragment();

            return mentionsFragment;
        }
        else {
            return null;
        }
    }


    // return the fragment title for each tab


    @Override
    public CharSequence getPageTitle(int position) {
        // generate title based on item position
        return tabTitles[position];
    }
}
