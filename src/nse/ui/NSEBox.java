package nse.ui;

import nse.levels.LevelHandler;
import nse.objects.NSEObject;
import nse.objects.NSETransform;
import nse.rendering.Camera;
import nse.resizing.Rescaler;
import processing.core.*;

public class NSEBox {

    private PApplet m_myApplet;

    public NSEObject gameObject;
    public NSETransform transform;

    private int m_baseColor, m_textColor;
    private String m_label;
    private PImage m_img;
    private PFont m_font;
    private float m_fontSize;

    private float rx, ry, rw, rh;

    public NSEBox(String label, int baseColor, int textColor, PImage img, float fontSize, PFont font, PApplet myApplet){
        m_label = label;
        m_baseColor = baseColor;
        m_textColor = textColor;
        m_img = img;
        m_fontSize = fontSize;
        m_font = font;
        m_myApplet = myApplet;
    }

    public NSEBox(String label, int baseColor, int textColor, PImage img, float fontSize, PApplet myApplet){
        m_label = label;
        m_baseColor = baseColor;
        m_textColor = textColor;
        m_img = img;
        m_fontSize = fontSize;
        m_font = null;
        m_myApplet = myApplet;
    }

    public void display(){

        NSEObject camRef = LevelHandler.findObjectSilent(LevelHandler.getCurrentLevelName(),"mainCamera");

        rx = camRef == null ? Rescaler.resizeOnWidthIgnore(transform.position.x) : Rescaler.resizeOnWidthIgnore(transform.position.x+camRef.transform.position.x-Rescaler.getHalfWidth());
        ry = camRef == null ? Rescaler.resizeOnHeight(transform.position.y) : Rescaler.resizeOnHeight(transform.position.y+camRef.transform.position.y-Rescaler.getHalfHeight());
        rw = Rescaler.resizeOnHeight(transform.scale.x);
        rh = Rescaler.resizeOnHeight(transform.scale.y);

        m_myApplet.rectMode(m_myApplet.CENTER);
        m_myApplet.imageMode(m_myApplet.CENTER);
        if(m_img != null)
        {
            m_myApplet.tint(m_baseColor);
            m_myApplet.image(m_img,rx,ry,rw,rh);
        } else
        {
            m_myApplet.fill(m_baseColor);
            m_myApplet.rect(rx,ry,rw,rh);
        }
        m_myApplet.fill(m_textColor);
        m_myApplet.textAlign(m_myApplet.CENTER, m_myApplet.CENTER);
        if(m_font != null)
            m_myApplet.textFont(m_font);
        m_myApplet.textSize(m_fontSize);
        m_myApplet.text(m_label, rx,ry,rw,rh);
        m_myApplet.fill(255);
        m_myApplet.rectMode(m_myApplet.CORNER);
        m_myApplet.imageMode(m_myApplet.CORNER);
    }
}
