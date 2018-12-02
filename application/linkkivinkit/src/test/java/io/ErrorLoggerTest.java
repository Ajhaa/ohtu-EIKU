package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import linkkivinkki.io.ErrorLogger;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ErrorLoggerTest {

    private ErrorLogger logger;

    @Before
    public void setUp() {
        logger = new ErrorLogger("test.log");
    }

    @After
    public void tearDown() {
        File testFile = new File("test.log");
        testFile.delete();
    }

    @Test
    public void writingReturnsTrueIfSuccessful() {
        assertTrue(logger.log(new Exception("TEST MESSAGE")));
    }

    @Test
    public void writingActuallyWritesErrorMessage() throws FileNotFoundException, IOException {
        String error = "TEST MESSAGE";
        logger.log(new Exception(error));
        
        FileReader reader = new FileReader("test.log");

        char[] results = new char[error.length()];
        reader.read(results);

        String fetched = "";
        for (int i = 0; i < results.length; i++) {
            fetched += results[i];
        }

        assertEquals(error, fetched);
    }

    @Test
    public void errorMessagesAreWrittenOnSeparateLines() throws FileNotFoundException, IOException {
        String error1 = "ERROR MESSAGE";
        String error2 = "ANOTHER ERROR MESSAGE";
        logger.log(new Exception(error1));
        logger.log(new Exception(error2));
        
        String expected = error1 + "\n" + error2 + "\n";

        FileReader reader = new FileReader("test.log");

        char[] results = new char[expected.length()];
        reader.read(results);

        String fetched = "";
        for (int i = 0; i < results.length; i++) {
            fetched += results[i];
        }

        assertEquals(expected, fetched);
    }
}
