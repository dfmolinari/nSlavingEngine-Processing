//Player class using the custom InputManager
public class Player extends NSEScript
{
  
  private float m_speed;

  private InputManager m_inputManager; //creating a custom InputManager

  private boolean m_left, m_right, m_up, m_down;

  void Start()
  {
    setPosition(width/2, height/2);
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

    transform.position.add(curXSpeed, curYSpeed);
    display();
  }

  void display()
  {
    pushMatrix();
    fill(255, 0, 0);
    translate(transform.position.x, transform.position.y);
    rectMode(CENTER);
    rect(0, 0, transform.scale.x, transform.scale.y);
    rectMode(CORNER);
    popMatrix();
  }

  //defining the InputManager keyPressed locally in the class
  void nseKeyPressed()
  {
    if (key == 'w') m_up = true;
    if (key == 'a') m_left = true;
    if (key == 's') m_down = true;
    if (key == 'd') m_right = true;
  }

  //defining the InputManager keyReleased locally in the class
  void nseKeyReleased()
  {
    if (key == 'w') m_up = false;
    if (key == 'a') m_left = false;
    if (key == 's') m_down = false;
    if (key == 'd') m_right = false;
  }

  void setPosition(float x, float y)
  {
    transform.position.set(x,y);
  }
}
