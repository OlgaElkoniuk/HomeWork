import java.util.Comparator;


public class Student {
private int points;
private int age;
private String name;

//---------------setters and getters-------------

public int getPoints() {
	return points;
}
public void setPoints(int points) {
	this.points = (points>0&&points<101)?points:0;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age =(age>18&&age<121)? age:0;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

//----------------constructor------------
public Student(int points, int age, String name) {
	super();
	setPoints(points);
	setAge(age);
	setName(name);
}

public String toString (){
	return "\n"+"student name: "+name+" \n"+"student age: "+ age+"\n"+"student points: "+points+"\n"+"---------------";
}


}
