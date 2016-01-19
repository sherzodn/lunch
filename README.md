# Lunch Voting System
Implemented a JSON API using Hibernate/Spring/SpringMVC without frontend
Voting system for deciding where to have lunch

Task definition
- 2 types of users: admin and regular users
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user votes again the same day:
- a)If it is before 11:00 we asume that he changed his mind.
- b)If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides new menu each day.

#Installation
-
