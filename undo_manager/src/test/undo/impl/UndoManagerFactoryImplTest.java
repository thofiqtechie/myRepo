package test.undo.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import undo.Document;
import undo.UndoManager;
import undo.UndoManagerFactory;
import undo.impl.DocumentImpl;
import undo.impl.UndoManagerFactoryImpl;

import java.io.File;

/**
 * Created by Thofiq.Khan on 8/23/2017.
 */
public class UndoManagerFactoryImplTest {

    private UndoManagerFactory undoManagerFactory = null;
    private Document document = null;

    @Before
    public void setUp() throws Exception {
        undoManagerFactory = new UndoManagerFactoryImpl();
        document = new DocumentImpl(new File("src\\undo\\properties\\test.txt"));
    }

    @After
    public void tearDown() throws Exception {
        undoManagerFactory = null;
        document = null;
    }

    @Test
    public void testCreateUndoManager() throws Exception {
        UndoManager undoManager = undoManagerFactory.createUndoManager(document, 10);
        Assert.assertNotNull(undoManager);

        UndoManager withoutDocument = undoManagerFactory.createUndoManager(null, 10);
        Assert.assertNull(withoutDocument);

        UndoManager withoutBufferSize = undoManagerFactory.createUndoManager(document, 0);
        Assert.assertNull(withoutBufferSize);
    }
}