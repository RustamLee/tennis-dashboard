<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>New Match</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
</head>
<body>
<%@ include file="partials/header.jsp" %>
<main>
  <div class="container">
    <div class="image-container">
      <img class="image" src="${pageContext.request.contextPath}/images/new-match.jpg" alt="new game image">
    </div>
    <div class="form-container">
      <h2>Start a New Match</h2>
      <form action="/new-match" method="POST">
        <label for="player1">Player 1:</label>
        <input type="text" id="player1" name="player1" required>

        <label for="player2">Player 2:</label>
        <input type="text" id="player2" name="player2" required>

        <button type="submit" class="button">Play</button>
      </form>
    </div>
  </div>
</main>
<%@ include file="partials/footer.jsp" %>
</body>
</html>
