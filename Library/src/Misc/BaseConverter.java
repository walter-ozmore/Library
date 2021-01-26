package Misc;

public class BaseConverter {
	static char[] hexCode = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	static char[] base64 = {
			'0','1','2','3','4','5','6','7','8','9',
			'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
			'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','-','_'};
	
	public static String toHex(int nums) throws InterruptedException {
		String str = "";
		int currentNumber = nums;
		int remander, result;
		do {
			remander = currentNumber % 16;
			result = currentNumber / 16;
			str = hexCode[remander]+str;
			currentNumber = result;
		}while(result>0);
		return str;
	}
	
	public static int hexToDec(String hex) throws InterruptedException {
		int re = 0;
		int place = 0;
		for(int z=hex.length()-1;z>=0;z--) {
			re += charLoc(hex.charAt(z), hexCode) * Math.pow(16, place);
			place++;
		}
		return re;
	}
	
	public static String toBase64(int nums) throws InterruptedException {
		String str = "";
		int currentNumber = nums;
		int remander, result;
		do {
			remander = currentNumber % 64;
			result = currentNumber / 64;
			str = base64[remander]+str;
			currentNumber = result;
		}while(result>0);
		return str;
	}
	
	public static int base64ToDec(String hex) throws InterruptedException {
		int re = 0;
		int place = 0;
		for(int z=hex.length()-1;z>=0;z--) {
			re += charLoc(hex.charAt(z),base64) * Math.pow(64, place);
			place++;
		}
		return re;
	}
	
	private static int charLoc(char c, char[] list) {
		for(int z=0;z<list.length;z++)
			if(list[z]==c) return z;
		return -1;
	}
}
