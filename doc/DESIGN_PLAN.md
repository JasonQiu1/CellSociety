# Cell Society Design Plan
### Team Number
### Names


#### Examples

Here is a graphical look at my design:

![This is cool, too bad you can't see it](images/online-shopping-uml-example.png "An initial UI")

made from [a tool that generates UML from existing code](http://staruml.io/).


Here is our amazing UI:

![This is cool, too bad you can't see it](images/29-sketched-ui-wireframe.jpg "An alternate design")

taken from [Brilliant Examples of Sketched UI Wireframes and Mock-Ups](https://onextrapixel.com/40-brilliant-examples-of-sketched-ui-wireframes-and-mock-ups/).



## Overview
![Design overview UML diagram](images/CellSocietyUMLDiagram.jpg)


## User Interface


## Configuration File Format


## Design Details
### Game
The Game class holds main and all of its attributes and methods are static since there will only ever be one game instance. It initializes the JavaFX stage and user interface before running the game loop, which will repeatedly:
2. tell the View instance to draw the screen (UI and grid)
3. tell the Simulation instance to update (if it exists)
4. tell the View instance to check+handle user input

It is also responsible for loading/saving simulations/grids from/to config files/simulations using ConfigSaver and ConfigLoader.

### ConfigSaver
The ConfigSaver class is responsible for taking a Simulation instance and file path and saving that simulation's details into the file in some XML format.

### ConfigLoader
The ConfigLoader class is responsible for taking a file path and returning a Simulation instance that corresponds to that file's configuration details.

### RuleSet
The RuleSet interface provides the functionality to take a given array of Cell instances and returns an array of the same size with flags. These flags are used by the next method to actually update the cells and returns the updated array of cells. It also returns the number of states the ruleset uses.

Each different simulation (Conway's Game of Life, Spreading of Fire, etc.) implements this interface.

### Grid
The Grid interface provides the functionality to update itself given a particular RuleSet instance.

Different types of grids (e.g. finite or infinite grids) implement this interface.

### Simulation
The Simulation class is responsible for any high-level details regarding when to update the stored Grid instance, such as handling simulation speed and pausing/unpausing, and also holds what RuleSet to use to update it.

### GridDrawer
The GridDrawer class is responsible for drawing the given Grid instance onto the screen given some Group/Pane/StackPane JavaFX object. It makes sure all Cells that are the same state are the same color.

### UserInterface
The UserInterface class is responsible for drawing the user interface given some Group/Pane/StackPane JavaFX object and detecting user input (via buttons, text field, or keypress).

It draws all the buttons and the about box.

### Controller
The Controller class is responsible for telling Game or Simulation what to do given user input (via buttons, text field, or keypress)

### View
The View class is responsible for drawing anything visible on the screen, detecting user input, and handling it. It does this by being instantiated with a Stage JavaFX instance and then dividing up the screen before passing the individual parts to instances of GridDrawer and UserInterface to draw on. It also uses a Controller instance to handle user input events from the UserInterace.

## Use Cases


## Design Considerations


## Team Responsibilities

 * Team Member #1

 * Team Member #2

 * Team Member #3
