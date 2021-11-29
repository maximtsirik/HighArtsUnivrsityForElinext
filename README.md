# Getting Started

## Documentation

### Versions

+ Java 17
+ Spring 2.6.0
+ PostgreSQL 13.1-alpine

### REST API for High Arts University

#### Student timetable endpoint

template: /api/v1/student/{studentId}/timetable/{date}

Date format: yyyy-mm-dd

GET <http://localhost:8080/api/v1/student/6/timetable/2021-09-02>

### Warning!

Generating data required an internet connection.

Also, timetable generation dates are from 01.09.2021 to 15.09.2021 (weekends are foreseen). It takes more time to
generate data than when starting a regular application

Model list:

+ Auditorium
+ Group
+ Lecturer
+ Lesson
+ Student
+ Timetable

The detailed schema you can view in "High Arts university.drawio"
(see root catalog)

For all of them available basic CRUD operations.

#### Auditorium

+ GET <http://localhost:8080/api/v1/auditorium>
+ GET <http://localhost:8080/api/v1/auditorium/81>
+ POST <http://localhost:8080/api/v1/auditorium>
+ PUT <http://localhost:8080/api/v1/auditorium/81>
+ DELETE <http://localhost:8080/api/v1/auditorium/81>

#### Group

+ GET <http://localhost:8080/api/v1/group>
+ GET <http://localhost:8080/api/v1/group/1>
+ POST <http://localhost:8080/api/v1/group>
+ PUT <http://localhost:8080/api/v1/group/1>
+ DELETE <http://localhost:8080/api/v1/group/1>

#### Lecturer

+ GET <http://localhost:8080/api/v1/lecturer>
+ GET <http://localhost:8080/api/v1/lecturer/56>
+ POST <http://localhost:8080/api/v1/lecturer>
+ PUT <http://localhost:8080/api/v1/lecturer/56>
+ DELETE <http://localhost:8080/api/v1/lecturer/56>

#### Lesson

+ GET <http://localhost:8080/api/v1/lesson>
+ GET <http://localhost:8080/api/v1/lesson/131>
+ POST <http://localhost:8080/api/v1/lesson>
+ PUT <http://localhost:8080/api/v1/lesson/131>
+ DELETE <http://localhost:8080/api/v1/lesson/131>

#### Student

+ GET <http://localhost:8080/api/v1/student>
+ GET <http://localhost:8080/api/v1/student/6>
+ POST <http://localhost:8080/api/v1/student>
+ PUT <http://localhost:8080/api/v1/student/6>
+ DELETE <http://localhost:8080/api/v1/student/6>

#### Timetable

+ GET <http://localhost:8080/api/v1/timetable>
+ GET <http://localhost:8080/api/v1/timetable/176>
+ POST <http://localhost:8080/api/v1/timetable>
+ PUT <http://localhost:8080/api/v1/timetable/176>
+ DELETE <http://localhost:8080/api/v1/timetable/176>




