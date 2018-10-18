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

### Example
![example](https://image.prntscr.com/image/WnotSfXCTUO5uQJuSdgLLA.png)