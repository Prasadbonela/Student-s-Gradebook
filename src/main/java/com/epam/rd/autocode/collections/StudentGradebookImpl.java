package com.epam.rd.autocode.collections;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


public class StudentGradebookImpl implements StudentGradebook {

	private Map<Student, Map<String, BigDecimal>> map;

	public StudentGradebookImpl() {

	}

	@Override
	public boolean addEntryOfStudent(Student student, String discipline, BigDecimal grade) {
		return false;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Comparator<Student> getComparator() {
		return null;
	}

	@Override
	public List<String> getStudentsByDiscipline(String discipline) {
		return null;
	}

	@Override
	public Map<Student, Map<String, BigDecimal>> removeStudentsByGrade(BigDecimal grade) {
		return null;
	}

	@Override
	public Map<BigDecimal, List<Student>> getAndSortAllStudents() {
		return null;
	}
	
}
