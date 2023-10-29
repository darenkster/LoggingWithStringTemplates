@ECHO OFF

%JAVA_HOME%\bin\javac.exe ^
-d bin ^
-classpath "C:\Users\Renko\.m2\repository\org\slf4j\slf4j-api\2.0.9\slf4j-api-2.0.9.jar;C:\Users\Renko\.m2\repository\ch\qos\logback\logback-classic\1.4.11\logback-classic-1.4.11.jar;C:\Users\Renko\.m2\repository\ch\qos\logback\logback-core\1.4.11\logback-core-1.4.11.jar" ^
--source 21 ^
--enable-preview ^
src\de\darenkster\stringtemplates2slf4j\loggers\*.java ^
src\de\darenkster\stringtemplates2slf4j\*.java 
