package com.drzkov.whtest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.regex.Matcher;

import org.junit.Before;
import org.junit.Test;

public class ConfigTest {
	
	String success[] = null;
	
	String missingFileName[] = new String[] {"-f"};
	
	String missingDirectory[] = new String[] {"-f", "fileName"};
	
	String badDirectory[] = new String[] {"-f", "fileName", "directoryNotExist"};
	
	@Before
	public void prepare() {
		File f = new File("." + File.separator);
		success = new String[] {"-f", "file_name\\.txt", f.getAbsolutePath()};
	}
	
	@Test
	public void successTest() {
		Config c = Config.parseCommandLine(success);
		Matcher m = c.getFileName().matcher("file_name.txt");		
		assertTrue(m.matches());		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void missingFileName() {
		Config c = Config.parseCommandLine(missingFileName);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMissingDirectory() {
		Config c = Config.parseCommandLine(missingDirectory);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonExistingDirecotry() {
		Config c = Config.parseCommandLine(badDirectory);
	}
	
	
}
