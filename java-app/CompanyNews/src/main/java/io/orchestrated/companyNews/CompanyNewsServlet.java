package io.orchestrated.companyNews;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CompanyNewsServlet", urlPatterns = {"news"}, loadOnStartup = 1) 
public class CompanyNewsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("application/json");
        response.getWriter().print("[\"First headline\",\"Second headline\", \"Third headline\", \"Fourth headline\", \"Fifth headline\"]");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null) name = "World";
        request.setAttribute("user", name);
        request.getRequestDispatcher("response.jsp").forward(request, response); 
    }
}
