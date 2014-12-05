set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=128m
mvn jetty:stop clean install -U