# Home Assignment

## Technologies
- Java SE Development Kit 17.0.5 (64-bit)
- Spark 2.9.4

## Java
### Basics
#### Links
- [How to Import Custom Class in Java?](https://www.geeksforgeeks.org/how-to-import-custom-class-in-java/)
- [Java 8 Functional Interfaces](https://www.digitalocean.com/community/tutorials/java-8-functional-interfaces)
- [Java Web Application Tutorial for Beginners](https://www.digitalocean.com/community/tutorials/java-web-application-tutorial-for-beginners#servlets-jsps)

### REST API
#### Links
- [Class HttpServer](https://docs.oracle.com/en/java/javase/17/docs/api/jdk.httpserver/com/sun/net/httpserver/HttpServer.html)
- [Package com.sun.net.httpserver](https://docs.oracle.com/en/java/javase/17/docs/api/jdk.httpserver/com/sun/net/httpserver/package-summary.html)
- [HTTP Client and WebSocket APIs](https://docs.oracle.com/en/java/javase/17/docs/api/java.net.http/java/net/http/package-summary.html)
- [Develop an HTTP Server in Java](https://medium.com/@sayan-paul/develop-an-http-server-in-java-2137071a54a1)

### HashMaps
#### Links
- [HashMap computeIfAbsent() method in Java with Examples](https://www.geeksforgeeks.org/hashmap-computeifabsent-method-in-java-with-examples/)
- [HashTable putIfAbsent() method in Java with Examples](https://www.geeksforgeeks.org/hashtable-putifabsent-method-in-java-with-examples/)

### Exit Method
#### Links
- [Exit Method in Java](https://www.scaler.com/topics/java/exit-in-java/)
- [How to add a java (jvm) shutdown (close, exit) listener](https://itsiastic.wordpress.com/2013/01/19/how-to-add-a-java-jvm-shutdown-close-exit-listener/)

## Maven

### Installation
#### Links
- [Downloading Apache Maven 3.8.6](https://maven.apache.org/download.cgi)
- [How to Install Maven on Windows](https://phoenixnap.com/kb/install-maven-windows)

1. Verify `Maven` version
   ```
   mvn -version
   ```

2. Global `Maven` artifacts folder on `Windows 10` ([source](https://stackoverflow.com/questions/4490135/how-to-change-maven-repository-folder-in-windows))
   ```
   C:\Users\<user_name>\.m2
   ```

### IntelliJ Integration
#### Links
- [Maven](https://www.jetbrains.com/help/idea/maven-support.html)
- [Creating a Maven project](https://www.jetbrains.com/idea/guide/tutorials/working-with-maven/creating-a-project/)
- [Add Maven support to an existing project](https://www.jetbrains.com/help/idea/convert-a-regular-project-into-a-maven-project.html)
- [Guide to Maven Archetype](https://www.baeldung.com/maven-archetype)
- [Add a Maven dependency](https://www.jetbrains.com/help/idea/work-with-maven-dependencies.html#generate_maven_dependency)
- [Configure a custom remote repository](https://www.jetbrains.com/help/idea/library.html#configure-custom-remote-repo)
- [Maven. Repositories](https://www.jetbrains.com/help/idea/maven-repositories.html)
- [Dependency analyzer](https://www.jetbrains.com/help/idea/work-with-maven-dependencies.html#dependency_analyzer)
- [How to Add External JAR File to an IntelliJ IDEA Project?](https://www.geeksforgeeks.org/how-to-add-external-jar-file-to-an-intellij-idea-project/)

### POM File
#### Links
- [Introduction to the POM](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html#:~:text=Available%20Variables-,What%20is%20a%20POM%3F,default%20values%20for%20most%20projects.)
- [Adding dependencies](https://www.jetbrains.com/idea/guide/tutorials/migrating-junit4-junit5/adding-dependencies/)

### Useful Commands
- Remove dependency ([source](https://stackoverflow.com/questions/32538853/how-to-properly-remove-a-dependency-in-a-maven-project))
  ```agsl
  mvn dependency:purge-local-repository
  ```

## Spark
### Basics
#### Links
- [Spark Overview](https://spark.apache.org/docs/latest/#:~:text=Spark%20runs%20on%20Java%208,that%20Spark%20was%20compiled%20for.)
- [Spark - Quick Start](https://sparkjava.com/)
- [Spark - Getting Started](https://sparkjava.com/documentation#getting-started)
- [Maven - Spark Search](https://search.maven.org/search?q=com.sparkjava)
- [Spark - Compatibility](https://spark.apache.org/docs/latest/#:~:text=Spark%20runs%20on%20Java%208,that%20Spark%20was%20compiled%20for.)
- [Creating a REST API quickly using pure Java](https://www.boxuk.com/insight/creating-a-rest-api-quickly-using-pure-java/)
- [SLF4J warning or error messages and their meanings](https://www.slf4j.org/codes.html#StaticLoggerBinder)
- [SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder"](https://stackoverflow.com/questions/7421612/slf4j-failed-to-load-class-org-slf4j-impl-staticloggerbinder)
- [java.lang.NoClassDefFoundError: org.slf4j.LoggerFactory](https://stackoverflow.com/questions/12926899/java-lang-noclassdeffounderror-org-slf4j-loggerfactory)
- [Spark CORS Access-control-allow-origin error](https://stackoverflow.com/questions/45295530/spark-cors-access-control-allow-origin-error)
- [Change Java spark port number](https://stackoverflow.com/questions/39260529/change-java-spark-port-number)
- [How to get the request parameters using get in Spark Java framework?](https://stackoverflow.com/questions/29127490/how-to-get-the-request-parameters-using-get-in-spark-java-framework)

## Threads and Multithreading
### Basics
#### Links
- [Class Thread](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Thread.html)
- [Multithreading in Java - Everything You MUST Know](https://www.digitalocean.com/community/tutorials/multithreading-in-java)
- [Defining and Starting a Thread](https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html)
- [Asynchronous Programming in Java](https://www.baeldung.com/java-asynchronous-programming)
- [Java Thread Example](https://www.digitalocean.com/community/tutorials/java-thread-example)
- [Java Thread wait, notify and notifyAll Example](https://www.digitalocean.com/community/tutorials/java-thread-wait-notify-and-notifyall-example)
- [Thread Life Cycle in Java - Thread States in Java](https://www.digitalocean.com/community/tutorials/thread-life-cycle-in-java-thread-states-in-java)
- [Java Thread Join Example](https://www.digitalocean.com/community/tutorials/java-thread-join-example)
- [Java executors: how to be notified, without blocking, when a task completes?](https://stackoverflow.com/questions/826212/java-executors-how-to-be-notified-without-blocking-when-a-task-completes)

### Passing Parameters
#### Links
- [Passing Parameters to Java Threads](https://www.baeldung.com/java-thread-parameters)
- [How can I pass a parameter to a Java Thread?](https://stackoverflow.com/questions/877096/how-can-i-pass-a-parameter-to-a-java-thread)

### Thread Safety
#### Terms
- Race Conditions
- Deadlocks
- Starvation

#### Links
- [Thread Safety in Java](https://www.digitalocean.com/community/tutorials/thread-safety-in-java)
- [ConcurrentHashMap in Java](https://www.digitalocean.com/community/tutorials/concurrenthashmap-in-java)
- [Deadlock in Java Example](https://www.digitalocean.com/community/tutorials/deadlock-in-java-example)
- [Guide to RejectedExecutionException in Java](https://medium.com/double-pointer/guide-to-rejectedexecutionexception-in-java-60d3fb787530)
- [Starvation and Livelock](https://docs.oracle.com/javase/tutorial/essential/concurrency/starvelive.html)

### Thread Sleep
#### Links
- [Thread.sleep() in Java - Java Thread sleep](https://www.digitalocean.com/community/tutorials/thread-sleep-java)
- [Why must I wrap every Thread.sleep() call in a try/catch statement? [duplicate]](https://stackoverflow.com/questions/37594251/why-must-i-wrap-every-thread-sleep-call-in-a-try-catch-statement#:~:text=However%2C%20it%20is%20there%20to,and%20it%20has%20valid%20uses.)

### Thread Pool Executer
#### Links
- [Class ThreadPoolExecutor](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/ThreadPoolExecutor.html)
- [A Guide to the Java ExecutorService](https://www.baeldung.com/java-executor-service-tutorial)
- [ThreadPoolExecutor - Java Thread Pool Example](https://www.digitalocean.com/community/tutorials/threadpoolexecutor-java-thread-pool-example-executorservice)
- [Java Multi-Threading With the ExecutorService](https://dzone.com/articles/java-concurrency-multi-threading-with-executorserv)
- [Java ThreadPoolExecutor with BlockingQueue](https://howtodoinjava.com/java/multi-threading/how-to-use-blockingqueue-and-threadpoolexecutor-in-java/#22-handling-rejected-tasks)
- [Binary Semaphore Tutorial and Example](https://howtodoinjava.com/java/multi-threading/binary-semaphore-tutorial-and-example/)
- [Throttling Task Submission Rate in Java](https://howtodoinjava.com/java/multi-threading/throttling-task-submission-rate-using-threadpoolexecutor-and-semaphore/)
- [ExecutorService – Waiting for Threads to Finish](https://www.baeldung.com/java-executor-wait-for-threads)
- [How to handle n requests in multi threading [closed]](https://stackoverflow.com/questions/33675139/how-to-handle-n-requests-in-multi-threading)

### Sockets Server
#### Links
- [Multithreaded Servers in Java](https://www.geeksforgeeks.org/multithreaded-servers-in-java/)
- [Java Socket Programming - Socket Server, Client example](https://www.digitalocean.com/community/tutorials/java-socket-programming-server-client)

### Future and Callable
#### Links
- [What is the Future.isDone() method in Java?](https://www.educative.io/answers/what-is-the-futureisdone-method-in-java)
- [Java Callable Future Example](https://www.digitalocean.com/community/tutorials/java-callable-future-example)

## RabbitMQ
### Basics
#### Links
- [RabbitMQ - Introduction](https://www.rabbitmq.com/tutorials/tutorial-one-java.html)

## Log4J
### Basics
#### Links
- [Welcome to Log4j 2!](https://logging.apache.org/log4j/2.x/manual/index.html)
- [Maven, Ivy, Gradle, and SBT Artifacts](https://logging.apache.org/log4j/2.x/maven-artifacts.html)
- [Apache Log4j™ 2](https://logging.apache.org/log4j/2.x/index.html)
- [Log4j 2 API](https://logging.apache.org/log4j/2.x/manual/api.html)
- [Migrating from Log4j 1.x to 2.x](https://logging.apache.org/log4j/2.x/manual/migration.html#ConfigurationCompatibility)
- [Log4j Maven](https://www.javatpoint.com/log4j-maven)
- [Configuration](https://logging.apache.org/log4j/2.x/manual/configuration.html)
- [Where should I put the log4j.properties file?](https://stackoverflow.com/questions/1485987/where-should-i-put-the-log4j-properties-file)
- [Logger in Java - Java Logging Example](https://www.digitalocean.com/community/tutorials/logger-in-java-logging-example)
- [What is logger in Java and why do you use it?](https://www.edureka.co/blog/logger-in-java) 