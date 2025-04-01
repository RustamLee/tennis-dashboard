<%-- final score page --%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>New Match</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
<%@ include file="partials/header.jsp" %>
<main>
  <div class="final-container">
    <p class="score-subtitle">
      <i class="ri-trophy-line"></i> Match Winner: ${matchScore.match.winner.name}!
    </p>
    <a href="${pageContext.request.contextPath}/new-match" class="button">New Match</a>
  </div>
</main>
<%@ include file="partials/footer.jsp" %>
</body>
</html>
