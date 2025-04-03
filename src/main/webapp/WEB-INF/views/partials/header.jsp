<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const modal = document.getElementById("aboutModal");
        const btn = document.querySelector(".about-link");
        const closeBtn = document.querySelector(".close");

        btn.addEventListener("click", function (event) {
            event.preventDefault(); // Предотвращает переход по ссылке
            modal.style.display = "flex";
        });

        closeBtn.addEventListener("click", function () {
            modal.style.display = "none";
        });

        window.addEventListener("click", function (event) {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        });
    });
</script>

<body>
<header class="header">
    <div class="header-container">
    <div class ="left-container">
        <a href="<%= request.getContextPath() %>/" class="header-logo-link"><i class="ri-home-2-line"></i></a>
        <a href="<%= request.getContextPath() %>/" class="about-link">About</a>
    </div>
        <nav class="nav-menu">
            <ul>
                <li><a href="<%= request.getContextPath() %>/new-match">New match</a></li>
                <li><a href="<%= request.getContextPath() %>/matches">Match history</a></li>
            </ul>
        </nav>
    </div>
</header>
<!-- Modal (pop-up) -->
<div id="aboutModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h3 class="modal-title">Rules</h3>
        <p class="modal-text">
            This application helps track scores in a tennis match. The match follows a best-of-three sets format.
            To win a set, a player needs at least 6 games with a 2-game lead. If the score reaches 6-6, a tiebreak is played.
            Points increase from 15 to 30 to 40, and then the game is won. If both players reach 40, one must win by 2 consecutive points.
        </p>

    </div>
</div>

<main></main>
</body>
</html>
