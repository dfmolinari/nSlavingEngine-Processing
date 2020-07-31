import nse.input.*;
import nse.levels.*;
import nse.ui.*;
import nse.collisions.*;
import nse.objects.*;

PApplet g_appRef = this;
InputManager g_inputManager;

ArrayList<NSEButton> buttons = new ArrayList<NSEButton>();

//Player p = new Player(10);

BoxCollider2D testTrigger = new BoxCollider2D(new PVector(1400,500), new PVector(100,100), true, "trigTest", this);

void setup()
{
  fullScreen();
  g_inputManager = new InputManager(g_appRef,this);
  
  LevelHandler.createLevel(new LevelBlueprint(0,"mainMenu",g_appRef,this)); //creating "mainMenu" level
  LevelHandler.createLevel(new LevelBlueprint(1,"game",g_appRef,this));
  
  NSEObject f = new NSEObject("hehe");
  Player p = new Player(); p.m_speed = 10;
  f.addComponent(p);
  f.addComponent(new BoxCollider2D(0,0,0,0,false,this));
  LevelHandler.addObject("game",f);
  
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
