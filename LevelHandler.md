---
title: LevelHandler
description: Wiki
---

### What is it?

The LevelHandler will handle level selection and display.

### Method List

1. Loading a level by it's ID - `LevelHandler.loadLevel(int id)`
  * This method loads the level with the corresponding ID if it exists. If a matching level cannot be found it will result in an error.
  * Example usage:
```java
LevelHandler.loadLevel(2);
```

2. Loading a level by it's name - `LevelHandler.loadLevel(String name)`
  * This method loads the level with the corresponding name if it exists. If a matching level cannot be fount it will result in an error.
  * Example usage:
```java
LevelHandler.loadLevel("mainMenu");
```

3. Creating a new level - `LevelHandler.createLevel(LevelBlueprint b)`
  * This method will load the passed level to the level list.
  * Example usage:
```java
LevelHandler.createLevel(new LevelBlueprint(0, "mainMenu", this));
```
```java
LevelBlueprint b = new LevelBlueprint(0, "mainMenu", this));
LevelHandler.createLevel(b);
```
4. Displaying the level - `LevelHandler.displayLevel()`
  * Will display the currently selected level, usually called in processing's `draw` method so that it can be updated.
  * Example usage:
```java
LevelHandler.displayLevel();
```

5. Retrieving the level ID - `LevelHandler.getCurrentLevelID()`
  * Will return the ID corresponding to the currently selected level.
  * Example usage:
```java
//checking if on level 1
if(LevelHandler.getCurrentLevelID() == 1)
{
    //code...
}
```

6. Retrieving the level name - `LevelHandler.getCurrentLevelName()`
  * Will return the name corresponding to the currently selected level.
  * Example usage:
```java
//checking if on level "mainMenu"
if(LevelHandler.getCurrentLevelID().equalsIgnoreCase("mainMenu"))
{
    //code...
}
```

***WARNING: BE CAREFUL WHEN USING THE FOLLOWING 2 METHODS, USE THEM ONLY WHEN CHANGING SCENE TO MAKE SURE YOU DON'T CALCULATE UNNECESSARY COLLISIONS***
7. Adding a collider to calculate - `LevelHandler.addCollider(BoxCollider2D)`
  * Adds a collider to the list necessary to calculate collisions.
  * ***WARNING: THIS GETS DONE AUTOMATICALLY WHEN CREATING A COLLIDER, DO NOT USE UNLESS YOU HAVE REMOVED THAT COLLIDER***
```java
//imagine having a BoxCollider2D called "box"
LevelHandler.addCollider(box);
```

8. Removing a collider from the calculation - `LevelHandler.removeCollider(BoxCollider2D)`
  * Removes a collider from the list and will not get count for collision detection.
```java
//imagine having a BoxCollider2D called "box"
LevelHandler.removeCollider(box);
```

9. Calculates the collision between every existing collider - `LevelHandler.calculateQuadtree()`
  * Checks the list of every existing collider(not removed from you) and calculates collisions
  * Tip: place it after moving your objects and, before displaying them, make sure you reposition them based on the collider's location, visit **[Collision Detection](CollisionDetection.md)** to see how
```java
LevelHandler.calculateQuadtree();
```
