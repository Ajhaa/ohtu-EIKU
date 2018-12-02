package linkkivinkki.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorLogger {

    private String fileAddress;

    public ErrorLogger(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    private void init() throws IOException {
        File f = new File(fileAddress);

        if (!f.exists()) {
            f.createNewFile();
        }
    }

    public boolean log(Exception e) {
        try {
            init();
            
            try (FileWriter writer = new FileWriter(fileAddress, true)) {
                writer.write(e.getMessage() + "\n");
                
                writer.flush();
            }
        } catch (IOException ex) {
            System.out.println("Error creating error log");
            return false;
        }
        return true;
    }
}
