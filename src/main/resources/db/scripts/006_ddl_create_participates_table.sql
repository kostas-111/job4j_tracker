CREATE TABLE if not exists participates (
                              id SERIAL PRIMARY KEY,
                              item_id INT NOT NULL REFERENCES items(id),
                              user_id INT NOT NULL REFERENCES users(id),
                              UNIQUE (item_id, user_id)
);