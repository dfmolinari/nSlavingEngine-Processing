---
title: Collision Detection
description: Wiki
---

### What is it?

Detects every currently exsiting collider of any type and calculates collisions between them.

To import collisions use `import nse.collisions.*`

### Quadtrees

The collision detection system revolves around Quadtrees, if you don't know what they are you could simply say it's a way of reducing the amount of calculations each frame lowering to only the ones that can collide with each other instead of every existing collider.

To see how to execute the Quadtree, please check out the 9th method in the **[LevelHandler](LevelHandler.md)**.

More on Collision Detection:
  * **[BoxCollider2D](BoxCollider2D.md)**
