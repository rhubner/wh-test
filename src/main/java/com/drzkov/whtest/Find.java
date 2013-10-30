package com.drzkov.whtest;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {

	private File startDir;
	
	private Pattern pattern;
	
	public Find(File startDir, String fileName) {
		if(!startDir.isDirectory()) {
			throw new IllegalArgumentException("StartDir must be Directory");
		}
		this.startDir = startDir;
		this.pattern = Pattern.compile("^" + fileName + "$");
	}
		
	public void traverse() {	
		traverseInternal(startDir, "");
	}
	
	/**
	 * Internal traverse method for recrusion
	 * @param f
	 */
	private void traverseInternal(File dir, String dirPrefix) {
		for(File f : dir.listFiles()) {
			if(f.isDirectory()) {
				traverseInternal(f, dirPrefix + f.getName() + File.separator);
			}else {
				Matcher m = pattern.matcher(f.getName());
				if(m.matches()) {
					System.out.println(dirPrefix + f.getName());
				}
			}
		}
	}
	
	
}
