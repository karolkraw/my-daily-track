CREATE TABLE IF NOT EXISTS sections (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    created DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS streak (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    start_date DATE NOT NULL,
    days LONG NOT NULL,
    section_id INT,
    CONSTRAINT fk_section_streak FOREIGN KEY (section_id)
            REFERENCES sections (id)
);

CREATE TABLE IF NOT EXISTS reflection (
    id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    created DATE NOT NULL UNIQUE,
    section_id INT,
    CONSTRAINT fk_section_reflection FOREIGN KEY (section_id)
            REFERENCES sections (id)
);
