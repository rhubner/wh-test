package com.drzkov.whtest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Config {

	private Pattern fileName;
	private File startDirectory;
	private Pattern fileContent;

	private Config() {
	}

	public static Config parseCommandLine(String[] args) {
		Config c = new Config();
		for (int i = 0; i < args.length; i++) {
			if ("-f".equals(args[i])) {
				if (++i >= args.length) {
					throw new IllegalArgumentException(
							"expect aditional parameters");
				} else {
					c.fileName = Pattern.compile(args[i]);
				}
			} else if ("-p".equals(args[i])) {
				if (++i >= args.length) {
					throw new IllegalArgumentException(
							"expect aditional parameters");
				} else {
					c.fileContent = Pattern.compile("^.*" + args[i] + ".*$");
				}
			} else {
				if (i + 1 == args.length) {
					c.startDirectory = new File(args[i]);
					if(!c.startDirectory.exists() || !c.startDirectory.isDirectory()) {
						throw new IllegalArgumentException("path : " + args[i] + "  not exist or it's not directory");
					}
				}
			}
		}
		if (c.startDirectory == null || c.fileName == null) {
			throw new IllegalArgumentException("not present all parameters");
		}
		return c;
	}

	public FileFilter getFileFilter() {
		return new FileFilter() {
			
			public boolean accept(File pathname) {
				String name = pathname.getName();
				if(fileContent == null) {
					return fileName.matcher(name).matches();
				}else {
					return fileName.matcher(name).matches() && checkContent(pathname);
				}				
			}			
			private boolean checkContent(File f) {
				try {
					BufferedReader in = new BufferedReader(new FileReader(f));
					try {
						String line = null;
						while((line = in.readLine()) != null) {
							if (fileContent.matcher(line).matches()) {
								return true;
							}
						}
						return false;
					}finally {
						in.close();	//don't forget to close resource
					}					
				}catch(IOException ex) {
					throw new RuntimeException("IO problem", ex);
				}
			}			
		};
	}
	
	
	
	
	public Pattern getFileName() {
		return fileName;
	}

	public File getStartDirectory() {
		return startDirectory;
	}

	public Pattern getFileContent() {
		return fileContent;
	}

}
