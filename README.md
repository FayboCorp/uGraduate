uGraduate

Demo: https://youtu.be/-QGqF_Kq7ew

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

uGraduate is built with Java as the backend and the main programming language. The Spring framework was added to
the stack and I ended up using Spring MVC, Spring Security, Spring Boot, and a little bit of Spring Data. I used
Hibernate as the ORM and MySql as the db. I tried to keep relationships semi simple but included everything from 
one-to-one to many-to-many. JWTs authenticate and authorize. I do not use bcrypt for encryption for data for demo 
purposes but you just need to change out the password encoder to use bcrypt and spring security should be able to 
decode. React is on the front end with redux. 

You can run it yourself if you go on Docker hub and look at all the faybocorp repositories. There are 3. Pull them
both and run the database first and wait until it spins up otherwise hibernate in the ugraduate container will throw
exceptions. 


