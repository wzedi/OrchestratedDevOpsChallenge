package io.orchestrated.companyNews;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CompanyNewsServlet", urlPatterns = {"news"}, loadOnStartup = 1) 
public class CompanyNewsServlet extends HttpServlet {
}
