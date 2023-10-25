-- Create a database for your web application
CREATE DATABASE hardcycle_database;

-- Use the created database
USE hardcycle_database;

-- Create a table to store user information
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100),
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- Add other user-related fields as needed
);

-- Create a table to store user activities and earned points
CREATE TABLE user_activities (
    activity_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    activity_type ENUM('benchmark', 'answer', 'question', 'content', 'milestone', 'consistency', 'sharing') NOT NULL,
    activity_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- Add other activity-related fields as needed
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

-- Create a table to store user milestones and their completion status
CREATE TABLE user_milestones (
    milestone_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    milestone_name VARCHAR(100) NOT NULL,
    is_completed BOOLEAN DEFAULT FALSE,
    -- Add other milestone-related fields as needed
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

-- Create a table for leaderboard rankings
CREATE TABLE leaderboard (
    leaderboard_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    points INT,
    ranking_position INT,
    category VARCHAR(50), -- Category-specific leaderboard or overall
    -- Add other leaderboard-related fields as needed
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);


