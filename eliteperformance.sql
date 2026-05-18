-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2026 at 03:41 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eliteperformance`
--

-- --------------------------------------------------------

--
-- Table structure for table `bonus_records`
--

CREATE TABLE `bonus_records` (
  `bonus_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  `review_year` int(11) NOT NULL,
  `total_kpi_score` decimal(5,2) NOT NULL,
  `category` varchar(20) NOT NULL,
  `bonus_percentage` decimal(5,2) NOT NULL,
  `bonus_amount` decimal(15,2) NOT NULL,
  `total_compensation` decimal(15,2) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bonus_records`
--

INSERT INTO `bonus_records` (`bonus_id`, `employee_id`, `review_year`, `total_kpi_score`, `category`, `bonus_percentage`, `bonus_amount`, `total_compensation`, `created_at`) VALUES
(1, 1, 2025, 92.00, 'Gold Tier', 20.00, 10000.00, 60000.00, '2026-05-17 17:18:03');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `employee_id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `designation` varchar(100) NOT NULL,
  `base_salary` decimal(15,2) NOT NULL,
  `role` enum('EMPLOYEE','MANAGER','ADMIN') NOT NULL,
  `last_promotion_date` date DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employee_id`, `name`, `designation`, `base_salary`, `role`, `last_promotion_date`, `username`, `password`) VALUES
(1, 'Alice Johnson', 'Software Engineer', 50000.00, 'EMPLOYEE', '2024-01-15', 'alice', 'password123'),
(2, 'Bob Smith', 'Team Lead', 75000.00, 'MANAGER', '2026-05-17', 'bob', 'password123'),
(3, 'Carol White', 'HR Director', 90000.00, 'ADMIN', '2022-03-20', 'carol', 'password123');

-- --------------------------------------------------------

--
-- Table structure for table `performance_reviews`
--

CREATE TABLE `performance_reviews` (
  `review_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  `review_year` int(11) NOT NULL,
  `task_completion` decimal(5,2) NOT NULL,
  `attendance` decimal(5,2) NOT NULL,
  `team_collaboration` decimal(5,2) NOT NULL,
  `problem_solving` decimal(5,2) NOT NULL,
  `communication` decimal(5,2) NOT NULL,
  `leadership` decimal(5,2) NOT NULL,
  `client_satisfaction` decimal(5,2) NOT NULL,
  `total_kpi_score` decimal(5,2) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `performance_reviews`
--

INSERT INTO `performance_reviews` (`review_id`, `employee_id`, `review_year`, `task_completion`, `attendance`, `team_collaboration`, `problem_solving`, `communication`, `leadership`, `client_satisfaction`, `total_kpi_score`, `created_at`) VALUES
(1, 1, 2025, 24.00, 14.00, 13.00, 14.00, 9.00, 9.00, 9.00, 92.00, '2026-05-17 17:18:03'),
(2, 2, 2026, 24.00, 14.00, 13.00, 14.00, 9.00, 9.00, 9.00, 92.00, '2026-05-17 17:30:45');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bonus_records`
--
ALTER TABLE `bonus_records`
  ADD PRIMARY KEY (`bonus_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employee_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `performance_reviews`
--
ALTER TABLE `performance_reviews`
  ADD PRIMARY KEY (`review_id`),
  ADD UNIQUE KEY `unique_review` (`employee_id`,`review_year`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bonus_records`
--
ALTER TABLE `bonus_records`
  MODIFY `bonus_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `employee_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `performance_reviews`
--
ALTER TABLE `performance_reviews`
  MODIFY `review_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bonus_records`
--
ALTER TABLE `bonus_records`
  ADD CONSTRAINT `bonus_records_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`);

--
-- Constraints for table `performance_reviews`
--
ALTER TABLE `performance_reviews`
  ADD CONSTRAINT `performance_reviews_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
