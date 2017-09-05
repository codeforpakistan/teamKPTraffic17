-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 04, 2017 at 01:31 PM
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
(1, 1, 2, 1, 33.996056, 71.806503, 'hai complaint here', '1.png', '', '2017-06-19'),
(2, 2, 1, 2, 33.999969, 71.476166, 'complaint near chief of wrong parking of a car', 'parking2.jpg', '', '2017-06-20'),
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

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
(20, 4, 7, 32.000000, 71.987656, 'nazo', '12324234', ''),
(24, 1, 4, 33.987656, 71.987656, 'qwerty', '03009873478', '');

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
(9, 1, 'pep');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `license_districts`
--

INSERT INTO `license_districts` (`district_id`, `name`) VALUES
(1, 'Peshawar'),
(2, 'Mardan'),
(4, 'Nowshera');

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
  `district_id` int(11) NOT NULL,
  PRIMARY KEY (`license_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `license_verification`
--

INSERT INTO `license_verification` (`license_id`, `dl_number`, `cnic`, `name`, `father_name`, `license_type`, `license_expiry_date`, `lic_status`, `district_id`) VALUES
(1, '100000239364', '1730116447399', 'IBAD ALI KHAN  ', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, 1),
(2, '100000239365', '2120238620993', 'WASEEM KHAN  ', 'HAZRAT AKBAR  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, 1),
(3, '100000239366', '1720145750521', 'SYED OBAID ULLAH SHAH', 'SYED SHAMSHAD ALI SHAH ', 'M.CAR/JEEP ONLY', '2022-03-15', 1, 1),
(4, '100000239367', '2110424776069', 'HAYN ULLAH  ', 'MUHAMMAD YOUSAF  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, 1),
(5, '100000239368', '1730124599441', 'MUHAMMAD SULEMAN  ', 'Ali  Rehman', 'LTV ONLY', '2022-03-15', 1, 1),
(6, '100000239369', '1730115569179', 'ISHTIAQ  ', 'JAN MUHAMMAD', 'LTV ONLY', '2022-03-15', 1, 1),
(7, '100000239370', '1730175954005', 'NAIMAT ULLAH  ', 'SABIR HUSSAIN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, 1),
(8, '100000239371', '1730196339713', 'ABDUL RASHEED  ', 'NOOR MUHAMMAD  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, 1),
(9, '100000239372', '1730186001409', 'GULZAR  ', 'Hussain  Khan ', 'LTV ONLY', '2022-03-15', 1, 1),
(10, '100000239373', '2170531761017', 'WALI MUHAMMAD  ', 'BOSTAN  ', 'M.CAR/JEEP ONLY', '2022-03-15', 1, 1),
(11, '100000239374', '4210135829705', 'ARBAB  SHAH', 'Fazal  Shah', 'LTV ONLY', '2022-03-15', 1, 1),
(12, '100000239364', '1730116447399', 'IBAD ALI KHAN', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2022-03-15', 1, 1),
(13, '100000239364', '1730116447399', 'IBAD ALI KHAN', 'MUGHAL BAZ KHAN  ', 'M.CYCLE + M.CAR/JEEP ONLY', '2020-03-15', 1, 1),
(14, '100000239364', '1730116447399', 'IBAD ALI', 'MUGHAL BAZ KHAN', 'LTV ONLY', '2021-03-10', 1, 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

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
(31, 10, 3, '2017-07-04 10:00:17'),
(32, 1, 1, '2017-07-04 09:55:47');

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
(2, 'abc', 'abc@mail.com', '7675785', '03001232434', 2);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

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
(28, '31.png', 'turn karo', 'take a turn', 'right turn krna hai');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
