@ECHO OFF

%JAVA_HOME%\bin\java.exe ^
--enable-preview 
^-classpath "lib\slf4j-api-2.0.9.jar;lib\logback-classic-1.4.11.jar;lib\logback-core-1.4.11.jar;bin\." 
^de.darenkster.stringtemplates2slf4j.Main
