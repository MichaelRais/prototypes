# Prototypes & Frameworks
The "prototypes_frameworks" area is for any prototypes built in a framework.

# Java microservice prototype in Spark framework with JUnit via Maven
<ul>
<li>A simple RESTful prototype of a microservice built in Spark framework, using JUnit in Maven via the Surefire plug-in.
<li>The prototype itself loads and serves country code lookups.  If you have Maven installed and download the source, you can run the prototype per the demo summary below.
<li>I'm still sanding rough edges in this <i>functioning</i> prototype.   Documentation just got started.
<li>I'm now a fan of the Spark Java framework for microservices:  http://sparkjava.com
</ul>

#Requirements
<ul>
<li>Java
<li>Maven:  Dependencies are in the project object model (pom.xml) file.
<li>Spark v2.1
<li>JUnit 
</ul>

#Demo Summary
<ul>
<li>mvn package
<li>mvn exec:java -Dexec.mainClass=router1.Countries
<li>http://0.0.0.0:4567/
<li>http://0.0.0.0:4567/initload/
<li>http://0.0.0.0:4567/get/US
<li>http://0.0.0.0:4567/get/IT
</ul>
