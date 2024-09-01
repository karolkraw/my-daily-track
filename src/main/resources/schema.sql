CREATE TABLE IF NOT EXISTS streak_tracker (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    streak_created DATE NOT NULL,
    streak_length LONG NOT NULL
);