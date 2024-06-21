# Chat Room Application

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Running the Application](#running-the-application)
- [How to Use](#how-to-use)
- [How It Works](#how-it-works)
    - [WebSocket Configuration](#websocket-configuration)
    - [Message Handling](#message-handling)
    - [User Management](#user-management)
    - [Persisting Data](#persisting-data)
- [Technologies Used](#technologies-used)

## Introduction
This is a simple chat room application built with Java and Spring Boot. The application allows users to send and receive messages in real-time using WebSockets. Users must enter their name to join the chat, and the chat history is stored in an H2 in-memory database. The application also displays a list of users who have posted messages and their last posted time.

## Features
- Real-time messaging using WebSocket
- User identification with username
- Display of users who have posted and their last posted time
- Persistence of chat messages in an H2 database
- Display of chat history for new users

## Getting Started

### Prerequisites
- Java 17+
- Maven
- An IDE (e.g., IntelliJ IDEA, Eclipse)

### Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/TheFiler/tilievchatwebapp
    ```
2. Navigate to the project directory:
    ```bash
    cd chat-room
    ```

### Running the Application
1. Compile and package the application using Maven:
    ```bash
    mvn clean install
    ```
2. Run the application:
    ```bash
    mvn spring-boot:run
    ```
3. Open your web browser and navigate to `http://localhost:8080`.

## How to Use
1. Enter your name in the input field and click "Send".
2. Type your message in the message input field and click the "Send" button or press Enter.
3. You will see your messages and messages from other users in the chat area.
4. The list of users who have posted messages and their last posted time will be displayed above the chat area.

## How It Works

### WebSocket Configuration
The application uses Spring Boot's WebSocket support to enable real-time communication. The `WebSocketConfig` class configures the WebSocket endpoint and message broker:
```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS().setInterceptors(new UserHandshakeInterceptor());
    }
}
