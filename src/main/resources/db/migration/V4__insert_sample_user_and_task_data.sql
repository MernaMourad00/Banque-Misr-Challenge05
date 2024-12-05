INSERT INTO users (username, email, password, role)
VALUES
    ('user1', 'user1@gmail.com', 'password123', 'USER'),
    ('user2', 'user2@gmail.com', 'password456', 'USER'),
    ('admin', 'admin@example.com', 'adminpassword', 'ADMIN');


INSERT INTO task (title, description, priority, status, due_date, user_id)
VALUES
    ('Task 1', 'Task 1 description', 'HIGH', 'TODO', '2024-12-10', 1),
    ('Task 2', 'Task 2 description', 'MEDIUM', 'DONE', '2024-12-12', 1),
    ('Task 3', 'Task 3 description', 'LOW', 'IN_PROGRESS', '2024-12-15', 2),
    ('Task 4', 'Task 4 description', 'HIGH', 'TODO', '2024-12-18', 2),
    ('Task 5', 'Task 5 description', 'MEDIUM', 'DONE', '2024-12-20', 3),
    ('Task 6', 'Task 6 description', 'LOW', 'TODO', '2024-12-22', 3),
    ('Task 7', 'Task 7 description', 'HIGH', 'IN_PROGRESS', '2024-12-25', 1),
    ('Task 8', 'Task 8 description', 'MEDIUM', 'TODO', '2024-12-28', 2),
    ('Task 9', 'Task 9 description', 'LOW', 'DONE', '2024-12-30', 3);
