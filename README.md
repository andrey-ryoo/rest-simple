# rest-simple

Rest-simple is a minimalistic library that simplifies REST requests. It's based on Jersey Client and Jackson

Usage:

RestSimple.get(url).getRawResponse();  // Raw Response returns an object containing Entity, Status, headers, cookies etc
RestSimple.post(url).field("name", "value").file(yourFile).getRawResponse();
...

Todo:
  * Add post strings as entities
  * Add expected conditions to surefy response is valid
  * Add other methods (HEAD, OPTION, PATCH...)
  
How to import with Maven:
<code>
  <repositories>
        <repository>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
            <id>bintray-kenshir007-maven</id>
            <name>bintray</name>
            <url>http://dl.bintray.com/kenshir007/maven/rest-simple</url>
        </repository>
   </repositories>
   
   <dependency>
            <groupId>pro.ryoo</groupId>
            <artifactId>rest-simple</artifactId>
            <version>1.0.0</version>
   </dependency>
</code>
   
The library is in its begining stage.
