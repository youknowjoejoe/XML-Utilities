package com.gmail.youknowjoejoe.xml.utilities;

public class Main {

	public static void main(String[] args) {
		Element parent = new Element("MrElement",null);
		parent.addAttribute("FavoriteFood", "Pork");
		parent.addAttribute("HairColor", "Black");
		parent.addAttribute("Age", "49");
		
		Element child1 = new Element("ElementJr",parent);
		child1.addAttribute("FavoriteFood", "Fried Chicken");
		child1.addAttribute("HairColor", "Brown");
		child1.addAttribute("Age", "11");
		
		Element child2 = new Element("EleMent",parent);
		child2.addAttribute("FavoriteFood", "Pizza");
		child2.addAttribute("HairColor", "Brown");
		child2.addAttribute("Age", "21");
		
		Element child3 = new Element("DOG",parent);
		child3.setText("Why is there text inside the dog tag?");
		
		/*System.out.println(parent.getName() + "'s children: ");
		parent.getChildren().forEach((Element e) -> System.out.println(e.getName()));
		System.out.println(parent.getName() + "'s attributes: ");
		parent.getAttributes().forEach((key,value) -> System.out.println(key + ": " + value));
		
		System.out.println(child1.getName() + "'s attributes: ");
		child1.getAttributes().forEach((key,value) -> System.out.println(key + ": " + value));
		
		System.out.println(child2.getName() + "'s attributes: ");
		child2.getAttributes().forEach((key,value) -> System.out.println(key + ": " + value));*/
		
		System.out.println(parent.toString());
		
		
		//System.out.println(StringUtils.replacePatternOutsideQuotes("<  dog  sandwhich  =  \"  t  ruu  uuu  \"  >"));
		
		/*System.out.println(new Document(parent.toString()).getRoot().toString());
		for(Element e: new Document(parent.toString()).getRoot().getChildren()){
			if(e.getName().equals("DOG")){
				System.out.println(e.getText());
			}
		}
		System.out.println(new Document("<  dog 	 sandwhich  =  \"  t  ruu  uuu  \"  >  	 hellloooooo there  </dog>").getRoot().toString());*/
		
		System.out.println(new Document(Document.getDocumentFromFile("/Sample.xml")).getRoot().toString());
	}

}
