import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class App {

	public static void main(String[] args) {
		ArrayList<Student> studentDataBase = new ArrayList<Student>();
		ComparatorPoints comarePoints = new ComparatorPoints();
		ComparatorAge comareAge = new ComparatorAge();
		ComparatorName comareName = new ComparatorName();
		Student student_1 = new Student(76, 32, "Daniel");
		Student student_2 = new Student(73, 27, "Anna");
		Student student_3 = new Student(60, 28, "Olga");
		Student student_4 = new Student(66, 30, "Mary");
		Student student_5 = new Student(98, 31, "Bill");
		studentDataBase.add(student_1);
		studentDataBase.add(student_2);
		studentDataBase.add(student_3);
		studentDataBase.add(student_4);
		studentDataBase.add(student_5);
		System.out.println(studentDataBase);
		System.out.println("*************************************************************");
		Collections.sort(studentDataBase, comarePoints);
		System.out.println(studentDataBase);
		System.out.println("*************************************************************");
		Collections.sort(studentDataBase, comareAge);
		System.out.println(studentDataBase);
		System.out.println("*************************************************************");
		Collections.sort(studentDataBase, comareName);
		System.out.println(studentDataBase);
		System.out.println("*************************************************************");

	}

}
