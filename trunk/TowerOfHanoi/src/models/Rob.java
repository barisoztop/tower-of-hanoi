/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.sun.j3d.utils.geometry.Cylinder;
import java.util.Stack;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Geometry;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

/**
 *
 * @author gokhan
 */
public class Rob extends BranchGroup
{
    private Stack<Disc> stack;
    private float radius;
    private float height;
    
    private final TransformGroup transGroup;
    private final Cylinder cylinder;
    public Rob(float radius,float height,Appearance app) 
    {
        stack = new Stack<Disc>();
        transGroup = new TransformGroup();
        transGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        
        // Create your Shape
        cylinder = new Cylinder(radius, height, app);
        this.radius=radius;
        this.radius=height;
        
        //translation
        Transform3D trans = new Transform3D();
        trans.rotX(Math.PI/2.0);
//        trans.setTranslation(new Vector3f(1.0f, 0.0f, height/2.0f));
        transGroup.setTransform(trans);
        
        
        transGroup.addChild(cylinder);
        setCapability(ALLOW_CHILDREN_EXTEND);
        setCapability(ALLOW_CHILDREN_WRITE);
        setCapability(ALLOW_DETACH);
        setCapability(ALLOW_CHILDREN_READ);
        addChild(transGroup);
        
    }

    public Stack<Disc> getStack() 
    {
        return stack;
    }
    
    public TransformGroup getTransformGroup() 
    {
        return transGroup;
    }

    public Geometry getGeometry() 
    {
        return cylinder.getShape(0).getGeometry();
    }
    
    
}
