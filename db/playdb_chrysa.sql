-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 08, 2014 at 12:14 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `playdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `devicemodel`
--

CREATE TABLE IF NOT EXISTS `devicemodel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `devicemodel`
--

INSERT INTO `devicemodel` (`id`, `createdOn`, `description`, `image`, `lastUpdated`, `name`, `displayName`) VALUES
(1, '2014-06-02 02:10:02', 'Testing 5S', 'apple-iphone-5s-grey-front-hires.jpg', '2014-06-02 02:10:02', 'iPhone-5S', 'iPhone 5S'),
(2, '2014-06-02 02:10:02', 'Testing 5S', 'iPhone4.png', '2014-06-02 02:10:02', 'iPhone-4', 'iPhone 4'),
(3, '2014-06-02 02:10:02', 'Testing 5S', 'iPhone5.jpg', '2014-06-02 02:10:02', 'iPhone-5', 'iPhone 5');

-- --------------------------------------------------------

--
-- Table structure for table `devicemodel_devicerepair`
--

CREATE TABLE IF NOT EXISTS `devicemodel_devicerepair` (
  `DeviceModel_id` bigint(20) NOT NULL,
  `deviceRepairList_id` bigint(20) NOT NULL,
  UNIQUE KEY `deviceRepairList_id` (`deviceRepairList_id`),
  KEY `FK605FD40FDC7B0C40` (`deviceRepairList_id`),
  KEY `FK605FD40FD7A08F16` (`DeviceModel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `devicemodel_devicerepair`
--

INSERT INTO `devicemodel_devicerepair` (`DeviceModel_id`, `deviceRepairList_id`) VALUES
(1, 2),
(2, 1),
(2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `devicerepair`
--

CREATE TABLE IF NOT EXISTS `devicerepair` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` float NOT NULL,
  `repairTime` varchar(255) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `devicerepair`
--

INSERT INTO `devicerepair` (`id`, `createdOn`, `description`, `image`, `lastUpdated`, `name`, `price`, `repairTime`, `displayName`) VALUES
(1, NULL, 'Repair broken iPhone 4 Screen', 'iPhone4-brokenScreen.jpg', NULL, 'iPhone-4-Broken-Screen', 40, '30 min', 'iPhone 4 Broken Screen'),
(2, NULL, 'iPhone 5S Power button repair with new button', NULL, NULL, 'iPhone-5S-Power-button', 30.5, '25 min', 'iPhone 5S Power button'),
(3, NULL, 'iPhone 4 Power button repair with new button', 'iPhone4-powerButton.jpg', NULL, 'iPhone-4-Power-button', 30.5, '25 min', 'iPhone 4 Power button');

-- --------------------------------------------------------

--
-- Table structure for table `manufacturer`
--

CREATE TABLE IF NOT EXISTS `manufacturer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `manufacturer`
--

INSERT INTO `manufacturer` (`id`, `createdOn`, `description`, `image`, `lastUpdated`, `name`, `displayName`) VALUES
(1, NULL, 'Testing with apple description', 'Lg-logo.jpg', NULL, 'LG', 'LG'),
(2, NULL, 'Testing with apple description', 'HTC-logo.jpg', NULL, 'HTC', 'HTC'),
(5, NULL, 'Testing with apple description', 'Samsung-logo.jpeg', NULL, 'Samsung', 'Samsung'),
(6, '2014-06-01 17:37:57', 'Testing with apple description', 'Apple-logo.png', '2014-06-01 17:37:57', 'Apple', 'Apple'),
(7, '2014-06-01 17:37:57', 'Testing with apple description', 'Huawei-logo.jpg', '2014-06-01 17:37:57', 'Huawei', 'Huawei');

-- --------------------------------------------------------

--
-- Table structure for table `manufacturer_devicemodel`
--

CREATE TABLE IF NOT EXISTS `manufacturer_devicemodel` (
  `Manufacturer_id` bigint(20) NOT NULL,
  `deviceModels_id` bigint(20) NOT NULL,
  UNIQUE KEY `deviceModels_id` (`deviceModels_id`),
  KEY `FK91A78F05DF348649` (`deviceModels_id`),
  KEY `FK91A78F0535C6653E` (`Manufacturer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `manufacturer_devicemodel`
--

INSERT INTO `manufacturer_devicemodel` (`Manufacturer_id`, `deviceModels_id`) VALUES
(6, 1),
(6, 2),
(6, 3);

-- --------------------------------------------------------

--
-- Table structure for table `technician`
--

CREATE TABLE IF NOT EXISTS `technician` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  `isExternal` tinyint(1) DEFAULT NULL,
  `contact` text,
  `schedule` text,
  `workingHours` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `devicemodel_devicerepair`
--
ALTER TABLE `devicemodel_devicerepair`
  ADD CONSTRAINT `FK605FD40FD7A08F16` FOREIGN KEY (`DeviceModel_id`) REFERENCES `devicemodel` (`id`),
  ADD CONSTRAINT `FK605FD40FDC7B0C40` FOREIGN KEY (`deviceRepairList_id`) REFERENCES `devicerepair` (`id`);

--
-- Constraints for table `manufacturer_devicemodel`
--
ALTER TABLE `manufacturer_devicemodel`
  ADD CONSTRAINT `FK91A78F0535C6653E` FOREIGN KEY (`Manufacturer_id`) REFERENCES `manufacturer` (`id`),
  ADD CONSTRAINT `FK91A78F05DF348649` FOREIGN KEY (`deviceModels_id`) REFERENCES `devicemodel` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
