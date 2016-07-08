package com.gmail.youknowjoejoe.xml.utilities;

import java.util.List;

public class Range {
	private int min;
	private int max;
	
	public Range(int min, int max){
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	
	public boolean within(int i){
		boolean condition = i >= min && i <= max;
		if(condition){
			return true;
		}
		return false;
	}
	public static boolean within(List<Range> rs,int i){
		for(Range r: rs){
			boolean condition = r.within(i);
			if(condition){
				return true;
			}
		}
		return false;
	}
}
