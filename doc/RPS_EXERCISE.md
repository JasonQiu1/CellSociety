# Rock Paper Scissors Lab Discussion
#### Jason Qiu (jq48), Spencer Katz (sek49), Prince Ahmend(pa99)


### High Level Design Goals
Small classes that are not coupled or have repeated code.


### CRC Card Classes

This class's purpose or value is to represent a customer's order:

|Order| |
|---|---|
|boolean isInStock(OrderLine)         |OrderLine|
|double getTotalPrice(OrderLine)      |Customer|
|boolean isValidPayment (Customer)    | |
|void deliverTo (OrderLine, Customer) | |


This class's purpose or value is to represent a player and their score:
```java
public class Player {
     // gets the player's current score
     public int getScore()
     // sets the player's score
     public void setScore(int score)
     // prompts the player for a weapon
     public String chooseWeapon()
     // inits player to 0 score
     public Player()
 }
 ```

This class's purpose or value is to hold weapon relationships
```java
public class Relationship {
     // return the result of the person who chose the first weapon
     public Result checkResult(String weapon1, String weapon2)
     // load a file of relationships
     public Relationship(String ruleFileName)
     // add a relationship
     public void addRelationship(String weapon1, String weapon2)
 }
```


### Use Cases

 * A new game is started with five players, their scores are reset to 0.
 ```java
 Player 
 ```

 * A player chooses his RPS "weapon" with which he wants to play for this round.
 ```java
 Something thing = new Something();
 Order o = thing.makeOrder("coffee,large,black");
 o.update(13);
 ```

 * Given three players' choices, one player wins the round, and their scores are updated.
 ```java
 Something thing = new Something();
 Order o = thing.makeOrder("coffee,large,black");
 o.update(13);
 ```

 * A new choice is added to an existing game and its relationship to all the other choices is updated.
 ```java
 Something thing = new Something();
 Order o = thing.makeOrder("coffee,large,black");
 o.update(13);
 ```

 * A new game is added to the system, with its own relationships for its all its "weapons".
 ```java
 Something thing = new Something();
 Order o = thing.makeOrder("coffee,large,black");
 o.update(13);
 ```

