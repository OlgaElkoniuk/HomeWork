import java.util.Comparator;


public class ComparatorPoints implements Comparator<Student>{

	public int compare(Student o1, Student o2) {
int points_1 = o1.getPoints();
int points_2 = o2.getPoints();
return points_1-points_2;
	}

}
