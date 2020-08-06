import nse.input.*;
import nse.levels.*;
import nse.ui.*;
import nse.collisions.*;
import nse.objects.*;
import nse.resizing.*;
import nse.rendering.*;

PApplet g_appRef = this;

//BoxCollider2D testTrigger = new BoxCollider2D(new PVector(1400,500), new PVector(100,100), true, "trigTest", this);

void setup()
{
  fullScreen(P3D);
  //surface.setLocation(displayWidth/2-1280/2,displayHeight/2-720/2);
  surface.setSize(1280,720);
  surface.setResizable(true);
  
  
  LevelHandler.createLevel(new LevelBlueprint(0,"mainMenu",g_appRef)); //creating "mainMenu" level
  LevelHandler.createLevel(new LevelBlueprint(1,"game",g_appRef));
  
  Input.createAxis("Vertical","s","w","gamepadYAx","");
  Input.createAxis("Horizontal","d","a","gamepadXAx","");
  Input.createButton("ResOne","f","Button 4");
  Input.createButton("Res2","Button 1","");
  Input.createButton("Res3","Button 2","");
  Input.createButton("Jump","space","Button 0");
  
  //UIManager
  NSEObject f = new NSEObject("manager");
  f.addComponent(UIManager.class,new UIManager());
  LevelHandler.addObject("mainMenu",f);
  
  //PLAY BUTTON
  f = new NSEObject("play");
  f.addComponent(NSEButton.class,new NSEButton("play",color(255),color(0),null,50,this));
  f.transform.position.set(Rescaler.getHalfWidth(),Rescaler.getHalfHeight());
  f.transform.scale.set(300,100);
  LevelHandler.addObject("mainMenu",f);
  
  //QUIT BUTTON
  f = new NSEObject("quit");
  f.addComponent(NSEButton.class,new NSEButton("quit",color(255),color(0),null,50,this));
  f.transform.position.set(Rescaler.getHalfWidth(),Rescaler.getHalfHeight()+150);
  f.transform.scale.set(300,100);
  LevelHandler.addObject("mainMenu",f);
  
  //HEADER
  f = new NSEObject("header");
  f.addComponent(NSEBox.class,new NSEBox("NSE Example",color(255),color(0),null,50,this));
  f.transform.position.set(Rescaler.getHalfWidth(),Rescaler.getHalfHeight()-300);
  f.transform.scale.set(400,200);
  LevelHandler.addObject("mainMenu",f);
  
  //PLAYER
  f = new NSEObject("player");
  Player p = new Player(); p.m_speed = 600; p.m_jumpSpeed = 550; p.m_fallSpeed = 15;
  f.addComponent(Player.class, p);
  f.transform.scale.set(100,100);
  f.addComponent(Renderer.class,new Renderer(Renderer.RECT));
  f.getComponent(Renderer.class).setColor(color(255,0,0));
  f.addComponent(BoxCollider2D.class, new BoxCollider2D(false,this));
  f.addComponent(Animator.class,new Animator());
  f.getComponent(Animator.class).addAnimation("Idle",new Animation(true,0.17,this));
  f.getComponent(Animator.class).getAnimation("Idle").addFrame(loadImage("Idle-0.png"));
  f.getComponent(Animator.class).getAnimation("Idle").addFrame(loadImage("Idle-1.png"));
  f.getComponent(Animator.class).getAnimation("Idle").addFrame(loadImage("Idle-2.png"));
  f.getComponent(Animator.class).playAnimation("Idle");
  LevelHandler.addObject("game",f);
  
  f = new NSEObject("testCollider");
  f.addComponent(BoxCollider2D.class,new BoxCollider2D(false,this));
  f.transform.position.set(Rescaler.getHalfWidth(),0);
  f.transform.scale.set(Rescaler.getWidth(),50);
  f.addComponent(Renderer.class,new Renderer(Renderer.RECT));
  f.getComponent(Renderer.class).setColor(color(0,255,0));
  LevelHandler.addObject("game",f);
  
  LevelHandler.loadLevel("mainMenu"); //setting default level
}

void draw()
{
  LevelHandler.displayLevel();
  if(LevelHandler.getCurrentLevelName().equalsIgnoreCase("game"))
  {
    NSEObject p = LevelHandler.findObject("game","player");
    camera(Rescaler.resizeOnWidth(p.transform.position.x), Rescaler.resizeOnHeight(p.transform.position.y), height / 2 / tan(PI * 30 / 180), Rescaler.resizeOnWidth(p.transform.position.x), Rescaler.resizeOnHeight(p.transform.position.y), 0, 0, 1, 0);
  }
}
