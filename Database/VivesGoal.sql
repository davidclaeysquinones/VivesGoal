/*sql script to create the necessary database*/

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databank: `VivesGoal`
--
CREATE DATABASE IF NOT EXISTS `VivesGoal` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `VivesGoal`;

-- --------------------------------------------------------

CREATE TABLE persoon (
id int(11) NOT NULL AUTO_INCREMENT, 
naam varchar(32) NOT NULL, 
voornaam varchar(32) NOT NULL, 
geboortedatum date NOT NULL, 
opmerking text, 
isTrainer tinyint(1) NOT NULL, 
ploeg_id int(11), 
CONSTRAINT voornaam PRIMARY KEY (id)
);

CREATE TABLE ploeg (
id int(11) NOT NULL AUTO_INCREMENT, 
naam varchar(32) NOT NULL, 
niveau enum('U6', 'U7', 'U8', 'U9','U10', 'U11') NOT NULL,
trainer_id int(11), 
PRIMARY KEY (id)
);

ALTER TABLE persoon 
ADD INDEX `is speler van` (ploeg_id), 
ADD CONSTRAINT `is speler van` FOREIGN KEY (ploeg_id) REFERENCES ploeg (id);

ALTER TABLE ploeg ADD INDEX `heeft als trainer` (trainer_id), ADD CONSTRAINT `heeft als trainer` FOREIGN KEY (trainer_id) 
REFERENCES persoon (id);

