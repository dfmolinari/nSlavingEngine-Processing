package nse.ui;

import processing.core.*;

public class NSEBox {

    private PApplet m_myApplet;

    private PVector m_position;
    private PVector m_size;
    private BoxType m_type;
    private int m_baseColor, m_textColor;
    private String m_label;
    private PImage m_img;
    private PFont m_font;
    private float m_fontSize;

    public NSEBox(PVector position, PVector size, BoxType type, String label, int baseColor, int textColor, PImage img, float fontSize, PFont font, PApplet myApplet){
        m_position = new PVector(position.x,position.y);
        m_size = new PVector(size.x,size.y);
        m_type = type;
        m_label = label;
        m_baseColor = baseColor;
        m_textColor = textColor;
        m_img = img;
        m_fontSize = fontSize;
        m_font = font;
        m_myApplet = myApplet;
    }

    public NSEBox(float x, float y, float w, float h, BoxType type, String label, int baseColor, int textColor, PImage img, float fontSize, PFont font, PApplet myApplet){
        m_position = new PVector(x,y);
        m_size = new PVector(w,h);
        m_type = type;
        m_label = label;
        m_baseColor = baseColor;
        m_textColor = textColor;
        m_img = img;
        m_fontSize = fontSize;
        m_font = font;
        m_myApplet = myApplet;
    }

    public void display(){
        if(m_type == BoxType.Default){
            m_myApplet.rectMode(m_myApplet.CENTER);
            m_myApplet.fill(m_baseColor);
            m_myApplet.rect(m_position.x, m_position.y, m_size.x, m_size.y);
            m_myApplet.fill(m_textColor);
            m_myApplet.textAlign(m_myApplet.CENTER, m_myApplet.CENTER);

            if(m_font != null)
                m_myApplet.textFont(m_font);

            m_myApplet.textSize(m_fontSize);
            m_myApplet.text(m_label,m_position.x, m_position.y, m_size.x, m_size.y);
            m_myApplet.fill(255);
            m_myApplet.rectMode(m_myApplet.CORNER);
        } else if(m_type == BoxType.Image){
            m_myApplet.imageMode(m_myApplet.CENTER);
            m_myApplet.image(m_img,m_position.x, m_position.y, m_size.x, m_size.y);
            m_myApplet.imageMode(m_myApplet.CORNER);
        } else if(m_type == BoxType.Mixed){
            m_myApplet.imageMode(m_myApplet.CENTER);
            m_myApplet.image(m_img,m_position.x, m_position.y, m_size.x, m_size.y);
            m_myApplet.imageMode(m_myApplet.CORNER);
            m_myApplet.fill(m_textColor);
            m_myApplet.textAlign(m_myApplet.CENTER,m_myApplet.CENTER);

            if(m_font != null)
                m_myApplet.textFont(m_font);
            
            m_myApplet.textSize(m_fontSize);
            m_myApplet.rectMode(m_myApplet.CENTER);
            m_myApplet.text(m_label,m_position.x, m_position.y, m_size.x, m_size.y);
            m_myApplet.rectMode(m_myApplet.CORNER);
            m_myApplet.fill(255);
        }
    }
}
