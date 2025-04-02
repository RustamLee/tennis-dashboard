<%-- main page --%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>tennis-dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">

</head>
<body>
<%@ include file="partials/header.jsp" %>
<main>
   <div class="ball rotation">
       <img class="ball-img" src="${pageContext.request.contextPath}/images/ball.png">
   </div>
   <div class="ball1 rotation1">
       <img class="ball-img" src="${pageContext.request.contextPath}/images/ball.png">
   </div>

    <div class="container">
        <div class="image-container">
            <img class="image" src="${pageContext.request.contextPath}/images/main-page.jpg">
        </div>
      <div class="buttons-container">
        <a href="${pageContext.request.contextPath}/new-match" class="button">New Match</a>
        <a href="${pageContext.request.contextPath}/matches" class="button">Finished Matches</a>
      </div>
    </div>
    <h1></h1>
</main>
    <%@ include file="partials/footer.jsp" %>
</body>
</html>
