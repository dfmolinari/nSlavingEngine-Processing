package nse.ui;

import nse.input.Input;
import nse.objects.NSEObject;
import nse.objects.NSETransform;
import nse.resizing.Rescaler;
import processing.core.*;

public class NSEButton {
    private PApplet m_myApplet;

    public NSEObject gameObject;
    public NSETransform transform;

    //as color
    private int m_baseColor, m_textColor;

    private PImage m_img;

    private PFont m_font;
    private float m_fontSize;
    private String m_label;

    private boolean m_isEnabled;
    private boolean m_isActive;

    private float rx, ry, rw, rh;

    public NSEButton(String label, int baseColor, int textColor, PImage img, float fontSize, PFont font, PApplet main){
        m_label = label;
        m_baseColor = baseColor;
        m_textColor = textColor;
        m_img = img;
        m_fontSize = fontSize;
        m_font = font;
        m_myApplet = main;
        m_isEnabled = true;
        m_isActive = true;
    }

    public NSEButton(String label, int baseColor, int textColor, PImage img, float fontSize, PApplet main){
        m_label = label;
        m_baseColor = baseColor;
        m_textColor = textColor;
        m_img = img;
        m_fontSize = fontSize;
        m_font = null;
        m_myApplet = main;
        m_isEnabled = true;
        m_isActive = true;
    }

    public void display(){

        rx = Rescaler.resizeOnWidth(transform.position.x);
        ry = Rescaler.resizeOnHeight(transform.position.y);
        rw = Rescaler.resizeOnHeight(transform.scale.x);
        rh = Rescaler.resizeOnHeight(transform.scale.y);

        if(Input.getMouseButtonUp(37))
        {
            if(m_isEnabled && m_isActive && mouseOver())
            {
                try
                {
                    m_myApplet.getClass().getMethod(m_label+"Click").invoke(m_myApplet);
                } catch (ReflectiveOperationException e)
                {
                    throw new RuntimeException(e);
                }

            }
        }

        if(m_isActive) {
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

    public void setActive(boolean state){
        m_isActive = state;
    }
    public boolean isActive(){
        return m_isActive;
    }

    public void setEnabled(boolean state) { m_isEnabled = state; }
    public boolean isEnabled() { return m_isEnabled; }

    private boolean mouseOver(){
        if(m_myApplet.mouseX >= rx-rw/2 && m_myApplet.mouseX <= rx+rw/2 && m_myApplet.mouseY >= ry-rh/2 && m_myApplet.mouseY <= ry+rh/2) return true;

        return false;
    }
}
