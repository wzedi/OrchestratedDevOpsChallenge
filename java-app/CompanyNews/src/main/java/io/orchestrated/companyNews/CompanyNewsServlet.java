package io.orchestrated.companyNews;

import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import io.orchestrated.companyNews.models.NewsArticle;
import io.orchestrated.companyNews.objectStore.ObjectStore;
import io.orchestrated.companyNews.objectStore.Model;

import com.google.gson.Gson;

@WebServlet(
        name = "CompanyNewsServlet", 
        urlPatterns = {"news"}, 
        loadOnStartup = 1,
        initParams={ @WebInitParam(name="objectStoreClass", value="io.orchestrated.companyNews.objectStore.prevayler.PrevaylerObjectStore") }
) 
public class CompanyNewsServlet extends HttpServlet {
    private ObjectStore objectStore;

    /**
     * Suppress unchecked/unsafe operations warning for reflection operations
     */
    @SuppressWarnings("unchecked")
    public void init(ServletConfig config) {
        String objectStoreClassName = config.getInitParameter("objectStoreClass");
        this.setObjectStoreClass(objectStoreClassName);
    }

    protected void setObjectStoreClass(String objectStoreClassName)
    {
        try {
            Class objectStoreClass = Class.forName(objectStoreClassName);
            Constructor objectStoreConstructor = objectStoreClass.getConstructor();
            this.objectStore = (ObjectStore)objectStoreConstructor.newInstance();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("application/json");
        Collection<Model> newsArticles = this.objectStore.getList();
        ArrayList<String> headlines = new ArrayList<String>();
        for (Model newsArticle : newsArticles) {
            headlines.add(((NewsArticle)newsArticle).getHeadline());
        }
        Gson gson = new Gson();
        response.getWriter().print(gson.toJson(headlines.toArray()));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null) name = "World";
        request.setAttribute("user", name);
        request.getRequestDispatcher("response.jsp").forward(request, response); 
    }
}
