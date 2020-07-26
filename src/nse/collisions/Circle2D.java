package nse.collisions;

import processing.core.PVector;

public class Circle2D implements CollisionSide {

    private PVector m_position;
    private float m_radius;

    //triggers
    private boolean m_isTrigger;
    private boolean m_canTrigger;

    public Circle2D(PVector position, float radius, boolean isTrigger)
    {
        m_position = new PVector(position.x, position.y);
        m_radius = radius;
        m_isTrigger = isTrigger;
        m_canTrigger = true;
    }

    public Circle2D(float x, float y, float radius, boolean isTrigger)
    {
        m_position = new PVector(x,y);
        m_radius = radius;
        m_isTrigger = isTrigger;
        m_canTrigger = true;
    }

    public PVector getPosition() { return m_position; }
    public void setPosition(float x, float y) { m_position.set(x,y); }
    public void setPosition(PVector position) { m_position.set(position.x,position.y); }

    public float getRadius() { return m_radius; }
    public void setRadius(float radius) { m_radius = radius; }

    public boolean isTrigger() { return m_isTrigger; }

    /*public void checkCollision(Box2D other)
    {
        float xDist = m_position.x - other.getPosition().x;
        float yDist = m_position.y - other.getPosition().y;

        float hWidths = m_radius/2 + other.getSize().x/2;
        float hHeights = m_radius/2 + other.getSize().y/2;

        if(Math.abs(xDist) < hWidths && Math.abs(yDist) < hHeights)
        {
            float xOverlap = 0;
            float yOverlap = 1;


        }
    }*/

    private void onTrigger()
    {
        if(m_canTrigger)
        {
            m_canTrigger = true;

        }
    }
}
