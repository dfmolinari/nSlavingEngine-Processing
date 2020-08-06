package nse.resizing;

public class Rescaler {

    private static float s_defaultWidth = 1920;
    private static float s_defaultHeight = 1080;
    private static float s_currentWidth = 0;
    private static float s_currentHeight = 0;

    public static void setSize(float w, float h) { s_currentWidth = w; s_currentHeight = h; }

    public static float resizeOnHeight(float value)
    {
        return (value * s_currentHeight) / s_defaultHeight;
    }

    public static float resizeOnWidth(float value)
    {
        return s_currentHeight == s_defaultHeight ? value : (value * s_currentWidth) / s_defaultWidth;
    }

    public static float getWidth() { return s_defaultWidth; }
    public static float getHalfWidth() { return s_defaultWidth/2; }
    public static float getHeight() { return s_defaultHeight; }
    public static float getHalfHeight() { return s_defaultHeight/2; }
}
