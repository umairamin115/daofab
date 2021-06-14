This is RESTful Java WebService Provider. It behaves as a server and expects requests from client avalaible at https://github.com/umairamin115/daofabrestclient.

http://localhost:8080/DAOFAB_REST/controller/parentmainpage At this path, Parent.json would be provided by the service. 

http://localhost:8080/DAOFAB_REST/controller/childmainpage At this path, Child.json would be provided by the service.

Deploy this RESTful webservice first, this service will read the database(provided json files) and make it avalaible on above links for client. Java and Jersey REST API is used in the development of server. Tomcat 8.5 was used with Eclipse IDE. 

