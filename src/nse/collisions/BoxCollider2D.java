package nse.collisions;

import nse.levels.LevelHandler;
import nse.objects.NSEObject;
import nse.objects.NSETransform;
import processing.core.PVector;

import java.lang.reflect.Method;

public class BoxCollider2D {

    public NSEObject gameObject;
    public NSETransform transform;

    private Object m_myParent;

    private boolean m_isGrounded;

    //triggers
    private boolean m_isTrigger;
    private boolean m_canTrigger;
    private boolean m_pushable;
    private String m_label;

    private float prevY = 0;

    public BoxCollider2D(boolean isTrigger, Object myParent)
    {
        m_isTrigger = isTrigger;
        m_pushable = false;
        m_canTrigger = true;
        m_label = "";
        m_isGrounded = false;
        m_myParent = myParent;
    }

    public BoxCollider2D(boolean isTrigger, String label, Object myParent)
    {
        m_isTrigger = isTrigger;
        m_pushable = false;
        m_canTrigger = true;
        m_label = label;
        m_isGrounded = false;
        m_myParent = myParent;
    }

    public boolean isTrigger() { return m_isTrigger; }
    public void setTriggerLabel(String label) { m_label = label; }
    public String getTriggerLabel() { return m_label; }

    public boolean isPushable() { return m_pushable; }
    public void setPushable(boolean state) { m_pushable = state; }

    public void checkCollision(BoxCollider2D other, boolean ignoreJumps)
    {
        //System.out.println(prevY + " " + transform.position.y);
        //if(prevY == transform.position.y) m_isGrounded = true;

        float dx = transform.position.x - other.transform.position.x;
        float dy = transform.position.y - other.transform.position.y;

        float hWidths = transform.scale.x/2 + other.transform.scale.x/2;
        float hHeights = transform.scale.y/2 + other.transform.scale.y/2;

        if(Math.abs(dx) < hWidths && Math.abs(dy) < hHeights)
        {
            if(!m_isTrigger && !other.isTrigger())
            {
                float overlapX = hWidths - Math.abs(dx);
                float overlapY = hHeights - Math.abs(dy);

                if(overlapX > overlapY)
                {
                    if(dy > 0)
                    {
                        if(!ignoreJumps)
                        {
                            if(other.isPushable())
                            {
                                other.transform.position.y = transform.position.y-transform.scale.y/2-other.transform.scale.y/2;
                            }
                            transform.position.y = other.transform.position.y+other.transform.scale.y/2+transform.scale.y/2;
                        }
                    }
                    else
                    {
                        if(other.isPushable())
                        {
                            other.transform.position.y = transform.position.y+transform.scale.y/2+other.transform.scale.y/2;
                        }
                        transform.position.y = other.transform.position.y-other.transform.scale.y/2-transform.scale.y/2;
                        m_isGrounded = true;
                    }
                } else
                {
                    if(dx > 0)
                    {
                        if(other.isPushable()) {
                            other.transform.position.x = transform.position.x - transform.scale.x / 2 - other.transform.scale.x / 2;
                        }
                        transform.position.x = other.transform.position.x+other.transform.scale.x/2+transform.scale.x/2;
                    }
                    else
                    {
                        if(other.isPushable())
                        {
                            other.transform.position.x = transform.position.x+transform.scale.x/2+other.transform.scale.x/2;
                        }
                        transform.position.x = other.transform.position.x-other.transform.scale.x/2-transform.scale.x/2;
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
        prevY = transform.position.y;
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

    public boolean isGrounded() { return m_isGrounded; }
    public void setGrounded(boolean state) { m_isGrounded = state; }

}
