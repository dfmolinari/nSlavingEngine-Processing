package nse;

import nse.collisions.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Quadtree {
    private int MAX_OBJECTS = 10;
    private int MAX_LEVELS = 5;

    private int m_level;
    private List m_objects;
    private Rectangle m_bounds;
    private Quadtree[] m_nodes;

    public Quadtree(int level, Rectangle bounds)
    {
        m_level = level;
        m_objects = new ArrayList();
        m_bounds = bounds;
        m_nodes = new Quadtree[4];
    }


    //clears the quadtree
    public void clear()
    {
        m_objects.clear();

        for(int i = 0; i < m_nodes.length; i++)
        {
            if(m_nodes[i] != null)
            {
                m_nodes[i].clear();
                m_nodes[i] = null;
            }
        }
    }

    //splits the node into 4 subnodes
    private void split()
    {
        int subWidth = (int)(m_bounds.getWidth()/2);
        int subHeight = (int)(m_bounds.getHeight()/2);
        int x = (int)m_bounds.getX();
        int y = (int)m_bounds.getY();

        m_nodes[0] = new Quadtree(m_level+1, new Rectangle(x+subWidth,y,subWidth,subHeight));
        m_nodes[1] = new Quadtree(m_level+1, new Rectangle(x,y,subWidth,subHeight));
        m_nodes[2] = new Quadtree(m_level+1, new Rectangle(x,y+subHeight,subWidth,subHeight));
        m_nodes[3] = new Quadtree(m_level+1, new Rectangle(x+subWidth,y+subHeight,subWidth,subHeight));
    }

    //determine which node the object belongs to
    private int getIndex(BoxCollider2D box)
    {
        int index = -1;
        double verticalMidpoint = m_bounds.getX() + (m_bounds.getWidth()/2);
        double horizontalMidpoint = m_bounds.getY() + (m_bounds.getHeight()/2);

        float top = box.transform.position.y-box.transform.scale.y/2;
        float bot = box.transform.position.y+box.transform.scale.y/2;
        float left = box.transform.position.x-box.transform.scale.x/2;
        float right = box.transform.position.x+box.transform.scale.x/2;

        boolean topQuadrant = (top < horizontalMidpoint && bot < horizontalMidpoint);
        boolean bottomQuadrant = (top > horizontalMidpoint);

        if(left < verticalMidpoint && right < verticalMidpoint)
        {
            if(topQuadrant)
                index = 1;
            else if (bottomQuadrant)
                index = 2;
        } else if(left > verticalMidpoint)
        {
            if(topQuadrant)
                index = 0;
            else if(bottomQuadrant)
                index = 3;
        }

        return index;
    }

    //insert node in quadtree, if exceeds the capacity split and add to corresponding nodes
    public void insert(BoxCollider2D box)
    {
        if(m_nodes[0] != null)
        {
            int index = getIndex(box);

            if(index != -1)
            {
                m_nodes[index].insert(box);
                return;
            }
        }

        m_objects.add(box);

        if(m_objects.size() > MAX_OBJECTS && m_level < MAX_LEVELS)
        {
            if(m_nodes[0] == null)
            {
                split();
            }

            int i = 0;
            while(i < m_objects.size())
            {
                int index = getIndex((BoxCollider2D)m_objects.get(i));
                if(index != -1)
                {
                    m_nodes[index].insert((BoxCollider2D)m_objects.get(i));
                    m_objects.remove(i);
                } else
                {
                    i++;
                }
            }
        }
    }

    //returns all objects that could collide with the given object
    public List retrieve(List returnObjects, BoxCollider2D box)
    {
        int index = getIndex(box);
        if(index != -1 && m_nodes[0] != null)
        {
            m_nodes[index].retrieve(returnObjects, box);
        }

        returnObjects.addAll(m_objects);

        return returnObjects;
    }
}
