package undo.impl;

import undo.Document;
import undo.UndoManager;
import undo.UndoManagerFactory;

/**
 * Created by Thofiq.Khan on 8/22/2017.
 * UndoManager implementation class
 */
public class UndoManagerFactoryImpl implements UndoManagerFactory{

	/**
	 * Document object doesn't have any unique attribute to return singleton object
	 * so every time returning new object assuming for every doc user create new manager
     */
	@Override
	public UndoManager createUndoManager(Document doc, int bufferSize) {
		if(doc != null && bufferSize != 0) {
			return new UndoManagerImpl(doc, bufferSize);
		}else{
			return null;
		}
	}

}
