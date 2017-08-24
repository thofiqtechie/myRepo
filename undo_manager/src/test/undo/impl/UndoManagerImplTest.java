package test.undo.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import undo.Change;
import undo.ChangeFactory;
import undo.Document;
import undo.UndoManagerFactory;
import undo.impl.ChangeFactoryImpl;
import undo.impl.DocumentImpl;
import undo.impl.UndoManagerFactoryImpl;
import undo.impl.UndoManagerImpl;

import java.io.File;
import java.util.List;

/**
 * Created by Thofiq.Khan on 8/23/2017.
 */
public class UndoManagerImplTest {

    private ChangeFactory changeFactory = null;
    private Change insertChange = null,deleteChange = null,
            insertSecondChange = null, deleteSecondChange = null;
    private int bufferSize;
    private UndoManagerFactory undoManagerFactory = null;
    private Document document = null;
    private UndoManagerImpl undoManager = null;


    @Before
    public void setUp() throws Exception {
        bufferSize = 10;
        changeFactory =  ChangeFactoryImpl.getChangeFactoryInstance();
        insertChange = changeFactory.createInsertion(0, "test data", 0, 0);
        deleteChange = changeFactory.createDeletion(0, "test data", 0, 0);

        insertSecondChange = changeFactory.createInsertion(0, "second test data", 0, 0);
        deleteSecondChange = changeFactory.createDeletion(0, "second test data", 0, 0);

        undoManagerFactory = new UndoManagerFactoryImpl();
        document = new DocumentImpl(new File("src\\undo\\properties\\test.txt"));
        undoManager = (UndoManagerImpl) undoManagerFactory.createUndoManager(document, bufferSize);
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * To test registerChange method of UndoManagerImpl
     * Register change method will populate the undo change list
     * @throws Exception
     */
    @Test
    public void testRegisterChange() throws Exception {
        undoManager.registerChange(insertChange);
        List<Change> undoChangedList = undoManager.getUndoChangedList();
        Assert.assertNotNull(undoChangedList);
        Assert.assertEquals(1, undoChangedList.size());
    }

    /**
     * To test CanUndo method of UndoManagerImpl
     * @throws Exception
     */
    @Test
    public void testCanUndo() throws Exception {
        Assert.assertFalse(undoManager.canUndo());
        Assert.assertNotNull(undoManager.getUndoChangedList());

        //Before registering an object
        Assert.assertFalse(undoManager.canUndo());
        undoManager.registerChange(insertChange);

        //After registering an object
        Assert.assertTrue(undoManager.canUndo());
    }

    /**
     * Case:
     * @throws Exception
     */
    @Test()
    public void testUndo() throws Exception {
        undoManager.registerChange(insertChange);
        undoManager.undo();
        List<Change> redoChangedList = undoManager.getRedoChangedList();
        Assert.assertNotNull(redoChangedList);
        Assert.assertEquals(1, redoChangedList.size());
        Assert.assertEquals(0, undoManager.getUndoChangedList().size());
    }

    /**
     * To test CanRedo method of UndoManagerImpl
     * @throws Exception
     */
    @Test
    public void testCanRedo() throws Exception {
        registerRedoObjects();
        Assert.assertTrue(undoManager.canRedo());
    }

    /**
     * Redo required objects setup
     */
    private void registerRedoObjects(){
        undoManager.registerChange(insertChange);
        undoManager.undo(); //After the undo, redo list will heave entry to perform redo operation
    }

    /**
     * Case: Register a change and perform redo operation
     * @throws Exception
     */
    @Test()
    public void testRedo() throws Exception {
        registerRedoObjects();
        Assert.assertEquals(1, undoManager.getRedoChangedList().size());
        undoManager.redo();
        Assert.assertEquals(0, undoManager.getRedoChangedList().size());
    }

    /**
     * To test Redo method of UndoManagerImpl for IllegalStateException
     * @throws Exception
     */
    @Test(expected = IllegalStateException.class)
    public void testRedoWithException() throws Exception{
        registerRedoObjects();
        Assert.assertEquals(1, undoManager.getRedoChangedList().size());
        undoManager.redo();
        Assert.assertEquals(0, undoManager.getRedoChangedList().size());
        //One object in redo and try to redo again
        undoManager.redo();
    }

    /**
     * Undo test : Register insertion change, undo the operation and again try to do undo exception should throw
     * @throws Exception
     */
    @Test(expected = IllegalStateException.class)
    public void testUndoWithMultipleUndoOperation() throws Exception {
        undoManager.registerChange(insertChange);
        undoManager.undo();
        List<Change> redoChangedList = undoManager.getRedoChangedList();
        Assert.assertNotNull(redoChangedList);
        Assert.assertEquals(1, redoChangedList.size());
        Assert.assertEquals(0, undoManager.getUndoChangedList().size());
        //One object in undo and try to undo again
        undoManager.undo();
    }

    /**
     * Undo test: without any change registration perform undo operation exception should throw
     * @throws Exception
     */
    @Test(expected = IllegalStateException.class)
    public void testUndoWithoutAnyObjectToUndo() throws Exception {
        undoManager.undo();
    }

    /**
     * Redo test: without any change registration perform Redo operation
     * @throws Exception
     */
    @Test(expected = IllegalStateException.class)
    public void testRedoWithoutAnyObjectToUndo() throws Exception {
        undoManager.redo();
    }


}