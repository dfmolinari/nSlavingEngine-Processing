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
  p.setPoisition(width/2,height/2);
}

void gameUpdate()
{
  background(200);
  p.update();
  f.update();
  if(p.m_collider.checkCollision(f.m_collider) != 0)
    fill(255,0,0);
  p.display();
  fill(255);
  f.display();
}
//--------------------
