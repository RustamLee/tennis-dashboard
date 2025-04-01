<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Match History</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="partials/header.jsp" %>
<main>
  <h1>Match History</h1>

  <!-- Форма поиска по имени игрока -->
  <form action="${pageContext.request.contextPath}/matches" method="get">
    <input type="text" name="filter_by_player_name" placeholder="Enter player name" value="${param.filter_by_player_name}">
    <button type="submit">Search</button>
  </form>

  <!-- Таблица завершенных матчей -->
  <table>
    <thead>
    <tr>
      <th>ID</th>
      <th>Player 1</th>
      <th>Player 2</th>
      <th>Winner</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="match" items="${matches}">
      <tr>
        <td>${match.id}</td>
        <td>${match.player1.name}</td>
        <td>${match.player2.name}</td>
        <td>${match.winner.name}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <!-- Пагинация -->
  <div class="pagination">
    <c:if test="${page > 1}">
      <a href="${pageContext.request.contextPath}/matches?page=${page - 1}&filter_by_player_name=${param.filter_by_player_name}">Previous</a>
    </c:if>
    <span>Page ${page} of ${totalPages}</span>
    <c:if test="${page < totalPages}">
      <a href="${pageContext.request.contextPath}/matches?page=${page + 1}&filter_by_player_name=${param.filter_by_player_name}">Next</a>
    </c:if>
  </div>
</main>
<%@ include file="partials/footer.jsp" %>
</body>
</html>
