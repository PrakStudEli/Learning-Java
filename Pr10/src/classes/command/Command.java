package classes.command;

// Содержит в себе описания команд
public enum Command {
    CREATE_LIST(
        "crlist",
        "crlist [name] [length] - create new linked list of items",
        2,
        true
    ),
    CREATE_ARRLIST(
        "crarrlist",
        "crarrlist [name] [length] - create new array list of items",
        2,
        true
    ), 
    FIND(
        "find", 
        "find [name] [element_id] - an [element_id] in list with [name] name", 
        2,
        true
    ),
    DELETE(
        "del", 
        "del [name] [element_id] - delete an [element_id] from list with [name] name", 
        2,
        true
    ),
    DELETE_LIST(
        "dellist",
        "dellist [name] - delete list with the name [name]",
        1,
        true
    ),
    COMPUTE(
        "compel",
        "compel [name] [element_id] - compute an indicator for an [element] of the list [name]", 
        2,
        true
    ),
    COMPUTE_LIST(
        "complst",
        "complist [name] compute an indicator for the whole list [name]",
        2,
        true
    ),
    EXIT(
        "exit",
        "leave the shell",
        0,
        true
    ),
    NULL_CMD(
        "",
        "",
        0,
        false
    ),
    ERR(
        "",
        "",
        0,
        false
    ),
    PRINT_LISTS(
        "printlst",
        "print lists and their contents",
        0,
        true
    ),
    NO_DEBUG(
        "nodebug",
        "leave debug mode",
        0,
        true
    ),
    DEBUG(
        "debug",
        "enter debug mode",
        0,
        true
    ),
    MESSAGE(
        "message",
        "enable messages",
        0,
        true
    ),
    NO_MESSAGE(
        "nomessage",
        "disable messages",
        0,
        true
    ),
    HELP(
        "help",
        "show this command list",
        0,
        true
    ),
    APPEND(
        "append",
        "append [name] [number] - append [number] elements to list [name]",
        2,
        true
    ),
    DIAGNOSE(
        "diagnose",
        "diagnose [number] - (debug mode only) diagnose system's speed when operating with [number]-sized LinkedLists and ArrayLists",
        1,
        true
    );

    private String name;
    private String description;
    private int args;
    private boolean printable;

    private Command(String name, String desc, int args, boolean printable) {
        this.name = name;
        this.description = desc;
        this.args = args;
        this.printable = printable;
    }

    public boolean IsPrintable() {
        return this.printable;
    }

    public String GetName() {
        return this.name;
    }

    public String GetDescription() {
        return this.description;
    }

    public int GetArgs() {
        return this.args;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public void SetDescription(String description) {
        this.description = description;
    }

    public void SetArgs(int args) {
        this.args = args;
    }

    public String GetCompleteDescription() {
        return this.name + ":\n    " + this.description;
    }
}