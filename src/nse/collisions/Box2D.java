package nse.collisions;

import processing.core.PVector;

public class Box2D implements CollisionSide{

    private PVector m_position;
    private PVector m_size;
    private float m_rotation;
    private points p;
    private float sin;
    private float cos;

    public Box2D(PVector position, PVector size, float rotation)
    {
        m_position = new PVector(position.x, position.y);
        m_size = new PVector(size.x,size.y);
        m_rotation = rotation;
        sin = (float)Math.sin(m_rotation);
        cos = (float)Math.cos(m_rotation);
        p = new points();
    }

    public PVector getPosition() { return m_position; }
    public void setPosition(float x, float y) { m_position.set(x,y); }
    public void setPosition(PVector position) { m_position.set(position.x,position.y); }

    public PVector getSize() { return m_size; }
    public void setSize(float x, float y) { m_size.set(x,y); }
    public void setSize(PVector position) { m_size.set(position.x,position.y); }

    public float getRotation() { return m_rotation; }
    public void setRotation(float rotation)
    {
        m_rotation = rotation;
        sin = (float)Math.sin(m_rotation);
        cos = (float)Math.cos(m_rotation);
        p.setPoints();
    }

    public int checkCollision(Box2D other)
    {

        float oMinX = other.p.vertices[0].x;
        float maxX = p.vertices[0].x;
        float oMaxX = other.p.vertices[0].x;

        float minY = p.vertices[0].y;
        float oMinY = other.p.vertices[0].y;
        float maxY = p.vertices[0].y;
        float oMaxY = other.p.vertices[0].y;



        //if(minY < oMaxY && maxY > oMinY && minX < oMaxX && maxX > oMinX) { return LEFT; }


        return NONE;
    }

    public PVector getMinX()
    {
        int x = 0;
        for(int i = 0; i < 4; i++)
        {
            if(p.vertices[i].x < p.vertices[x].x) x = i;
        }
        return new PVector(p.vertices[x].x, p.vertices[x].y);
    }
    public PVector getMaxX()
    {
        int x = 0;
        for(int i = 0; i < 4; i++)
        {
            if(p.vertices[i].x > p.vertices[x].x) x = i;
        }
        return new PVector(p.vertices[x].x, p.vertices[x].y);
    }

    public PVector getMinY()
    {
        int x = 0;
        for(int i = 0; i < 4; i++)
        {
            if(p.vertices[i].y < p.vertices[x].y) x = i;
        }
        return new PVector(p.vertices[x].x, p.vertices[x].y);
    }

    public PVector getMaxY()
    {
        int x = 0;
        for(int i = 0; i < 4; i++)
        {
            if(p.vertices[i].y > p.vertices[x].y) x = i;
        }
        return new PVector(p.vertices[x].x, p.vertices[x].y);
    }



    private class v2
    {
        public float x, y;
        v2(float x, float y) { this.x = x; this.y = y; }
        public void setPos(float x, float y) { this.x = x; this.y = y; }
    }

    private class points
    {
        public v2[] vertices = new v2[4];
        points() {
            vertices[0] = new v2(getPosition().x+(-getSize().x/2*cos)-(-getSize().y/2*sin),getPosition().y+(-getSize().x/2*sin)+(-getSize().y/2*cos));
            vertices[1] = new v2(getPosition().x+(getSize().x/2*cos)-(-getSize().y/2*sin),getPosition().y+(getSize().x/2*sin)+(-getSize().y/2*cos));
            vertices[2] = new v2(getPosition().x+(getSize().x/2*cos)-(getSize().y/2*sin),getPosition().y+(getSize().x/2*sin)+(getSize().y/2*cos));
            vertices[3] = new v2(getPosition().x+(-getSize().x/2*cos)-(getSize().y/2*sin),getPosition().y+(-getSize().x/2*sin)+(getSize().y/2*cos));
        }

        public void setPoints()
        {
            vertices[0].setPos(getPosition().x+(-getSize().x/2*cos)-(-getSize().y/2*sin),getPosition().y+(-getSize().x/2*sin)+(-getSize().y/2*cos));
            vertices[1].setPos(getPosition().x+(getSize().x/2*cos)-(-getSize().y/2*sin),getPosition().y+(getSize().x/2*sin)+(-getSize().y/2*cos));
            vertices[2].setPos(getPosition().x+(getSize().x/2*cos)-(getSize().y/2*sin),getPosition().y+(getSize().x/2*sin)+(getSize().y/2*cos));
            vertices[3].setPos(getPosition().x+(-getSize().x/2*cos)-(getSize().y/2*sin),getPosition().y+(-getSize().x/2*sin)+(getSize().y/2*cos));
        }
    }
}
