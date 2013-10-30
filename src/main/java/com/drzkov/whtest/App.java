package com.drzkov.whtest;

import java.io.File;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		
		
		
		
		Find f = new Find(new File("c:\\workspace\\studna\\"), ".+\\.java");
		f.traverse();
	}
}
