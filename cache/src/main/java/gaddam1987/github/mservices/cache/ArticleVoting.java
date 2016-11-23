package gaddam1987.github.mservices.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@Component
public class ArticleVoting implements CommandLineRunner {
    private final String votes = "article:%s:votes";
    private final String headline = "article:%s:headline";

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void run(String... strings) throws Exception {
        reset();
        populateArticles(articleList());
        upVote("12345");
        upVote("12345");
        upVote("12345");
        upVote("60056");
        upVote("10001");
        upVote("10001");

        downVote("10001");

        printResults("12345");
        printResults("10001");
        printResults("60056");
    }

    private void reset() {
        redisTemplate.delete(Arrays.asList("article:12345:headline", "article:10001:headline", "article:60056:headline", "article:12345:votes", "article:10001:votes", "article:60056:votes"));
    }

    private void upVote(String articleId) {
        redisTemplate.opsForValue().increment(format(votes, articleId), 1L);
    }

    private void downVote(String articleId) {
        redisTemplate.opsForValue().increment(format(votes, articleId), -1L);
    }

    private Map<String, String> articleList() {
        Map<String, String> listOfArticles = new HashMap<>();
        listOfArticles.put("article:12345:headline", "Google Wants to Turn Your Clothes Into a Computer");
        listOfArticles.put("article:10001:headline", "For Millennials, the End of the TV Viewing Party");
        listOfArticles.put("article:60056:headline", "Alicia Vikander, Who Portrayed Denmark's Queen, Is Screen Royalty");

        return listOfArticles;
    }

    private void printResults(String id) {
        List<String> strings = redisTemplate.opsForValue().multiGet(Arrays.asList(format(headline, id), format(votes, id)));
        System.out.println(format("The article %s has %s votes", strings.get(0), strings.get(1)));
    }


    private void populateArticles(Map<String, String> articleList) {
        redisTemplate.opsForValue().multiSet(articleList);
    }
}
