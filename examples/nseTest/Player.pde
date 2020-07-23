//Player class using the custom InputManager
public class Player
{
  private PVector m_position;
  private PVector m_size;
  
  private float m_speed;
  
  private InputManager m_inputManager; //creating a custom InputManager
  
  private boolean m_left,m_right,m_up,m_down;
  
  Player(float speed)
  {
    m_position = new PVector();
    m_size = new PVector(100,100);
    m_speed = speed;
    m_inputManager = new InputManager(g_appRef,this); //initializing the InputManager with the main applet reference and using this as parent
  }
  
  void update()
  {
    float curXSpeed = 0;
    float curYSpeed = 0;
    if(m_right && !m_left) curXSpeed = m_speed;
    if(!m_right && m_left) curXSpeed = -m_speed;
    if((!m_right && !m_left) || (m_right && m_left)) curXSpeed = 0;
    if(m_up && !m_down) curYSpeed = -m_speed;
    if(!m_up && m_down) curYSpeed = m_speed;
    if((!m_up && !m_down) || (m_up && m_down)) curYSpeed = 0;
    
    m_position.add(curXSpeed,curYSpeed);
    
    rectMode(CENTER);
    rect(m_position.x,m_position.y,m_size.x,m_size.y);
    rectMode(CORNER);
  }
  
  //defining the InputManager keyPressed locally in the class
  void nseKeyPressed()
  {
    if(key == 'w') m_up = true;
    if(key == 'a') m_left = true;
    if(key == 's') m_down = true;
    if(key == 'd') m_right = true;
  }
  
  //defining the InputManager keyReleased locally in the class
  void nseKeyReleased()
  {
    if(key == 'w') m_up = false;
    if(key == 'a') m_left = false;
    if(key == 's') m_down = false;
    if(key == 'd') m_right = false;
  }
  
  void setPoisition(float x, float y)
  {
    m_position.set(x,y);
  }
}
