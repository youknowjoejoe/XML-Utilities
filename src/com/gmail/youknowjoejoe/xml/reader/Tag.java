package com.gmail.youknowjoejoe.xml.reader;

public class Tag {
	private boolean closing;
	private String name;
	private String txt;
	
	public Tag(String txt){
		
		
	}

	public boolean isClosing() {
		return closing;
	}

	public String getName() {
		return name;
	}

	public String getTxt() {
		return txt;
	}
}
