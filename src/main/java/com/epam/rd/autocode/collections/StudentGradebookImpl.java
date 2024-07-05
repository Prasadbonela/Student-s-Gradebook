package com.epam.rd.autocode.collections;

import java.math.BigDecimal;
import java.util.*;

public class StudentGradebookImpl implements StudentGradebook {
	private final Map<Student, Map<String, BigDecimal>> map;
	private final Comparator<Student> studentComparator;

	public StudentGradebookImpl() {
		this.map = new TreeMap<>(Comparator.comparing(Student::getLastName)
				.thenComparing(Student::getFirstName)
				.thenComparing(Student::getGroup));
		this.studentComparator = Comparator.comparing(Student::getLastName)
				.thenComparing(Student::getFirstName)
				.thenComparing(Student::getGroup);
	}

	@Override
	public boolean addEntryOfStudent(Student student, String discipline, BigDecimal grade) {
		if (grade == null || grade.compareTo(BigDecimal.ZERO) < 0 || grade.compareTo(new BigDecimal("5.0")) > 0) {
			throw new IllegalArgumentException("Grade must be between 0 and 5 inclusive");
		}

		map.putIfAbsent(student, new HashMap<>());
		return map.get(student).put(discipline, grade) == null;
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Comparator<Student> getComparator() {
		return studentComparator;
	}

	@Override
	public List<String> getStudentsByDiscipline(String discipline) {
		List<String> result = new ArrayList<>();
		for (Map.Entry<Student, Map<String, BigDecimal>> entry : map.entrySet()) {
			BigDecimal grade = entry.getValue().get(discipline);
			if (grade != null) {
				result.add(entry.getKey().getFirstName() + "_" + entry.getKey().getLastName() + ": " + grade);
			}
		}
		return result;
	}




	@Override
	public Map<Student, Map<String, BigDecimal>> removeStudentsByGrade(BigDecimal grade) {
		Map<Student, Map<String, BigDecimal>> removedStudents = new TreeMap<>(studentComparator);

		Iterator<Map.Entry<Student, Map<String, BigDecimal>>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Student, Map<String, BigDecimal>> entry = iterator.next();
			boolean shouldRemove = false;
			for (Map.Entry<String, BigDecimal> grades : entry.getValue().entrySet()) {
				if (grades.getValue().compareTo(grade) < 0) {
					shouldRemove = true;
					break;
				}
			}
			if (shouldRemove) {
				removedStudents.put(entry.getKey(), entry.getValue());
				iterator.remove();
			}
		}

		return removedStudents;
	}

	@Override
	public Map<BigDecimal, List<Student>> getAndSortAllStudents() {
		Map<BigDecimal, List<Student>> sortedStudents = new TreeMap<>();

		for (Map.Entry<Student, Map<String, BigDecimal>> entry : map.entrySet()) {
			BigDecimal averageGrade = calculateAverageGrade(entry.getValue());

			List<Student> students = sortedStudents.get(averageGrade);
			if (students == null) {
				students = new ArrayList<>();
				sortedStudents.put(averageGrade, students);
			}
			students.add(entry.getKey());
		}

		return sortedStudents;
	}

	private BigDecimal calculateAverageGrade(Map<String, BigDecimal> grades) {
		BigDecimal total = BigDecimal.ZERO;
		int count = 0;
		for (BigDecimal grade : grades.values()) {
			total = total.add(grade);
			count++;
		}
		return total.divide(BigDecimal.valueOf(count), 1, BigDecimal.ROUND_HALF_UP);
	}
}

