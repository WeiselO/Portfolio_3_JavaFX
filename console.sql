DROP TABLE IF EXISTS City;
DROP TABLE IF EXISTS Student;
DROP TABLE IF EXISTS Teacher;
DROP TABLE IF EXISTS Grade;
DROP TABLE IF EXISTS Course;

CREATE TABLE IF NOT EXISTS City(
                                   CityName TEXT,
                                   Country TEXT,
                                   CityCode INTEGER,
                                   PRIMARY KEY (CityName,Country)
);

CREATE TABLE IF NOT EXISTS Student(
                                      StudentID INTEGER PRIMARY KEY,
                                      StudentName TEXT NOT NULL,
                                      CityName TEXT,
                                      FOREIGN KEY (CityName) REFERENCES City (CityName)
);
CREATE TABLE IF NOT EXISTS Teacher(
                                      TeacherID INTEGER PRIMARY KEY,
                                      TeacherName TEXT,
                                      CityName TEXT,
                                      FOREIGN KEY (CityName) REFERENCES City (CityName)


);
CREATE TABLE IF NOT EXISTS Grade(
                                    Gradenumber INTEGER,
                                    CourseID INTEGER,
                                    StudentID INTEGER,
                                    FOREIGN KEY (CourseID) REFERENCES Course (CourseID),
                                    FOREIGN KEY (StudentID) REFERENCES  Student (StudentID) ON DELETE RESTRICT ON UPDATE CASCADE,
                                    PRIMARY KEY (CourseID, StudentID)
);
CREATE TABLE IF NOT EXISTS Course(
                                     CourseID INTEGER PRIMARY KEY,
                                     CourseName TEXT,
                                     CourseSeason TEXT,
                                     CourseYear INTEGER,
                                     TeacherID INTEGER,
                                     FOREIGN KEY (TeacherID) REFERENCES Teacher (TeacherID)
);

INSERT INTO City (CityName, Country, CityCode) VALUES ('Nykøbing Falster', 'Denmark', 4800),
                                                      ('Karlsrona', 'Sweden', NULL),
                                                      ('Billund', 'Denmark', 7190),
                                                      ('Sorø', 'Denmark', 4180),
                                                      ('Eskildstrup', 'Denmark', 4863),
                                                      ('Odense', 'Denmark', 5000),
                                                      ('Stockholm', 'Sweden', NULL),
                                                      ('Tølløse', 'Denmark', 4340),
                                                      ('Jyllinge', 'Denmark', 4040),
                                                      ('Roskilde', 'Denmark', 4000);

INSERT INTO Student (StudentID, StudentName, CityName) VALUES (1, 'Aisha Lincoln',  'Nykøbing Falster'),
                                                              (2, 'Anya Nielsen', 'Nykøbing Falster'),
                                                              (3, 'Alfred Jensen', 'Karlskrona'),
                                                              (4, 'Berta Bertelsen', 'Billund'),
                                                              (5, 'Albert Antonsen', 'Sorø'),
                                                              (6, 'Eske Eriksen', 'Eskildstrup'),
                                                              (7, 'Olaf Olesen', 'Odense'),
                                                              (8, 'Salma Simonsen', 'Stockholm'),
                                                              (9, 'Theis Thomasen', 'Tølløse'),
                                                              (10, 'Janet Jensen', 'Jyllinge'),
                                                              (11, 'Egon Damdrup', 'Roskilde');

INSERT INTO Teacher (TeacherID, TeacherName, CityName) VALUES (1, 'Line Reinhardt', 'Nykøbing Falster'),
                                                              (2, 'Bo Holst', 'Roskilde');


INSERT INTO Course (CourseID, CourseName, CourseSeason, CourseYear, TeacherID) VALUES (1, 'Software Development', 'Autumn', 2020, 1),
                                                                                      (2, 'Essential Computing', 'Autumn', 2020, NULL),
                                                                                      (3, 'Software Development', 'Autumn', 2021, 1);

INSERT INTO Grade (Gradenumber, CourseID, StudentID) VALUES (12,1,1),
                                                            (10,2,1),
                                                            (NULL,3,2),
                                                            (12,2,2),
                                                            (7,1,3),
                                                            (10,2,3),
                                                            (NULL,3,4),
                                                            (2,2,4),
                                                            (10,1,5),
                                                            (7,2,5),
                                                            (NULL,3,6),
                                                            (10,2,6),
                                                            (4,1,7),
                                                            (12,2,7),
                                                            (NULL,3,8),
                                                            (12,2,8),
                                                            (12,1,9),
                                                            (12,2,9),
                                                            (NULL,3,10),
                                                            (7,2,10),
                                                            (NULL,3,11),
                                                            (2,2,11);

SELECT Gradenumber,(CourseName ||' '|| CourseYear) AS Course FROM Course JOIN Grade G on Course.CourseID = G.CourseID.StudentID = G.StudentID WHERE StudentID =?;

SELECT Gradenumber,(CourseName ||' '|| CourseYear) AS Course FROM Grade
    JOIN Course C on C.CourseID = Grade.CourseID WHERE StudentID = ?;

SELECT AVG(Gradenumber) AS 'Grade Average'  FROM Grade  WHERE StudentID = ?;

SELECT AVG(Gradenumber) AS 'Grade Average'  FROM Grade  WHERE CourseID = ?;

SELECT AVG(Gradenumber) AS Average  FROM Grade  WHERE CourseID = ?;