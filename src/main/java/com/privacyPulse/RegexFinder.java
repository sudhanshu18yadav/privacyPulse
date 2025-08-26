package com.privacyPulse;

import java.util.*;
import java.util.regex.*;


public class RegexFinder{
	private String name;
	private Pattern pattern;
	private static int DEFAULT_FLAG = 138;

	public RegexFinder(String _name, String _pattern){
		this.name = _name;
		this.pattern = Pattern.compile(_pattern, DEFAULT_FLAG);
	}

	public List<String> find(String input){
		List<String> result = new ArrayList<>();
		Matcher matcher = pattern.matcher(input);

		while(matcher.find()){
			result.add(input.substring(matcher.start(), matcher.end()));
		}
		return result;
	}
}