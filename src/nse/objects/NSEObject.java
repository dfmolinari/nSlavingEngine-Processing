package nse.objects;

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
        } else if(component instanceof NSEBox)
        {
            ((NSEBox)component).gameObject = this;
            ((NSEBox)component).transform = getComponent(NSETransform.class);
        } else if(component instanceof NSEButton)
        {
            ((NSEButton)component).gameObject = this;
            ((NSEButton)component).transform = getComponent(NSETransform.class);
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
}
