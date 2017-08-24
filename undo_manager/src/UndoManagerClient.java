import undo.*;
import undo.impl.ChangeFactoryImpl;
import undo.impl.DocumentImpl;
import undo.impl.UndoManagerFactoryImpl;

import java.io.File;

/**
 * Created by Thofiq.Khan on 8/22/2017
 * Client code to verify the changes
 */
public class UndoManagerClient {

	/**
	 * Main method to test undo manager
	 * Invoke the method names available in this file to test undo manager implementation
	 * @param args
     */
	public static void main(String[] args) {

		UndoManagerClient undoManagerClient = new UndoManagerClient();

		Change insert  = undoManagerClient.getChangeFactory().createInsertion(0, "test data", 0, 0);
		Change secondInsertChange  = undoManagerClient.getChangeFactory().createInsertion(12, "second test data", 0, 0);
		Document document = new DocumentImpl(new File("src\\undo\\properties\\test.txt"));
		UndoManager undoManager = undoManagerClient.getUndoManagerFactory().createUndoManager(document, 10);

		//Change the method names to verify the results for each scenario
		undoManagerClient.testTwoInsertionChangeWithOneUndo(undoManager, document);

	}

	/**
	 * Test Case 1:  Insert string "test data" in to the file test.txt, undo the changes again
	 * Result:  	 document will be empty
	 * prerequisite: test.txt should be an empty file
	 * Note: 		 Remove the content of file test.txt available in properties folder
	 */
	private void testInsertionChangeWithUndo(Change insert, UndoManager undoManager, Document document){
		undoManager.registerChange(insert);
		insert.apply(document);
		undoManager.undo();
	}

	/**
	 * Test Case 2:  Insert string "test data" in to the file test.txt, undo the changes and redo again
	 * Result:  	 document should have string "test data"
	 * prerequisite: test.txt should be an empty file
	 * Note: 		 Remove the content of file test.txt available in properties folder
	 */
	private void testInsertionChangeWithUndoAndRedo(Change insert, UndoManager undoManager, Document document){
		undoManager.registerChange(insert);
		insert.apply(document);
		undoManager.undo();
		undoManager.redo();
	}

	/**
	 * Test Case 3:  IllegalStateException if undo performed without any change to the document
	 * Result:  	 Method should throw IllegalStateException
	 * prerequisite: test.txt should be an empty file
	 * Note: 		 Remove the content of file test.txt available in properties folder
	 */
	private void testUndoWithoutChangeToDoc(Change insert, UndoManager undoManager, Document document){
		undoManager.undo();
	}

	/**
	 * Test Case 4:  IllegalStateException if redo performed without any change to the document
	 * Result:  	 Method should throw IllegalStateException
	 * prerequisite: test.txt should be an empty file
	 * Note: 		 Remove the content of file test.txt available in properties folder
	 */
	private void testRedoWithoutChangeToDoc(Change insert, UndoManager undoManager, Document document){
		undoManager.redo();
	}

	/**
	 * Test Case 5:  Insert string "test data" and "second test data" in to the file test.txt
	 * Result:  	 document should have both the string specified in test case
	 * prerequisite: test.txt should be an empty file
	 * Note: 		 Remove the content of file test.txt available in properties folder
	 */
	private void testTwoInsertionChange(UndoManager undoManager, Document document){
		Change insert  = this.getChangeFactory().createInsertion(0, "test data", 0, 0);
		Change secondInsertChange  = this.getChangeFactory().createInsertion(12, "second test data", 0, 0);
		undoManager.registerChange(insert);
		undoManager.registerChange(secondInsertChange);
		insert.apply(document);
		secondInsertChange.apply(document);
	}

	/**
	 * Test Case 6:  Insert string "test data" and "second test data" in to the file test.txt, perform undo 2 times
	 * Result:  	 document will be empty
	 * prerequisite: test.txt should be an empty file
	 * Note: 		 Remove the content of file test.txt available in properties folder
	 */
	private void testTwoInsertionChangeWithTwoUndo(UndoManager undoManager, Document document){
		Change insert  = this.getChangeFactory().createInsertion(0, "test data", 0, 0);
		Change secondInsertChange  = this.getChangeFactory().createInsertion(12, "second test data", 0, 0);
		undoManager.registerChange(insert);
		undoManager.registerChange(secondInsertChange);
		insert.apply(document);
		secondInsertChange.apply(document);
		undoManager.undo();
		undoManager.undo();
	}

	/**
	 * Test Case 6:  Insert string "test data" and "second test data" in to the file test.txt, perform 1 undo operation
	 * Result:  	 Document should have "test data" string alone
	 * prerequisite: test.txt should be an empty file
	 * Note: 		 Remove the content of file test.txt available in properties folder
	 */
	private void testTwoInsertionChangeWithOneUndo(UndoManager undoManager, Document document){
		Change insert  = this.getChangeFactory().createInsertion(0, "test data", 0, 0);
		Change secondInsertChange  = this.getChangeFactory().createInsertion(12, "second test data", 0, 0);
		undoManager.registerChange(insert);
		undoManager.registerChange(secondInsertChange);
		insert.apply(document);
		secondInsertChange.apply(document);
		undoManager.undo();
	}

	/**
	 * To get undo manager factory its singleton object
	 * @return UndoManagerFactory
	 */
	private UndoManagerFactory getUndoManagerFactory(){
		return new UndoManagerFactoryImpl();
	}

	/**
	 * To get change factory object to register its singleton object
	 * @return ChangeFactory
	 */
	private ChangeFactory getChangeFactory(){
		return ChangeFactoryImpl.getChangeFactoryInstance();
	}

}
