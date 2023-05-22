@echo off
javac application/*.java
jar cfm "Pipemaster 1988.jar" manifest.mf application/*.class
@echo on