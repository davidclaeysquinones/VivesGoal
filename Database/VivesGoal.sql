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

--
-- Tabelstructuur voor tabel `persoon`
--

DROP TABLE IF EXISTS `persoon`;
CREATE TABLE IF NOT EXISTS `persoon` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Naam` varchar(50) NOT NULL,
  `Voornaam` varchar(50) NOT NULL,
  `Geboortedatum` DATE NOT NULL,
  `TRAINER` BOOLEAN NOT NULL,
  `Opmerking` varchar(50),
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1  ;
-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `ploeg`
--

DROP TABLE IF EXISTS `ploeg`;
CREATE TABLE IF NOT EXISTS `ploeg` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Naam` varchar(50) NOT NULL,
  `Status` enum('U6','U7','U8','U9','U10','U11') NOT NULL,
  `trainer` int(11),
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1  ;
-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `ploeg/persoon`
--

DROP TABLE IF EXISTS `ploegpersoon`;
CREATE TABLE IF NOT EXISTS `ploegpersoon` (
  `ploeg` int(11) NOT NULL ,
  `speler` int(11) NOT NULL,
  PRIMARY KEY (`speler`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1  ;
