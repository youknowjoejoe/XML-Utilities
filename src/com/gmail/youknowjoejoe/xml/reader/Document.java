package com.gmail.youknowjoejoe.xml.reader;

import java.util.LinkedList;
import java.util.List;

public class Document {
	private Element root;
	private String document;
	
	public Document(String document){
		this.document = document;
		
		this.parse();
	}
	
	public void parse(){
		List<Element> elements = new LinkedList<Element>();
		
		String txt = this.document + "";
		
		String[] tags = txt.split("<");
		for(int i = 0; i < tags.length; i++){
			boolean closingTag = false;
			
		}
		
		
	}
}
