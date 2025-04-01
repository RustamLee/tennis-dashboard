<%-- page with created match --%>

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
    <div class="ball rotation">
        <img class="ball-img" src="${pageContext.request.contextPath}/images/ball.png">
    </div>
    <div class="ball1 rotation1">
        <img class="ball-img" src="${pageContext.request.contextPath}/images/ball.png">
    </div>
    <div class="table-container">
        <table class="content-table">
            <thead>
            <tr>
                <th>Player</th>
                <th>Sets</th>
                <th>Games</th>
                <th>Points</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${matchScore.match.player1.name}</td>
                <td>${matchScore.setsPlayer1}</td>
                <td>${matchScore.gamesPlayer1}</td>
                <td>${matchScore.pointsPlayer1}</td>
                <td><button type="button" onclick="window.location.href='/match-score?player=1'">Score</button></td>
            </tr>
            <tr>
                <td>${matchScore.match.player2.name}</td>
                <td>${matchScore.setsPlayer2}</td>
                <td>${matchScore.gamesPlayer2}</td>
                <td>${matchScore.pointsPlayer2}</td>
                <td><button type="button" onclick="window.location.href='/match-score?player=2'">Score</button></td>
            </tr>
            </tbody>
        </table>

    </div>
</main>
<%@ include file="partials/footer.jsp" %>
</body>
</html>
