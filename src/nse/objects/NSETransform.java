package nse.objects;

import processing.core.PVector;

public class NSETransform {

    PVector m_position;

    NSETransform()
    {
        m_position = new PVector(0,0);
    }

    public void setPosition(float x, float y) { m_position.set(x,y); }
    public void getPosition(PVector pos) { m_position.set(pos.x,pos.y); }

    public PVector getPosition()
    {
        return m_position;
    }
}
