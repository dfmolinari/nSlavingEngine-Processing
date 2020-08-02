---
title: LevelManager
description: Wiki
---

### What is it?

The Level Manager will handle everything regarding levels, their loading and their looks.

To import the Level Manager into your project use `import nse.levels.*`.

More on the Level Manager:
  * **[LevelBlueprint](LevelBlueprint.md)**
  * **[LevelHandler](LevelHandler.md)**

---

### Full Level Management example

Here is an example on using the Level Manager

```java
import nse.levels.*;

void setup()
{
    //creating the "mainMenu" level
    LevelHandler.createLevel(0,"mainMenu",this);

    //setting the startup level
    LevelHandler.loadLevel(0);
}

void draw()
{
    LevelHandler.displayLevel(); //drawing the currently selected level
}

//setting up the "mainMenu" start method
void mainMenuStart()
{
    println("main menu initialized");
}

//setting up the "mainMenu" update method
void mainMenuUpdate()
{
    background(0);
    rect(width/2,height/2,100,100);
}
```
