CREATE TABLE employees (
    employee_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    designation VARCHAR(100) NOT NULL,
    base_salary DECIMAL(15,2) NOT NULL,
    role ENUM('EMPLOYEE', 'MANAGER', 'ADMIN') NOT NULL,
    last_promotion_date DATE,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE performance_reviews (
    review_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    review_year INT NOT NULL,
    task_completion DECIMAL(5,2) NOT NULL,
    attendance DECIMAL(5,2) NOT NULL,
    team_collaboration DECIMAL(5,2) NOT NULL,
    problem_solving DECIMAL(5,2) NOT NULL,
    communication DECIMAL(5,2) NOT NULL,
    leadership DECIMAL(5,2) NOT NULL,
    client_satisfaction DECIMAL(5,2) NOT NULL,
    total_kpi_score DECIMAL(5,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    UNIQUE KEY unique_review (employee_id, review_year)
);

CREATE TABLE bonus_records (
    bonus_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    review_year INT NOT NULL,
    total_kpi_score DECIMAL(5,2) NOT NULL,
    category VARCHAR(20) NOT NULL,
    bonus_percentage DECIMAL(5,2) NOT NULL,
    bonus_amount DECIMAL(15,2) NOT NULL,
    total_compensation DECIMAL(15,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

INSERT INTO employees (name, designation, base_salary, role, last_promotion_date, username, password) VALUES
('Alice Johnson', 'Software Engineer', 50000.00, 'EMPLOYEE', '2024-01-15', 'alice', 'password123'),
('Bob Smith', 'Team Lead', 75000.00, 'MANAGER', '2023-06-01', 'bob', 'password123'),
('Carol White', 'HR Director', 90000.00, 'ADMIN', '2022-03-20', 'carol', 'password123');