package nse.rendering;

import nse.objects.NSEObject;
import nse.objects.NSETransform;
import nse.resizing.Rescaler;
import processing.core.PApplet;
import processing.core.PImage;

public class Renderer {

    private PApplet m_myApplet;

    public static int RECT = 0;

    public static int LEFT = -1;
    public static int RIGHT = 1;

    private int m_meshType;

    public NSEObject gameObject;
    public NSETransform transform;

    private PImage m_texture;
    private int m_color;

    private int m_dir = 1;

    private float rx, ry, rw, rh;

    public Renderer(int type)
    {
        m_meshType = type;
    }

    public void setupApplet(PApplet app)
    {
        m_myApplet = app;
        m_color = m_myApplet.color(230,230,230);
    }

    public void setColor(int color) { m_color = color; }
    public void setTexture(PImage texture) { m_texture = texture; }

    public void display()
    {
        rx = Rescaler.resizeOnWidth(transform.position.x);
        ry = Rescaler.resizeOnHeight(transform.position.y);
        rw = Rescaler.resizeOnHeight(transform.scale.x);
        rh = Rescaler.resizeOnHeight(transform.scale.y);

        if(m_meshType == RECT)
        {
            m_myApplet.rectMode(m_myApplet.CENTER);
            m_myApplet.imageMode(m_myApplet.CENTER);
            m_myApplet.pushMatrix();
            m_myApplet.translate(rx,ry);
            m_myApplet.scale(m_dir,1);
            if(gameObject.hasComponent(Animator.class))
            {
                gameObject.getComponent(Animator.class).animate();
            } else
            {
                if(m_texture != null)
                {
                    m_myApplet.tint(m_color);
                    m_myApplet.image(m_texture,0,0,rw,rh);
                } else
                {
                    m_myApplet.fill(m_color);
                    m_myApplet.rect(0,0,rw,rh);
                }
            }
            m_myApplet.popMatrix();
            m_myApplet.rectMode(m_myApplet.CORNER);
            m_myApplet.imageMode(m_myApplet.CORNER);
        }
    }

    public void setDir(int dir) { m_dir = dir != 0 ? dir : m_dir; }
    public int getDir() { return m_dir; }
}
