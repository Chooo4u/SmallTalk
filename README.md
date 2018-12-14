# SmallTalk
This is a group project of the course MIS571 in WPI. Thanks to my group member Renee's work on data processing and idea inspiration.

I. App Idea
 
  When your friends mention an entertainment-related topic (sports, movies, TV shows, music, etc.) that you are unfamiliar with, use Small Talk to get a quick overview of it so you don’t feel out of the loop. No need to filter through all the Google results while your friends have already moved onto the next topic of conversation.
  For example, let’s say your friends start talking about a movie called Fight Club. You haven’t seen it before, so you pull up the app to get the main gist of the movie. You didn’t know Brad Pitt starred in it, and now you can participate in the conversation since you know who he is.
  Another feature of the app is pre-filtering of information. For example, if you want to bet on the next basketball game of the season, you need to look up relevant statistics for athletes of both teams to make an informed decision. This app will let you search for the players either directly or indirectly from tournaments, awards, etc. then filter the information for ease of use.
  Depending on the sports/entertainment datasets available, we are hoping to incorporate information not just on media topics themselves, but on important people involved, such as athletes, actors, and music producers, as well as any awards given.
 
II. Dataset
 
  Our group has decided on the following two datasets to populate our database: the IMDb dataset (https://www.imdb.com/interfaces/) and the sports Stattleship API (http://developers.stattleship.com/). We will only be focusing on four sports (football, basketball, baseball, and hockey) and only store athlete and team information for one season. Movies and TV shows released before 1990 will not be included.
  We queried approximately 1.25GB of sports data from Stattleship’s API in JSON format. Although the data has not yet been piped into SQLite, some examples of our queries are shown below. We plan to hand-pick which information will be a part of our app.
