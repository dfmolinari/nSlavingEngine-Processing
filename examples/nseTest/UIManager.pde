class UIManager extends NSEScript
{

	//Gets executed when the level is loaded
	void Start()
	{
  
	}

	//Gets executed once every frame
	void Update()
	{
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

}
