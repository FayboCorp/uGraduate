# We Push to Master Final Project

**NOTE** 
To run our application, go to the following link:
TestRun2-env.xptiszvwju.us-east-1.elasticbeanstalk.com

This is our deployed application.

## Description

Overview: uGraduate is going to be an application that centralizes the current
in place systems students use to register. Students currently use 3 different
applications/systems to register for a semester. uAchieve, Register XE,
and uAdvise. All of the systems are not consistent and they are not centered
around the user experience. uGraduate will allow students to register, drop
classes, visually see all required courses through graduation in a tree like
fashion, and see available resources. All access control will be done through
administrators that have the ability to not only drop/add students to classes,
but they will indicate whether or not a student passes or fails a class and
updates the GUI accordingly.


## Authors

| Member | Web dev level | Specialization |
| --- | --- | --- |
| Fabian Miranda Corpuz | Dabbled with a lot of Java and Spring MVC | Security. I want to learn how to properly utilize security in a non-trivial way. I will customize the IAM on AWS and use credential-less storing so we are not storing keys on the repo. 2f authentication. Since there is no incredibly obvious security solution for this registration system, I will designate all security aspects of the application as my specialization |
| Hanna Thayyil | Novice | --- |
| Jonathan Vega the Intermediate | Took IT202, flew through most content though.. | Front-end. Bootstrap, JQuery, probably React |
| Stephen Lambert | Novice | --- |

## Deliverables for checkpoint 2

Have Hibernate and MySQL set up with a good amount of toy data we can use for querying.
We should at least have the following amount of toy data
50 students with all of their credentials
2 different colleges with 2 majors per college and 10 classes for each major
20 classes with class names, times and seat information
2 admins

Our app should be able to open to a login page where we can choose if we are a student or user, We should be able to register and login as a Student, this information should be stored in our DB.All the student functionalities

-Class Functionalities for Students should be done and their CRUD test suite should be written. -if a student registers, their name and credentials should be added to our DB.
-student should be able to search for a class and return the proper query.
-students should be able to query unfinished classes and results should be displayed to user
-students should be able to query classes they have already completed
-if a student unregisters, their name and credentials should be removed from our DB
The functionalities listed above under Students will be what our tests are checking for. Basic CRUD tests should be written for the students' class.

Fabian - mess around with Spring Security and configure things as needed. Still unsure of how to fully use Spring security but will have more detailed Spring security actions by checkpoint 4. Work on IAM configurations and learn how to do that. Will try to get those configurations up and running.

## Deliverables for checkpoint 3
Deployment Notes
- Security progress: Configured spring security to only accept roles signified in the SecurityConfig.java class. Only 
people with the role "ADMIN" and "STUDENT" can log in. Spring security puts it all on lockdown. Our database has 
a schema that use a master record of all users. There is a one to many join between users and authorities. Authorities
has a value "ROLE_USERROLE" and it can be "STUDENT" or "ADMIN". Redirects are then sent according to roles. 

**NOTE: I spent too much time figuring out the workings of Spring Security and how to properly configure it. I even 
pushed all throughout spring break. I was not able to get .env and secrets to work so at the moment I have them 
saved in the code. Will be changing that for next deployment.
- How to Deploy: 
- Step 1: Download our directory and open up src\main\resources\applications.properties. set spring.datasource.url to
jdbc:mysql://test-database.cmuqwxmahqss.us-east-1.rds.amazonaws.com/test?useSSL=false&serverTimezone=UTC
There is a username and password right under that. Change that to 
username: admin
password: 12345678
Run a "mvn clean package" on the cmd line and this should create a file under the target directory. 
(in intellij it created it under master/target). This file was SNAP_SHOT.war. Don't user the .war.original. 

- Step 2: Log in to your aws account and go to the dashboard that has all the services. Click on Elastic 
Beanstalk. You are going to create a new environment, click on new environment button and leave web server environment 
default. go to the next page. Add an application name, environment name (to whatever you want), choose platform 
as TOMCAT. The next section will then have you upload your own code (under the same page of application code). local 
and upload the war file you recently created. It will upload for about a few minutes. And go on to the next page to 
deploy. This part will take a while (took me 10min). 
NOTES OF THE APP. 
Login to the app first by using username: admin | password: admin. Here you can add students, search them and add
sections. Adding a student will create a user and allow login access. Adding a section will link students with
that required major. 

Try this. 

Add new student. Name: Whatever you want. last name: whatever you want. Major to whatever you want. 
Click submit. Save the username and password it gives. Now go to add section. Add a section. Enter 
a class name. Leave professor blank (will implement later) and pick the course that corresponds to the major
you selected earlier. submit. 

Then go to localhost:8080/logout. logout. 

Login with that credentials you got from the first username you created. Click on the first hyperlink. (Second one
is broken) and it will make a get request of all the courses associated with that user.   

## Deliverables for checkpoint 4

Functionality is ALMOST complete based off of what 


## Deliverables for final project

Outline in English what the deliverables will be for the final checkpoint. This will should be
similar to the **Description** above, but written out as an explicit checklist rather than a human
readable description. Reminder that this is not *due* until checkpoint 4, but failing to plan is
planning to fail.

For each specialization, you must list specific checkpoints that are relevant to that particular specialization.

## Specialization deliverables

Fabian - Secure management of the registration system. IAM customization for Admin roles only. String access control. 2F auth for admins. Extensive logging. Credential-less storing, and other related security vulnerabilities within the scope of the registration system

Jon - An interactive Class tree map that displays classes completed, in progress, and finished. Will make a very user experience centered design that focuses on user recognition (rather than recall) and really adhere to the Nielson 10 design heuristics

# Installation

AWS Deployment Instructions:
- Intall Intellij or Eclipse
- Import this project folder as a MAVEN project
- Run mvn clean package. This will create a directory called target and add folders and files inside. Inside the file, there is a .war file (not the .war.original). This is the file that will be used to deploy on the server
- On AWS, create a new instance of Elastic Beanstalk and upload the .war file from the project directory
- Good to go
