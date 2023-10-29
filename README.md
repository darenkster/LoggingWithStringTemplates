# LoggingWithStringTemplates

Just a proof of concept of how to use the new String Template feature from JDK 21 to use with a logging framework, in this case SLF4J   

The different loggers (info, debug, error etc) are defined in die class LOG as fields and are initialized statically.

To use the loggers ether call them directly:

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
The calling class ist determied via the StackWalker API, which gets the first class that isn't anonymous.
