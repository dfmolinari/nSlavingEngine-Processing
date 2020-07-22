#nSlavingEngine
#### Welcome to the nSlavingEngine library for processing. Following you will find a guide on how to use it the best way.

## The Level Manager

The Level Manager will handle everything regarding levels, their loading and their looks.

To import the level manager into your project use `import nse.levels.*`.

### LevelBlueprint

A LevelBlueprint contains all the necessary information to create and edit a level.

#### Method list

1. Constructor - `LevelBlueprint(int id, String name, Object myParent)`
  * Will create a level with the specified ID and name if it doesn't already exist, if it does it will result in an error.
  * The "myParent" parameter will represent the parent class, usually filled with the `this` keyword.
  * Example usage:
```
LevelBlueprint b = new LevelBlueprint(0, "mainMenu", this);
```
#### Customizing a LevelBlueprint's content
A level blueprint's content can be customized by creating methods with the following format `levelName+Start` & `levleName+Update`.

A start method will contain everything that will be executed when the level gets loaded, while the update will be executed every other time.

***WARNING: METHODS NAME ARE KEY SENSITIVE, MISTYPING A LEVEL NAME WILL RESULT IN THE ENGINE NOT FINDING THE METHOD AND RESULT IN AN ERROR***
```
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
### LevelHandler

The LevelHandler will handle level selection and display.

#### Method List

1. Loading a level by it's ID - `LevelHandler.loadLevel(int id)`
  * This method loads the level with the corresponding ID if it exists. If a matching level cannot be found it will result in an error.
  * Example usage:
```
LevelHandler.loadLevel(2);
```
2. Loading a level by it's name - `LevelHandler.loadLevel(String name)`
  * This method loads the level with the corresponding name if it exists. If a matching level cannot be fount it will result in an error.
  * Example usage:
```
LevelHandler.loadLevel("mainMenu");
```
3. Creating a new level - `LevelHandler.createLevel(LevelBlueprint b)`
  * This method will load the passed level to the level list.
  * Example usage:
```
LevelHandler.createLevel(new LevelBlueprint(0, "mainMenu", this));
```
```
LevelBlueprint b = new LevelBlueprint(0, "mainMenu", this));
LevelHandler.createLevel(b);
```
4. Displaying the level - `LevelHandler.displayLevel()`
  * Will display the currently selected level, usually called in processing's `draw` method so that it can be updated.
  * Example usage:
```
LevelHandler.displayLevel();
```
5. Retrieving the level ID - `LevelHandler.getCurrentLevelID()`
  * Will return the ID corresponding to the currently selected level.
  * Example usage:
```
//checking if on level 1
if(LevelHandler.getCurrentLevelID() == 1)
{
    //code...
}
```
6. Retrieving the level name - `LevelHandler.getCurrentLevelName()`
  * Will return the name corresponding to the currently selected level.
  * Example usage:
```
//checking if on level "mainMenu"
if(LevelHandler.getCurrentLevelID().equalsIgnoreCase("mainMenu"))
{
    //code...
}
```
---
### Full Level Management example
```
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

## The InputManager
The input manager will handle user Input, it is mainly used to setup custom input locally.
#### Method List
1. Constructor - `InputManager(PApplet myApplet, Object myParent)`
  * Will create a custom InputManager that can be used locally.
  * The "myApplet" parameter will represent the main app, filling it with a PApplet instance is recommended.
  * The "myParent" parameter will represent the parent class, usually filled with the `this` keyword.
  * Example usage:
```
InputManager im = new InputManager(appRef, this);
```
#### Customizing Events
To customize events as you would for example with processing's `void keyReleased()` the input manager will look for the following methods in it's parent class.

*Keep in mind that these methods are not necessary but will be used to customize different events*

1. Customizing the keyPressed event - `void nseKeyPressed()`
2. Customizing the keyReleased event - `void nseKeyReleased()`
3. Customizing the mousePressed event - `void nseMousePressed()`
4. Customizing the mouseReleased event - `void nseMouseReleased()`
5. Customizing the mouseClicked event - `void nseMouseClicked()`

---
Ant is a Java-based build tool. For [more information](http://ant.apache.org/faq.html#what-is-ant) visit the [Ant web site](http://ant.apache.org/). Ant uses a file named `build.xml` to store build settings for a project.

Javadoc is an application that creates an HTML-based API documentation of Java code. You can check for its existence by typing `javadoc` on the command line. On Mac OS X, it is installed by default. On Windows and Linux, installing the JDK will also install the Javadoc tool. 
