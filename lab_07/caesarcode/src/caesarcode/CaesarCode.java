package caesarcode;

public class CaesarCode {
	
	static String caesarCode(String input, char offset) {
		input = input.toUpperCase();
		offset = Character.toUpperCase(offset);
		
		int x = (int)offset - (int)'A';
		
		String output = "";
		
		for( int i = 0; i < input.length(); i++) {
			char c = (char) (input.charAt(i) + x);
			if(c > 'Z') {
				output += (char) (input.charAt(i) - (26-x));
			}else {
				output += (char) (input.charAt(i) + x);
			}
			
		}
		return output;
		
	}
	
	static String caesarDeCode(String input, char offset) {
		input = input.toUpperCase();
		offset = Character.toUpperCase(offset);
		
		int x = (int)offset - (int)'A';
		
		String output = "";
		
		for( int i = 0; i < input.length(); i++) {
			char c = (char) (input.charAt(i) - x);
			if(c > 'Z') {
				output += (char) (input.charAt(i) + (26-x));
			}else {
				output += (char) (input.charAt(i) - x);
			}
			
		}
		return output;
		
	}

}
