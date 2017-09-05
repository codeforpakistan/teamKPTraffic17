-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 03, 2017 at 06:16 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kp_traffic_police`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_login`
--

CREATE TABLE IF NOT EXISTS `admin_login` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(40) NOT NULL,
  `admin_email` varchar(50) NOT NULL,
  `admin_password` varchar(30) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin_login`
--

INSERT INTO `admin_login` (`admin_id`, `admin_name`, `admin_email`, `admin_password`) VALUES
(1, 'admin', 'admin@admin.com', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `challan`
--

CREATE TABLE IF NOT EXISTS `challan` (
  `challan_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `district` varchar(50) NOT NULL,
  `to_name` varchar(50) NOT NULL,
  `duty_point` varchar(60) NOT NULL,
  `ticket_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`challan_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `challan`
--

INSERT INTO `challan` (`challan_id`, `date`, `district`, `to_name`, `duty_point`, `ticket_id`, `amount`, `status`) VALUES
(1, '2009-03-09 06:32:03', 'Peshawar', 'Mokamil Khan', 'Jail Chowk', 2087, 100, 'Direct Pay'),
(2, '2009-03-04 06:52:28', 'Peshawar', 'Rehmat wali', 'Transfer Officers', 2022, 100, 'Paid'),
(3, '2009-03-08 06:52:32', 'Peshawar', 'Muhammad Alam', 'Transfer Officers', 9022, 100, 'Paid'),
(4, '2009-03-27 02:20:09', 'Peshawar', 'Sabz Ali', 'Transfer Disst:', 90822, 100, 'Paid');

-- --------------------------------------------------------

--
-- Table structure for table `complaints`
--

CREATE TABLE IF NOT EXISTS `complaints` (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `complaint_type_id` int(11) NOT NULL,
  `signup_id` int(11) NOT NULL,
  `complaints_status_id` int(11) NOT NULL,
  `latitude` float(10,6) NOT NULL,
  `longitude` float(10,6) NOT NULL,
  `description` text NOT NULL,
  `image` varchar(255) NOT NULL,
  `video` varchar(255) NOT NULL,
  `dated` date NOT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `complaints`
--

INSERT INTO `complaints` (`complaint_id`, `complaint_type_id`, `signup_id`, `complaints_status_id`, `latitude`, `longitude`, `description`, `image`, `video`, `dated`) VALUES
(1, 1, 2, 1, 33.996056, 71.806503, 'hai complaint herehai complaint herehai complaint herehai complaint herehai complaint herehai complaint herehai complaint herehai complaint herehai complaint herehai complaint herehai complaint here', '1.png', '', '2017-06-19'),
(2, 2, 1, 2, 33.999969, 71.476166, 'complaint near chief of wrong parking of a car...complaint near chief of wrong parking of a car...complaint near chief of wrong parking of a car...complaint near chief of wrong parking of a car...complaint near chief of wrong parking of a car...complaint near chief of wrong parking of a car...complaint near chief of wrong parking of a car...', 'parking2.jpg', '', '2017-06-20'),
(3, 3, 2, 3, 33.999310, 71.470032, 'violation near BISE Peshawar', '', '11 Second Animation - YouTube.MP4', '2017-06-20'),
(4, 3, 2, 1, 33.996586, 71.483849, 'wrong parked car', 'parking2.jpg', '', '2017-06-11'),
(5, 2, 1, 3, 33.999447, 71.494514, 'complaint against violation', '', 'Zombie Kid Likes Turtles - YouTube.MP4', '2017-06-17'),
(6, 1, 1, 1, 33.996586, 71.483849, 'Here is another complaint', '21.png', '', '2017-06-15');

-- --------------------------------------------------------

--
-- Table structure for table `complaints_report`
--

CREATE TABLE IF NOT EXISTS `complaints_report` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT,
  `complaint_type_id` int(11) NOT NULL,
  `Dated` date NOT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `complaints_status`
--

CREATE TABLE IF NOT EXISTS `complaints_status` (
  `complaints_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`complaints_status_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `complaints_status`
--

INSERT INTO `complaints_status` (`complaints_status_id`, `status`) VALUES
(1, 'Completed'),
(2, 'Pending'),
(3, 'In Progress');

-- --------------------------------------------------------

--
-- Table structure for table `complaint_types`
--

CREATE TABLE IF NOT EXISTS `complaint_types` (
  `complaint_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `complaint_type` varchar(255) NOT NULL,
  PRIMARY KEY (`complaint_type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `complaint_types`
--

INSERT INTO `complaint_types` (`complaint_type_id`, `complaint_type`) VALUES
(1, 'illegal parking'),
(2, 'corruption'),
(3, 'signal violation'),
(4, 'warden complaint');

-- --------------------------------------------------------

--
-- Table structure for table `emergency_categories`
--

CREATE TABLE IF NOT EXISTS `emergency_categories` (
  `emergency_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  PRIMARY KEY (`emergency_category_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `emergency_categories`
--

INSERT INTO `emergency_categories` (`emergency_category_id`, `category_name`) VALUES
(1, 'Rescue 1122'),
(2, 'Health'),
(3, 'Mechanics'),
(4, 'Highway Officer');

-- --------------------------------------------------------

--
-- Table structure for table `emergency_contacts_detail`
--

CREATE TABLE IF NOT EXISTS `emergency_contacts_detail` (
  `emergency_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `emergency_category_id` int(11) NOT NULL,
  `emergency_district_id` int(11) NOT NULL,
  `latitude` float(10,6) NOT NULL,
  `longitude` float(10,6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `contact_no` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`emergency_detail_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `emergency_contacts_detail`
--

INSERT INTO `emergency_contacts_detail` (`emergency_detail_id`, `emergency_category_id`, `emergency_district_id`, `latitude`, `longitude`, `name`, `contact_no`, `address`) VALUES
(1, 3, 1, 34.017014, 71.562485, 'mr.mechanics', '03009876543', 'deans trade center.psh'),
(2, 3, 1, 33.999969, 71.476166, 'mr.mechanics2', '03019234578', 'Chief Burgers, Peshawar'),
(3, 2, 4, 33.996586, 71.483849, 'Doctor ka naam', '091823764', 'KTH, Peshawar'),
(4, 2, 3, 34.001717, 71.542725, 'doc', '09178767364', 'CMH'),
(5, 1, 2, 34.124062, 71.806503, 'rescue team', '030087637644', 'Peshawar Motorway'),
(6, 4, 3, 34.003002, 71.441566, 'Other', '03338767364', 'UOP'),
(7, 3, 1, 33.999969, 71.476166, 'mechanics', '03349876756', 'KMC'),
(8, 3, 1, 33.996056, 71.476189, 'mecha', '03228888887', 'Chaaye Khana'),
(9, 3, 1, 34.002125, 71.479950, 'mech', '0917765653', 'Shelton''s Rezidor '),
(10, 1, 3, 33.354530, 71.984276, 'abc', '76567456', 'Fake'),
(17, 1, 3, 32.000000, -26.000000, 'new entry', '123456789', ''),
(18, 4, 3, 33.000000, 71.000000, 'name', 'contact', ''),
(20, 4, 4, 32.000000, 71.987656, 'xyz', '12324234', ''),
(24, 1, 2, 33.987656, 71.987656, 'xyz', '03009873478', ''),
(25, 2, 4, 33.987656, 71.987656, 'asd', '12324234', ''),
(26, 1, 2, 33.987656, 71.987656, 'Muhammad Ali', '03331234567', '');

-- --------------------------------------------------------

--
-- Table structure for table `emergency_districts`
--

CREATE TABLE IF NOT EXISTS `emergency_districts` (
  `emergency_districts_id` int(11) NOT NULL AUTO_INCREMENT,
  `emergency_division_id` int(11) NOT NULL,
  `district_name` varchar(255) NOT NULL,
  PRIMARY KEY (`emergency_districts_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `emergency_districts`
--

INSERT INTO `emergency_districts` (`emergency_districts_id`, `emergency_division_id`, `district_name`) VALUES
(1, 1, 'Kohat road'),
(2, 1, 'GT road'),
(3, 1, 'Charsadda road'),
(4, 2, 'Hoti'),
(5, 2, 'Nowshera'),
(6, 2, 'Rashakai'),
(7, 3, 'Upper Dir'),
(8, 3, 'Mingora'),
(9, 3, 'pep');

-- --------------------------------------------------------

--
-- Table structure for table `emergency_divisions`
--

CREATE TABLE IF NOT EXISTS `emergency_divisions` (
  `emergency_division_id` int(11) NOT NULL AUTO_INCREMENT,
  `division_name` varchar(255) NOT NULL,
  PRIMARY KEY (`emergency_division_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `emergency_divisions`
--

INSERT INTO `emergency_divisions` (`emergency_division_id`, `division_name`) VALUES
(1, 'Peshawar'),
(2, 'Mardan'),
(3, 'Swat');

-- --------------------------------------------------------

--
-- Table structure for table `license_districts`
--

CREATE TABLE IF NOT EXISTS `license_districts` (
  `district_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`district_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `license_districts`
--

INSERT INTO `license_districts` (`district_id`, `name`) VALUES
(1, 'Peshawar'),
(2, 'Mardan'),
(4, 'Nowshera'),
(5, 'Upper Dir');

-- --------------------------------------------------------

--
-- Table structure for table `license_types`
--

CREATE TABLE IF NOT EXISTS `license_types` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `license_type` varchar(50) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `license_types`
--

INSERT INTO `license_types` (`type_id`, `license_type`) VALUES
(1, 'M.CAR/JEEP ONLY'),
(2, 'LTV ONLY'),
(3, 'M.CYCLE + M.CAR/JEEP ONLY'),
(4, 'HTV PSV ONLY');

-- --------------------------------------------------------

--
-- Table structure for table `license_verification`
--

CREATE TABLE IF NOT EXISTS `license_verification` (
  `license_id` int(11) NOT NULL AUTO_INCREMENT,
  `dl_number` varchar(30) NOT NULL,
  `cnic` varchar(40) NOT NULL,
  `name` varchar(50) NOT NULL,
  `father_name` varchar(50) NOT NULL,
  `license_type` varchar(50) NOT NULL,
  `license_expiry_date` date NOT NULL,
  `lic_status` int(11) NOT NULL,
  `district_id` varchar(30) NOT NULL,
  PRIMARY KEY (`license_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=136 ;

--
-- Dumping data for table `license_verification`
--

INSERT INTO `license_verification` (`license_id`, `dl_number`, `cnic`, `name`, `father_name`, `license_type`, `license_expiry_date`, `lic_status`, `district_id`) VALUES
(1, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(2, '100000239365', '2120238620993', 'WASEEM KHAN  ', 'HAZRAT AKBAR  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(3, '100000239366', '1720145750521', 'SYED OBAID ULLAH SHAH', 'SYED SHAMSHAD ALI SHAH ', 'M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(4, '100000239367', '2110424776069', 'HAYN ULLAH  ', 'MUHAMMAD YOUSAF  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(5, '100000239368', '1730124599441', 'MUHAMMAD SULEMAN  ', 'Ali  Rehman', 'LTV ONLY', '2022-03-15', 1, '1'),
(6, '100000239369', '1730115569179', 'ISHTIAQ  ', 'JAN MUHAMMAD', 'LTV ONLY', '2022-03-15', 1, '1'),
(7, '100000239370', '1730175954005', 'NAIMAT ULLAH  ', 'SABIR HUSSAIN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(8, '100000239371', '1730196339713', 'ABDUL RASHEED  ', 'NOOR MUHAMMAD  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(9, '100000239372', '1730186001409', 'GULZAR  ', 'Hussain  Khan ', 'LTV ONLY', '2022-03-15', 1, '1'),
(10, '100000239373', '2170531761017', 'WALI MUHAMMAD  ', 'BOSTAN  ', 'M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(11, '100000239374', '4210135829705', 'ARBAB  SHAH', 'Fazal  Shah', 'LTV ONLY', '2022-03-15', 1, '1'),
(12, '100000239375', '1730127793543', 'SALIM KHAN  ', 'Wazir  Khan', 'LTV ONLY', '2022-03-15', 1, '1'),
(13, '100000239376', '1730179072947', 'MUHAMMAD NISAR', 'Hayat  Gul', 'LTV ONLY', '2015-03-15', 0, '1'),
(14, '100000239377', '1560217438697', 'YOUSUF WASIL KHAN', 'MUHAMMAD WASIL', 'M.CAR/JEEP ONLY', '2019-07-30', 1, '1'),
(15, '100000239378', '2120145128195', 'INZAR GUL', 'FAZAL GHANI', 'LTV ONLY', '2016-07-30', 0, '1'),
(16, '100000239379', '2130134625419', 'ABDUL SALAM', 'SYED REHMAN', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-06-20', 1, '1'),
(17, 'lic_number', 'cnic', 'name', 'father_name', 'license_type', '0000-00-00', 0, '0'),
(18, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(19, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(20, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(21, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(22, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(23, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(24, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(25, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(26, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(27, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(28, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(29, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(30, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(31, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(32, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(33, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(34, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(35, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(36, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(37, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(38, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(39, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(40, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(41, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(42, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(43, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(44, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(45, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(46, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(47, '100000239364', '1730116447399', 'IBAD ALI KHAN ?', 'MUGHAL BAZ KHAN ?', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(48, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(49, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(50, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(51, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(52, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(53, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(54, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(55, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(56, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(57, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(58, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(59, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(60, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(61, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(62, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(63, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(64, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(65, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(66, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(67, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '0000-00-00', 1, '1'),
(78, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(79, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2021-03-15', 1, '1'),
(80, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2012-09-10', 1, '1'),
(81, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2012-12-12', 1, '1'),
(82, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2010-03-21', 1, '1'),
(83, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2020-09-12', 1, '1'),
(84, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2010-03-10', 1, '1'),
(85, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2020-03-12', 1, '1'),
(86, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'LTV ONLY', '2017-03-12', 0, '1'),
(87, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'LTV ONLY', '2010-03-23', 0, '1'),
(88, '100000239364444', '173011644739911', 'ARBAB  SHAH', 'Fazal  Shah', 'LTV ONLY', '2018-01-26', 1, 'Nowshera'),
(89, '100000239364444', '1730116447399', 'Peshawar', 'xyz', 'LTV ONLY', '2017-08-31', 1, 'Mardan'),
(90, '100000239364', '1730116447399', 'Doctor', 'Fazal  Shah', 'HTV PSV ONLY', '2017-08-31', 1, 'Mardan'),
(91, '1234567890', '0987654321', 'asad', 'xyz', 'M.CAR/JEEP ONLY', '2018-08-31', 1, 'Nowshera'),
(92, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(93, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(94, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(95, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(96, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(97, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(98, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(99, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(100, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(101, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(102, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(103, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(104, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(105, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(106, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(107, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(108, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(109, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(110, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(111, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(112, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(113, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2021-03-15', 1, '1'),
(114, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2012-09-10', 1, '1'),
(115, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2012-12-12', 1, '1'),
(116, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2010-03-21', 1, '1'),
(117, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2020-09-12', 1, '1'),
(118, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2010-03-10', 1, '1'),
(119, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2020-03-12', 1, '1'),
(120, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2017-03-12', 1, '1'),
(121, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2010-03-10', 1, '1'),
(122, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(123, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(124, '100000239364', '1730116447399', 'ALI KHAN', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(125, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'LTV ONLY', '2022-03-15', 1, '1'),
(126, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'LTV ONLY', '2022-03-15', 1, '1'),
(127, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'HTV ONLY', '2022-03-15', 1, '1'),
(128, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(129, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(130, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(131, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'LTV ONLY', '2022-03-15', 1, '1'),
(132, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'HTV ONLY', '2022-03-15', 1, '1'),
(133, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'HTV ONLY', '2022-03-15', 1, '1'),
(134, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1'),
(135, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, '1');

-- --------------------------------------------------------

--
-- Table structure for table `routes`
--

CREATE TABLE IF NOT EXISTS `routes` (
  `route_id` int(11) NOT NULL AUTO_INCREMENT,
  `route_name` varchar(255) NOT NULL,
  `flag` varchar(40) NOT NULL,
  PRIMARY KEY (`route_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `routes`
--

INSERT INTO `routes` (`route_id`, `route_name`, `flag`) VALUES
(1, 'G.T road', 'gt_road'),
(2, 'Khyber road', 'khyber_road'),
(3, 'Charsadda road', 'charsadda_road'),
(4, 'Jail road', 'jail_road'),
(5, 'University road', 'university_road'),
(6, 'Dalazak road', 'dalazak_road'),
(7, 'Saddar road', 'saddar_road'),
(8, 'Bagh-e-Naran road', 'baghenaran_road'),
(9, 'Warsak road', 'warsak_road'),
(10, 'Kohat road', 'kohat_road');

-- --------------------------------------------------------

--
-- Table structure for table `route_status`
--

CREATE TABLE IF NOT EXISTS `route_status` (
  `route_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `route_status` varchar(255) NOT NULL,
  PRIMARY KEY (`route_status_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `route_status`
--

INSERT INTO `route_status` (`route_status_id`, `route_status`) VALUES
(1, 'Clear'),
(2, 'Busy'),
(3, 'Congested');

-- --------------------------------------------------------

--
-- Table structure for table `route_updates`
--

CREATE TABLE IF NOT EXISTS `route_updates` (
  `route_update_id` int(11) NOT NULL AUTO_INCREMENT,
  `route_id` int(11) NOT NULL,
  `route_status_id` int(11) NOT NULL,
  `updated_time` datetime NOT NULL,
  PRIMARY KEY (`route_update_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=38 ;

--
-- Dumping data for table `route_updates`
--

INSERT INTO `route_updates` (`route_update_id`, `route_id`, `route_status_id`, `updated_time`) VALUES
(1, 1, 1, '2017-07-06 09:52:53'),
(2, 5, 2, '2017-04-11 12:16:24'),
(3, 7, 3, '2017-04-11 15:16:25'),
(4, 2, 1, '2017-04-11 09:09:21'),
(5, 1, 2, '2017-04-14 17:23:35'),
(6, 1, 3, '2017-05-02 12:06:11'),
(7, 5, 3, '2017-05-02 12:06:31'),
(8, 5, 1, '2017-05-02 13:16:27'),
(9, 7, 1, '2017-05-05 07:31:14'),
(10, 7, 2, '2017-05-15 08:26:10'),
(11, 2, 2, '2017-05-01 06:20:38'),
(12, 7, 1, '2017-07-05 14:37:02'),
(13, 3, 3, '2017-05-11 18:30:17'),
(14, 3, 2, '2017-05-11 14:28:08'),
(15, 3, 1, '2017-05-02 08:10:24'),
(16, 4, 1, '2017-05-08 10:18:20'),
(17, 4, 3, '2017-05-09 10:32:21'),
(18, 4, 2, '2017-05-11 13:28:35'),
(19, 6, 1, '2017-05-12 09:33:18'),
(20, 6, 2, '2017-05-12 08:29:41'),
(21, 6, 3, '2017-05-03 06:33:23'),
(22, 8, 3, '2017-05-16 08:37:25'),
(23, 8, 2, '2017-05-13 16:27:39'),
(24, 8, 1, '2017-05-15 09:33:23'),
(25, 9, 3, '2017-05-24 15:27:44'),
(26, 8, 1, '2017-05-16 05:43:32'),
(27, 9, 2, '2017-05-12 17:54:40'),
(28, 9, 1, '2017-05-11 09:38:21'),
(29, 10, 1, '2017-05-06 09:43:58'),
(30, 10, 2, '2017-05-13 12:52:17'),
(31, 10, 3, '2017-07-04 10:00:17'),
(32, 1, 1, '2017-07-04 09:55:47'),
(37, 0, 0, '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `signup`
--

CREATE TABLE IF NOT EXISTS `signup` (
  `signup_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `cnic` varchar(30) NOT NULL,
  `phone_no` varchar(30) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`signup_id`),
  UNIQUE KEY `cnic` (`cnic`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `signup`
--

INSERT INTO `signup` (`signup_id`, `name`, `email`, `cnic`, `phone_no`, `user_id`) VALUES
(1, 'abc', 'abc@mail.com', '87679', '03001232434', 1),
(2, 'xyz', 'abc@mail.com', '7675785', '03451234567', 2);

-- --------------------------------------------------------

--
-- Table structure for table `traffic_education`
--

CREATE TABLE IF NOT EXISTS `traffic_education` (
  `traffic_education_id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) NOT NULL,
  `image_title` varchar(255) NOT NULL,
  `image_description_eng` varchar(255) NOT NULL,
  `image_description_urdu` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`traffic_education_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=31 ;

--
-- Dumping data for table `traffic_education`
--

INSERT INTO `traffic_education` (`traffic_education_id`, `image`, `image_title`, `image_description_eng`, `image_description_urdu`) VALUES
(1, 'apple_gif.gif', 'Traffic rules', 'obey traffic rules', 'ٹریفک قوانین کا احترام کریں۔'),
(2, '5.png', 'Zebra Crossing', 'Zebra Crossing', 'زیبرا کراسنگ۔'),
(3, '4.png', 'Pelican Crossing', 'Pelican Crossing Area', 'بچوں کی سڑک پار کرنے کی جگہ'),
(4, '2.png', 'Parked Vehicles', 'Parked Vehicles', 'کھڑی کی گئی گاڑیاں'),
(5, '3.png', 'U-turn', 'U-turn', 'یہاں سے آگے مڑ سکتے ہیں۔'),
(6, '14.png', 'Slow', 'Drive Slowly', 'آہستہ چلیں'),
(8, '31.png', 'Bend Left', 'Turn left', 'دوہرا موڑ بائیں طرف'),
(9, '41.png', 'Bend Right', 'Turn to right side please', 'دوہرا موڑ دائیں طرف'),
(10, '9.png', 'Upward Slope', 'Dangerous Slope', 'خطرناک چڑھائی'),
(11, '11.png', 'Pit', 'Poor road', 'گڑھا'),
(12, '12.png', 'Breaker', 'Reduce speed breaker ahead', 'رفتار کم کرنے کا خم'),
(13, '13.png', 'Rough road', 'Reduce Speed, Rough road ahed', 'ناہموار سڑک'),
(14, '15.png', 'Land Sliding', 'Beware of falling rocks', 'گرتے ہوئےپتھروں سے ہوشیار رہیں'),
(15, '16.png', 'River dock', 'The road is by the sea or river dock', 'سڑک سمندری گودی یا دریا کی طرف جاتی ہے'),
(16, '17.png', 'Rocks', 'Gravel, stone', 'کنکر، پتھر'),
(17, '18.png', 'Slippery road', 'Slippery road', 'پھسلنے والی سڑک'),
(18, '19.png', 'Animals crossing', 'Cattle or other animals crossing road space', 'مویشی یا دوسرے جانوروں کے سڑک پار کرنے کی جگہ'),
(19, '201.png', 'wild animals crossing space', 'Place for wild animals to cross the road', 'جنگلی جانوروں کے سڑک پار کرنے کی جگہ'),
(20, '211.png', 'Bike riding cross space', 'Place for bike riding to cross road', 'سائیکل سوار کے سڑک پار کرنے کی جگہ'),
(21, 'ezgif_com-optimize.gif', 'Traffic rules', 'Don''t break traffic rules', 'apni line mai rahin'),
(22, '511.png', 'Right Turn', 'Right Turn', 'دایاں موڑ'),
(23, '611.png', 'Left Turn', 'Left Turn', 'بایاں موڑ'),
(24, '711.png', 'Separate Bridge', 'The Separate Bridge', 'علیحدہ ہونے والا پل'),
(25, '81.png', 'Narrow road', 'The road is narrow ahead', 'آگے سڑک تنگ ہے'),
(26, '101.png', 'Dangerous Unloading', 'Dangerous Unloading', 'خطرناک اترائی'),
(28, '31.png', 'turn karo', 'take a turn towards the right..........take a turn towards the right..........take a turn towards the right..........take a turn towards the right..........take a turn towards the right..........', 'right turn krna hai...right turn krna hai....right turn krna hai.........right turn krna hai........right turn krna hai.......right turn krna hai'),
(29, '32.png', 'image 3', 'image by name of 3.png is added now to dbase', 'gdhgfdhgftrdt'),
(30, '34.png', 'abc', 'image here for my test', 'yahn per tasveer hai');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
