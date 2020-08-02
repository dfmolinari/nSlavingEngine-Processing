---
title: LevelBlueprint
description: Wiki
---

### What is it?

A LevelBlueprint contains all the necessary information to create and edit a level.

### Method list

1. Constructor - `LevelBlueprint(int id, String name, PApplet myApplet, Object myParent)`
  * Will create a level with the specified ID and name if it doesn't already exist, if it does it will result in an error.
  * The "myParent" parameter will represent the parent class, usually filled with the `this` keyword.
  * Example usage:
```java
LevelBlueprint b = new LevelBlueprint(0, "mainMenu", appRef, this);
```

### Customizing a LevelBlueprint's content
A level blueprint's content can be customized by creating methods with the following format `levelName+Start` & `levleName+Update`.

A start method will contain everything that will be executed when the level gets loaded, while the update will be executed every other time.

***WARNING: METHODS NAME ARE KEY SENSITIVE, MISTYPING A LEVEL NAME WILL RESULT IN THE ENGINE NOT FINDING THE METHOD AND RESULT IN AN ERROR***
```java
//setting start method for the "game" level
void gameStart()
{
    p.setPosition(100,100);
    println("game initialized");
}

//setting the update metod for the "game" level"
void gameUpdate()
{
    background(0);
    p.update();
}
```
