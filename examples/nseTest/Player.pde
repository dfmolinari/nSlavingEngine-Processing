//Player class using the custom InputManager
public class Player extends NSEScript
{
  private PVector m_size;

  private float m_speed;

  private InputManager m_inputManager; //creating a custom InputManager

  private boolean m_left, m_right, m_up, m_down;
  
  void Start()
  {
    println("test");
    setPosition(width/2,height/2);
    m_size = new PVector(150,100);
    m_inputManager = new InputManager(g_appRef, this);
  }

  void Update()
  {
    float curXSpeed = 0;
    float curYSpeed = 0;
    if (m_right && !m_left) curXSpeed = m_speed;
    if (!m_right && m_left) curXSpeed = -m_speed;
    if ((!m_right && !m_left) || (m_right && m_left)) curXSpeed = 0;
    if (m_up && !m_down) curYSpeed = -m_speed;
    if (!m_up && m_down) curYSpeed = m_speed;
    if ((!m_up && !m_down) || (m_up && m_down)) curYSpeed = 0;

    transform.getComponent(NSETransform.class).getPosition().add(curXSpeed, curYSpeed);
    display();
  }

  void display()
  {
    pushMatrix();
    translate(transform.getComponent(NSETransform.class).getPosition().x, transform.getComponent(NSETransform.class).getPosition().y);
    rectMode(CENTER);
    rect(0, 0, m_size.x, m_size.y);
    rectMode(CORNER);
    popMatrix();
  }

  //defining the InputManager keyPressed locally in the class
  void nseKeyPressed()
  {
    if (m_speed == 10)
    {
      if (key == 'w') m_up = true;
      if (key == 'a') m_left = true;
      if (key == 's') m_down = true;
      if (key == 'd') m_right = true;
    } else if (m_speed == 11)
    {
      if (key == 'i') m_up = true;
      if (key == 'j') m_left = true;
      if (key == 'k') m_down = true;
      if (key == 'l') m_right = true;
    }
  }

  //defining the InputManager keyReleased locally in the class
  void nseKeyReleased()
  {
    if (m_speed == 10)
    {
      if (key == 'w') m_up = false;
      if (key == 'a') m_left = false;
      if (key == 's') m_down = false;
      if (key == 'd') m_right = false;
    } else if (m_speed == 11)
    {
      if (key == 'i') m_up = false;
      if (key == 'j') m_left = false;
      if (key == 'k') m_down = false;
      if (key == 'l') m_right = false;
    }
  }

  void setPosition(float x, float y)
  {
    transform.getComponent(NSETransform.class).setPosition(x, y);
  }
}
