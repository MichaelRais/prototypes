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
<li>Spark v2.1
<li>Maven:  Dependencies are in the project object model file.   (e.g. You'll see JUnit and Gson listed in the pom.xml.)
</ul>

#Demo Summary
<ul>
<li>mvn package
<li>mvn exec:java -Dexec.mainClass=router1.Countries
<li>0.0.0.0:4567
<li>0.0.0.0:4567/initload/
<li>0.0.0.0:4567/get/IO
<li>0.0.0.0:4567/get/US
</ul>
