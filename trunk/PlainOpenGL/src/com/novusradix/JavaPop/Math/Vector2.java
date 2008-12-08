/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novusradix.JavaPop.Math;

import java.io.Serializable;

/**
 *
 * @author mom
 */
public class Vector2 implements Serializable {

    public float x,  y;

    public Vector2() {
        x = y = 0;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public float length(){
        return (float) Math.sqrt(x*x+y*y);
    }
}
