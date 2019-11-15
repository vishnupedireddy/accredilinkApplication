package com.accredilink.bgv;

import java.time.LocalDateTime;
import java.util.HashMap;

public class AppTest {
	
	private static int[] bases = {50, 40, 10, 9, 5, 4, 1 };
	private static HashMap<Integer, String> map = new HashMap<Integer, String>();
	
	private static void setup() {
		map.put(1, "I");
		map.put(4, "IV");
		map.put(5, "V");
		map.put(9, "IX");
		map.put(10, "X");
		map.put(40, "XL");
		map.put(50, "L");
	}
	
	public static String intToRoman(int num) {
		setup();
		String result = new String();
		for (int i : bases)	{
			while (num >= i){
				result += map.get(i);
				num -= i;
			}
		}
		return result;
	}
	public static void main(String[] args) {
		
		/*
		 * String output = intToRoman(12); System.out.println("output : " + output);
		 */
		
		String s = "vishnu";
		
		int i = Integer.parseInt(s);
		
		System.out.println("i : " + i);
	}
	
	
}

