-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2017 at 06:47 AM
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
-- Table structure for table `emergency_categories`
--

CREATE TABLE IF NOT EXISTS `emergency_categories` (
  `emergency_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `emergency_category_name` varchar(255) NOT NULL,
  PRIMARY KEY (`emergency_category_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `emergency_categories`
--

INSERT INTO `emergency_categories` (`emergency_category_id`, `emergency_category_name`) VALUES
(1, 'Health'),
(2, 'Mechanics'),
(3, 'Police'),
(4, 'Rescue1122');

-- --------------------------------------------------------

--
-- Table structure for table `emergency_cities`
--

CREATE TABLE IF NOT EXISTS `emergency_cities` (
  `emergency_cities_id` int(11) NOT NULL AUTO_INCREMENT,
  `emergency_districts_id` int(11) NOT NULL,
  `city_name` varchar(255) NOT NULL,
  PRIMARY KEY (`emergency_cities_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `emergency_cities`
--

INSERT INTO `emergency_cities` (`emergency_cities_id`, `emergency_districts_id`, `city_name`) VALUES
(1, 1, 'kohat road'),
(2, 1, 'GT road');

-- --------------------------------------------------------

--
-- Table structure for table `emergency_contacts_detail`
--

CREATE TABLE IF NOT EXISTS `emergency_contacts_detail` (
  `emergency_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `emergency_city_id` int(11) NOT NULL,
  `emergency_category_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `contact_no` varchar(255) NOT NULL,
  PRIMARY KEY (`emergency_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `emergency_districts`
--

CREATE TABLE IF NOT EXISTS `emergency_districts` (
  `emrgency_districts_id` int(11) NOT NULL AUTO_INCREMENT,
  `districts_name` varchar(255) NOT NULL,
  PRIMARY KEY (`emrgency_districts_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `emergency_districts`
--

INSERT INTO `emergency_districts` (`emrgency_districts_id`, `districts_name`) VALUES
(1, 'Peshawar'),
(2, 'Mardan');

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
  `district` varchar(40) NOT NULL,
  PRIMARY KEY (`license_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `license_verification`
--

INSERT INTO `license_verification` (`license_id`, `dl_number`, `cnic`, `name`, `father_name`, `license_type`, `license_expiry_date`, `district`) VALUES
(1, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 'Peshawar'),
(2, '100000239365', '2120238620993', 'WASEEM KHAN  ', 'HAZRAT AKBAR  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 'Peshawar'),
(3, '100000239366', '1720145750521', 'SYED OBAID ULLAH SHAH', 'SYED SHAMSHAD ALI SHAH ', 'M.CAR/JEEP ONLY', '2022-03-15', 'Peshawar'),
(4, '100000239367', '2110424776069', 'HAYN ULLAH  ', 'MUHAMMAD YOUSAF  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 'Peshawar'),
(5, '100000239368', '1730124599441', 'MUHAMMAD SULEMAN  ', 'Ali  Rehman', 'LTV ONLY', '2022-03-15', 'Peshawar'),
(6, '100000239369', '1730115569179', 'ISHTIAQ  ', 'JAN MUHAMMAD', 'LTV ONLY', '2022-03-15', 'Peshawar'),
(7, '100000239370', '1730175954005', 'NAIMAT ULLAH  ', 'SABIR HUSSAIN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 'Peshawar'),
(8, '100000239371', '1730196339713', 'ABDUL RASHEED  ', 'NOOR MUHAMMAD  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 'Peshawar'),
(9, '100000239372', '1730186001409', 'GULZAR  ', 'Hussain  Khan ', 'LTV ONLY', '2022-03-15', 'Peshawar'),
(10, '100000239373', '2170531761017', 'WALI MUHAMMAD  ', 'BOSTAN  ', 'M.CAR/JEEP ONLY', '2022-03-15', 'Peshawar');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Dumping data for table `route_updates`
--

INSERT INTO `route_updates` (`route_update_id`, `route_id`, `route_status_id`, `updated_time`) VALUES
(1, 1, 1, '2017-04-11 08:07:14'),
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
(12, 2, 3, '2017-05-11 15:28:18'),
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
(31, 10, 3, '2017-05-11 10:39:58');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `traffic_education`
--

INSERT INTO `traffic_education` (`traffic_education_id`, `image`, `image_title`, `image_description_eng`, `image_description_urdu`) VALUES
(1, 'rules.jpg', 'Traffic rules', 'obey traffic rules', 'ٹریفک قوانین کا احترام کریں۔'),
(2, 'left-curve-ahead.png', 'Left Curve', 'left curve ahead', 'آگے بائیں مڑئیں۔'),
(3, 'right-turn-ahead.png', 'Right Curve', 'right curve ahead', 'آگے دائیں مڑیں۔'),
(4, 'men at work.jpg', 'Men at Work', 'men at work', 'آگے کام ہو رہا ہے۔'),
(5, 'no-u-turn.png', 'No U-turn', 'no u-turn', 'مڑنا منع ہے۔');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
