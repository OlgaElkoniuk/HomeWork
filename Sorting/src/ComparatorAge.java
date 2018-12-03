import java.util.Comparator;


public class ComparatorAge implements Comparator<Student>{

	public int compare(Student o1, Student o2) {
		int age_1 = o1.getAge();
		int age_2 = o2.getAge();
		return age_1-age_2;
	}

}
