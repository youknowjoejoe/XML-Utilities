package com.gmail.youknowjoejoe.xml.utilities;

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
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setText(String text){
		this.text = text.replaceAll("\n", "");
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
	
	public String get(String key){
		return attributes.get(key);
	}
	
	public List<Element> getChildWithName(String name){
		List<Element> es = new LinkedList<Element>();
		for(Element e: this.children){
			if(e.getName() == name){
				es.add(e);
			}
		}
		return es;
	}
	
	public String getText(){
		return text;
	}
	
	public void getElementsBy(String key, String value, List<Element> elements){
		String attribValue = this.attributes.get(key);
		if(attribValue != null){
			if(attribValue.equals(value)){
				elements.add(this);
			}
		}
		
		this.children.forEach((Element e) -> e.getElementsBy(key, value, elements));
	}

	@Override
	public String toString() {
		String contents = "<" + this.name;
		for(Entry<String,String> e: this.attributes.entrySet()){
			contents = contents + " " + e.getKey() + "=\"" + e.getValue() + "\"";
		}
		
		contents = contents + ">";
		
		if(!this.children.isEmpty()){
			for(Element e: this.children){
				contents = contents + e.toString();
			}
		} else if(this.text != null){
			if(!this.text.isEmpty()){
				contents = contents + this.text;
			}
		}
		contents = contents + "</" + this.name + ">";
		
		return contents;
	}
}
