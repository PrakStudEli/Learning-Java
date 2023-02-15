#!/bin/bash

/home/ilya/lib/jdk-19.0.2/bin/javac Main.java classes/*.java
/home/ilya/lib/jdk-19.0.2/bin/java Main

rm *.class
rm classes/*.class
