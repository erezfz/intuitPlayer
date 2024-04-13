# Erez Fiengertz - Home Assignment

General
---
Application for providing information about multiple or single player,
via dedicated REST end-points.

Run The Application
---
The app is running on port 8090 (e.g http://localhost:8090)  
* **Options**
  * jar sent in the replay mail
    * Prerequisite: java 21.
    * run the command in your host: java -jar intuitErez.jar
  * maven
    * Prerequisite: java 21, maven.
    * Run:
      * command: mvn package <br> execute in the project home directory.intuitErez.jar will be created in the target folder.
      * command: java -jar intuitErez.jar
  * Docker
    * Prerequisite: Docker engine and client (e.g DockerDesktop).
    * Run:
      * command (build the image): docker build -t intuitplayer . <br> in the project home directory
      * start a container from the image. It automatically starts the App inside the docker on port 8090

Summery
---
* **REST Endpoints**
  * `GET /api/players` - returns a list of all players.
  * `GET /api/players/{playerID}` - returns a single player by its ID
    * if the ID doesn't exist, return 204 code
* **Validations:**
  * Endpoints:
    * No empty playerID
    * no '/' sign in the ID
  * import data - can't import null record from csv
  * Persistance will not accept empty record or no player id
  * Other:
    * no null PlayerID
* **BootStrap Flow**
  * The App reads startup related configuration from file 
  * The App performs import of the players data into its persistence (data file configuration from file)

Additions with infinite time
---
* Performance
  * Add pagination
    * import data - paginating and restrict the amount of data sent in different layers (repository service, dao) when adding data
    * /api/players end point - add paging support (e.g: /api/players/{page_id}, add api to get pages numbers)
    * not allow /api/players will not return all data but part of it (chunk)
* Persistence
  * add mySQL or sqlite DAO, and use it in the app.
* Deployment & DevOps
  * add docker compose (for placing resources in volume and DB), or setting K8 related configuration
* Configuration
  * Add framework providing dependency-injection (e.g spring); especially usefull for class members configuration and better testing.
  * ability to configure the functionality and server bootstrap from outside the app - via K8 and helm artifacts (configMaps, values files) and env variables.
* improve test coverage
* Objects Mapping
  * use 3d-party MapStruct as standard to produce the mapping between players 'raw-data' and player class, between player to json
* Add functional validations
* Use a robust framework to construct and run the App - like springboot
  * use of better mapping mechanism - with imperative annotation to do uri validation and extractions
  * support for pagination, security, persistance APIs
* Add robust CI/CD
* Add more checked exceptions types
* Add AOP support (aspect-oriented programing) for exception handling
  * handle all exceptions in a single location (when applicalbe), and not in every method that has to deal with exceptions
  * use @AspectJ 

