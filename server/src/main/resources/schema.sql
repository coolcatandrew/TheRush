DROP TABLE IF EXISTS Player;
CREATE TABLE Player (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR (100),
    team VARCHAR (3),
    position VARCHAR (2)
);
CREATE INDEX idxPlayerId ON Player(id);

DROP TABLE IF EXISTS RushingStatistic;
CREATE TABLE RushingStatistic (
    id INT AUTO_INCREMENT PRIMARY KEY,
    playerId INT,
    averageAttempts DOUBLE,
    attempts INT,
    yards INT,
    averageYards DOUBLE,
    yardsPerGame DOUBLE,
    touchdowns INT,
    longest VARCHAR(5),
    firstDowns INT,
    firstDownPercentage DOUBLE,
    twentyPlusYards INT,
    fortyPlusYards INT,
    fumbles INT,
    created DATE default CURRENT_DATE
);
CREATE INDEX idxStatisticId ON RushingStatistic(id);
CREATE INDEX idxStatisticPlayerId ON RushingStatistic(playerId);