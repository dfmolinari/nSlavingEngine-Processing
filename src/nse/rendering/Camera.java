package nse.rendering;

import nse.levels.LevelHandler;
import nse.objects.NSEObject;
import nse.objects.NSETransform;
import nse.resizing.Rescaler;

public class Camera {

    public NSEObject gameObject;
    public NSETransform transform;

    private NSEObject m_target;

    public Camera() { m_target = null; }

    public void calculateCamera()
    {
        if(m_target != null)
        {
            transform.position.set(m_target.transform.position.x,m_target.transform.position.y);
        }

        LevelHandler.getApp().camera(Rescaler.resizeOnWidth(transform.position.x),Rescaler.resizeOnHeight(transform.position.y),LevelHandler.getApp().height / 2 / LevelHandler.getApp().tan(LevelHandler.getApp().PI * 30 / 180),Rescaler.resizeOnWidth(transform.position.x),Rescaler.resizeOnHeight(transform.position.y),0,0,1,0);
    }

    public void setTarget(NSEObject target) { m_target = target; }

}
