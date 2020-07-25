//Player class using the custom InputManager
public class Player
{
  private PVector m_position;
  private PVector m_size;
  private float m_rotation;

  private Box2D m_collider;

  private float m_speed;

  private InputManager m_inputManager; //creating a custom InputManager

  private boolean m_left, m_right, m_up, m_down;

  Player(float speed)
  {
    m_position = new PVector(0, 0);
    m_size = new PVector(150, 100);
    m_speed = speed;
    m_inputManager = new InputManager(g_appRef, this); //initializing the InputManager with the main applet reference and using this as parent
    m_collider = new Box2D(m_position, m_size, m_rotation);
  }

  void update()
  {
    float curXSpeed = 0;
    float curYSpeed = 0;
    if (m_right && !m_left) curXSpeed = m_speed;
    if (!m_right && m_left) curXSpeed = -m_speed;
    if ((!m_right && !m_left) || (m_right && m_left)) curXSpeed = 0;
    if (m_up && !m_down) curYSpeed = -m_speed;
    if (!m_up && m_down) curYSpeed = m_speed;
    if ((!m_up && !m_down) || (m_up && m_down)) curYSpeed = 0;

    m_position.add(curXSpeed, curYSpeed);
    m_collider.setPosition(m_position);
    m_collider.setRotation(m_rotation);
  }

  void display()
  {
    pushMatrix();
    translate(m_position.x, m_position.y);
    rotate(m_rotation);
    rectMode(CENTER);
    rect(0, 0, m_size.x, m_size.y);
    rectMode(CORNER);
    popMatrix();
    strokeWeight(5);
    stroke(0, 255, 0);
    //center to right
    float endX = m_collider.getPosition().x+(m_collider.getSize().x/2)*(float)Math.cos(m_collider.getRotation());
    float endY = m_collider.getPosition().y+((m_collider.getSize().x/2)*(float)Math.sin(m_collider.getRotation()));
    line(m_collider.getPosition().x, m_collider.getPosition().y, endX, endY);
    //right to rotated pos
    float rotX = m_collider.getPosition().x;
    line(endX, endY, rotX, m_collider.getPosition().y);
    stroke(255, 0, 0);
    float sin = (float)Math.sin(m_collider.getRotation());
    float cos = (float)Math.cos(m_collider.getRotation());
    float botLeftX = m_collider.getPosition().x+(-m_collider.getSize().x/2*cos)-(-m_collider.getSize().y/2*sin);
    float botLeftY = m_collider.getPosition().y+(-m_collider.getSize().x/2*sin)+(-m_collider.getSize().y/2*cos);
    point(botLeftX, botLeftY);
    strokeWeight(1);
    stroke(0);
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
      if (key == 'e') m_rotation += HALF_PI/6;
      if (key == 'q') m_rotation -= HALF_PI/6;
    } else if (m_speed == 11)
    {
      if (key == 'i') m_up = true;
      if (key == 'j') m_left = true;
      if (key == 'k') m_down = true;
      if (key == 'l') m_right = true;
      if (key == CODED && keyCode == RIGHT) m_rotation += HALF_PI/6;
      if (key == CODED && keyCode == LEFT) m_rotation -= HALF_PI/6;
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

  void setPoisition(float x, float y)
  {
    m_position.set(x, y);
  }
}
