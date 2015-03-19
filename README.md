# Prototypes & Frameworks
The "prototypes_frameworks" area is for any prototypes built in a framework.

# Java microservice prototype in Spark framework with JUnit via Maven
<ul>
<li>A simple prototype of a microservice built in Spark framework, using JUnit in Maven via the Surefire plug-in.
<li>This is a <i>functioning</i> prototype. The prototype itself loads and serves country code lookups. I've kept it super demo-friendly by using GET & JSON even though PUT/DELETE & XML would be called for to make it RESTful instead of 'REST-like'.    
<li>If you have Maven installed and download the source, you can run the prototype per the demo summary below.
<li>I'm now a fan of the Spark Java framework for microservices:  http://sparkjava.com
</ul>

#Requirements
<ul>
<li>Java 8
<li>Spark v2.1
<li>Maven:  Dependencies are in the project object model file.   (e.g. You'll see JUnit and Gson listed in the pom.xml.)
</ul>

#Demo Summary
<ul>
<li>mvn package
<li>mvn exec:java -Dexec.mainClass=router1.CountryRouter
<li>0.0.0.0:4567/v0/load/IT/Italy
<li>0.0.0.0:4567/v0/load/FR/France
<li>0.0.0.0:4567/v0/codescountries
<li>0.0.0.0:4567/v0/delete/FR
<li>0.0.0.0:4567/v0/codescountries
<li>0.0.0.0:4567/v0/initload
<li>0.0.0.0:4567/v0/get/IO
<li>0.0.0.0:4567/v0/get/US
<li>0.0.0.0:4567/v0/codescountries
<li>...and so on...
</ul>
