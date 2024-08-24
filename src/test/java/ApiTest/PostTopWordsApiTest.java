package ApiTest;

import io.qameta.allure.Allure;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ph.salmon.api.entity.DataItem;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Data

public class PostTopWordsApiTest extends BaseApiTest {// не стал упорядочивать по классам и разбивать саму проверку и реализацию в разные. не вижу смысла

    @Test
    public void countWords() {
        List<DataItem> posts = postsController.getAllPosts();
        String allBodies = posts.stream()
                .map(DataItem::getBody)
                .collect(Collectors.joining(" "));
        String[] words = allBodies.split("\\W+");
        Map<String, Long> wordCounts = Arrays.stream(words)
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
        List<Map.Entry<String, Long>> topWords = wordCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toList());
        topWords.forEach(entry -> loggers(entry.getKey() + " - " + entry.getValue()));
        // checking
        assertFalse(topWords.isEmpty());
        for (int i = 0; i < topWords.size() - 1; i++) {
            assertTrue(topWords.get(i).getValue() >= topWords.get(i + 1).getValue());
        }
    }

    private void loggers(String txt){
        log.info(txt);
        Allure.step(txt);
    }
}
