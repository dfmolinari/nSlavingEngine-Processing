import nse.input.*;
import nse.levels.*;
import nse.ui.*;
import nse.collisions.*;
import nse.objects.*;

PApplet g_appRef = this;
InputManager g_inputManager;

//BoxCollider2D testTrigger = new BoxCollider2D(new PVector(1400,500), new PVector(100,100), true, "trigTest", this);

void setup()
{
  fullScreen();
  g_inputManager = new InputManager(g_appRef,this);
  
  LevelHandler.createLevel(new LevelBlueprint(0,"mainMenu",g_appRef)); //creating "mainMenu" level
  LevelHandler.createLevel(new LevelBlueprint(1,"game",g_appRef));
  
  //UIManager
  NSEObject f = new NSEObject("manager");
  f.addComponent(UIManager.class,new UIManager());
  LevelHandler.addObject("mainMenu",f);
  
  //PLAY BUTTON
  f = new NSEObject("play");
  f.addComponent(NSEButton.class,new NSEButton(ButtonType.Default,"play",color(255),color(0),null,50,this));
  f.transform.position.set(width/2,height/2);
  f.transform.scale.set(300,100);
  LevelHandler.addObject("mainMenu",f);
  
  //QUIT BUTTON
  f = new NSEObject("quit");
  f.addComponent(NSEButton.class,new NSEButton(ButtonType.Default,"quit",color(255),color(0),null,50,this));
  f.transform.position.set(width/2,height/2+150);
  f.transform.scale.set(300,100);
  LevelHandler.addObject("mainMenu",f);
  
  //HEADER
  f = new NSEObject("header");
  f.addComponent(NSEBox.class,new NSEBox(BoxType.Default,"NSE Example",color(255),color(0),null,50,this));
  f.transform.position.set(width/2,height/2-300);
  f.transform.scale.set(400,200);
  LevelHandler.addObject("mainMenu",f);
  
  //PLAYER
  f = new NSEObject("player");
  Player p = new Player(); p.m_speed = 10;
  f.addComponent(Player.class, p);
  f.transform.scale.set(150,100);
  f.addComponent(BoxCollider2D.class, new BoxCollider2D(0,0,0,0,false,this));
  
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
