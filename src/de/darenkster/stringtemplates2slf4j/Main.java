package de.darenkster.stringtemplates2slf4j;

import static de.darenkster.stringtemplates2slf4j.loggers.LOG.*;

public class Main {

    public static void main(String[] args) {
        var test = "test";
        var ex = new Exception();
        INFO."This is a info \{test}";
        ERROR."This is a error \{test} \{ex}";
        DEBUG."This is a debug \{test}";
        WARN."""
                This is a warn 
                \{test}""";
        TRACE."This is a trace \{test}";
        
        new Object() {
            public void log() {
                INFO."This is a info \{test} from within an anonymous class";
            }
        }.log();
        
        Runnable lambda = () -> INFO."This is a info \{test} from within a lambda";
        lambda.run();
    }
    /*
    * Prints outs:
    * 10:33:44.899 [main] INFO de.darenkster.stringtemplates2slf4j.Main -- This is a info test
    * 10:33:44.907 [main] ERROR de.darenkster.stringtemplates2slf4j.Main -- This is a error test java.lang.Exception
    * java.lang.Exception: null
    *   at de.darenkster.stringtemplates2slf4j.Main.main(Main.java:9)
    * 10:33:44.909 [main] DEBUG de.darenkster.stringtemplates2slf4j.Main -- This is a debug test
    * 10:33:44.910 [main] WARN de.darenkster.stringtemplates2slf4j.Main -- This is a warn
    * test
    * 10:33:44.912 [main] INFO de.darenkster.stringtemplates2slf4j.Main -- This is a info test from within an anonymous class
    * 10:33:44.912 [main] INFO de.darenkster.stringtemplates2slf4j.Main -- This is a info test from within a lambda
    */ 
}
