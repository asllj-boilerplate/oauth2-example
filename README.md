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

* run ``docker-compose up``
* after keycloak is running import the realm-export.json inseide keycloak folder to create users and other stuff
* Inside resource-server folder build the image and run the application :
   * run ``docker build -t resource-server-example .``
   * run ``docker run --rm resource-server-example``
   

## Testing application

* You can import the postman collection provided to get access to the requests

