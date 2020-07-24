package nse.ui;

import processing.core.*;

public class NSEBox {

    private PApplet myApplet;

    private PVector m_position;
    private PVector m_size;
    private BoxType type;
    private int baseColor, textColor;
    private String label;
    private PImage img;
    private PFont font;
    private float fontSize;

    public NSEBox(PVector position, PVector size, BoxType type, String label, int baseColor, int textColor, PImage img, float fontSize, PFont font, PApplet myApplet){
        m_position = new PVector(position.x,position.y);
        m_size = new PVector(size.x,size.y);
        this.type = type;
        this.label = label;
        this.baseColor = baseColor;
        this.textColor = textColor;
        this.img = img;
        this.fontSize = fontSize;
        this.font = font;
        this.myApplet = myApplet;
    }

    public NSEBox(float x, float y, float w, float h, BoxType type, String label, int baseColor, int textColor, PImage img, float fontSize, PFont font, PApplet myApplet){
        m_position = new PVector(x,y);
        m_size = new PVector(w,h);
        this.type = type;
        this.label = label;
        this.baseColor = baseColor;
        this.textColor = textColor;
        this.img = img;
        this.fontSize = fontSize;
        this.font = font;
        this.myApplet = myApplet;
    }

    public void display(){
        if(type == BoxType.Default){
            myApplet.rectMode(myApplet.CENTER);
            myApplet.fill(baseColor);
            myApplet.rect(m_position.x, m_position.y, m_size.x, m_size.y);
            myApplet.fill(textColor);
            myApplet.textAlign(myApplet.CENTER, myApplet.CENTER);

            if(font != null)
                myApplet.textFont(font);

            myApplet.textSize(fontSize);
            myApplet.text(label,m_position.x, m_position.y, m_size.x, m_size.y);
            myApplet.fill(255);
            myApplet.rectMode(myApplet.CORNER);
        } else if(type == BoxType.Image){
            myApplet.imageMode(myApplet.CENTER);
            myApplet.image(img,m_position.x, m_position.y, m_size.x, m_size.y);
            myApplet.imageMode(myApplet.CORNER);
        } else if(type == BoxType.Mixed){
            myApplet.imageMode(myApplet.CENTER);
            myApplet.image(img,m_position.x, m_position.y, m_size.x, m_size.y);
            myApplet.imageMode(myApplet.CORNER);
            myApplet.fill(textColor);
            myApplet.textAlign(myApplet.CENTER,myApplet.CENTER);

            if(font != null)
                myApplet.textFont(font);
            
            myApplet.textSize(fontSize);
            myApplet.rectMode(myApplet.CENTER);
            myApplet.text(label,m_position.x, m_position.y, m_size.x, m_size.y);
            myApplet.rectMode(myApplet.CORNER);
            myApplet.fill(255);
        }
    }
}
