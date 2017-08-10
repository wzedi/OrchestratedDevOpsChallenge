package io.orchestrated.companyNews;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.ArrayList;

import io.orchestrated.companyNews.objectStore.ObjectStore;
import io.orchestrated.companyNews.objectStore.Model;
import io.orchestrated.companyNews.models.NewsArticle;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CompanyNewsServletTest {
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher requestDispatcher;
    @Mock private ObjectStore objectStore;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMustReturnHeadlines() throws Exception {
        when(request.getRequestDispatcher("response.jsp"))
            .thenReturn(requestDispatcher);

        when(request.getServerName())
            .thenReturn("localhost");

        Collection<Model> newsArticles = new ArrayList<Model>();
        NewsArticle newsArticle;
        for (int i = 0; i < 5; i++) {
            newsArticle = new NewsArticle();
            newsArticle.setHeadline("Headline " + i);
            newsArticle.setStory("Story " + i);
            newsArticles.add(newsArticle);
        }

        when(objectStore.getList()).
            thenReturn(newsArticles);

        CompanyNewsServlet servlet = new CompanyNewsServlet();
        servlet.setObjectStore(objectStore);
        servlet.doGet(request, response);

        verify(request).setAttribute("articles", newsArticles);
    }

    @Test
    public void doPostNewArticle() throws Exception {
        when(request.getParameter("headline")).thenReturn("headline");
        when(request.getParameter("story")).thenReturn("story");
        when(request.getRequestDispatcher("response.jsp"))
            .thenReturn(requestDispatcher);
        when(request.getServerName())
            .thenReturn("localhost");

        NewsArticle newsArticle = new NewsArticle();
        newsArticle.setHeadline(request.getParameter("headline"));
        newsArticle.setStory(request.getParameter("story"));

        Collection<Model> newsArticles = new ArrayList<Model>();
        newsArticles.add(newsArticle);

        when(objectStore.getList()).
            thenReturn(newsArticles);

        CompanyNewsServlet servlet = new CompanyNewsServlet();
        servlet.setObjectStore(objectStore);
        servlet.doPost(request, response);

        verify(requestDispatcher).forward(request,response);
        verify(request).setAttribute("articles", newsArticles);
    }
}
