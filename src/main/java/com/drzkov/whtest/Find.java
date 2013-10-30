package com.drzkov.whtest;

import java.io.File;
import java.io.FileFilter;

public class Find {

	private Config c;
	
	private FileFilter filter;
	
	public Find(Config c) {
		this.c = c;
		this.filter = c.getFileFilter();
		
	}
		
	public void traverse() {	
		traverseInternal(c.getStartDirectory(), "");
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
				
				if(filter.accept(f)) {
					System.out.println(dirPrefix + f.getName());
				}
			}
		}
	}
	
	
}
