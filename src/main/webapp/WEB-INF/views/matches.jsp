<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Match History</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
</head>
<body>
<%@ include file="partials/header.jsp" %>
<main>
  <div class="matches-container">
  <h2>Match History</h2>

  <form action="${pageContext.request.contextPath}/matches" method="get">
    <div class="search-bar">
    <input type="text" name="filter_by_player_name" placeholder="player's name" value="${param.filter_by_player_name}">
    <button class="button search-button" type="submit">Search</button>
    </div>
  </form>
  <table class="content-table matches-table">
    <thead>
    <tr>
      <th>Winner</th>
      <th>Player 1</th>
      <th>Player 2</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${matches}" var="match">
      <tr>
        <td>${match.winner.name}</td>
        <td>${match.player1.name}</td>
        <td>${match.player2.name}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
    <div class="pagination">
      <c:if test="${currentPage > 1}">
        <a href="?page=${currentPage - 1}&filter_by_player_name=${filter_by_player_name}">Previous</a>
      </c:if>

      <c:forEach begin="1" end="${totalPages}" var="i">
        <a href="?page=${i}&filter_by_player_name=${filter_by_player_name}">${i}</a>
      </c:forEach>

      <c:if test="${currentPage < totalPages}">
        <a href="?page=${currentPage + 1}&filter_by_player_name=${filter_by_player_name}">Next</a>
      </c:if>
    </div>
  </div>
</main>
<%@ include file="partials/footer.jsp" %>
</body>
</html>
