# oauth2-example

Example of oauth2 usage with client, resource-server and authorization-server(keycloak)


## Application scenario:

Build Backend Application for a fitness application
In this example we are going to focus only on store the history of workouts.

### User Cases are:

* **Add new workout record for a user:** we want to add a new record that stores user, the start and the end times of the workout and the difficulty of the workout, using a scale between 1 to 5.
* **Find all workout for a user:** all workouts of a specific user will be return, in case of this same user is authenticated. A user cannot get others user's workouts
* **Delete a workout:** Any user having the admin role can delete a workout for any other user


### Roles

 * fitnessuser - standard user
     * user can add a workout for themselves and can see their own workout history
 * fitnessadmin - admin
     * can add workouts for themselves or see their own recorded workouts 
	 
### Users

* mary - fitnessadmin user
*rachel, bill - fitnessuser user


## Requirements:
* Java
* Maven
* docker

## Run
* Inside resource-server folder build the image and run the application :
   * run ``docker build -t resource-server-example .``
   * run ``docker run --rm resource-server-example``
  
* Add on /etc/hosts file :  ``127.0.0.1		keycloak``
* Run ``docker-compose up``
* After keycloak is running import the realm-export.json inside keycloak folder to create users and other stuff
* Follow the next configuration in case of missing:
 ![image](https://user-images.githubusercontent.com/15162574/165830861-36264780-0a45-4c43-ab5e-9666e7d45830.png)
 ![image](https://user-images.githubusercontent.com/15162574/165830935-295c0a86-a9c4-48f3-ac8e-e8fc3b77affd.png)
 ![image](https://user-images.githubusercontent.com/15162574/165830975-5e590f61-8c10-49e7-a551-b6d9693e6541.png)
 ![image](https://user-images.githubusercontent.com/15162574/165831011-b6377e0b-e5e6-4773-aedb-94845687f44b.png)
 ![image](https://user-images.githubusercontent.com/15162574/165831047-6d9f5d62-a0e1-4ca5-a094-17daec0760c7.png)
 ![image](https://user-images.githubusercontent.com/15162574/165831077-333a87fc-1a0a-4835-ab9b-1245a6776cd9.png)
 ![image](https://user-images.githubusercontent.com/15162574/165831107-8f8d64ab-c45a-4a19-9743-66b105e78649.png)

## Testing application

* You can import the postman collection provided to get access to the requests
* Get the token first 
* And use it with other requests

