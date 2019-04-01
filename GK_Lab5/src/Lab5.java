
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

/**
 * CPSC 424, Fall 2015, Lab 4:  Some objects in 3D.  The arrow keys
 * can be used to rotate the object.  The number keys 1 through 6
 * select the object.  The space bar toggles the use of anaglyph
 * stereo.
 */
class Lab5 extends GLJPanel implements GLEventListener, KeyListener{

    /**
     * A main routine to create and show a window that contains a
     * panel of type Lab5.  The program ends when the user closes the
     * window.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Some Objects in 3D");
        Lab5 panel = new Lab5();
        window.setContentPane(panel);
        window.pack();
        window.setResizable(false);
        window.setLocation(50,50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    
    public Lab5() {
        super( new GLCapabilities(null) ); // Makes a panel with default OpenGL "capabilities".
        setPreferredSize( new Dimension(700,700) );
        addGLEventListener(this); // This panel will respond to OpenGL events.
        addKeyListener(this);  // The panel will respond to key events.
    }


    //------------------- TODO: Complete this section! ---------------------

    private int objectNumber = 2;        // Which object to draw (1 ,2, 3, 4, 5, or 6)?
    //   (Controlled by number keys.)

    private boolean useAnaglyph = false; // Should anaglyph stereo be used?
    //    (Controlled by space bar.)

    private int rotateX = 0;    // Rotations of the cube about the axes.
    private int rotateY = 0;    //   (Controlled by arrow, PageUp, PageDown keys;
    private int rotateZ = 0;    //    Home key sets all rotations to 0.)

    private GLUT glut = new GLUT(); // An object for drawing GLUT shapes.

    /*--------------variable---------------------*/
    private GLU glu = new GLU();
    private double radius = 5;
    private double angle = 0;
    private float x=0,z=0;
    /*--------------variable---------------------*/

    /**
     * The method that draws the current object, with its modeling transformation.
     */
    private void draw(GL2 gl2) {

        gl2.glRotatef(rotateZ,0,0,1);   // Apply rotations to complete object.
        gl2.glRotatef(rotateY,0,1,0);
        gl2.glRotatef(rotateX,1,0,0);

        // TODO: Draw the currently selected object, number 1, 2, 3, 4, 5, or 6.
        // (Objects should lie in the cube with x, y, and z coordinates in the
        // range -5 to 5.)
        if (objectNumber ==1) {             //korkoci¹g

            Corksrew(gl2,4,12);

        }
        else if (objectNumber == 2){        //piramida
            PiramidBaseOnShape(gl2,5);
        }

    }

    public void Corksrew(GL2 gl2,int radius, int numberOfTurns){
        gl2.glClear(GL2.GL_COLOR_BUFFER_BIT);

        int numVertices = numberOfTurns*100;
        double angleIncrement = numberOfTurns * Math.PI / numVertices;
        float height=0;

        gl2.glBegin(GL2.GL_POLYGON);
        for (int i = 1; i <= numVertices; i++) {
            angle = i * angleIncrement * Math.PI;
            gl2.glColor3f(x/0.6f,(x+z)*x/0.4f,z/0.5f);
            x = (float) (radius * Math.cos(angle));
            z = (float) (radius * Math.sin(angle));
            gl2.glVertex3f(x, height, z);
            height+=numberOfTurns/3000f;
        }
        gl2.glEnd();

        gl2.glTranslated(0,height,-4.5);
        glu.gluCylinder(glu.gluNewQuadric(),1.3,1.3,9,100,50);
    }

    public void Polygon(GL2 gl2,int numVertices){
        gl2.glClear(GL2.GL_COLOR_BUFFER_BIT);

        gl2.glBegin(GL2.GL_POLYGON);
        double angleIncrement = 2 * Math.PI / numVertices;
        for (int i = 1; i <= numVertices; i++) {
            gl2.glColor3f(x/0.6f,(x+z)*x/0.4f,z/0.5f);
            x = (float) (radius * Math.cos(angle));
            z = (float) (radius * Math.sin(angle));
            gl2.glVertex3f(x, -2.0f, z);
            angle = i * angleIncrement;
        }
        gl2.glEnd();
    }

    public void Triangle(GL2 gl2){
        gl2.glBegin(  GL2.GL_TRIANGLES );
        gl2.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl2.glVertex3f( -1.45f, -2.0f, 0.0f );
        gl2.glColor3f( 0.0f, -1.3f, 0.0f );  // Green
        gl2.glVertex3f( 0.0f, 5.0f, -4.85f );
        gl2.glColor3f( 1.0f, 0.65f, 0.80f );  // Purple
        gl2.glVertex3f( 1.45f, -2.0f, 0.0f );

        gl2.glEnd();
    }

    public void PiramidBaseOnShape(GL2 gl2,double size){
        gl2.glBegin(gl2.GL_TRIANGLE_FAN);
        int n = 12;
        gl2.glVertex3f(0,6,0);
        gl2.glVertex3f(1,0,0);
        for ( int i = 0; i<n;i++)
        {
            gl2.glColor3f(0.0f,0.0f,1.0f);
            gl2.glVertex3f((float) (Math.cos(i*2*Math.PI/n)*size), 0, (float) (Math.sin(i*2*Math.PI/n)*size));
            gl2.glColor3f(0.0f, 1.0f,0.0f);
            gl2.glVertex3f((float) (Math.cos((i+1)*2*Math.PI/n)*size), 0, (float) (Math.sin((i+1)*2*Math.PI/n)*size));
        }
        gl2.glEnd();
        gl2.glBegin(gl2.GL_POLYGON);
        gl2.glVertex3f(0,0,0);
        for (int i=0; i<n;i++)
        {
            gl2.glColor3f(1.0f,0.0f,1.0f);
            gl2.glVertex3f((float) (Math.cos(i*2*Math.PI/n)*size), 0, (float) (Math.sin(i*2*Math.PI/n)*size));
            gl2.glColor3f(0.0f,0.0f,1.0f);
            gl2.glVertex3f((float) (Math.cos((i+1)*2*Math.PI/n)*size), 0, (float) (Math.sin((i+1)*2*Math.PI/n)*size));
        }
        gl2.glEnd();
    }

    //-------------------- Draw the Scene  -------------------------

    /**
     * The display method is called when the panel needs to be drawn.
     * It's called when the window opens and it is called by the keyPressed
     * method when the user hits a key that modifies the scene.
     */
    public void display(GLAutoDrawable drawable) {

        GL2 gl2 = drawable.getGL().getGL2(); // The object that contains all the OpenGL methods.

        if (useAnaglyph) {
            gl2.glDisable(GL2.GL_COLOR_MATERIAL); // in anaglyph mode, everything is drawn in white
            gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, new float[]{1,1,1,1}, 0);
        }
        else {
            gl2.glEnable(GL2.GL_COLOR_MATERIAL);  // in non-anaglyph mode, glColor* is respected
        }
        gl2.glNormal3f(0,0,1); // (Make sure normal vector is correct for object 1.)

        gl2.glClearColor( 0, 0, 0, 1 ); // Background color (black).
        gl2.glClear( GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );


        if (useAnaglyph == false) {
            gl2.glLoadIdentity(); // Make sure we start with no transformation!
            gl2.glTranslated(0,0,-15);  // Move object away from viewer (at (0,0,0)).
            draw(gl2);
        }
        else {
            gl2.glLoadIdentity(); // Make sure we start with no transformation!
            gl2.glColorMask(true, false, false, true);
            gl2.glRotatef(4,0,1,0);
            gl2.glTranslated(1,0,-15);
            draw(gl2);  // draw the current object!
            gl2.glColorMask(true, false, false, true);
            gl2.glClear(GL2.GL_DEPTH_BUFFER_BIT);
            gl2.glLoadIdentity();
            gl2.glRotatef(-4,0,1,0);
            gl2.glTranslated(-1,0,-15);
            gl2.glColorMask(false, true, true, true);
            draw(gl2);
            gl2.glColorMask(true, true, true, true);
        }

    } // end display()

    /** The init method is called once, before the window is opened, to initialize
     *  OpenGL.  Here, it sets up a projection, turns on some lighting, and enables
     *  the depth test.
     */
    public void init(GLAutoDrawable drawable) {
        GL2 gl2 = drawable.getGL().getGL2();
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glFrustum(-3.5, 3.5, -3.5, 3.5, 5, 25);
        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        gl2.glEnable(GL2.GL_LIGHTING);
        gl2.glEnable(GL2.GL_LIGHT0);
        gl2.glLightfv(GL2.GL_LIGHT0,GL2.GL_DIFFUSE,new float[] {0.7f,0.7f,0.7f},0);
        gl2.glLightModeli(GL2.GL_LIGHT_MODEL_TWO_SIDE, 1);
        gl2.glEnable(GL2.GL_DEPTH_TEST);
        gl2.glLineWidth(3);  // make wide lines for the stellated dodecahedron.
    }

    public void dispose(GLAutoDrawable drawable) {
        // called when the panel is being disposed
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // called when user resizes the window
    }

    // ----------------  Methods from the KeyListener interface --------------

    /**
     * Responds to keypressed events.  The four arrow keys control the rotations
     * about the x- and y-axes.  The PageUp and PageDown keys control the rotation
     * about the z-axis.  The Home key resets all rotations to zero.  The number
     * keys 1, 2, 3, 4, 5, and 6 select the current object number.  Pressing the space
     * bar toggles anaglyph stereo on and off.  The panel is redrawn to reflect the
     * change.
     */
    public void keyPressed(KeyEvent evt) {
        int key = evt.getKeyCode();
        boolean repaint = true;
        if ( key == KeyEvent.VK_LEFT )
            rotateY -= 6;
        else if ( key == KeyEvent.VK_RIGHT )
            rotateY += 6;
        else if ( key == KeyEvent.VK_DOWN)
            rotateX += 6;
        else if ( key == KeyEvent.VK_UP )
            rotateX -= 6;
        else if ( key == KeyEvent.VK_PAGE_UP )
            rotateZ += 6;
        else if ( key == KeyEvent.VK_PAGE_DOWN )
            rotateZ -= 6;
        else if ( key == KeyEvent.VK_HOME )
            rotateX = rotateY = rotateZ = 0;
        else if (key == KeyEvent.VK_1)
            objectNumber = 1;
        else if (key == KeyEvent.VK_2)
            objectNumber = 2;
        else if (key == KeyEvent.VK_3)
            objectNumber = 3;
        else if (key == KeyEvent.VK_4)
            objectNumber = 4;
        else if (key == KeyEvent.VK_5)
            objectNumber = 5;
        else if (key == KeyEvent.VK_6)
            objectNumber = 6;
        else if (key == KeyEvent.VK_SPACE)
            useAnaglyph = ! useAnaglyph;
        else
            repaint = false;
        if (repaint)
            repaint();
    }

    public void keyReleased(KeyEvent evt) {
    }

    public void keyTyped(KeyEvent evt) {
    }

}
