package nse.levels;

import nse.Quadtree;
import nse.collisions.*;
import nse.objects.NSEObject;
import nse.objects.NSEScript;
import nse.objects.NSETransform;
import processing.core.PApplet;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
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

    ArrayList<NSEObject> m_gameObjects = new ArrayList<NSEObject>();

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
                for(NSEObject o : m_gameObjects)
                {
                    for(Object b : o.getComponents())
                    {
                        if(o.getComponent(b.getClass()).getClass().isAssignableFrom(NSEScript.class))
                            ((NSEScript)o.getComponent(b.getClass())).Start();
                    }

                }
                m_myParent.getClass().getMethod(m_start).invoke(m_myParent);
            } catch(ReflectiveOperationException e)
            {
                throw new RuntimeException(e);
            }
            m_resetLevel = false;
        }
        try {
            for(NSEObject o : m_gameObjects)
            {
                for(Object b : o.getComponents())
                {
                    if(o.getComponent(b.getClass()).getClass().isAssignableFrom(NSEScript.class))
                        ((NSEScript)o.getComponent(b.getClass())).Update();
                    if(o.getComponent(b.getClass()) instanceof BoxCollider2D)
                        o.getComponent(BoxCollider2D.class).setPosition(o.getComponent(NSETransform.class).getPosition());
                }
            }
            executeQuadtree();
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

    protected void addGameObject(NSEObject obj)
    {

        m_gameObjects.add(obj);
        m_myApplet.println(m_gameObjects.size());
    }

    protected void executeQuadtree()
    {
        quad.clear();
        for(int i = 0; i < m_gameObjects.size(); i++)
        {
            if(m_gameObjects.get(i).hasComponent(BoxCollider2D.class))
            {
                quad.insert(m_gameObjects.get(i).getComponent(BoxCollider2D.class));
            }
        }
        List returnObjects = new ArrayList();
        for(int i = 0; i < m_gameObjects.size(); i++)
        {
            returnObjects.clear();
            if(m_gameObjects.get(i).hasComponent(BoxCollider2D.class))
                quad.retrieve(returnObjects, m_gameObjects.get(i).getComponent(BoxCollider2D.class));

            for(int j = 0; j < returnObjects.size(); j++)
            {
                if(returnObjects.get(j) instanceof BoxCollider2D && m_gameObjects.get(i).hasComponent(BoxCollider2D.class))
                {
                    if(returnObjects.get(j) != m_gameObjects.get(i).getComponent(BoxCollider2D.class))
                    {
                        //System.out.println("checking collision between" + ((BoxCollider2D) LevelHandler.s_colliders.get(i)).getTriggerLabel() + " and " + ((BoxCollider2D)returnObjects.get(j)).getTriggerLabel());
                        m_gameObjects.get(i).getComponent(BoxCollider2D.class).checkCollision((BoxCollider2D)returnObjects.get(j),false);
                    }
                }
            }
        }
    }
}
