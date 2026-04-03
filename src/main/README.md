## Student Information

Name: Mihret Girum
Id: ATE/1607/14

## Project Description

This project is a Spring Boot application implementing a simple product catalogue system for ShopWave. It includes REST APIs for managing products, searching, and updating stock, following a layered architecture (Controller, Service, Repository, Model)

## How to Build

Make sure Java 21 and Maven are installed, then run:

mvn clean install

## How to Run

Run the application using:

mvn spring-boot:run

The application will start at:

http://localhost:8080

## How to Run Tests

To run tests, use:

mvn test

## API Testing

You can test the API using Postman.

Example endpoints:

GET /api/products
POST /api/products
GET /api/products/{id}
PATCH /api/products/{id}/stock
GET /api/products/search

## Academic Integrity & AI Declaration
In accordance with the assignment guidelines, I confirm that this project and the accompanying report are my own work.

Generative AI tools (ChatGPT and Google Gemini) were used only as learning and support tools during development. Specifically, they were used to:

Clarify core concepts related to Spring Boot, REST APIs, and layered architecture
Assist in understanding and fixing configuration issues (e.g., Maven setup, application startup errors)
Provide guidance on structuring code (entities, services, controllers) and best practices
Help debug errors encountered during development

All final code, design decisions, and implementation were written, reviewed, and understood by me.
