package com.privacyPulse;

import java.io.*;
import javax.xml.parsers.*;
import java.util.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class XmlEngine{
	private String filename;
	public XmlEngine(String _filename){
		this.filename = _filename;
		fileReader();
	}

	private InputStream fileReader(){
		InputStream stream = null;
		try{
			stream = new FileInputStream(filename);
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return stream;
	}
	
	private List<RegexFinder> list = new ArrayList<>();
	
	private void saxParser(InputStream inputStream){
		try{
			SAXParserFactory sAXParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = sAXParserFactory.newSAXParser();

			DefaultHandler dh = new DefaultHandler(){
				
				boolean bName = false, bPattern = false, bClass = false, bEnabled = false;
				String name = "", pattern = "", className = "", strEnabled = "";

				public void startElement(String uri, String localName, String qName, Attributes attributes){
					if(qName.equalsIgnoreCase("NAME")){
						bName = true;
					}
					else if(qName.equalsIgnoreCase("Class")){
						bClass = true;
					}
					else if(qName.equalsIgnoreCase("ENABLED")){
						bEnabled = true;
					}
					else if(qName.equalsIgnoreCase("PATTERN")){
						bPattern = true;
					}
				}

				public void endElement(String uri, String localName, String qName){
					if(qName.equalsIgnoreCase("NAME")){
						bName = false; name = name.trim();
					}
					else if(qName.equalsIgnoreCase("PATTERN")){
						bPattern = false; pattern = pattern.trim();
					}
					else if(qName.equalsIgnoreCase("ENABLED")){
						bEnabled = false; strEnabled = strEnabled.trim();
					}
					else if(qName.equalsIgnoreCase("CLASS")){
						bClass = false; className = className.trim();
					}
					else if(qName.equalsIgnoreCase("FINDER")){
						list.add(new RegexFinder(name, pattern));
					 	
					 	name = ""; pattern = ""; className = ""; strEnabled = "";
					}
				}

				public void characters(char[] ch, int start, int length){
					String str = new String(ch, start, length);
					if(bName) {
						name += str;
					}
					else if(bClass){
						className += str;
					} 
					else if(bPattern){
						pattern += str;
					}
					else if (bEnabled){
						strEnabled += str;
					}
				}
			};

			saxParser.parse(inputStream,dh);
		} catch(Exception e){
			e.printStackTrace();
		}
	}


	public List<String> response = new ArrayList<>();

	private void findResult(List<RegexFinder> regexFinders){
		for(RegexFinder finder: regexFinders){
			response.addAll(finder.find(input));
		}
	}
}