Spark Puppet Webapp Sample
==========================

The king of hello worlds.


For dev
```
mvn clean compile
mvn exec:java -Dexec.mainClass="com.puppet.sample.App"
```
  

For real
```
mvn clean package
java -jar target/puppet-webapp-<VERSION>.jar [-DappPort=XXXX]
```
  
  

Dependency projects
-------------------
[Spark Puppet Webapp Spanish Language](https://github.com/ipcrm/spark_puppet_webapp_lang_sp)



