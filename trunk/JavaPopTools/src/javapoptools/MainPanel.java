package javapoptools;

import com.novusradix.JavaPop.Client.GLHelper;
import com.novusradix.JavaPop.Client.GLHelper.GLHelperException;
import com.novusradix.JavaPop.Math.Matrix4;
import com.novusradix.JavaPop.Math.Vector3;
import java.net.URL;
import java.nio.FloatBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import static javax.media.opengl.GL.*;

/**
 *
 * @author gef
 */
public class MainPanel extends GLCanvas implements GLEventListener {

    private static final float fHeightScale = 0.4082f;
    private Model model;
    private ModelData data;
    private URL textureURL;
    private Vector3 modelPosition;
    private Matrix4 modelBasis;
    private long startTime;
    private float zoom = 1;
    private MainWindow mw;
    private String vertexShaderSource,  fragmentShaderSource;
    private int vertexShader,  fragmentShader,  shaderProgram;
    private boolean initShaders = true;

    public MainPanel(GLCapabilities caps, MainWindow mainWindow) {
        super(caps);
        addGLEventListener(this);
        startTime = System.nanoTime();
        mw = mainWindow;
    }

    public void setData(ModelData d) {
        data = d;
        model = new Model(data, textureURL);
        modelPosition = new Vector3();
        modelBasis = new Matrix4(Matrix4.identity);
    }

    public void setTexture(URL u) {
        textureURL = u;
        if (model != null) {
            model.setTextureURL(u);
        }
    }

    public void init(GLAutoDrawable glAD) {
        //Called before first display and on fullscreen/mode changes
        final GL gl = glAD.getGL();
        GLHelper.glHelper.init(gl);
        gl.setSwapInterval(1);
        gl.glEnable(GL.GL_LIGHTING);
        float global_ambient[] = {0.1f, 0.1f, 0.1f, 1.0f};
        gl.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(global_ambient));

        gl.glLightfv(GL.GL_LIGHT1, GL.GL_DIFFUSE, FloatBuffer.wrap(new float[]{0.8f, 0.8f, 0.8f, 1.0f}));

        gl.glEnable(GL.GL_LIGHT1);
        gl.glEnable(GL.GL_COLOR_MATERIAL);
        gl.glColorMaterial(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT_AND_DIFFUSE);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        vertexShader = gl.glCreateShader(GL.GL_VERTEX_SHADER);
        fragmentShader = gl.glCreateShader(GL.GL_FRAGMENT_SHADER);
        shaderProgram = gl.glCreateProgram();
        gl.glAttachShader(shaderProgram, vertexShader);
        gl.glAttachShader(shaderProgram, fragmentShader);

    }

    public void display(GLAutoDrawable glAD) {
        final GL gl = glAD.getGL();
        float time = (System.nanoTime() - startTime) / 1e9f;

        if (!mw.getVertexShader().equals(vertexShaderSource)) {
            vertexShaderSource = mw.getVertexShader();
            initShaders = true;
        }
        if (!mw.getFragmentShader().equals(fragmentShaderSource)) {
            fragmentShaderSource = mw.getFragmentShader();
            initShaders = true;
        }
        if (initShaders) {
            initializeShaders(gl);
        }
        gl.glClearColor(0, 0, 0.8f, 0);
        gl.glEnable(GL.GL_LIGHTING);
        gl.glColor4f(0.5f,0.5f,0.5f,0.5f);
        gl.glEnable(GL.GL_DEPTH_TEST);

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glTranslatef(0, 0, -50);
        gl.glRotatef(-60.0f, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(45.0f, 0.0f, 0.0f, 1.0f);

        gl.glScalef(1.0f, 1.0f, fHeightScale);
        gl.glScalef(zoom, zoom, zoom);

        gl.glRotatef(time * 20.f, 0, 0, 1);

        if (model != null) {
            model.setShader(shaderProgram);
            model.prepare(gl);
            model.display(modelPosition, modelBasis, gl);
        }
        gl.glPopMatrix();
        try {
            GLHelper.glHelper.checkGL(gl);
        } catch (GLHelperException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reshape(final GLAutoDrawable glDrawable, final int x, final int y, final int w, int h) {
        final GL gl = glDrawable.getGL();

        if (h <= 0) // avoid a divide by zero error!
        {
            h = 1;
        }
        gl.glViewport(0, 0, w, h); //strictly unneccesary as the component calls this automatically
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-w / 64.0f, w / 64.0f, -h / 64.0f, h / 64.0f, 1, 100);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void setZoom(float value) {
        zoom = value;
    }

    private void initializeShaders(final GL gl) {
        initShaders = false;
        gl.glUseProgram(0);
        mw.setVertexStatus("Compile:\n");
        mw.setFragmentStatus("Compile:\n");
        mw.appendVertexStatus(GLHelper.glHelper.CompileShader(gl, vertexShader, new String[]{vertexShaderSource}));
        mw.appendFragmentStatus(GLHelper.glHelper.CompileShader(gl, fragmentShader, new String[]{fragmentShaderSource}));
        int[] is = new int[1];
        byte[] chars;
        mw.appendVertexStatus("Link:\n");
        mw.appendFragmentStatus("Link:\n");
        gl.glLinkProgram(shaderProgram);
        gl.glGetProgramiv(shaderProgram, GL_INFO_LOG_LENGTH, is, 0);
        if (is[0] > 0) {
            chars = new byte[is[0]];
            gl.glGetProgramInfoLog(shaderProgram, is[0], is, 0, chars, 0);
            String s = new String(chars);
            mw.appendFragmentStatus(s);
            mw.appendVertexStatus(s);
        } else {
            mw.appendFragmentStatus("OK\n");
            mw.appendVertexStatus("OK\n");
        }
        mw.appendVertexStatus("Validate:\n");
        mw.appendFragmentStatus("Validate:\n");

        gl.glValidateProgram(shaderProgram);
        gl.glGetProgramiv(shaderProgram, GL_INFO_LOG_LENGTH, is, 0);
        if (is[0] > 0) {
            chars = new byte[is[0]];
            gl.glGetProgramInfoLog(shaderProgram, is[0], is, 0, chars, 0);
            String s = new String(chars);
            mw.appendFragmentStatus(s);
            mw.appendVertexStatus(s);
        } else {
            mw.appendFragmentStatus("OK");
            mw.appendVertexStatus("OK");
        }
    }
}
