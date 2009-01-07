package com.novusradix.JavaPop.Client;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLException;

/**
 *
 * @author gef
 * */
public class GLButton implements GLObject, GLClickable {

    Polygon buttonShape;
    boolean selected;
    boolean enabled;
    boolean visible;
    String texname;
    Texture tex;
    boolean flipx, flipy;

    public GLButton() {
        selected = false;
        enabled = true;
        visible = false; 
    }

    
    public void display(GL gl, float time) {
        int[] view = new int[4];
        gl.glGetIntegerv(GL.GL_VIEWPORT, view, 0);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glScalef(2.0f, 2.0f, 1.0f);
        if(flipx)
            gl.glScalef(-1.0f,1.0f,1.0f);
        if(!flipy)
            gl.glScalef(1.0f,-1.0f,1.0f);
        
        gl.glTranslatef(-0.5f, -0.5f, 0.0f);
        gl.glScalef(1.0f / view[2], 1.0f / view[3], 1.0f);
        gl.glDisable(GL.GL_LIGHTING);
        if (selected || (mDown && mOver)) {
            gl.glColor3f(0.6f, 0.6f, 0.8f);
        } else {
            gl.glColor3f(0.8f, 0.8f, 0.8f);
        }
        gl.glDisable(GL.GL_DEPTH_TEST);
        tex.disable();
        gl.glBegin(GL.GL_POLYGON);

        for (int n = 0; n < buttonShape.npoints; n++) {
            gl.glVertex2f(buttonShape.xpoints[n], buttonShape.ypoints[n]);
        }
        gl.glEnd();
        tex.enable();
        tex.bind();
        gl.glBegin(GL.GL_POLYGON);

        for (int n = 0; n < buttonShape.npoints; n++) {
            gl.glTexCoord2f(n / 2, (n + n / 2 + 1) % 2);
            gl.glVertex2f(buttonShape.xpoints[n], buttonShape.ypoints[n]);
        }
        gl.glEnd();
        gl.glPopMatrix();

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glPopMatrix();
    }

    public void init(GL gl) {
        URL u = getClass().getResource(texname);
        try {
            tex = TextureIO.newTexture(u, false, "png");
        } catch (IOException ex) {
            Logger.getLogger(GLButton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(GLButton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Shape getShape() {
        return buttonShape;
    }
    
    private boolean mDown,  mOver;

    public void mouseDown(MouseEvent e) {
        mDown = true;
    }

    public void mouseUp(MouseEvent e) {
        mDown = false;
        if (mOver) {
            selected = true;
        }
    }

    public void mouseOver(MouseEvent e) {
        mOver = true;
    }

    public void mouseOut(MouseEvent e) {
        mOver = false;
    }

    public boolean anchorLeft() {
        return !flipx;
    }

    public boolean anchorTop() {
        return !flipy;
    }
}