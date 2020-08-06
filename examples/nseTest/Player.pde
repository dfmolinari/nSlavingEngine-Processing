//Player class using the custom InputManager
public class Player extends NSEScript
{
  
  private float m_speed;
  private float m_jumpSpeed;
  private float m_fallSpeed;
  
  private float curYSpeed;
  
  private float prevX;
  private float prevY;

  void Start()
  {
    setPosition(100, -230);
    curYSpeed = m_fallSpeed;
  }

  void Update()
  {
    boolean wasGrounded = gameObject.getComponent(BoxCollider2D.class).isGrounded();
    
    if(wasGrounded)
      curYSpeed = 0;
      
    if(wasGrounded)
      gameObject.getComponent(BoxCollider2D.class).setGrounded(true);
    
    if(Input.getButtonDown("Jump") && gameObject.getComponent(BoxCollider2D.class).isGrounded())
    {
      gameObject.getComponent(BoxCollider2D.class).setGrounded(false);
      curYSpeed = -m_jumpSpeed * deltaTime;
    }
    
    gameObject.getComponent(BoxCollider2D.class).setGrounded(false);
    
    if(!gameObject.getComponent(BoxCollider2D.class).isGrounded())
      curYSpeed += m_fallSpeed * deltaTime;
    
    float curXSpeed = m_speed * Input.getAxis("Horizontal") * deltaTime;
    
    gameObject.getComponent(Renderer.class).setDir(curXSpeed > 0 ? Renderer.RIGHT : curXSpeed < 0 ? Renderer.LEFT : 0);
    
    transform.position.add(curXSpeed, curYSpeed);
    
    if(Input.getButtonDown("ResOne"))
    {
      surface.setLocation(displayWidth/2-1280/2,displayHeight/2-720/2);
      surface.setSize(1280,720);
    }
    if(Input.getButtonDown("Res2"))
    {
      surface.setLocation(0,0);
      surface.setSize(1920,1080);
    }
    if(Input.getButtonDown("Res3"))
    {
      surface.setLocation(-2560,0);
      surface.setSize(2560,1080);
    }
  }

  void setPosition(float x, float y)
  {
    transform.position.set(x,y);
  }
}
