# cell society

## Team 4

## Jason Qiu, Spencer Katz, Prince Ahmed

This project implements a cellular automata simulator.

### Timeline

* Start Date: January 25

* Finish Date: February 12

* Hours Spent: Jason (25 hours) + Spencer (? hours) + Prince (? hours)

### Attributions

* Resources used for learning (including AI assistance)

* Resources used directly (including AI assistance)

    - https://stackoverflow.com/questions/49732048/autoscalepane-in-javafx-with-layoutchildren

### Running the Program

* Main class: Game.java

* Data files needed: Any xml config following the template (see data folder for examples)

* Interesting data files:

* Key/Mouse inputs: Only mouse clicks, no keyboard input.

### Notes/Assumptions

* Assumptions or Simplifications:

* Known Bugs:

* Features implemented:
    - View (and Controller)
        - All public classes and functions fully documented with Javadoc comments
        - No style violations other than dead code waiting for additional implementation from the
          model
        - SHY, encapsulated code. All local variables are private, and all classes except View,
          SimulationWindow, and Controller are package-level scope.
        - Supports displaying multiple independent simulations running simultaneously
        - Extensible class/interface inheritance hierarchy
        - Extensible, hot-swappable views for simulations
            - Currently implemented: SquareGrid, Histogram
            - Extensible grid view interface for different grid shapes like hex and triangle
        - Extensible simulation control panel
            - Currently implemented controls: start/stop, pause/unpause, speed up/down, switch
              views, enable/disable toroidal edge policy, save simulation
            - Extensible Controller class working in tandem
            - Shows all info in the simulation's config
        - Extensible, customizable languages for all text in UI
            - Examples for english, danish, and malay
        - Styleclasses for all ui components, allowing large amounts of customization
        - Uses state colors defined in configuration file if provided, otherwise randomly generates
          them
        - Expects and handles exceptions when calling any function from the main class or model

* Features unimplemented:
    - View:
        - Edit save information
        - Implementation for hex grid view
        - Implementation for dynamic button toggle for grid edges
        - Arbitrary grid view cell visuals
        - Implementation for dynamic change for grid view cell visual
        - Implementation for reset simulation grid to initial state button
        - Support different languages for each individual simulation
        - Legend for which state is which color

* Noteworthy Features:

### Assignment Impressions

Jason: Very interesting and engaging assignment. I learned a lot about designing class hierarchies
and understand a lot more about the open/closed principle and how decisions about what is
open/closed significantly affects the resulting design. Being responsible for the entire view and
controller portion of the project, I ran into a lot of frustrating issues with JavaFX, but otherwise
the assignment went relatively well and am proud of my design.