package nse.rendering;

import nse.objects.NSEObject;
import nse.objects.NSETransform;
import nse.resizing.Rescaler;
import processing.core.PApplet;
import processing.core.PImage;

public class Renderer {

    private PApplet m_myApplet;

    public static int RECT = 0;

    private int m_meshType;

    public NSEObject gameObject;
    public NSETransform transform;

    private PImage m_texture;
    private int m_color;

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
            if(gameObject.hasComponent(Animator.class))
            {
                gameObject.getComponent(Animator.class).animate();
            } else
            {
                if(m_texture != null)
                {
                    m_myApplet.tint(m_color);
                    m_myApplet.image(m_texture,rx,ry,rw,rh);
                } else
                {
                    m_myApplet.fill(m_color);
                    m_myApplet.rect(rx,ry,rw,rh);
                }
            }
            m_myApplet.rectMode(m_myApplet.CORNER);
            m_myApplet.imageMode(m_myApplet.CORNER);
        }
    }

}
