import java.util.Comparator;


public class ComparatorName implements Comparator<Student>{

	public int compare(Student s1, Student s2) {
		  String S1Name = s1.getName().toUpperCase();
		   String S2Name = s2.getName().toUpperCase();
		
		
		return S1Name.compareTo(S2Name);
	}

}
