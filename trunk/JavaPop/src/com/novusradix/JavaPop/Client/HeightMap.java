/*
 * This class is the client side map. It specifies creation, response to server update messages, and rendering.
 * Other client classes can also query points on the map.
 */
package com.novusradix.JavaPop.Client;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;
import javax.media.opengl.GL;
import com.novusradix.JavaPop.Messaging.HeightMapUpdate;

public class HeightMap extends com.novusradix.JavaPop.HeightMap implements GLObject {

    HeightMapImpl implementation;
    GLObject renderer;

    public HeightMap(Dimension mapSize) {
        super(mapSize);

        HeightMapGLSL h = new HeightMapGLSL();
        implementation = h;
        renderer = h;
        implementation.initialise(this);
    }

    public void display(GL gl, float time) {
        renderer.display(gl, time);
    }

    public void init(GL gl) {
        renderer.init(gl);
    }

    @Override
    public void applyUpdate(HeightMapUpdate u) {
        implementation.applyUpdate(u);
    }

    @Override
    public byte getHeight(int x, int y) {
        return implementation.getHeight(x, y);
    }

    @Override
    protected void setHeight(int x, int y, byte b) {
        implementation.setHeight(x, y, b);
    }

    @Override
    public void addRocks(Set<Point> newRocks) {
        implementation.addRocks(newRocks);
    }

    @Override
    public void removeRocks(Set<Point> deadRocks) {
        implementation.removeRocks(deadRocks);
    }
}