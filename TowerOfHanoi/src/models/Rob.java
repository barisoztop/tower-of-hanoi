/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.sun.j3d.utils.geometry.Cylinder;
import java.awt.Color;
import java.util.Stack;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

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
        
        //Create Rectangle for fancy look
        TransformGroup rectangleGroup = new TransformGroup();
        QuadArray rect = new QuadArray (4, QuadArray.COORDINATES|QuadArray.COLOR_3);

        rect.setCoordinate (0, new Point3f (-0.05f, -0.04f, -0.0005f));
    	rect.setCoordinate (1, new Point3f (0.05f, -0.04f,-0.0005f));
    	rect.setCoordinate (2, new Point3f (0.05f, 0.04f,-0.0005f));
    	rect.setCoordinate (3, new Point3f (-0.05f, 0.04f, -0.0005f));
        rect.setColor(0,new Color3f(0.3f, 0.3f, 0.8f));
        rect.setColor(1,new Color3f(0.3f, 0.3f, 0.8f));
        rect.setColor(2,new Color3f(0.3f, 0.3f, 0.8f));
        rect.setColor(3,new Color3f(0.3f, 0.3f, 0.8f));
        
        rectangleGroup.addChild(new Shape3D(rect));
        
        // Create Cylinder
        cylinder = new Cylinder(radius,height,app);
        this.radius=radius;
        this.radius=height;
        Transform3D transCylinder = new Transform3D();
        transCylinder.rotX(Math.PI/2);
        transCylinder.setTranslation(new Vector3d(0.0,0.0,(height/2.0)));
        TransformGroup cylinderGroup = new TransformGroup(transCylinder);
        cylinderGroup.addChild(cylinder);
        
        BranchGroup group = new BranchGroup();
        group.setCapability(ALLOW_CHILDREN_WRITE);
        group.setCapability(ALLOW_DETACH);
        group.setCapability(ALLOW_CHILDREN_EXTEND);
        group.setCapability(ALLOW_CHILDREN_READ);
        
        group.addChild(cylinderGroup);
        group.addChild(rectangleGroup);
        
        
        transGroup.addChild(group);
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

    public float getRadius() 
    {
        return radius;
    }

    public float getHeight() 
    {
        return height;
    }
    
    
}
