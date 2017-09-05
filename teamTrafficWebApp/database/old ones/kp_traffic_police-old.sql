-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 18, 2017 at 06:39 AM
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
-- Table structure for table `challan`
--

CREATE TABLE IF NOT EXISTS `challan` (
  `challan_id` int(11) NOT NULL AUTO_INCREMENT,
  `challan_no` int(11) NOT NULL,
  `challan_status` varchar(20) NOT NULL,
  PRIMARY KEY (`challan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `complaints`
--

CREATE TABLE IF NOT EXISTS `complaints` (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `complaint_type_id` int(11) NOT NULL,
  `signup_id` int(11) NOT NULL,
  `latitude` float(10,6) NOT NULL,
  `longitude` float(10,6) NOT NULL,
  `description` text NOT NULL,
  `image` varchar(255) NOT NULL,
  `video` varchar(255) NOT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=54 ;

--
-- Dumping data for table `complaints`
--

INSERT INTO `complaints` (`complaint_id`, `complaint_type_id`, `signup_id`, `latitude`, `longitude`, `description`, `image`, `video`) VALUES
(1, 1, 2, 23.988001, -29.677999, 'wrong parking', 'car_Parked.jpg', 'video here'),
(2, 1, 2, 23.988001, -29.677999, 'wrong parking', '<p>The file you are attempting to upload is larger than the permitted size.</p>', 'video here'),
(3, 1, 2, 23.988001, -29.677999, 'wrong parking', '<p>The file you are attempting to upload is larger than the permitted size.</p>', 'video uploaded'),
(4, 1, 2, 23.988001, -29.677999, 'wrong parking', 'car_Parked1.jpg', 'video uploaded'),
(5, 1, 2, 23.988001, -29.677999, 'wrong parking', 'car_Parked2.jpg', 'video uploaded'),
(6, 1, 2, 23.988001, -29.677999, 'wrong parking', 'car_Parked3.jpg', 'video uploaded'),
(7, 1, 2, 23.988001, -29.677999, 'wrong parking', 'car_Parked4.jpg', 'video uploaded'),
(8, 1, 2, 23.988001, -29.677999, 'wrong parking', 'car_Parked5.jpg', 'video uploaded'),
(9, 1, 2, 23.988001, -29.677999, 'wrong parking', 'parking21.jpg', 'video uploaded'),
(10, 1, 2, 23.988001, -29.677999, 'wrong parking', 'parking213.jpg', '11_Second_Animation_-_YouTube.MP4'),
(11, 1, 2, 23.988001, -29.677999, 'wrong parking', 'parking214.jpg', '11_Second_Animation_-_YouTube1.MP4'),
(12, 1, 2, 23.988001, -29.677999, 'wrong parking', 'parking216.jpg', 'Zombie_Kid_Likes_Turtles_-_YouTube.MP4'),
(13, 1, 2, 23.988001, -29.677999, 'wrong parking', 'car_Parked8.jpg', ''),
(14, 1, 2, 23.988001, -29.677999, 'wrong parking', '', '11_Second_Animation_-_YouTube2.MP4'),
(18, 1, 2, 23.988001, -29.677999, 'wrong parking', 'parking219.jpg', ''),
(24, 1, 2, 23.988001, -29.677999, 'wrong parking', '', 'Zombie_Kid_Likes_Turtles_-_YouTube1.MP4'),
(25, 1, 2, 23.988001, -29.677999, 'wrong parking', 'car_Parked9.jpg', ''),
(26, 1, 2, 23.988001, -29.677999, 'wrong parking', 'parking21.jpg', ''),
(27, 1, 2, 23.988001, -29.677999, 'wrong parking', '', 'Zombie_Kid_Likes_Turtles_-_YouTube2.MP4'),
(28, 1, 2, 23.988001, -29.677999, 'wrong parking', '', '11_Second_Animation_-_YouTube3.MP4'),
(29, 1, 2, 23.988001, -29.677999, 'wrong parking', 'car_Parked1.jpg', ''),
(30, 2, 1, 23.988001, -29.677999, 'car is parked at wrong place', 'parking23.jpg', ''),
(31, 2, 1, 23.988001, -29.677999, 'car is parked at wrong place', 'parking24.jpg', ''),
(32, 2, 1, 23.988001, -29.677999, 'car is parked at wrong place', 'asdfgh.jpg', ''),
(33, 2, 1, 37.866280, -38.972301, 'complaint against warden', 'parking25.jpg', ''),
(34, 2, 1, 37.866280, -38.972301, 'complaint against warden', 'parking26.jpg', ''),
(35, 2, 1, 37.866280, -38.972301, 'complaint against warden', 'asdfgh1.jpg', ''),
(36, 2, 1, 37.866280, -38.972301, 'complaint against warden', 'asdfgh2.jpg', ''),
(37, 2, 1, 37.866280, -38.972301, 'complaint against warden', 'car_Parked.jpg', ''),
(38, 2, 1, 37.866280, -38.972301, 'complaint against warden', 'car_Parked1.jpg', ''),
(39, 2, 1, 37.866280, -38.972301, 'complaint against warden', 'asdfgh3.jpg', ''),
(40, 2, 1, 37.866280, -38.972301, 'complaint against warden', 'car_Parked.jpg', ''),
(41, 2, 1, 37.866280, -38.972301, 'complaint against warden', 'car_Parked.jpg', ''),
(42, 2, 1, 37.866280, -38.972301, 'complaint against warden', 'asdfgh.jpg', ''),
(43, 2, 1, 37.866280, -38.972301, 'complaint against warden', '', ''),
(44, 2, 1, 37.866280, -38.972301, 'complaint against warden', '027706f0ac1336a60d63d648c5412e1a.jpg', ''),
(45, 2, 1, 37.866280, -38.972301, 'complaint against warden', '763e191e21f14754e9edfee3f8327000.jpg', ''),
(46, 2, 1, 37.866280, -38.972301, 'complaint against warden', '0594c7272562b1f34d1307f780129dde.jpg', ''),
(47, 2, 1, 37.866280, -38.972301, 'complaint against warden', '04a9e91d9b3b726488eda7f32e2856b9.jpg', ''),
(48, 2, 1, 37.866280, -38.972301, 'complaint against warden', '15e91474e0f2f3dafb7ab4c8df60f91f.jpg', ''),
(49, 2, 1, 37.866280, -38.972301, 'complaint against warden', '2ca49c0ece89f7f628b273697069886d.jpg', ''),
(50, 2, 1, 37.866280, -38.972301, 'complaint against warden', 'car_Parked.jpg', ''),
(51, 2, 1, 37.866280, -38.972301, 'complaint against warden', '', '11_Second_Animation_-_YouTube.MP4'),
(52, 3, 1, 30.000000, -23.000000, 'complaint goes here', 'asdfgh.jpg', ''),
(53, 3, 1, 30.000000, -23.000000, 'complaint goes here', '', '11_Second_Animation_-_YouTube1.MP4');

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
-- Table structure for table `emergency_contacts`
--

CREATE TABLE IF NOT EXISTS `emergency_contacts` (
  `emergency_id` int(11) NOT NULL AUTO_INCREMENT,
  `emergency_name` varchar(255) NOT NULL,
  PRIMARY KEY (`emergency_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `emergency_contacts_detail`
--

CREATE TABLE IF NOT EXISTS `emergency_contacts_detail` (
  `emergency_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `emergency_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `contact_no` varchar(11) NOT NULL,
  `latitude` float(10,6) NOT NULL,
  `longitude` float(10,6) NOT NULL,
  PRIMARY KEY (`emergency_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `license_verification`
--

CREATE TABLE IF NOT EXISTS `license_verification` (
  `license_id` int(11) NOT NULL AUTO_INCREMENT,
  `candidate_name` varchar(255) NOT NULL,
  `candidate_father_name` varchar(255) NOT NULL,
  `candidate_cnic` varchar(30) NOT NULL,
  `candidate_license_no` varchar(30) NOT NULL,
  `candidate_district` varchar(255) NOT NULL,
  `license_status` varchar(25) NOT NULL,
  `license_issue_date` date NOT NULL,
  `license_expiry_date` date NOT NULL,
  PRIMARY KEY (`license_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `routes`
--

CREATE TABLE IF NOT EXISTS `routes` (
  `route_id` int(11) NOT NULL AUTO_INCREMENT,
  `route_name` varchar(255) NOT NULL,
  PRIMARY KEY (`route_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `routes`
--

INSERT INTO `routes` (`route_id`, `route_name`) VALUES
(1, 'G.T road'),
(2, 'Khyber road'),
(3, 'Charsadda road'),
(4, 'Jail road'),
(5, 'University road'),
(6, 'Dalazak road'),
(7, 'Saddar road'),
(8, 'Bagh-e-Naran road'),
(9, 'Warsak road'),
(10, 'Kohat road');

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
(3, 'Conjusted');

-- --------------------------------------------------------

--
-- Table structure for table `route_updates`
--

CREATE TABLE IF NOT EXISTS `route_updates` (
  `route_update_id` int(11) NOT NULL AUTO_INCREMENT,
  `route_id` int(11) NOT NULL,
  `route_status_id` int(11) NOT NULL,
  `updated_time` datetime NOT NULL,
  `flag` varchar(20) NOT NULL,
  PRIMARY KEY (`route_update_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `route_updates`
--

INSERT INTO `route_updates` (`route_update_id`, `route_id`, `route_status_id`, `updated_time`, `flag`) VALUES
(1, 1, 1, '2017-04-11 00:00:00', 'R1'),
(2, 5, 2, '2017-04-11 00:00:00', 'R5'),
(3, 7, 3, '2017-04-11 00:00:00', 'R7'),
(4, 2, 1, '2017-04-11 00:00:00', 'R2'),
(5, 1, 2, '2017-04-14 00:00:00', 'R1');

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
  PRIMARY KEY (`signup_id`),
  UNIQUE KEY `cnic` (`cnic`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `signup`
--

INSERT INTO `signup` (`signup_id`, `name`, `email`, `cnic`, `phone_no`) VALUES
(1, 'Saima', 'sam@gmail.com', '173019098777', '333099999'),
(3, 'Saima', 'sam@gmail.com', '1710108445091', '987234355'),
(4, 'Saima', 'sam@gmail.com', '1540193082642', '234578900'),
(9, 'Saima', 'sam@gmail.com', '173028767535', '987234355'),
(12, 'Fazal', 'fazal@gmail.com', '1717832498736', '033392748972'),
(13, 'Fazal', 'fazal@gmail.com', '1540136876528', '033392748972'),
(14, 'Fazal', 'fazal@gmail.com', '154019789676528', '033392748972');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
