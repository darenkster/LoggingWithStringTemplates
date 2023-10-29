package de.darenkster.stringtemplates2slf4j;

import static de.darenkster.stringtemplates2slf4j.loggers.LOG.*;

public class Main {

	public static void main(String[] args) {
		var test = "test";
		INFO."This is a info \{test}";
		ERROR."This is a error \{test}";
		DEBUG."This is a debug \{test}";
		WARN."This is a warn \{test}";
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
	* 09:15:33.309 [main] INFO de.darenkster.stringtemplates2slf4j.Main -- This is a info test
	* 09:15:33.311 [main] ERROR de.darenkster.stringtemplates2slf4j.Main -- This is a error test
	* 09:15:33.312 [main] DEBUG de.darenkster.stringtemplates2slf4j.Main -- This is a debug test
	* 09:15:33.312 [main] WARN de.darenkster.stringtemplates2slf4j.Main -- This is a warn test
	* 09:15:33.313 [main] INFO de.darenkster.stringtemplates2slf4j.Main -- This is a info test from within a anonymous class
	* 09:15:33.314 [main] INFO de.darenkster.stringtemplates2slf4j.Main -- This is a info test from within a lambda
	*/
}
