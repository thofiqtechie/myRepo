package undo.impl;

import undo.Change;
import undo.Document;
import undo.UndoManager;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Thofiq.Khan on 8/22/2017.
 * Proposal: When we use this code in application we need to define the transaction
 * 			 mechanism to all the implemented methods available in this class
 * Assumption: This is a java project so considering as a single transaction
 */
public class UndoManagerImpl implements UndoManager{

	private LinkedList<Change> undoChangedList = null;
	private LinkedList<Change> redoChangedList = null;
	private Document document;
	private int bufferSize;

	/**Constructor to create undo manager with mandatory attributes
	 * @param document
	 * @param bufferSize
     */
	public UndoManagerImpl(Document document, int bufferSize){
		this.document = document;
		this.bufferSize = bufferSize;
		this.undoChangedList = new LinkedList<Change>();
		this.redoChangedList = new LinkedList<Change>();
	}
	
	@Override
	public void registerChange(Change change) {
		if(undoChangedList.size() == bufferSize){ //Assuming list size never grow beyond buffer size
			undoChangedList.removeLast(); //Removing the oldest change in the list
		}
		undoChangedList.push(change); //Push the element to the top
	}

	@Override
	public boolean canUndo() {
		return !undoChangedList.isEmpty();
	}

	@Override
	public void undo() {
		if(canUndo()) {
			try {
				Change change = undoChangedList.pop(); //Removes and return the latest change
				change.revert(document); //Revert the latest change in the document
				this.pushChangeToRedoList(change); //Add this change in redo list to perform redo
			}catch (Exception exception){
				throw new IllegalStateException(exception);
			}
		}else{
			throw new IllegalStateException();
		}
	}

	@Override
	public boolean canRedo() {
		return !redoChangedList.isEmpty();
	}

	@Override
	public void redo() {
		if(canRedo()) {
			try {
				Change change = redoChangedList.pop(); //Removes and return the latest change
				change.apply(document); //Apply the change to the document
			}catch (Exception exception){
				throw new IllegalStateException(exception);
			}
		}else{
			throw new IllegalStateException();
		}
	}

	/**
	 * Private method to add element to redo list to keep track of redo object
	 * @param redoChange
	 */
	private void pushChangeToRedoList(Change redoChange) {
		if(redoChangedList.size() == bufferSize){ //Assuming list size never grow beyond buffer size
			redoChangedList.removeLast(); //Remove old object if buffer size is more
		}
		redoChangedList.push(redoChange); //Add new redo object to the top of the list
	}

	/**
	 * Getters for junit testing
	 */
	public List<Change> getUndoChangedList() {
		return undoChangedList;
	}

	public List<Change> getRedoChangedList() {
		return redoChangedList;
	}

	public Document getDocument() {
		return document;
	}

	public int getBufferSize() {
		return bufferSize;
	}
}
