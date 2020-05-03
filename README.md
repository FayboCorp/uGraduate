uGraduate

The idea to make this application came to me when I was taking an interdisciplinary course during my Senior year. 
I was on a team tasked to prototype a redesign for UIC's current registration system. We analyzed 40+ students 
on how they reacted with the current solutions and discovered a huge flaw. The current system relied on 3 separate
web applications (not including resources that were college or major specific) just to register for a semester. 
One of application provided a transcript of all courses that were taken, needed, and in progress but did not let
students actually register. The second application was needed to actually search for classes and register for them
but offered no way to actually see if this class counted for anything. The third system was an interface that let 
you connect with your advisor. This was the cause for several pain points and a lot of inefficiency. We needed to 
design a more centralized system (a one stop shop so to speak) that gave students the freedom to see what they 
needed and the flexibility to let them do what they wanted to do. 

*Note: This project is under reconstruction. I originally built this with Java and Spring on the back end and a 
very simple wire frame using jsp files to navigate form page to page. I have converted the back end to function 
as a RESTful service and I am currently teaching myself React so I can make a more interactive user experience.


uGraduate is built with Java as the backend and the main programming language. The Spring framework was added to
the stack and I ended up using Spring MVC, Spring Security, Spring Boot, and a little bit of Spring Data. I used
Hibernate as the ORM and MySql as the db. I tried to keep relationships semi simple but included everything from 
one-to-one to many-to-many. 

If you want to test it and run it, feel free to. If you open create_database.md on the root directory, you can
run that script in the mysql workbench to create the database I have been using. Import this file as a MAVEN 
project and all your dependencies will already be important after your IDE indexes.  

Example HTTP requests 

POST localhost:8080/api/students
{
	
	"username": "none",
	"firstName": "James",
	"lastName": "Holden",
	"major": "Computer Science"
	
}

GET localhost:8080/api/students/{student_id} 

