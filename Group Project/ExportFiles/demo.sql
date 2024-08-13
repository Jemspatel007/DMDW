DROP TABLE IF EXISTS course;
CREATE TABLE course (
  course_id int PRIMARY KEY,
  course_name varchar (NN) (U)
);

INSERT INTO course (course_id, course_name) VALUES ('5', 'DMDW');
INSERT INTO course (course_id, course_name) VALUES ('ABC', 'ACC');
INSERT INTO course (course_id, course_name) VALUES ('1', 'ASDC');
INSERT INTO course (course_id, course_name) VALUES ('7', 'SDC');
INSERT INTO course (course_id, course_name) VALUES ('8', 'cloud');

DROP TABLE IF EXISTS student;
CREATE TABLE student (
  student_id int PRIMARY KEY,
  name varchar,
  course_id int REFERENCES course(course_id) RELATION(enroll)
);


