skellybones
===========

Example Spring 3.1 REST container in Scala. The only XML is the build file. 

Uses embedded jetty for web container.

BUILD

mvn package

RUN INSIDE JETTY

mvn jetty:run

THEN TRY BROWSING TO

http://localhost:9001/hello
http://localhost:9001/demo/3
http://localhost:9001/person/3
