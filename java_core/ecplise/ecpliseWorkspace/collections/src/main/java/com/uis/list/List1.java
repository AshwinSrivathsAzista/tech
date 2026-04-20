package com.uis.list;

import java.util.ArrayList;
import java.util.Collection;



public class List1 {

	public static void main(String[] args) {

		
				
				Collection col = new ArrayList<>();
				
				for(String str: args) {
					col.add(str);
				}
				
				
				System.out.println(col);
				

	}

}
