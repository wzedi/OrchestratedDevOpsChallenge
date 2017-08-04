package io.orchestrated.companyNews.objectStore.prevayler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import io.orchestrated.companyNews.models.NewsArticle;

public class PrevaylerObjectStoreTest {
    private PrevaylerObjectStore objectStore;

    @Before
    public void setUp() throws Exception {
        this.objectStore = new PrevaylerObjectStore();
    }

    @Test
    public void testStoreAnObject() throws Exception {

        String headline = "Headline 1";
        String story = "MELBOURNE - Company News site launched.";

        NewsArticle newsArticle = new NewsArticle(headline, story);

        int id = this.objectStore.store(newsArticle);

        NewsArticle retrievedNewsArticle = (NewsArticle)this.objectStore.get(id);
        assertEquals(id, retrievedNewsArticle.getId());
        assertEquals(headline, retrievedNewsArticle.getHeadline());
        assertEquals(story, retrievedNewsArticle.getStory());
    }

    @Test
    public void testDeleteAnObject() throws Exception {

        String headline = "Headline 1";
        String story = "MELBOURNE - Company News site launched.";

        NewsArticle newsArticle = new NewsArticle(headline, story);

        int id = this.objectStore.store(newsArticle);

        NewsArticle retrievedNewsArticle = (NewsArticle)this.objectStore.get(id);
        assertEquals(id, retrievedNewsArticle.getId());

        this.objectStore.delete(id);
        retrievedNewsArticle = (NewsArticle)this.objectStore.get(id);
        assertNull(retrievedNewsArticle);
    }
}
