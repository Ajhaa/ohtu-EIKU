
package linkkivinkki.app.command;

import linkkivinkki.io.Color;
import linkkivinkki.io.IO;

public class Invalid implements Command {
    private IO io;

    public Invalid(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute() {
        io.print(Color.redText("Invalid command."));
        return false;
    }

}