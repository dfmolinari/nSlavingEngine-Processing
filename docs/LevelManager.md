# **LevelManager**

### What is it?

The Level Manager will handle everything regarding levels, their loading and their looks.

To import the Level Manager into your project use `import nse.levels.*`.

More on the Level Manager:
  * **[LevelBlueprint](https://github.com/dfmolinari/nSlavingEngine-Processing/wiki/LevelBlueprint)**
  * **[LevelHandler](https://github.com/dfmolinari/nSlavingEngine-Processing/wiki/LevelHandler)**

---

### Full Level Management example

Here is an example on using the Level Manager

```
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

<div height:"100%";width:"160px";position:"fixed";z-index:"1";top:"0";left:"0";overflow-x:"hidden";padding-top:"20px";>
<a padding:"6px,8px,6px,16px";text-decoration:"none";font-size:"25px";color:"#818181";display:"block";>test</a>
</div>
