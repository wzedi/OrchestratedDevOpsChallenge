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

        CompanyNewsServlet servlet = new CompanyNewsServlet();

        servlet.setObjectStoreClass("io.orchestrated.companyNews.objectStore.prevayler.PrevaylerObjectStore");
        servlet.doGet(request, response);

        String[] headlines = (String[])this.parseResponse(stringWriter);
        assertEquals(0, headlines.length);
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
