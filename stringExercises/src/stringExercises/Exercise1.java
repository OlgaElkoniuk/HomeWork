package stringExercises;

public class Exercise1 {

	public static void main(String[] args) {
		String s = "abcdefghijklmnopqrstuvwxy";
		System.out.println(s.length());
		
		int start = 0;
	    int end = 5;
	    char buf[] = new char[end - start];
		
		for (int i=0; i<5; i++){
	    s.getChars(start, end, buf, 0);
	    System.out.println(buf);
	    start+=5;
	    end+=5;
		}

	}

}
