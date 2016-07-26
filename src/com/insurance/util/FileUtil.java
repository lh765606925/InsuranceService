package com.insurance.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;

public class FileUtil {
	private boolean IsIncludeSubFolder;

	@SuppressWarnings("unused")
	public FileUtil() {
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("resouse/config.properties");
		Properties props = new Properties();
		try {
			props.load(in);
			String value = props.getProperty("IncludeSubFolder");
			this.IsIncludeSubFolder = "1".equals(props.getProperty("IncludeSubFolder")) ? true : false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean getIsIncludeSubFolder() {
		return IsIncludeSubFolder;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileUtil test = new FileUtil();
		Vector<String> files = new Vector<String>();
		test.getFileList(files, "F://tomacat", test.getIsIncludeSubFolder());
		for (String string : files) {
			System.out.println(string);
		}
	}

	public boolean getFileList(Vector<String> outFileLists, String filePath, boolean subFolderFlag) {
		if (outFileLists == null) {
			outFileLists = new Vector<String>();
		}
		File file = new File(filePath);
		if (file.exists()) {
			File files[] = file.listFiles();
			if (subFolderFlag) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile()) {
						outFileLists.add(files[i].getName());
					} else if (files[i].isDirectory()) {
						getFileList(outFileLists, filePath + "//" + files[i].getName(), subFolderFlag);
					}
				}
			} else {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile()) {
						outFileLists.add(files[i].getName());
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}
}