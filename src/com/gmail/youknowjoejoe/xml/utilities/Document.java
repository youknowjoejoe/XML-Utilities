package com.gmail.youknowjoejoe.xml.utilities;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Document {
	private Element root;
	private String document;
	
	public Document(String document){
		this.document = document;
		
		this.parse();
	}
	
	public Document(Element root){
		this.root = root;
	}
	
	public static String getStringFromInternalFile(String path){
		InputStream input = Document.class.getClass().getResourceAsStream(path);
		Scanner s = new Scanner(input);
		s.useDelimiter("\\A");
		String t = s.hasNext() ? s.next() : "";
		s.close();
		return t;
	}
	
	public Element getRoot(){
		return root;
	}
	
	public List<Element> getElementsBy(String key,String value){
		List<Element> es = new LinkedList<Element>();
		
		root.getElementsBy(key, value, es);
		
		return es;
		
	}
	
	private void parse(){
		List<Element> elements = new ArrayList<Element>();
		
		String txt = this.document;
		
		String[] tags = txt.split("<");
		for(int i = 1; i < tags.length; i++){
			Element currentElement;
			
			if(tags[i].contains("/")){
				elements.remove(elements.size()-1);
			} else {
				if(elements.isEmpty()){
					root = new Element(null, null);
					elements.add(root);
					currentElement = root;
				} else {
					currentElement = new Element(null, elements.get(elements.size()-1));
					elements.add(currentElement);
				}
				
				int tempStartIndex = -1;
				String tempAttributeKey = null;
				String tempAttributeValue = null;
				boolean usesSingleQuotes = false;
				
				for(int e = 0; e < tags[i].length(); e++){
					if(currentElement.getName() == null){
						if(!Character.isWhitespace(tags[i].charAt(e)) && tempStartIndex == -1 && tags[i].charAt(e) != '>'){
							tempStartIndex = e;
						}
						if((Character.isWhitespace(tags[i].charAt(e)) || tags[i].charAt(e) == '>') && tempStartIndex != -1){
							currentElement.setName(tags[i].substring(tempStartIndex, e));
							tempStartIndex = -1;
						}
					} else {
						if(tempAttributeKey == null){
							if(tempStartIndex == -1){
								if(!Character.isWhitespace(tags[i].charAt(e)) && tags[i].charAt(e) != '>'){
									tempStartIndex = e;
								}
							} else {
								if(Character.isWhitespace(tags[i].charAt(e)) || tags[i].charAt(e) == '='){
									tempAttributeKey = tags[i].substring(tempStartIndex, e);
									tempStartIndex = -1;
								}
							}
						} else {
							if(tempStartIndex == -1){
								if(tags[i].charAt(e) == '"'){
									usesSingleQuotes = false;
									tempStartIndex = e+1;
								} else if(tags[i].charAt(e) == '\''){
									usesSingleQuotes = true;
									tempStartIndex = e+1;
								}
							} else {
								if((tags[i].charAt(e) == '"' && !usesSingleQuotes) || (tags[i].charAt(e) == '\'' && usesSingleQuotes)){
									tempAttributeValue = tags[i].substring(tempStartIndex, e);
									tempStartIndex = -1;
									currentElement.addAttribute(tempAttributeKey, tempAttributeValue);
									tempAttributeKey = null;
									tempAttributeValue = null;
								}
							}
						}
					}
					if(tags[i].charAt(e) == '>'){
						currentElement.setText(tags[i].substring(e+1, tags[i].length()));
						break;
					}
					
				}
			}
			
			
		}
		
		
	}
}
