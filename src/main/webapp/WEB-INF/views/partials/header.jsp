<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= request.getAttribute("title") != null ? request.getAttribute("title") : "Турнир" %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
</head>
<body>
<header class="header">
    <div class="header-container">
        <a href="<%= request.getContextPath() %>/" class="about-link">About</a>
        <a href="<%= request.getContextPath() %>/" class="header-logo-link">
            <i class="ri-ping-pong-line header-logo"></i>
        </a>
        <nav class="nav-menu">
            <ul>
                <li><a href="<%= request.getContextPath() %>/">Home</a></li>
                <li><a href="<%= request.getContextPath() %>/new-match">New match</a></li>
                <li><a href="<%= request.getContextPath() %>/matches">Match history</a></li>
            </ul>
        </nav>
    </div>
</header>


<main></main>
</body>
</html>
