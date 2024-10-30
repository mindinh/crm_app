<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/buoi27/index.css" type="text/css">
<title>Modern News Page</title>
</head>
<body>
	<header>
        <div class="logo">
            <h1>News Today</h1>
        </div>
        <nav>
            <ul>
                <li><a href="#">Home</a></li>
                <li><a href="#">World</a></li>
                <li><a href="#">Technology</a></li>
                <li><a href="#">Sports</a></li>
                <li><a href="#">Entertainment</a></li>
            </ul>
        </nav>
    </header>
    
    <main>
    	<section class="news-grid">
    		<c:forEach items="${ newsList }" var="item">
	            <article class="news-item">
	                <img src="${ item.img_url }"
	                    alt="News Image 1">
	                <div class="news-content">
	                    <h2>${ item.news_title }</h2>
	                    <p>${ item.news_content }
	                    </p>
	                    <a href="#" class="read-more">Read more</a>
	                </div>
	            </article>
			</c:forEach>
            
        </section>
    </main>

    <footer>
        <p>© 2024 News Today. All rights reserved.</p>
    </footer>
    
</body>
</html>