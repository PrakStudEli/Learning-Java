package classes;

import java.util.Vector;

import classes.command.Command;

// Используется для удобной передачи данных, полученных от пользователя
public class UserInput {
    public Command cmd;
    public Vector<String> args;

    public UserInput(Command cmd, Vector<String> args) {
        this.cmd = cmd;
        this.args = args;
    }

    public String toString() {
        return cmd.GetName() + args.toString();
    }
}
