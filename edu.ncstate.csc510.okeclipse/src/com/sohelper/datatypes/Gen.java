package com.sohelper.datatypes;

import java.io.File;

public class Gen {

	
	public static void main(String[] args) {
		
		
		File f = new File("C:\\Users\\ncshr\\OneDrive\\Desktop\\git\\edu.ncstate.csc510.okeclipse\\");
		
		
		gen(f);
		
		
	}

	private static void gen(File f) {
		
		if (f.isDirectory()) {
			
			for (File file : f.listFiles()) {
				
				gen(file);
			}
		}else if (f.getPath().endsWith(".java"))
		{
			f.getName();
		}
	}
}
