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

<header></header>
<main></main>
<footer>
    <div>
     <p>&copy; 2025 All rights reserved. Tennis Dashboard.</p>
    </div>
</footer>
</body>
</html>
