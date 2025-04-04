<%-- page with created match --%>

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
    <div class="table-container">
        <p class="score-subtitle">
            <c:choose>
                <c:when test="${not empty matchScore.match.winner}">
                    Match Winner: ${matchScore.match.winner.name}!
                </c:when>
                <c:when test="${matchScore.tieBreak}">
                    TIE-BREAK in progress!
                </c:when>
                <c:otherwise>
                    Current match in progress!
                </c:otherwise>
            </c:choose>
        </p>

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
                <td>
                    <c:choose>
                        <c:when test="${matchScore.tieBreak}">
                            ${matchScore.tieBreakPointsPlayer1}
                        </c:when>
                        <c:when test="${matchScore.player1HasAdvantage}">
                            AD
                        </c:when>
                        <c:otherwise>
                            ${matchScore.pointsPlayer1}
                        </c:otherwise>
                    </c:choose>
                </td>


                <td>
                    <form action="${pageContext.request.contextPath}/match-score" method="POST">
                        <input type="hidden" name="uuid" value="${matchScore.match.matchUuid}">
                        <input type="hidden" name="winner" value="player1">
                        <button type="submit">Score</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td>${matchScore.match.player2.name}</td>
                <td>${matchScore.setsPlayer2}</td>
                <td>${matchScore.gamesPlayer2}</td>
                <td>
                    <c:choose>
                        <c:when test="${matchScore.tieBreak}">
                            ${matchScore.tieBreakPointsPlayer2}
                        </c:when>
                        <c:when test="${matchScore.player2HasAdvantage}">
                            AD
                        </c:when>
                        <c:otherwise>
                            ${matchScore.pointsPlayer2}
                        </c:otherwise>
                    </c:choose>
                </td>


                <td>
                    <form action="${pageContext.request.contextPath}/match-score" method="POST">
                        <input type="hidden" name="uuid" value="${matchScore.match.matchUuid}">
                        <input type="hidden" name="winner" value="player2">
                        <button type="submit">Score</button>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>

    </div>
</main>
<%@ include file="partials/footer.jsp" %>
</body>
</html>

