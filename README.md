# Student Gradebook

The purpose of this exercise is to work with `Comparator` implementations on the `TreeMap` maps.  
Duration _60 minutes_.

## Description

In this task, you will simulate the work of the student’s gradebook. The internal structure of the gradebook is a collection of the `TreeMap` type where the key is an object of the `Student` type and the value is a `HashMap`, where the key is a discipline name (object of the `String` type) and the value is the grade (object of the `BigDecimal` type).  

The `Student` class describes a student with the following characteristics: _first_ & _last_ name and _group_. All characteristics are passed to the constructor to create an object. **You must not change this class.**  

Now, please proceed to the `StudentsGradebookImpl` class, which implements the `StudentsGradebook` interface, and provide implementations of the following content:  

* public GradeBookImpl()  
Creates the gradebook object in which a `Comparator` is describes to compare students.  

* boolean addEntryOfStudent(Student student, String discipline, BigDecimal grade)  
Adds a student entry to the gradebook. Returns true if the addition was successful, and, if not, false.  

* int size()  
Returns the number of students in the gradebook.  

* Comparator<Student> getComparator()  
Returns the `Comparator` that is used to compare students.  

* List<String> getStudentsByDiscipline(String discipline)  
Gets a student’s list with their grades as a String in the format "firstName_lastName : grade" for the specified discipline.  

* Map<Student, Map<String, BigDecimal>> removeStudentsByGrade(BigDecimal grade)  
Finds, removes, and returns from the gradebook students who have a grade below the specified one in any discipline. If such students are not found, then an empty map will be returned.   

* Map<BigDecimal, List<Student>> getAndSortAllStudents()  
Gets the gradebook as an ordered map where the key is an average gradegrade, and the value is a list of the Student type.  

## Details

Since the `Student` objects are the keys to the gradebook, when you create the gradebook, you will need to declare the `Comparator` to compare objects of the `Student` type and pass it to the `TreeMap<Student, Map<String, BigDecimal>>` constructor.  

The grading scale for any discipline is in the range [0 .. 5]. Minimum positive rating - 3 points. 

The `removeStudentsByGrade` method returns an ordered map by the `Student` type. To create the resulting map, you must use the `Comparator` applied in the gradebook constructor.  

The `getAndSortAllStudents` method generates a gradebook based on the student's average grade. The average grade has a step of one tenth. For example, 3.1, 3.2, 3.3, etc.  

For implementations of all methods, it is guaranteed that the parameters passed to them are correct.  

You can add any private methods to the `StudentsGradebookImpl` class.  

## Restrictions: 

You may not use lambdas and streams while doing this task.  
