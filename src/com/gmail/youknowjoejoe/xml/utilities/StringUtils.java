package com.gmail.youknowjoejoe.xml.utilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StringUtils {
	public static String replacePatternOutsideQuotes(String s){
		List<Integer> quoteIndices = new ArrayList<Integer>();
		List<Range> protectedZones = new LinkedList<Range>();
		
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '"'){
				quoteIndices.add(i);
			}
		}
		
		for(int i = 0; i < quoteIndices.size(); i+=2){
			Range r = new Range(quoteIndices.get(i),quoteIndices.get(i+1));
			protectedZones.add(r);
		}
		
		int currentMark = 0;
		int index = -1;
		String patternToReplace = "  ";
		String replacement = " ";
		while((s.indexOf(patternToReplace, currentMark)) != -1){
			index = s.indexOf(patternToReplace, currentMark);
			if(!Range.within(protectedZones, index)){
				s = s.substring(0, index) + replacement + s.substring(index+patternToReplace.length());
			} else {
				currentMark = index+1;
			}
		}
		
		return s;
	}
}
