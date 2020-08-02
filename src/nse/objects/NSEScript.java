package nse.objects;

public abstract class NSEScript {

    public NSEObject gameObject;
    public NSETransform transform;

    public abstract void Start();
    
    public abstract void Update();

}
