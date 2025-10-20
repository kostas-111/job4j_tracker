CREATE TABLE if not exists user_notification (
                                     id SERIAL PRIMARY KEY,
                                     messenger TEXT,
                                     identify TEXT,
                                     user_id INT REFERENCES users(id)
);