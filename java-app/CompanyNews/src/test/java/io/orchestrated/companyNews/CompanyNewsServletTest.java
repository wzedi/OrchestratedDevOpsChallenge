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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.google.gson.Gson;

public class CompanyNewsServletTest {
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMustReturnHeadlines() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);

        new CompanyNewsServlet().doGet(request, response);
        String[] headlines = (String[])this.parseResponse(stringWriter);
        assertEquals(5, headlines.length);
        assertEquals("First headline", headlines[0]);
        assertEquals("Second headline", headlines[1]);
        assertEquals("Third headline", headlines[2]);
        assertEquals("Fourth headline", headlines[3]);
        assertEquals("Fifth headline", headlines[4]);
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

    private Object parseResponse(StringWriter stringWriter) {
        Gson gson = new Gson();
        return gson.fromJson(stringWriter.toString(), String[].class);
    }
}
