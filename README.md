Task Management System

Overview:

The Task Management System is a Spring Boot-based application designed to manage tasks for users. The application allows creating, updating, deleting, retrieving tasks based on various filters (such as title, status, due date, etc.),and send reminders before the deadline of the tasks.

Features:

Create Task: Create a new task and assign it to a user.
Update Task: Modify the details of an existing task.
Delete Task: Remove a task from the system.
Get Tasks: Retrieve tasks based on different filters like title, description, status, and due date.
Assign Tasks to Users: Tasks can be assigned to users and queried by user ID.
Status Management: Tasks can be marked as DONE or other statuses as per business logic.
Email Notifications: Sends email notifications to users one day before the task deadline using SMTP.
Task Deadline Notification Scheduler: A scheduler automatically sends notifications to users about tasks with upcoming deadlines (1 day before).

Technologies Used:

Java version: 17
Spring Boot version: 3.4.0
Spring Data JPA.
MySql database.
Flyway: Used for database migrations to insert initial users and tasks. This data can be used to test the application, but due to password encryption issues, new users must be registered and logged in to obtain a token for API testing.
MapStruct.
JUnit & Mockito: For unit testing and mocking dependencies.
SMTP (Simple Mail Transfer Protocol).
Spring Scheduler:.
ModelMapper: For mapping DTOs to entities and vice versa.


Notes for Reviewers:

Flyway: Flyway is used for database migrations to create and insert some initial users and tasks for testing purposes. Although the data inserted with Flyway can be used to view the tasks, to obtain a token and perform authentication, you will need to register a new user and log in due to password encryption. The Flyway data is mainly there to demonstrate my ability to use Flyway for database migrations.

Unit Testing: Unit tests have been implemented for some service functions to verify their correctness. However, not all functions have been tested, and more tests can be added if needed. The tests mainly focus on key logic within the service layer.

Pagination: Pagination is implemented to return a Page object, which contains not just the data but also important metadata like the total number of elements and the total pages. Since I am working as a full-stack developer, returning a Page is more convenient for front-end integration as it provides all the necessary details. However, if a different structure (such as returning just a list of tasks) is preferred, I can modify this behavior to return a list of tasks with a specific page size and number.

History Table: Regarding the "history table" mentioned in the requirements, I was uncertain about the exact meaning, so I implemented it as a notification history. This stores notifications for each user, which can be useful for tracking events related to their tasks. If a different implementation of a history table was expected, I would be happy to adjust the approach.

Task Notification Scheduler: A Spring scheduler is implemented to automatically send notifications to users who have tasks due the following day. The task deadline is checked every day at 7pm, and if there are tasks with a due date of the next day, the system sends out an email reminder to the assigned users.

Discussion and Feedback: I value any feedback or discussion about this task, whether I pass or not. Constructive feedback helps me improve and refine my skills, and I look forward to hearing your thoughts on the implementation and areas where I can grow.
