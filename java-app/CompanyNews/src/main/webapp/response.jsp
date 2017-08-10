<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
    <head>
        <title>Company News</title>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <img src="${assetUrl}/company_news.jpg"/>
                </tr>
                <tr>
                    <th>Headline</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${articles}" var="article">

                <tr>      
                    <c:choose>
                    <c:when test="${fn:length(articles) > 0}">
                    <td><a href="#" onclick="document.getElementById('headline').value = '${article.getHeadline()}'; document.getElementById('story').value = '${article.getStory()}';">${article.getHeadline()}</a></td>
                    </c:when>
                    <c:otherwise>
                    <td>No articles found.</td>
                    </c:otherwise>
                    </c:choose>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <form method="post" action="news">  
            <table>
                <tbody>
                    <tr>
                        <td>
                            <h2>Post a new article</h2>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" id="headline" name="headline" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <textarea id="story" name="story" cols="80" rows="10"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" id="poststory" value="Post article" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
