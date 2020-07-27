package nse.collisions;

import nse.levels.LevelHandler;
import processing.core.PVector;

import java.lang.reflect.Method;

public class BoxCollider2D {

    private PVector m_position;
    private PVector m_size;
    private PVector m_velocity;
    private boolean m_pushable;

    private Object m_myParent;

    //triggers
    private boolean m_isTrigger;
    private boolean m_canTrigger;
    private String m_label;

    public BoxCollider2D(PVector position, PVector size, boolean isTrigger, Object myParent)
    {
        m_position = new PVector(position.x, position.y);
        m_size = new PVector(size.x,size.y);
        m_velocity = new PVector(0,0);
        m_isTrigger = isTrigger;
        m_pushable = false;
        m_canTrigger = true;
        m_label = "";
        m_myParent = myParent;
        LevelHandler.addCollider(this);
    }

    public BoxCollider2D(PVector position, PVector size, boolean isTrigger, String label, Object myParent)
    {
        m_position = new PVector(position.x, position.y);
        m_size = new PVector(size.x,size.y);
        m_velocity = new PVector(0,0);
        m_isTrigger = isTrigger;
        m_pushable = false;
        m_canTrigger = true;
        m_label = label;
        m_myParent = myParent;
        LevelHandler.addCollider(this);
    }

    public BoxCollider2D(float x, float y, float w, float h, boolean isTrigger, Object myParent)
    {
        m_position = new PVector(x,y);
        m_size = new PVector(w,h);
        m_velocity = new PVector(0,0);
        m_isTrigger = isTrigger;
        m_pushable = false;
        m_canTrigger = true;
        m_label = "";
        m_myParent = myParent;
        LevelHandler.addCollider(this);
    }

    public BoxCollider2D(float x, float y, float w, float h, boolean isTrigger, String label, Object myParent)
    {
        m_position = new PVector(x,y);
        m_size = new PVector(w,h);
        m_velocity = new PVector(0,0);
        m_isTrigger = isTrigger;
        m_pushable = false;
        m_canTrigger = true;
        m_label = label;
        m_myParent = myParent;
        LevelHandler.addCollider(this);
    }

    public PVector getPosition() { return m_position; }
    public void setPosition(float x, float y) { m_position.set(x,y); }
    public void setPosition(PVector position) { m_position.set(position.x,position.y); }

    public PVector getSize() { return m_size; }
    public void setSize(float x, float y) { m_size.set(x,y); }
    public void setSize(PVector position) { m_size.set(position.x,position.y); }

    public PVector getVelocity() { return m_velocity; }
    public void setVelocity(PVector velocity) { m_velocity.set(velocity.x,velocity.y); }
    public void setVelocity(float x, float y) { m_velocity.set(x,y); }

    public boolean isTrigger() { return m_isTrigger; }
    public void setTriggerLabel(String label) { m_label = label; }
    public String getTriggerLabel() { return m_label; }

    public boolean isPushable() { return m_pushable; }
    public void setPushable(boolean state) { m_pushable = state; }

    public void checkCollision(BoxCollider2D other, boolean ignoreJumps)
    {
        float dx = m_position.x - other.getPosition().x;
        float dy = m_position.y - other.getPosition().y;

        float hWidths = m_size.x/2 + other.getSize().x/2;
        float hHeights = m_size.y/2 + other.getSize().y/2;

        if(Math.abs(dx) < hWidths && Math.abs(dy) < hHeights)
        {
            if(!m_isTrigger && !other.isTrigger())
            {
                float overlapX = hWidths - Math.abs(dx);
                float overlapY = hHeights - Math.abs(dy);

                if(overlapX >= overlapY)
                {
                    if(dy > 0)
                    {
                        if(!ignoreJumps && m_velocity.y < 0)
                        {
                            //other.getPosition().y = m_position.y-m_size.y/2-other.m_size.y/2;
                            if(other.isPushable())
                            {
                                other.getPosition().y = m_position.y-m_size.y/2-other.m_size.y/2;
                            }
                            m_position.y = other.getPosition().y+other.getSize().y/2+m_size.y/2;
                        }
                    }
                    else if(m_velocity.y > 0)
                    {
                        if(other.isPushable())
                        {
                            other.getPosition().y = m_position.y+m_size.y/2+other.getSize().y/2;
                        }
                        m_position.y = other.getPosition().y-other.getSize().y/2-m_size.y/2;
                    }
                } else
                {
                    if(dx > 0)
                    {
                        if(m_velocity.x < 0)
                        {
                            if(other.isPushable())
                            {
                                other.m_position.x = m_position.x-m_size.x/2-other.getSize().x/2;
                            }
                            m_position.x = other.getPosition().x+other.getSize().x/2+m_size.x/2;
                        }
                    }
                    else if(m_velocity.x > 0)
                    {
                        if(other.isPushable())
                        {
                            other.m_position.x = m_position.x+m_size.x/2+other.getSize().x/2;
                        }
                        m_position.x = other.getPosition().x-other.getSize().x/2-m_size.x/2;
                    }
                }
            } else {
                if(m_isTrigger)
                {
                    onTriggerStay(other);
                    onTriggerEnter(other);
                } else if(other.isTrigger())
                {
                    other.onTriggerStay(this);
                    other.onTriggerEnter(this);
                }
            }
        } else
        {
            if(m_isTrigger && !other.m_canTrigger)
                onTriggerExit(other);
            else if(other.isTrigger() && !m_canTrigger)
                other.onTriggerExit(this);
        }
    }

    /*public int checkCollision(Circle2D other)
    {
        float dx = m_position.x - other.getPosition().x;
        float dy = m_position.y - other.getPosition().y;

        float hWidths = m_size.x/2 + other.getRadius();
        float hHeights = m_size.y/2 + other.getRadius();

        if(Math.abs(dx) < hWidths && Math.abs(dy) < hHeights)
        {
            float overlapX = hWidths - Math.abs(dx);
            float overlapY = hHeights - Math.abs(dy);

            if(overlapX >= overlapY)
            {
                if(dy > 0) return UP;
                else return DOWN;
            } else
            {
                if(dx > 0) return LEFT;
                else return RIGHT;
            }
        }

        return NONE;
    }*/

    private void onTriggerEnter(BoxCollider2D other)
    {
        if(other.m_canTrigger)
        {
            other.m_canTrigger = false;
            try
            {
                for(Method m : m_myParent.getClass().getMethods())
                {
                    if(m.getName().equalsIgnoreCase(m_label+"OnTriggerEnter"))
                        m_myParent.getClass().getMethod(m_label+"OnTriggerEnter").invoke(m_myParent);
                }
            } catch (ReflectiveOperationException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private void onTriggerStay(BoxCollider2D other)
    {
        if(!other.m_canTrigger)
        {
            try
            {
                for(Method m : m_myParent.getClass().getMethods())
                {
                    if(m.getName().equalsIgnoreCase(m_label+"OnTriggerStay"))
                        m_myParent.getClass().getMethod(m_label+"OnTriggerStay").invoke(m_myParent);
                }
            } catch (ReflectiveOperationException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private void onTriggerExit(BoxCollider2D other)
    {
        other.m_canTrigger = true;
        try
        {
            for(Method m : m_myParent.getClass().getMethods())
            {
                if(m.getName().equalsIgnoreCase(m_label+"OnTriggerExit"))
                    m_myParent.getClass().getMethod(m_label+"OnTriggerExit").invoke(m_myParent);
            }
        } catch (ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
    }

}
