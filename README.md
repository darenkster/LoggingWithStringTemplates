# LoggingWithStringTemplates

Just a proof of concept of how to use the new String Template feature from JDK 21 to use with a logging framework, in this case SLF4J.

The different loggers (info, debug, error etc) are defined in the class LOG as fields and are initialized statically.
The calling class ist determined via the StackWalker API, which gets the first class that isn't anonymous or the LOG class.
If the StringTemplate contains a Throwable, then the log method with the Throwable parameter is called, else thje log method with only one String parameter.

To use the loggers either call them directly:

```
import de.darenkster.stringtemplates2slf4j.loggers.LOG;

...

LOG.INFO."Text \{variable}"
```
or statically import the fields from the LOG class:
```
import static de.darenkster.stringtemplates2slf4j.loggers.LOG.*;

...

INFO."Text \{variable}"
```
The Main class prints out the following:
```
10:33:44.899 [main] INFO de.darenkster.stringtemplates2slf4j.Main -- This is a info test
10:33:44.907 [main] ERROR de.darenkster.stringtemplates2slf4j.Main -- This is a error test java.lang.Exception
java.lang.Exception: null
  at de.darenkster.stringtemplates2slf4j.Main.main(Main.java:9)
10:33:44.909 [main] DEBUG de.darenkster.stringtemplates2slf4j.Main -- This is a debug test
10:33:44.910 [main] WARN de.darenkster.stringtemplates2slf4j.Main -- This is a warn
test
10:33:44.912 [main] INFO de.darenkster.stringtemplates2slf4j.Main -- This is a info test from within an anonymous class
10:33:44.912 [main] INFO de.darenkster.stringtemplates2slf4j.Main -- This is a info test from within a lambda
```
