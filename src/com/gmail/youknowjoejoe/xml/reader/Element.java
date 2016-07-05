package com.gmail.youknowjoejoe.xml.reader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Element {
	private Element parent;
	private List<Element> children;
	private String text;
	
	private String name;
	private Map<String,String> attributes;
	
	public Element(String name, Element parent){
		this.name = name;
		this.parent = parent;
		if(parent != null){
			this.parent.addChild(this);
		}
		this.attributes = new HashMap<String,String>();
		this.children = new LinkedList<Element>();
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public void addChild(Element child){
		if(!this.children.contains(child)){
			this.children.add(child);
		}
	}
	
	public void removeChild(Element child){
		this.children.remove(child);
	}
	
	public void addAttribute(String key, String value){
		this.attributes.put(key, value);
	}

	public Element getParent() {
		return parent;
	}

	public List<Element> getChildren() {
		return children;
	}

	public String getName() {
		return name;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}
	
	public String getText(){
		return text;
	}

	@Override
	public String toString() {
		String contents = "<" + this.name;
		for(Entry<String,String> e: this.attributes.entrySet()){
			contents = contents + " " + e.getKey() + "=\"" + e.getValue() + "\"";
		}
		
		contents = contents + ">";
		
		if(!this.attributes.isEmpty()){
			for(Element e: this.children){
				contents = contents + e.toString();
			}
		} else {
			contents = contents + this.text;
		}
		contents = contents + "</" + this.name + ">";
		
		return contents;
	}
}
