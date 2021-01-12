# wingspanscores

```
CREATE TABLE players (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL
)

INSERT INTO players (name) VALUES ('Automa')

CREATE TABLE scores (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    player_id INTEGER NOT NULL,
    birds INTEGER NOT NULL,
    bonus INTEGER NOT NULL,
    round INTEGER NOT NULL,
    eggs INTEGER NOT NULL,
    food INTEGER NOT NULL,
    tucked INTEGER NOT NULL,
    total INTEGER NOT NULL,
    rank INTEGER NOT NULL
)

CREATE TABLE `histories` (
    `id` INTEGER PRIMARY KEY AUTOINCREMENT
)
```