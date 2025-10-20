CREATE TABLE if not exists users (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(2000),
                        role_id INT NOT NULL REFERENCES roles(id)
);