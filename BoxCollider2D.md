---
title: BoxCollider2D
description: Wiki
---

### What is it?

A BoxCollider2D is a non-rotatable rectangle collider.

### Triggers

If you don't know what triggers are, they are colliders that don't stop your movement and execute an event when entering/staying/exing it. To learn how to customize a trigger's events **[click here](BoxCollider2D.md#customizing-a-colliders-trigger-events)**.

### Method list

1. Constructor `BoxCollider2D(PVector position, PVector size, boolean isTrigger, Object myParent)`
  * Createse a new button with the given position, size and defines if it's a trigger
  * The "myParent" parameter will represent the parent class, usually filled with the `this` keyword.
  * Example usage:
```java
BoxCollider2D box = new BoxCollider2D(new PVector(width/2,height/2), new PVector(150,100), false, this);
```

2. Constructor `BoxCollider2D(PVector position, PVector size, boolean isTrigger, String label, Object myParent)`
  * Createse a new button with the given position, size and defines if it's a trigger
  * The "myParent" parameter will represent the parent class, usually filled with the `this` keyword.
  * The label parameter is used for trigger definition
  * Example usage:
```java
BoxCollider2D box = new BoxCollider2D(new PVector(width/2,height/2), new PVector(150,100), true, "testTrigger", this);
```

3. Constructor `BoxCollider2D(float x, float y, float w, float h, boolean isTrigger, Object myParent)`
  * Createse a new button with the given position, size and defines if it's a trigger
  * The "myParent" parameter will represent the parent class, usually filled with the `this` keyword.
  * Example usage:
```java
BoxCollider2D box = new BoxCollider2D(width/2,height/2,150,100, false, this);
```

4. Constructor `BoxCollider2D(float x, float y, float w, float h, boolean isTrigger, String label, Object myParent)`
  * Createse a new button with the given position, size and defines if it's a trigger
  * The "myParent" parameter will represent the parent class, usually filled with the `this` keyword.
  * Example usage:
```java
BoxCollider2D box = new BoxCollider2D(width/2,height/2,150,100, true, "testTrigger", this);
```

5. Getting the position - `BoxCollider2D.getPosition()`
  * Returns the collider's position as a [PVector](https://processing.org/reference/PVector.html)
  * Example usage:
```java
//imagine having a collider called "box"
println("Collider at x: " + box.getPosition().x + "; y: " + box.getPosition().y);
```

6. Setting the position - `BoxCollider2D.setPosition(float x, float y)`
  * Sets the collider's position
  * Example usage:
```java
//imagine having a collider called "box"
box.setPosition(300,200);
```

7. Setting the position - `BoxCollider2D.setPosition(PVector position)`
  * Sets the collider's position
  * Example usage:
```java
//imagine having a collider called "box"
box.setPosition(new PVector(300,200);
```

8. Getting the size - `BoxCollider2D.getSize()`
  * Returns the collider's size as a [PVector](https://processing.org/reference/PVector.html)
  * Example usage:
```java
//imagine having a collider called "box"
println("Collider's width: " + box.getSize().x + "; height: " + box.getSize().y);
```

9. Setting the size - `BoxCollider2D.setSize(float x, float y)`
  * Sets the collider's size
  * Example usage:
```java
//imagine having a collider called "box"
box.setSize(150,200);
```

10. Setting the size - `BoxCollider2D.setSize(PVector position)`
  * Sets the collider's size
  * Example usage:
```java
//imagine having a collider called "box"
box.setSize(new PVector(150,200);
```

11. Checking if it's a trigger - `BoxCollider2D.isTrigger()`
  * Returns a boolean, true if it's a trigger, false if not
  * Example usage:
```java
//imagine having a collider called "box"
if(box.isTrigger)
  println("box is a trigger");
```

12. Setting the trigger's label - `BoxCollider2D.setTriggerLabel(String label)`
  * Sets the trigger's label to execute trigger events
  * Example usage:
```java
//imagine having a collider called "box"
box.setTriggerLabel("testTrigger");
```

13. Getting the trigger's label - `BoxCollider2D.getTriggerLabel()`
  * Returns a string with the trigger's label
  * Example usage:
```java
//imagine having a collider called "box"
println("Collider's name: " + box.getTriggerLabel);
```

14. Setting a pushable collider - `BoxCollider2D.setPushable(boolean state)`
  * Sets if a collider is pushable or not(default is false)
  * If a collider is pushable the colliding object will not stop at this but will push it
  * Example usage:
```java
//imagine having a collider called "box"
box.setPushable(true);
```

15. Checking if a collider is pushable - `BoxCollider2D.isPushable()`
  * Checks if a collider is pushable, returns true if it is, false if it isn't
  * Example usage:
```java
//imagine having a collider called "box"
if(box.isPushable())
  println("box can be pushed");
```

### Customizing a collider's trigger events

A collider's on trigger events can be customized by creating methods with the following format `label+OnTrigger+Status`, where "status" can be `Enter,Stay,Exit`.

Be careful with trigger naming, for example you might have different triggers in your game with the label `levelSwap`, this is going to mean that every trigger with that label will point to that method. Easy fixes for that could be checking in which level the user is located into by using the **[LevelHandler](LevelHandler.md)** and make the trigger work accordingly.

***WARNING: METHODS NAME ARE KEY SENSITIVE, MISTYPING A LEVEL NAME WILL RESULT IN THE ENGINE NOT FINDING THE METHOD AND RESULT IN AN ERROR***

```java
//customizing the events for the "testTrigger" collider
void testTriggerOnTriggerEnter()
{
    println("Entering trigger...");
}

void testTriggerOnTriggerStay()
{
  //imagine having a Player class with an health stat
  p.heal(0.5f);
}

void testTriggerOnTriggerExit()
{
  println("Exiting trigger...");
}
```
