package com.drzkov.whtest;

import java.io.File;

public class App {
	public static void main(String[] args) {
		if(args.length < 2) {
			printHelp();
			return;
		}
		Config c = Config.parseCommandLine(args);
		
		Find f = new Find(c);
		f.traverse();		
	}
		
	public static void printHelp() {
		System.out.println("usage : search.bat -f fileName [-p \"text in file\"] <directory> ");
		System.out.println("fileName & \"text in file are\" java regex : ");
		System.out.println(" http://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html");
		System.out.println("");
		System.out.println("search -f .*\\.java  ." + File.separator + "  find all files which end .java");
		
		
	}	
}
