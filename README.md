MonkeyPox is a Java Spring Boot-based application designed to manage user registration, handle HTTP requests, and integrate with a multi-server world simulation. The application allows users to interact with a game world where they can create players, issue commands, and see their impact on the world. It also includes features for managing player health, stamina recovery, and state persistence.
Features

    User Registration: Allows new users to register through an HTML form.
    Player Management: Users can create and manage player profiles in the game world.
    Command Execution: Commands such as movement, interaction, and recovery are processed through the CommandFactory.
    Health and Stamina Recovery: A Delay mechanism is used to manage player stamina and health recovery over time.
    World Interaction: Players can interact with a simulated world where their actions influence both the player and the world state.
    RESTful API: Handles HTTP requests to interact with the world using Spring Boot controllers.

Tech Stack

    Java Spring Boot: Backend framework for managing requests and server interactions.
    Thymeleaf: For rendering HTML templates.
    Multi-Server Integration: Game logic integrated with a multi-threaded server to handle player interactions in real-time.
    HTML/JavaScript: Frontend with form submission and fetch API for sending commands to the server.

Getting Started

    Clone the repository:

    bash

git clone https://github.com/pmwelase/monkeypox-app.git

Navigate to the project directory:

bash

cd monkeypox-app

Install dependencies using Maven:

bash

mvn clean install

Start the application:

bash

mvn spring-boot:run

Access the app on your browser:

arduino

    http://localhost:8081/
    
Future Improvements

    Additional commands for interacting with the game world.
    More detailed world simulation with enhanced player features.
    Extended RESTful API for better third-party integration.

Contributing

Feel free to submit issues or pull requests to improve the app!

Let me know if you'd like any adjustments!
