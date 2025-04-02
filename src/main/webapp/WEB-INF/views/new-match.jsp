<%-- the page for create a new match --%>

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
            <p class="subtitle" id="message1">Enter the names of the players. Names cannot be the same</p>
            <form action="${pageContext.request.contextPath}/new-match" method="POST" class="form">
                <div class="input-container">
                    <i class="ri-user-line"></i>
                    <input type="text" id="player1" name="playerOne" required placeholder="player's name" oninput="validateInputs()">
                </div>
                <div class="input-container">
                    <i class="ri-user-line"></i>
                    <input type="text" id="player2" name="playerTwo" required placeholder="player's name" oninput="validateInputs()">
                </div>
                <button id="playButton" type="submit" class="button new-match-button" disabled>Play</button>
            </form>

        </div>
    </div>
</main>
<%@ include file="partials/footer.jsp" %>
</body>
</html>
