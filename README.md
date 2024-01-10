# TeacherCrudApp

**Overview**

TeacherCrudApp is a Full-Stack Web Application developed as part of the Java Full Stack Web Programming course of AUEB,
October 2023. This application allows users to perform CRUD operations (Create, Read, Update, Delete) on a database of
teachers. Various technologies are being used, including **Spring Boot**, **Thymeleaf**, **Hibernate**, and **Spring
Security**.

**Tools Used**

- **IDE:** IntelliJ IDEA 2023.1
- **Database:** MySQL Workbench 8.0.30
- **Version Control:** GitHub
- **Build Tool:** Maven
- **Password Encoding:** [bcrypt-generator.com](https://bcrypt-generator.com/)


**Technologies Used**

- **Java Version:** Amazon-Corretto 17.0.7
- **Spring Boot:** 2.7.18
- **Spring Security:** 2.5.2
- **Spring OAuth2:** OAuth 2.0 authentication
- **Thymeleaf:** Template engine for server-side rendering
- **Hibernate:** ORM framework

**Application Features**

1. **Login: (http://localhost:8080/teacherapp/login)**
    - Access the application by providing a valid username or email along with a password. (See at the end of this file)

2. **Main Dashboard: (http://localhost:8080/teacherapp/index)**
    - Upon successful login, you will be automatically redirected to the main dashboard.
    - Two buttons, **FetchApiAjax** and **XMLHttpsRequest Ajax**, lead to pages (http://localhost:8080/teacherapp/fetchApiAjax 
      and http://localhost:8080/teacherapp/XMLHttpsRequestAjax respectively) demonstrating different Ajax approaches,
      while keeping the same functionality. 

3. **CRUD Operations:**
    - Each Ajax demonstration page includes the same table with a search bar, add, edit, and delete buttons.
    - Perform CRUD operations on the teacher data.

4. **Navigation:**
    - Use the **"Teachers CRUD Application"** on the topbar to navigate back to the main dashboard.
    - Sign out using the **"Sign Out"** button, which redirects to the login screen.


**Database Setup**

- The MySQL database schema used in this project can be found in the repository as a dump file (`teachersapp.sql`).
- The application is configured to connect to a localhost database. To set up the database, import the provided
  schema dump, and update the database credentials in the `application.properties` file located in the Spring project.
- Test User Credentials:
    - Username: **admin**
    - Password: **admin2024!**
    - Email: **adminTest@test.com**
