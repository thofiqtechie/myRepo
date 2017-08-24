package undo.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import undo.Document;

/**
 * Its dummy implementation for testing
 * Created by Thofiq.Khan on 8/22/2017.
 * Note: Dummy document implementation for unit testing
 * pls don't review this code :):)
 */
public class DocumentImpl implements Document{
	
	private File textFile;
	
	public DocumentImpl(File textFile){
		this.textFile = textFile;
	}

	/**
	 * Dummy implementation deletion will happen based on the string
	 * @param pos The position to start deletion.
	 * @param s The string to delete.
     */
	@Override
	public void delete(int pos, String s) {
		try{
		BufferedReader reader = new BufferedReader(new FileReader(textFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(textFile));
		String currentLine;
		while((currentLine = reader.readLine()) != null){
		   String trimmedLine = currentLine.trim();
		   if(trimmedLine.contains(s)){
			   trimmedLine = trimmedLine.replace(s, "");
		   }
		   writer.write(trimmedLine + "\n");
		}
	    reader.close();
		writer.flush();
	    writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Dummy implementation insertion happen based on the given string
	 * @param pos The position to insert the string at.
	 * @param s The string to insert.
     */
	@Override
	public void insert(int pos, String s) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(textFile, true));
			writer.write(s + "\n");
			writer.flush();
		    writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void setDot(int pos) {

	}
	

}
