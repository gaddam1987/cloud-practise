package gaddam1987.github.mservices.cache;

import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class ShowHighlyVotedArticles {
    private static final int ONE_WEEK_IN_SECONDS = 7 * 86400;
    private static final int VOTE_SCORE = 432;
    private static final int ARTICLES_PER_PAGE = 25;
    /**
     * 1000 articles are subitted per day
     *
     * Top 50 voted are displayed
     *
     * 50 should have atleast 200 votes
     *
     * score of an item is function of time that article posted + constant mutiplier times the number of votes that article received
     *
     */


    public String postArticle(Jedis conn, String user, String title, String link) {
        String articleId = String.valueOf(conn.incr("article:"));

        String voted = String.valueOf(conn.incr("article:"));

        conn.sadd(voted, user);
        conn.expire(voted, ONE_WEEK_IN_SECONDS);

        long now = System.currentTimeMillis() / 1000;
        String article = "article:" + articleId;
        HashMap<String,String> articleData = new HashMap<String,String>();
        articleData.put("title", title);
        articleData.put("link", link);
        articleData.put("user", user);
        articleData.put("now", String.valueOf(now));
        articleData.put("votes", "1");
        conn.hmset(article, articleData);
        conn.zadd("score:", now + VOTE_SCORE, article);
        conn.zadd("time:", now, article);

        return articleId;

    }
}
