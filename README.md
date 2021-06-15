# LabyrinthGeneration
                                                PROJECT REQUIREMENTS
    1. Solution should be developed as best as you can using SpringBoot and built with Maven or Gradle.
    2. In order to use application we should login, if user is not logged in, login page should be shown to enter the application (HTML page).
    3. As soon as we login  successfully we should see the HTML 
    page with file upload input in the middle of the screen to upload our plain text file with labyrinth configuration and links on top such as "Logout" and "My labyrinths".
    4. Our labyrynth configuration is a plain text file which contains the initial location of person and matrix with 0 and 1. See example
       below: 1 1
              1 1 1 1 1 1
              1 0 0 0 0 1
              1 0 1 0 0 1
              1 0 1 0 0 0
              1 1 1 1 1 1
       5. First row in the file contains (x, y) initial coordinates of the person. X-axis goes from left to right, Y-axis goes from top to bottom. 
       1 - wall, 0 - no wall. Matrix width and height could be different size up to 1000 elements.
       6. When we upload a new labyrinth configuration file, application should run the algorithm to find a path for us how to exit the labyrinth 
       and show us the labyrinth as colorful table with the path how to exit the labyrinth (HTML page). If there is no way to exit the labyrinth, it should say about it.
