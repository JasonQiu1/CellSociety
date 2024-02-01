# Breakout Abstractions Lab Discussion
#### Jason Qiu, Spencer Katz, Prince Ahmed


### Block

This superclass's purpose as an abstraction: Superclass of different types of blocks that could implement different features but are still blocks.
```java
 public class Block {
    public void hitBall(Ball ball)
    // what to do when hit a block
 }
```

#### Subclasses

Each subclass's high-level behavorial differences from the superclass
```java
 public class Paddle {
    public void userMove(KeyCode userInput)
    // move depending on user input
 }

 public class MovingBlock{
    public void move(double elapsedTime)
    // move automatically each timestep
 }
```


#### Affect on cellsociety.Game/Level class

Which methods are simplified by using this abstraction and why

Not all blocks need to be moving. All of blocks, paddle, and moving blocks share the same collision code.


### Item

This superclass's purpose as an abstraction:
```java
 public abstract class Item {
     public abstract void hitPaddle()
     // do something when it hits the paddle
     public void move()
     // moves down each step
     public void removeSelf()
     // removes itself
 }
```

#### Subclasses

Each subclass's high-level behavorial differences from the superclass
```java
 public class Powerup extends Item {
     @Override
     public void hitPaddle()
     // uses the powerup and removes itself
 }
 public class Bomb extends Item {
     @Override
     public void hitPaddle()
     // reduces one life and removes itself
 }
```

#### Affect on cellsociety.Game/Level class

Item no longer needs to take a boolean to determine whether or not it is a bomb, simplifying any conditionals in the other sections of code.


### Others?

