The objective of this lab is to synchronize several clients to connect to a server at a predetermined time. This is a part of the requirements of a Distributed Denial of Service (DDoS) attack.
The parties involved in this project are 
(a) a coordinator who communicates to nodes to connect ("attack") 
(b) nodes who serve as clients to open a connection to the server and 
(c) the server, who allows concurrent TCP connections on some designated port. The objective of the nodes is to open a TCP connection (each) to the server as close to simultaneously as possible. Notice that for this functionality to be supported, the nodes must be in server mode (listening on a port) until they hear from the coordinator. Then they switch to being in client mode, connecting to the server at the specified time.

Step 1: Check that your program works by using one coordinator, 3 clients and a server. Have the server respond to each client by sending its current time and close its connection. Have the server write the log of connections to a file, and close each connection. 
Step 2: Keep each connection alive 10-30 seconds to allow overlap between connections. You can do this by having the nodes and server exchange random strings a few times. Have the server write the log of connections to a file.





TO RUN THE PROGRAM:

Compile the three java files and run 
1) Server first
2) Node second
3) Coordinator third

Follow the instructions given by the Coordinator.

QUESTIONS:

The program may not work correctly if a string is entered instead of an integer when asked how many attacks is wanted.
Precondtions could have been added in order to ensure correctness, as well as cleaner code in terms of multiple helper classes.
