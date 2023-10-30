package de.darenkster.stringtemplates2slf4j.loggers;

import java.lang.StackWalker.StackFrame;
import java.lang.StringTemplate.Processor;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        INFO = StringTemplate.Processor.of((StringTemplate stringTemplate) -> {
            Logger logger = getLoggerForCallingClass();
            interpolateAndLog(stringTemplate, 
                (message,throwable) -> logger.info(message, throwable),
                (message) -> logger.info(message) 
                );
            return null;
        });
        ERROR = StringTemplate.Processor.of((StringTemplate stringTemplate) -> {
            Logger logger = getLoggerForCallingClass();
            interpolateAndLog(stringTemplate, 
                (message,throwable) -> logger.error(message, throwable),
                (message) -> logger.error(message) 
                );
            return null;
        });
        DEBUG = StringTemplate.Processor.of((StringTemplate stringTemplate) -> {
            Logger logger = getLoggerForCallingClass();
            interpolateAndLog(stringTemplate, 
                (message,throwable) -> logger.debug(message, throwable),
                (message) -> logger.debug(message) 
                );
            return null;
        });
        WARN = StringTemplate.Processor.of((StringTemplate stringTemplate) -> {
            Logger logger = getLoggerForCallingClass();
            interpolateAndLog(stringTemplate, 
                (message,throwable) -> logger.warn(message, throwable),
                (message) -> logger.warn(message) 
                );
            return null;
        });
        TRACE = StringTemplate.Processor.of((StringTemplate stringTemplate) -> {
            Logger logger = getLoggerForCallingClass();
            interpolateAndLog(stringTemplate, 
                (message,throwable) -> logger.trace(message, throwable),
                (message) -> logger.trace(message) 
                );
            return null;
        });
    }
    
    /** Looking up what class is calling the Logger */
    private static final Logger getLoggerForCallingClass() {
        List<StackFrame> stackTrace = StackWalker
            .getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
            .walk((stackFrameStream) -> 
                stackFrameStream
                    // TODO more filters for classes with weird names
                    .filter((stackFrame) -> !stackFrame.getDeclaringClass().isAnonymousClass())
                    .filter((stackFrame) -> !stackFrame.getDeclaringClass().equals(LOG.class))
                    .findFirst()
                    .stream()
                    .toList());
        
        Class<?> clazz = stackTrace.getFirst().getDeclaringClass();
        return LoggerFactory.getLogger(clazz);
    }

    /** Interpolate the String and either call the basic log method or the one with the throwable */
    private static void interpolateAndLog(StringTemplate stringTemplate, 
            BiConsumer<String, Throwable> exceptionLogMethod,
            Consumer<String> basicLogMethod) {
        String interpolatedMessage = stringTemplate.interpolate();
        stringTemplate.values()
            .stream()
            .filter(Throwable.class::isInstance)
            .map(Throwable.class::cast)
            .findFirst()
            .ifPresentOrElse(
                throwable -> exceptionLogMethod.accept(interpolatedMessage, throwable), 
                () ->basicLogMethod.accept(interpolatedMessage));
        
    }
}
