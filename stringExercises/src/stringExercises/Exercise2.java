package stringExercises;

public class Exercise2 {

	public static void main(String[] args) {
		String str1 = "the quick brown fox";
		String str2 = "queen";
		
		int start = 0;
		int end = str2.length();
		char bufStr2[] = new char[end - start];
		str2.getChars(start, end, bufStr2, 0);
		
		end = str1.length();
		char bufStr1[] = new char[end - start];
		str1.getChars(start, end, bufStr1, 0);
		 
		for(int i=0; i<str2.length(); i++){
			for(int j=0; j<str1.length(); j++){
				if(bufStr2[i]==(bufStr1[j])){
					bufStr1[j]=0;
				}
					
					
			}
		}
		str1 = String.valueOf(bufStr1);
		System.out.println(str1);

	}

}
