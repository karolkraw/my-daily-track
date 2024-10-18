CREATE TABLE IF NOT EXISTS sections (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    created DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS streaks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    start_date DATE NOT NULL,
    days LONG NOT NULL,
    section_id INT,
    CONSTRAINT fk_section_streak FOREIGN KEY (section_id)
            REFERENCES sections (id)
);

CREATE TABLE IF NOT EXISTS reflections (
    id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    created DATE NOT NULL UNIQUE,
    section_id INT,
    CONSTRAINT fk_section_reflection FOREIGN KEY (section_id)
            REFERENCES sections (id)
);

CREATE TABLE IF NOT EXISTS goals (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title varchar(255) NOT NULL,
    description text NOT NULL,
    completed_date date NOT NULL,
    created_date date NOT NULL,
    section_id INT,
    CONSTRAINT fk_section_goal FOREIGN KEY (section_id)
                REFERENCES sections (id)
);

CREATE TABLE IF NOT EXISTS subtasks (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    goal_id bigint NOT NULL,
    title varchar(255) NOT NULL,
    description text NOT NULL,
    completed_date date NOT NULL,
    created_date date NOT NULL,
    CONSTRAINT fk_goal FOREIGN KEY (goal_id)
            REFERENCES goals (id)
);

CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles (
    user_id INT NOT NULL,
    role VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(id)
);