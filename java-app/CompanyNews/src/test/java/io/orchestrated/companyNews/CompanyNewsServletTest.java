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
    public void doPostWithoutName() throws Exception {
        when(request.getRequestDispatcher("response.jsp"))
            .thenReturn(requestDispatcher);

        new CompanyNewsServlet().doPost(request, response);

        verify(request).setAttribute("user", "World");
        verify(requestDispatcher).forward(request,response);
    }

    @Test
    public void doPostWithName() throws Exception {
        when(request.getParameter("name")).thenReturn("Dolly");
        when(request.getRequestDispatcher("response.jsp"))
            .thenReturn(requestDispatcher);

        new CompanyNewsServlet().doPost(request, response);

        verify(request).setAttribute("user", "Dolly");
        verify(requestDispatcher).forward(request,response);
    }
}
