Project 4 - SimpleTweets

SimpleTweets is an android app that allows a user to view home and mentions timelines, view user profiles with user timelines, as well as compose and post a new tweet. The app utilizes Twitter REST API.

Time spent: 40 hours spent in total

User Stories

The following required functionality is completed:

 User can sign in to Twitter using OAuth login process
 User can view the tweets from their home timeline
 RecyclerView is used to display listings of any tweets
 User is displayed the username, name, and body for each tweet
 User is displayed the relative timestamp for each tweet "8m", "7h"
 User can compose and post a new tweet
 User can click a "Compose" icon in the App Bar on the top right
 User can then enter a new tweet from a second activity and then post this to twitter
 User is taken back to home timeline with new tweet visible in timeline
 Newly created tweet should be manually inserted into the timeline and not rely on a full refresh
 User can switch between Timeline and Mention views using tabs.
 User can navigate to view their own profile
 User can click on the profile image in any tweet to see another user's profile.
New required functionalities:

 User can switch between Timeline and Mention views using tabs. (3 points)
 User can view their home timeline tweets.
 User can view the recent mentions of their username.
 User can compose tweets. See this conceptual guide for passing data into a timeline fragment.
 User can navigate to view their own profile (2 points)
 User can see picture, tagline, # of followers, # of following, and tweets on their profile.
 The users/verify_credentials endpoint can be used to access this information.
 User can click on the profile image in any tweet to see another user's profile. (3 points)
 User can see picture, tagline, # of followers, # of following, and tweets of clicked user.
 Profile view should include that user's timeline
 The users/show endpoint can be used to access this information.
The following optional features are implemented:

 While composing a tweet, user can see a character counter with characters remaining for tweet out of 140
 User can pull down to refresh tweets in either timeline.
 Improve the user interface and theme the app to feel twitter branded with colors and styles
 User can search for tweets matching a particular query and see results.
 When a network request is sent, user sees an indeterminate progress indicator
 User can "reply" to any tweet on their home timeline
 The user that wrote the original tweet is automatically "@" replied in compose
 User can click on a tweet to be taken to a "detail view" of that tweet
 User can take favorite (and unfavorite) or retweet actions on a tweet
 User can see embedded image media within the tweet item in list or detail view.
 Compose activity is replaced with a modal compose overlay.
 User can click a link within a tweet body on tweet details view. The click will launch the web browser with relevant page opened.
 Used Parcelable instead of Serializable leveraging the popular Parceler library when passing data between activities.
 Replaced all icon drawables and other static image assets with vector drawables where appropriate.
 User can view following / followers list through the profile of a user
 Apply the popular Butterknife annotation library to reduce view boilerplate.
 Implement collapse scrolling effects on the Twitter profile view using CoordinatorLayout.
 User can open the twitter app offline and see last loaded tweets. Persisted in SQLite tweets are refreshed on every application launch. While "live data" is displayed when app can get it from Twitter API, it is also saved for use in an offline mode.
The following additional features are implemented:

 User can view more tweets as they scroll with infinite pagination. Number of tweets is unlimited.
Video Walkthrough

Here's a walkthrough of implemented user stories:

<https://github.com/rubendicker/Twitter/blob/master/MySimpleTweets/demo.gif>

GIF created with LiceCap.

Notes

Describe any challenges encountered while building the app.

Open-source libraries used

Android Async HTTP - Simple asynchronous HTTP requests with JSON parsing
Glide - Image loading and caching library for Android
License

Copyright [2017] [Ruben Dicker]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
