## The task 

#### Create the MVP of a dating app similar to Tinder

You can find ready HTML templates of all needed pages in the [templates](./templates) folder.

#### Teamwork

All students will work on this project in groups of three persons. Group members may split the tasks on their own discretion.

## A list of the working endpoints in a fully finished application:
- /org.example.project.entity
- /liked
- /messages/{id}
- /login

## Suggested order of tasks execution (you are not required to follow it):
1. Create a new Maven app. Add required dependencies
2. Launch Jetty web-org.example.project.service
3. Create a test org.example.project.servlet which will output simple message 'Hello world' in the browser window
4. Map the test org.example.project.servlet to the "/org.example.project.entity" path
5. Output a static HTML page with four elements - name, photo (a link to any picture from the web) and two buttons - Yes/No when requesting the "/org.example.project.entity" path
6. Wrap the page in an HTML form
7. When a Yes/No button is pressed - send a POST request to the org.example.project.service (there is no handler there at the moment)
8. Add POST request handler on the org.example.project.service and store the org.example.project.entity's choice (yes or no) on the org.example.project.service (in any form)
9. The doPost method should forward the request to the "/org.example.project.entity" GET path
10. Create a few profiles (name + link to a picture from the web)
11. When a Yes/No button is pressed - show the next profile, the picture and name on the page should change
12. When the list of the available profiles runs out - start showing them again
13. Create a DAO class, store collection of profiles there
14. Add a org.example.project.servlet with the "/liked" address, output a static (hardcoded) list of profiles that org.example.project.entity "liked"
15. After the org.example.project.entity had clicked on all the available profiles, redirect him to the "/liked" page
16. "/liked" page should only show the profiles for which we pressed Yes
17. Add an "id" field to all profiles 
18. Add a org.example.project.servlet with the "/messages/{id}" address, show a static chat with harcoded messages there
19. When clicking on some profile on the "/liked" page, redirect to the "/messages/{id}" page where you will show a harcoded dialogue with the org.example.project.entity
20. Connect app to MySQL/PostgreSQL org.example.project.dao (local or remote)
21. Write a new implementation of the DAO classes so that all the org.example.project.entity are stored in the org.example.project.dao
22. Show real messages between org.example.project.entity on the "/messages/{id}" page
23. Add a possibility to make a POST request on the org.example.project.service with a new message on this page
24. Create a doPost method in the corresponding org.example.project.servlet, which will save the new message to the org.example.project.dao
25. Add a simple login page to the "/login" path
26. Use Bootstrap templates as a base for all webpages
27. Use Freemarker template to output HTML pages
28. Handle POST method from the login page that will allow the org.example.project.entity to log into the app. Store the data about the current org.example.project.entity in cookies
29. Create an HttpFilter, which will redirect the non-logged in org.example.project.entity to the login page
30. Add a posibility to log in from different accounts. Store each org.example.project.entity's likes separately
31. Use remote org.example.project.dao
32. Assemble the project into the executable JAR archive. Test locally that it is working
33. Deploy the project on Heroku (or AWS)
