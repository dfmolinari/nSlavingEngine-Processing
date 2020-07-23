import nse.input.*;
import nse.levels.*;
import nse.ui.*;

PApplet g_appRef = this;
InputManager g_inputManager;

ArrayList<NSEButton> buttons = new ArrayList<NSEButton>();

Player p = new Player(10);

void setup()
{
  fullScreen();
  g_inputManager = new InputManager(g_appRef,this);
  
  LevelHandler.createLevel(new LevelBlueprint(0,"mainMenu",this)); //creating "mainMenu" level
  LevelHandler.createLevel(new LevelBlueprint(1,"game",this));
  
  LevelHandler.loadLevel("mainMenu"); //setting default level
}

void draw()
{
  LevelHandler.displayLevel();
}

void nseKeyPressed()
{
  if(key == ESC && !LevelHandler.getCurrentLevelName().equalsIgnoreCase("mainMenu")) key = 0;
}

void nseKeyReleased()
{
  if(key == ESC && LevelHandler.getCurrentLevelName().equalsIgnoreCase("game")) LevelHandler.loadLevel("mainMenu");
}
