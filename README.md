# Simple chat with saving history to DB
#### My playing with Spring boot, Spring websocket and Aerospike

### Requirements

* JDK 1.8+
* Maven 3.3+

### How to run

* Entry point is in Server.java 
* After running server you can connect as client to `http://localhost:8080`

### Using database

* You must have your own Aerospike server and pass its ip and port to `application.properties`
* To run chat with saving history, pass `db` Spring profile in `application.properties`
* History is saving every 1 minute
* To see history go to `http://localhost:8080/getHistory`
* To clear history go to `http://localhost:8080/clearHistory`. **Warning!** It is irreversible.

### Troubleshooting

* If you see errors like `java: java.lang.NoSuchFieldError: Class com.sun.tools.javac.tree.JCTree$JCImport does not have member field 'com.sun.tools.javac.tree.JCTree qualid'`, try to use JDK 1.8. Once I'll align the project with the newest Java versions. Or not.

### Example
<img width="766" height="733" alt="example" src="https://github.com/user-attachments/assets/3347677a-c389-4ea0-bfa1-e93f4c002061" />
