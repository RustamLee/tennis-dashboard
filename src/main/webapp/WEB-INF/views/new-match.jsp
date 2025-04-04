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
                <button id="playButton" type="submit" class="button new-match-button" >Play</button>
            </form>

        </div>
    </div>
</main>
<%@ include file="partials/footer.jsp" %>
<script>
    function validateInputs() {
        const player1 = document.getElementById("player1").value.trim();
        const player2 = document.getElementById("player2").value.trim();
        const message = document.getElementById("message1");
        const playButton = document.getElementById("playButton");

        const nameRegex = /^[\p{L}]{2,15}$/u;

        let errorMessage = "Enter the names of the players. Names cannot be the same";

        if (!player1 || !player2) {
            errorMessage = "Names cannot be empty or spaces only";
        } else if (!nameRegex.test(player1) || !nameRegex.test(player2)) {
            errorMessage = "Names must contain only letters and be 2-15 characters long";
        } else if (player1.toLowerCase() === player2.toLowerCase()) {
            errorMessage = "Names cannot be the same";
        } else {
            message.textContent = "Enter the names of the players. Names cannot be the same";
            playButton.disabled = false;
            return;
        }

        message.textContent = errorMessage;
        playButton.disabled = true;
    }
</script>
</body>
</html>
