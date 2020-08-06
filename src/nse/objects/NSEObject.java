package nse.objects;

import nse.collisions.BoxCollider2D;
import nse.levels.LevelHandler;
import nse.rendering.Animator;
import nse.rendering.Camera;
import nse.rendering.Renderer;
import nse.ui.NSEBox;
import nse.ui.NSEButton;

import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NSEObject {

    private final Map<Class<?>,Object> m_components = new HashMap<>();

    public NSETransform transform;

    private String m_objectName;

    public NSEObject(String objectName)
    {
        m_objectName = objectName;
        addComponent(NSETransform.class, new NSETransform());
        transform = getComponent(NSETransform.class);
    }

    public <T> void addComponent(Class<T> type, T component)
    {
        Object previous = m_components.putIfAbsent(type, component);
        if(previous != null) throw new InvalidParameterException("Object already has a component of type " + type);

        if(component instanceof NSEScript)
        {
            ((NSEScript)component).gameObject = this;
            ((NSEScript)component).transform = getComponent(NSETransform.class);
            /*if(component.getClass().getAnnotation(RequireComponent.class) != null)
            {
                Class<?> comp = null;
                try
                {
                    comp = Class.forName(component.getClass().getAnnotation(RequireComponent.class).component());
                } catch(ClassNotFoundException e)
                {
                    e.printStackTrace();
                }

                if(!hasComponent(comp)) addComponent(comp, comp.getConstructor());
            }*/
        } else if(component instanceof BoxCollider2D)
        {
            ((BoxCollider2D)component).gameObject = this;
            ((BoxCollider2D)component).transform = getComponent(NSETransform.class);
        } else if(component instanceof NSEBox)
        {
            ((NSEBox)component).gameObject = this;
            ((NSEBox)component).transform = getComponent(NSETransform.class);
        } else if(component instanceof NSEButton)
        {
            ((NSEButton)component).gameObject = this;
            ((NSEButton)component).transform = getComponent(NSETransform.class);
        } else if(component instanceof Renderer)
        {
            ((Renderer)component).gameObject = this;
            ((Renderer)component).transform = getComponent(NSETransform.class);
            ((Renderer)component).setupApplet(LevelHandler.getApp());
        } else if(component instanceof Animator)
        {
            ((Animator)component).gameObject = this;
            ((Animator)component).transform = getComponent(NSETransform.class);
        } else if(component instanceof Camera)
        {
            ((Camera)component).gameObject = this;
            ((Camera)component).transform = getComponent(NSETransform.class);
        }
    }

    public <T> T getComponent(Class<T> comp)
    {
        T c = comp.cast(m_components.get(comp));
        if(c == null)
        {
            throw new InvalidParameterException(("Cannot find component of type " + comp.getName()));
        }
        return c;
    }

    public Collection<Object> getComponents() { return m_components.values(); }

    public <T> boolean hasComponent(Class<T> comp)
    {
        return m_components.containsKey(comp);
    }

    public String getName() { return m_objectName; }
}
