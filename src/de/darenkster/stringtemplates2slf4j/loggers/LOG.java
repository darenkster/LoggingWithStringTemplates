package de.darenkster.stringtemplates2slf4j.loggers;

import java.lang.StringTemplate.Processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.StackWalker.StackFrame;
import java.util.List;
import java.util.stream.Collectors;

/**
* Usage: 
* import static de.darenkster.stringtemplates2slf4j.loggers.LOG.*;
* ...
* INFO."Logtext"
*/
public class LOG{

    /* The Logger Fields*/
    public static final Processor<Void, RuntimeException> INFO;
    public static final Processor<Void, RuntimeException> ERROR;
    public static final Processor<Void, RuntimeException> DEBUG;
    public static final Processor<Void, RuntimeException> WARN;
    public static final Processor<Void, RuntimeException> TRACE;
    
    static {
        INFO = StringTemplate.Processor.of((StringTemplate st) -> {
            Logger logger = getLoggerForDeclaringClass();
            String interpolatedMessage = st.interpolate();
            logger.info(interpolatedMessage);
            return null;
        });
        ERROR = StringTemplate.Processor.of((StringTemplate st) -> {
            Logger logger = getLoggerForDeclaringClass();
            String interpolatedMessage = st.interpolate();
            logger.error(interpolatedMessage);
            return null;
        });
        DEBUG = StringTemplate.Processor.of((StringTemplate st) -> {
            Logger logger = getLoggerForDeclaringClass();
            String interpolatedMessage = st.interpolate();
            logger.debug(interpolatedMessage);
            return null;
        });
        WARN = StringTemplate.Processor.of((StringTemplate st) -> {
            Logger logger = getLoggerForDeclaringClass();
            String interpolatedMessage = st.interpolate();
            logger.warn(interpolatedMessage);
            return null;
        });
        TRACE = StringTemplate.Processor.of((StringTemplate st) -> {
            Logger logger = getLoggerForDeclaringClass();
            String interpolatedMessage = st.interpolate();
            logger.trace(interpolatedMessage);
            return null;
        });
    }
    
    /** Looking up what class is calling the Logger */
    public static final Logger getLoggerForDeclaringClass() {
        List<StackFrame> stackTrace = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).walk((sfs) -> {
                return sfs
                    // TODO more filters for classes with weird names
                    .filter((StackFrame sf) -> !sf.getDeclaringClass().isAnonymousClass())
                    .filter((StackFrame sf) -> !sf.getDeclaringClass().equals(LOG.class))
                    .findFirst()
                    .stream()
                    .toList();                      
                    
            });
        
            Class<?> clazz = stackTrace.getFirst().getDeclaringClass();
            return LoggerFactory.getLogger(clazz);
    }

}
