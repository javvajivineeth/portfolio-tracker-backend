# Portfolio Management Application

This project is a full-stack web application for managing a stock portfolio. It features a React frontend and a Spring Boot backend, and it integrates with the Alpha Vantage API to provide real-time stock prices.

## Table of Contents

*   [Features](#features)
*   [Prerequisites](#prerequisites)
*   [Project Structure](#project-structure)
*   [Backend Setup](#backend-setup)
    *   [API Key](#api-key)
    *   [Database Configuration](#database-configuration)
    *   [Running the Backend](#running-the-backend)
*   [Frontend Setup](#frontend-setup)
    *   [Running the Frontend](#running-the-frontend)
*   [How it Works](#how-it-works)
    *   [Backend](#backend)
    *   [Frontend](#frontend)
    *   [Real-Time Data](#real-time-data)
*   [Further Improvements](#further-improvements)
*   [Contributing](#contributing)
*   [License](#license)

## Features

*   **Portfolio Dashboard:** Displays the total portfolio value.
*   **Stock Management:**
    *   Add new stocks to the portfolio.
    *   Edit existing stock details.
    *   Delete stocks from the portfolio.
*   **Stock List:** Displays a table of current stock holdings.
*   **Real-Time Stock Prices:** Integrates with the Alpha Vantage API to fetch real-time prices.
*   **Random Portfolio:** Generates a random portfolio of 5 stocks for each user.
*   **Dynamic Portfolio Value Calculation:** Calculates the total portfolio value using real-time prices and a fixed quantity of 1 for each stock.

## Prerequisites

*   **Java Development Kit (JDK):** Version 11 or higher.
*   **Node.js and npm:** Node.js (version 16 or higher recommended) and npm (comes bundled with Node.js).
*   **Maven:** For building the backend (Spring Boot).
*   **Alpha Vantage API Key:** Obtain a free API key from [https://www.alphavantage.co/](https://www.alphavantage.co/).

## Project Structure

portfolio-management/
├── portfolio-backend/      (Spring Boot Application)
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── portfolio/
│   │   │   │           └── backend/
│   │   │   │               ├── config/       &lt;- Configuration files
│   │   │   │               │   └── AppConfig.java
│   │   │   │               ├── controller/   &lt;- REST Controller
│   │   │   │               │   └── PortfolioController.java
│   │   │   │               ├── exception/    &lt;- Exception Handlers
│   │   │   │               │   └── GlobalExceptionHandler.java
│   │   │   │               ├── model/        &lt;- Entity (Database Model)
│   │   │   │               │   └── Stock.java
│   │   │   │               ├── repository/   &lt;- JPA Repository
│   │   │   │               │   └── StockRepository.java
│   │   │   │               ├── service/      &lt;- Business Logic Services
│   │   │   │               │   ├── AlphaVantageService.java
│   │   │   │               │   └── PortfolioService.java
│   │   │   │               └── BackendApplication.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   │       └── java/
│   │           └── ...
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
└── portfolio-frontend/     (React Application)
├── public/
│   ├── index.html
│   └── ...
├── src/
│   ├── components/
│   │   ├── Dashboard.js
│   │   ├── StockForm.js
│   │   ├── StockList.js
│   │   └── CreatePortfolio.js
│   ├── App.js
│   ├── index.js
│   └── ...
├── package.json
├── package-lock.json
├── .gitignore
└── README.md


## Backend Setup

### API Key

1.  Obtain an API key from Alpha Vantage: [https://www.alphavantage.co/support/#api-key](https://www.alphavantage.co/support/#api-key)
2.  Open `portfolio-backend/src/main/resources/application.properties`.
3.  Replace `YOUR_ALPHA_VANTAGE_API_KEY` with your actual API key:

    ```properties
    alpha-vantage.api.key=YOUR_ALPHA_VANTAGE_API_KEY
    ```

### Database Configuration

The backend uses an H2 in-memory database by default for development. You can change this to MySQL, PostgreSQL, or another database by modifying the `application.properties` file.

**For H2 (In-Memory):**

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
For MySQL (Example):

Add the MySQL driver dependency to pom.xml.   
Update application.properties:
<!-- end list -->

Properties

spring.datasource.url=jdbc:mysql://localhost:3306/portfolio_db
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
Important:

Create the database portfolio_db in your MySQL instance.
Change your_db_user and your_db_password to your actual database credentials.
For production, change spring.jpa.hibernate.ddl-auto to validate or none.
Running the Backend
Open a terminal and navigate to the portfolio-backend directory.

Run the following command to start the Spring Boot application:

Bash

./mvnw spring-boot:run
(On Windows, use mvnw.cmd spring-boot:run)

The backend will start on port 8080.

Frontend Setup
Running the Frontend
Open a separate terminal and navigate to the portfolio-frontend directory.

Install the required npm packages:

Bash

npm install
Start the React application:

Bash

npm start
This will open the application in your default browser, usually at http://localhost:3000.

How it Works
Backend
The backend is built using Spring Boot and handles the following:

RESTful API: Exposes REST endpoints for managing the portfolio (adding, updating, deleting stocks) and fetching data.
Database Interaction: Uses Spring Data JPA and Hibernate to interact with the database (H2 by default).
Alpha Vantage Integration: The AlphaVantageService fetches real-time stock prices from the Alpha Vantage API using your API key.
Portfolio Logic: The PortfolioService handles the creation of a random portfolio, calculation of the total portfolio value using real-time prices, and other business logic.
CORS Configuration: Allows requests from the frontend running on http://localhost:3000 (configured in PortfolioController).
Frontend
The frontend is built using React and handles the following:

User Interface: Provides a user interface for interacting with the portfolio.
Components: Uses reusable components (e.g., Dashboard, StockForm, StockList, CreatePortfolio) for different parts of the application.
API Calls: Uses axios to make HTTP requests to the backend API endpoints.
State Management: Manages the application state using useState and useEffect hooks.
Real-Time Data
Portfolio Creation: When the "Create Portfolio" button is clicked, the frontend sends a request to the /api/portfolio/create endpoint.
Random Stock Selection: The PortfolioService selects 5 random stock tickers from a predefined list.
Real-time Price Fetching: The AlphaVantageService fetches the current price for each selected stock from the Alpha Vantage API.
Portfolio Value Calculation: The PortfolioService calculates the total portfolio value using the real-time prices and a quantity of 1 for each stock.
Data Display: The frontend fetches the portfolio data and displays it in the Dashboard and StockList components.
Further Improvements
Authentication and Authorization: Secure the application by adding user authentication and authorization.
API Key Security: Store the Alpha Vantage API key more securely (e.g., environment variables, secrets management service).
Rate Limiting and Caching: Implement caching and rate-limiting to handle API usage limits.
Error Handling: Improve error handling on both the frontend and backend.
UI/UX Enhancements: Add loading indicators, charts, better input validation, and improve the overall user experience.
Asynchronous Updates: Implement periodic updates or use WebSockets for a more real-time experience.
Stock Name Lookup: Fetch and display the actual stock names in addition to ticker symbols.
Testing: Add unit and integration tests for both frontend and backend.
Deployment: Deploy the application to a production environment.
Contributing
Contributions are welcome! If you find any bugs or want to add new features, please follow these steps:

Fork the repository.
Create a new branch for your feature or bug fix.
Make your changes and commit them with clear messages.   
Push your branch to your forked repository.   
Submit a pull request to the main repository.
License
This project is licensed under the MIT License - see the LICENSE file for details. (You'll need to create a LICENSE file if you want to specify a license).   


**Remember to:**

*   Replace `YOUR_ALPHA_VANTAGE_API_KEY` with your actual API key in the `application.properties` file.
*   Create a `LICENSE` file in the root of your project if you want to specify a license (e.g., MIT, GPL).
*   Adapt the database configuration if you are not using H2.
*   Consider adding a `.gitignore` file to both the `portfolio-backend` and `portfolio-frontend` directories to exclude unnecessary files from version control (e.g., `node_modules`, build directories).
