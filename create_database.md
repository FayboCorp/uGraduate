drop database ugraduate;
use ugraduate;

CREATE TABLE `major` (
  `major_id` int unsigned NOT NULL AUTO_INCREMENT,
  `major_name` varchar(255) NOT NULL,
  PRIMARY KEY (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

CREATE TABLE `professor` (
  `professor_id` int unsigned NOT NULL AUTO_INCREMENT,
  `professor_name` varchar(255) NOT NULL,
  `section_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`professor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

CREATE TABLE `section` (
  `crn_` int unsigned NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) NOT NULL,
  `professor_id` int unsigned DEFAULT NULL,
  `major_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`crn_`),
  UNIQUE KEY `class_name` (`class_name`),
  KEY `professor_id` (`professor_id`),
  KEY `major_id` (`major_id`),
  CONSTRAINT `section_ibfk_1` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`professor_id`),
  CONSTRAINT `section_ibfk_2` FOREIGN KEY (`major_id`) REFERENCES `major` (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;



CREATE TABLE `user` (
  `user_id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

CREATE TABLE `authorities` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user` int unsigned DEFAULT NULL,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user` (`user`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

CREATE TABLE `student` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

CREATE TABLE `student_section` (
  `student_id` int unsigned NOT NULL,
  `crn_` int unsigned NOT NULL,
  PRIMARY KEY (`student_id`,`crn_`),
  KEY `crn_` (`crn_`),
  CONSTRAINT `student_section_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `student_section_ibfk_2` FOREIGN KEY (`crn_`) REFERENCES `section` (`crn_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

