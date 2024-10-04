# Backend Test - Barcelona Activa Hackathon 2024

This repository contains the backend for the **Barcelona Activa Hackathon 2024**. The application is built using **Spring Boot**, **MongoDB**, and **Gradle** as the build system.

## Table of Contents
- [Requirements](#requirements)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Testing with Postman](#testing-with-postman)
- [Endpoints](#endpoints)

## Requirements

To run this project, make sure you have the following installed on your machine:

1. [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or newer
2. [Gradle](https://gradle.org/install/)
3. [MongoDB](https://www.mongodb.com/try/download/community) (Ensure that MongoDB is running locally or available on a cloud provider like MongoDB Atlas)
4. [Postman](https://www.postman.com/downloads/) (optional, for testing)

## Installation

### 1. Clone the repository

```bash
git clone https://github.com/Alalilacias/Backend-Test-Barcelona-Activa-Hackaton-2024.git
cd Backend-Test-Barcelona-Activa-Hackaton-2024
```

### 2. Set up MongoDB

Ensure MongoDB is installed and running. If you're using a local MongoDB instance, it should be accessible at the default URL (`mongodb://localhost:27017`). If you're using MongoDB Atlas, ensure the connection string is updated in the `application.properties` file located in `src/main/resources/`.

In `application.properties`, update:

```properties
spring.data.mongodb.uri = mongodb://<your_mongodb_uri>
```

### 3. Build the project

Build the project using Gradle:

```bash
./gradlew build
```

## Running the application

Run the Spring Boot application:

```bash
./gradlew bootRun
```

The application should now be running at `http://localhost:8080`.

## Testing with Postman

### 1. Import the Collection

1. Copy the entire [Collection JSON File](https://github.com/Alalilacias/Backend-Test-Barcelona-Activa-Hackaton-2024/blob/main/src/test/Postman/Barcelona%20Activa%20Hackathon%202024%20API%20Copy.postman_collection.json) to the clipboard.
2. Open Postman, navigate to a new workspace, open the collection tab, and click on **Import**.
3. Paste the copied text and allow Postman to configure itself.

### 2. Import the Environment

1. Copy the contents of the [Environment JSON File](https://github.com/Alalilacias/Backend-Test-Barcelona-Activa-Hackaton-2024/blob/main/src/test/Postman/Test%20Environment.postman_environment.json) to the clipboard.
2. Open Postman, navigate to a new workspace, open the environment tab, and click on **Import**.
3. Paste the copied text and allow Postman to configure itself.

### 3. Run the Tests

After importing the collection and environment, ensure the backend is running (`http://localhost:8080`). Then, follow these steps:

1. Select the **Barcelona Activa Hackathon 2024** collection.
2. Click **Run** to execute all the endpoints.

This will automatically test all the backend's endpoints and verify their responses.

## Endpoints

Here are the key endpoints exposed by this API.

### **Activity Endpoints**

| **Endpoint**                                    | **Method** | **Description**              | **Request Body** (if applicable)         | **Response**        |
|-------------------------------------------------|------------|------------------------------|------------------------------------------|---------------------|
| `/appActivitats/activity`                       | `POST`     | Create a new activity             | JSON with activity details (`name`, `description`, `capacity`) | `200 OK` with activity data |
| `/appActivitats/activities`                     | `GET`      | Retrieve all activities           | -                                        | `200 OK` with a list of activities |
| `/appActivitats/activities/{id}`                | `GET`      | Retrieve a specific activity      | -                                        | `200 OK` with activity data |
| `/appActivitats/activities/{id}`                | `PUT`      | Update an activity                | JSON with updated activity details       | `200 OK` with updated activity data |
| `/appActivitats/activities/{id}`                | `DELETE`   | Delete an activity by ID          | -                                        | `204 No Content`    |
| `/appActivitats/activities/{id}/users/{idUser}` | `POST`     | Enroll a user to an activity      | -                                        | `200 OK` with updated activity data |
| `/appActivitats/importActivities`               | `POST`     | Create activities from JSON string| Raw JSON string                          | `200 OK` with a list of activities |

### **User Endpoints**

| **Endpoint**              | **Method** | **Description**              | **Request Body** (if applicable)         | **Response**        |
|---------------------------|------------|------------------------------|------------------------------------------|---------------------|
| `/appActivitats/user`              | `POST`     | Register a new user              | JSON with user details (`name`, `surname`, `contactInformation`) | `200 OK` with user data |
| `/appActivitats/users/{id}`        | `GET`      | Retrieve a specific user         | -                                        | `200 OK` with user data |
| `/appActivitats/users`             | `GET`      | Retrieve all users               | -                                        | `200 OK` with a list of users |
| `/appActivitats/users/{id}`        | `PUT`      | Update user details              | JSON with updated user details           | `200 OK` with updated user data |
| `/appActivitats/users/{id}`        | `DELETE`   | Delete a user by ID              | -                                        | `204 No Content`    |

### **DTOs**

#### **ActivityDTO**
- Fields: `id`, `name`, `description`, `capacity`, `placesLeft`, `enrolledUsers` (Set of `UserReference`)

#### **ActivityRegistrationRequest**
- Fields: `name`, `description`, `capacity`

#### **UserDTO**
- Fields: `name`, `surname`, `email`

#### **UserRequest**
- Fields: `name`, `surname`, `contactInformation`

---
