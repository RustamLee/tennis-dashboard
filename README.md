# Tennis Match Scoreboard

A web application that implements a scoreboard for tennis matches.

## Project Overview
The **Tennis Match Scoreboard** is a client-server application with a web interface that allows users to track and manage tennis matches in real time. The project follows the **MVCS architectural pattern** and does not use frameworks to provide hands-on experience with servlets, JSP, and manual frontend development.

## Features
### Match Management
- Create a new match
- View completed matches and search by player names
- Track and update scores in an ongoing match

### Tennis Scoring System
The application follows a simplified version of the **official tennis scoring system**:
- Matches are played in a **best-of-3 sets** format
- A **tiebreaker to 7 points** is played if a set reaches **6-6**
- Standard tennis point progression: **15 → 30 → 40 → game** (win requires a two-point lead at deuce)

### User Interface
- **Home Page:** Links to start a new match or view past matches
- **New Match Page:** Input fields for player names and a "Start" button
- **Match Score Page:** Displays current score and allows users to update points
- **Completed Matches Page:** Displays a list of finished matches with pagination and search functionality

## Technologies Used
### Backend:
- **Java Servlets & JSP** for handling HTTP requests and rendering views
- **Hibernate ORM** for database interaction
- **H2 Database** (in-memory SQL database) for storing completed matches
- **Maven** for project dependency management
- **JUnit 5** for unit testing

### Frontend:
- **HTML/CSS** for layout and styling
- **Basic JavaScript** for UI enhancements (e.g., responsive navigation menu)

### Deployment:
- **Apache Tomcat** as the application server
- **Linux Server** for hosting the application
- **Manual WAR deployment** via command-line tools

## Deployment Process
1. **Build the WAR file** locally using Maven
2. **Set up a cloud Linux server** with Java and Tomcat installed
3. **Deploy the WAR file** to the Tomcat webapps directory
4. **Access the application** at `http://$server_ip:8080/$app_root_path`

## Testing
The project includes **unit tests** to verify the correctness of the tennis scoring system. Example test cases:
- If Player 1 wins a point at **40-40**, the game is not immediately won (deuce rule applies)
- If Player 1 wins a point at **40-0**, they win the game
- If the score reaches **6-6**, a tiebreak begins instead of a normal game

## Motivation
- Gain hands-on experience with **Java Servlets, JSP, and Hibernate**
- Implement a **web-based** client-server application
- Learn **manual HTML/CSS layout** without frameworks
- Understand the **MVCS architecture**
- Practice **manual deployment** on a cloud server
