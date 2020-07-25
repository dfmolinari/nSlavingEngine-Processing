package nse.collisions;

import processing.core.PVector;

public class Circle2D implements CollisionSide {

    private PVector m_position;
    private PVector m_size;

    public Circle2D(PVector position, PVector size)
    {
        m_position = new PVector(position.x, position.y);
        m_size = new PVector(size.x,size.y);
    }

    public Circle2D(float x, float y, float w, float h)
    {
        m_position = new PVector(x,y);
        m_size = new PVector(w,h);
    }

    public PVector getPosition() { return m_position; }
    public void setPosition(float x, float y) { m_position.set(x,y); }
    public void setPosition(PVector position) { m_position.set(position.x,position.y); }

    public PVector getSize() { return m_size; }
    public void setSize(float x, float y) { m_size.set(x,y); }
    public void setSize(PVector position) { m_size.set(position.x,position.y); }

    public int checkCollision(Box2D other)
    {
        float xDist = m_position.x - other.getPosition().x;
        float yDist = m_position.y - other.getPosition().y;

        float hWidths = m_size.x/2 + other.getSize().x/2;
        float hHeights = m_size.y/2 + other.getSize().y/2;

        if(Math.abs(xDist) < hWidths && Math.abs(yDist) < hHeights)
        {
            float xOverlap = 0;
            float yOverlap = 1;


        }

        return NONE;
    }

}
