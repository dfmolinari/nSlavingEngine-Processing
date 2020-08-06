package nse.rendering;

import nse.objects.NSEObject;
import nse.objects.NSETransform;
import nse.resizing.Rescaler;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Animation {

    private PApplet m_myApplet;

    ArrayList<PImage> frames = new ArrayList<PImage>();

    int currentFrame;
    private int prevFrame;

    float m_animFreq;
    float m_readyCounter;

    boolean m_loop;

    boolean m_isPlaying;

    public Animation(boolean loop, float animFreq, PApplet mainApp){
        m_myApplet = mainApp;
        m_loop = loop;
        currentFrame = 0;
        prevFrame = 0;
        this.m_animFreq = animFreq * 1000;
        m_readyCounter = animFreq;
        m_isPlaying = false;

    }

    public void stateHandler(NSEObject object, NSETransform transform){
        if(m_isPlaying) {
            m_myApplet.imageMode(m_myApplet.CENTER);
            m_myApplet.image(frames.get(currentFrame), 0,0,Rescaler.resizeOnHeight(transform.scale.x),Rescaler.resizeOnHeight(transform.scale.y));
            if (currentFrame < frames.size() - 1 && m_myApplet.millis() >= m_animFreq+m_readyCounter) {
                m_readyCounter = m_myApplet.millis();
                if(prevFrame == frames.size()-1 && !m_loop)
                    currentFrame = 0;
                else
                    prevFrame = currentFrame++;
            } else if (currentFrame == frames.size() - 1 && m_loop && m_myApplet.millis() >= m_animFreq+m_readyCounter) {
                m_myApplet.millis();
                prevFrame = frames.size() - 1;
                currentFrame = 0;
            } else if (currentFrame == frames.size() - 1 && !m_loop && m_readyCounter <= m_myApplet.millis()){
                m_readyCounter = m_myApplet.millis();
                prevFrame = frames.size()-1;
            }
        }
    }

    public void addFrame(PImage frame){
        frames.add(frame);
    }

    public void play() {
        m_isPlaying = true;
    }

    public void pause(){
        m_isPlaying = false;
    }

    public void stop(){
        m_isPlaying = false;
        prevFrame = 0;
        currentFrame = 0;
    }

}
