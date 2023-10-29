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
}
