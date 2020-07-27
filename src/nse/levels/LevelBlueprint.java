package nse.levels;

import nse.Quadtree;
import nse.collisions.*;
import processing.core.PApplet;

import java.awt.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Level class used to define levels and their content.
 *
 * @author Davide Furfaro
 */
public class LevelBlueprint {

    private PApplet m_myApplet;
    private Object m_myParent;

    private String m_name;
    private int m_nId;

    protected boolean m_resetLevel;

    protected String m_start;
    protected String m_update;

    private Quadtree quad;


    /**
     * A constructor, usually called when adding a new level from the LevelHandler
     * with LevelHandler.createLevel() ||
     * WARNING: Creating a level with an ID or name that already exists will result in an error!
     *
     * @param id the level ID
     * @param name the level name
     * @param myParent the main applet used
     */
    public LevelBlueprint(int id, String name, PApplet myApplet, Object myParent){
        m_myApplet = myApplet;
        m_myParent = myParent;

        quad = new Quadtree(0,new Rectangle(0,0,m_myApplet.width,m_myApplet.height));

        m_nId = id;
        m_name = name;

        m_start = name+"Start";
        m_update = name+"Update";

        m_resetLevel = true;

        for(LevelBlueprint n : LevelHandler.s_levelList)
        {
            if(id == n.m_nId) throw new InvalidParameterException("Cannot create level: " + id + "; A level with the same ID is already existing");
            else if(name.equalsIgnoreCase(n.m_name)) throw new InvalidParameterException("Cannot create level: " + name + "; A level with the same Name is already existing");
        }
    }

    /**
     * Executes the start method once then executes the update method.
     */
    protected void content()
    {
        if(m_resetLevel)
        {
            try {
                m_myParent.getClass().getMethod(m_start).invoke(m_myParent);
            } catch(ReflectiveOperationException e)
            {
                throw new RuntimeException(e);
            }
            m_resetLevel = false;
        }
        try {
            m_myParent.getClass().getMethod(m_update).invoke(m_myParent);
        } catch(ReflectiveOperationException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the level ID.
     *
     * @return int
     */
    protected int getID() { return m_nId; }

    /**
     * Returns the level name.
     *
     * @return String
     */
    protected String getName() { return m_name; }

    protected void executeQuadtree()
    {
        quad.clear();
        for(int i = 0; i < LevelHandler.s_colliders.size(); i++)
        {
            if(LevelHandler.s_colliders.get(i) instanceof BoxCollider2D)
            {
                quad.insert((BoxCollider2D)LevelHandler.s_colliders.get(i));
            }
        }
        List returnObjects = new ArrayList();
        for(int i = 0; i < LevelHandler.s_colliders.size(); i++)
        {
            returnObjects.clear();
            if(LevelHandler.s_colliders.get(i) instanceof BoxCollider2D)
                quad.retrieve(returnObjects, (BoxCollider2D)LevelHandler.s_colliders.get(i));

            for(int j = 0; j < returnObjects.size(); j++)
            {
                if(returnObjects.get(j) instanceof BoxCollider2D && LevelHandler.s_colliders.get(i) instanceof BoxCollider2D)
                {
                    if(returnObjects.get(j) != LevelHandler.s_colliders.get(i))
                    {
                        //System.out.println("checking collision between" + ((BoxCollider2D) LevelHandler.s_colliders.get(i)).getTriggerLabel() + " and " + ((BoxCollider2D)returnObjects.get(j)).getTriggerLabel());
                        ((BoxCollider2D)LevelHandler.s_colliders.get(i)).checkCollision((BoxCollider2D)returnObjects.get(j),false);
                    }
                }
            }
        }
    }
}
