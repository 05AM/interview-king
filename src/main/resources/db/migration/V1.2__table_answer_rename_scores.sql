ALTER TABLE `answer`
    CHANGE COLUMN `score` `score` INT NOT NULL,
    CHANGE COLUMN `logic_score` `logic_score` INT NOT NULL,
    CHANGE COLUMN `accuracy` `accuracy_score` INT NOT NULL,
    CHANGE COLUMN `structure` `structure_score` INT NOT NULL,
    CHANGE COLUMN `practicality` `practicality_score` INT NOT NULL;
