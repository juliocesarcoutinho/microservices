-- Create databases for each microservice
CREATE DATABASE IF NOT EXISTS email_db;
CREATE DATABASE IF NOT EXISTS user_db;

-- Create users and grant privileges
CREATE USER IF NOT EXISTS 'email_user'@'%' IDENTIFIED BY 'email_password';
GRANT ALL PRIVILEGES ON email_db.* TO 'email_user'@'%';

-- Create user for user-service
CREATE USER IF NOT EXISTS 'user_service'@'%' IDENTIFIED BY 'user_password';
GRANT ALL PRIVILEGES ON user_db.* TO 'user_service'@'%';

-- Add more databases and users as needed for your other microservices
-- CREATE DATABASE IF NOT EXISTS other_service_db;
-- CREATE USER IF NOT EXISTS 'other_user'@'%' IDENTIFIED BY 'other_password';
-- GRANT ALL PRIVILEGES ON other_service_db.* TO 'other_user'@'%';

FLUSH PRIVILEGES;
