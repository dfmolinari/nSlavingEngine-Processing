//MAIN MENU
//--------------------
void mainMenuStart()
{
  if(buttons.size() > 0) buttons.clear();
  
  buttons.add(new NSEButton(width/2,height/2,300,100, ButtonType.Default,"play",color(255),color(0),null,50,null,this));
  buttons.add(new NSEButton(width/2,height/2+150,300,100, ButtonType.Default,"quit",color(255),color(0),null,50,null,this));
}

void mainMenuUpdate()
{
  background(200);
  NSEBox h = new NSEBox(width/2,height/2-300,400,200,BoxType.Default,"NSE Example",color(255),color(0),null,50,null,this);
  h.display();
  for(NSEButton b : buttons)
  {
    b.display();
  }
}

void trigTestOnTriggerEnter()
{
  println("testOnTriggerEnter");
}

void trigTestOnTriggerStay()
{
  println("testOnTriggerStay");
}

void trigTestOnTriggerExit()
{
  println("testOnTriggerExit");
}

void playClick()
{
  LevelHandler.loadLevel("game");
}

void quitClick()
{
  exit();
}
//--------------------

//GAME
//--------------------
void gameStart()
{
  p.setPosition(width/2,height/2);
  f.setPosition(width/2-200,height/2-200);
  //p.m_collider.setPushable(true);
  f.m_collider.setPushable(true);
}

void gameUpdate()
{
  background(200);
  p.update();
  f.update();
  //p.m_collider.checkCollision(f.m_collider,false);
  //f.m_collider.checkCollision(p.m_collider,false);
  //p.m_collider.checkCollision(testTrigger,false);
  LevelHandler.executeQuadtree();
  p.setPosition(p.m_collider.getPosition().x,p.m_collider.getPosition().y);
  f.setPosition(f.m_collider.getPosition().x,f.m_collider.getPosition().y);
  
  
  p.display();
  fill(255);
  f.display();
  
  pushMatrix();
  fill(0,255,0,200);
  translate(testTrigger.getPosition().x,testTrigger.getPosition().y);
  rectMode(CENTER);
  rect(0, 0, testTrigger.getSize().x, testTrigger.getSize().y);
  rectMode(CORNER);
  popMatrix();
}
//--------------------
