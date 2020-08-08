package nse.rendering;

import nse.objects.NSEObject;
import nse.objects.NSETransform;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;
import java.util.Map;

public class Animator {

    Map<String,Animation> m_animationList  = new HashMap<>();

    private String m_currentAnimation = null;

    public void addAnimation(String name, Animation anim){
        m_animationList.put(name,anim);
    }

    public NSEObject gameObject;
    public NSETransform transform;

    public Animator(){ }

    Animator(Animator copy, PApplet appRef){
        for(Map.Entry<String,Animation> e : copy.m_animationList.entrySet()){
            Animation anim = new Animation(e.getValue().m_loop,e.getValue().m_animFreq, appRef);
            for(PImage i : e.getValue().frames) {
                anim.addFrame(i);
            }
            addAnimation(e.getKey(),anim);
        }
    }

    void animate(){
        for(Animation anim : m_animationList.values()){
            anim.stateHandler(gameObject,transform);
        }
    }

    public Animation getAnimation(String name){
        return m_animationList.get(name);
    }

    public void playAnimation(String name){
        for(Map.Entry<String, Animation> anim : m_animationList.entrySet()){
            if(!anim.getKey().equalsIgnoreCase(name)) anim.getValue().stop();
            else
            {
                m_currentAnimation = anim.getKey();
                anim.getValue().play();
            }
        }
    }

    public Map<String,Animation> getAnimationList() { return m_animationList; }

    public void pauseAnimation(String name){
        m_animationList.get(name).pause();
    }

    public void stopAnimation(String name){
        m_animationList.get(name).stop();
    }

    public String getCurrentAnimation() { return m_currentAnimation; }

}
