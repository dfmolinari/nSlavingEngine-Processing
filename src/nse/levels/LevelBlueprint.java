package nse.levels;

import nse.Quadtree;
import nse.collisions.*;
import nse.objects.*;
import nse.rendering.Camera;
import nse.rendering.Renderer;
import nse.ui.*;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Level class used to define levels and their content.
 *
 * @author Davide Furfaro
 */
public class LevelBlueprint {

    protected PApplet m_myApplet;

    private String m_name;
    private int m_nId;

    protected boolean m_resetLevel;

    private Quadtree quad;

    private PImage m_backgroundImage;
    private int m_backgroundColor;

    protected float m_lastFrameTime = 0.0f;

    ArrayList<NSEObject> m_gameObjects = new ArrayList<NSEObject>();

    /**
     * A constructor, usually called when adding a new level from the LevelHandler
     * with LevelHandler.createLevel() ||
     * WARNING: Creating a level with an ID or name that already exists will result in an error!
     *
     * @param id the level ID
     * @param name the level name
     * @param myApplet the main applet used
     */
    public LevelBlueprint(int id, String name, PApplet myApplet){
        m_myApplet = myApplet;

        quad = new Quadtree(0,new Rectangle(0,0,m_myApplet.width,m_myApplet.height));

        m_nId = id;
        m_name = name;

        m_resetLevel = true;

        m_backgroundColor = myApplet.color(0,100,148);

        for(LevelBlueprint n : LevelHandler.s_levelList)
        {
            if(id == n.m_nId) throw new InvalidParameterException("Cannot create level: " + id + "; A level with the same ID is already existing");
            else if(name.equalsIgnoreCase(n.m_name)) throw new InvalidParameterException("Cannot create level: " + name + "; A level with the same Name is already existing");
        }
    }

    /**
     * Executes the start method once then executes the update method.
     */
    protected void content(float deltaTime)
    {
        if(m_resetLevel)
        {
            for(NSEObject o : m_gameObjects)
            {
                for(Object b : o.getComponents())
                {
                    if(o.getComponent(b.getClass()) instanceof NSEScript)
                    {
                        ((NSEScript) o.getComponent(b.getClass())).deltaTime = deltaTime;
                        ((NSEScript)o.getComponent(b.getClass())).Start();
                    }
                }

            }
            m_resetLevel = false;
        }
        if(m_backgroundImage == null) m_myApplet.background(m_backgroundColor);
        else m_myApplet.background(m_backgroundImage);

        for(NSEObject o : m_gameObjects)
        {
            for(Object b : o.getComponents())
            {
                if(o.getComponent(b.getClass()) instanceof NSEScript)
                {
                    ((NSEScript)o.getComponent(b.getClass())).deltaTime = deltaTime;
                    ((NSEScript)o.getComponent(b.getClass())).Update();
                }
                executeQuadtree();
                if(o.getComponent(b.getClass()) instanceof Renderer)
                    o.getComponent(Renderer.class).display();
                if(o.getComponent(b.getClass()) instanceof NSEBox)
                    o.getComponent(NSEBox.class).display();
                if(o.getComponent(b.getClass()) instanceof NSEButton)
                    o.getComponent(NSEButton.class).display();
                if(o.getComponent(b.getClass()) instanceof Camera)
                    o.getComponent(Camera.class).calculateCamera();
            }
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
    }

    private void executeQuadtree()
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
                        //if(m_myApplet.frameCount % 30 == 0)System.out.println("checking collision between" + (m_gameObjects.get(i).getName() + " and " + ((BoxCollider2D) returnObjects.get(j)).gameObject.getName()));
                        m_gameObjects.get(i).getComponent(BoxCollider2D.class).checkCollision((BoxCollider2D)returnObjects.get(j),false);
                    }
                }
            }
        }
    }

    protected void setBackground(PImage img) { img.resize(m_myApplet.width,m_myApplet.height); m_backgroundImage = img; }
    protected void setBackground(int color) { m_backgroundColor = color; }
}
