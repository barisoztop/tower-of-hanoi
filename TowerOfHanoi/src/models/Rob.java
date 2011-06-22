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
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
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
        
        // Create Cylinder
        
        cylinder = new Cylinder(radius, height, app);
        this.radius=radius;
        this.radius=height;
        Transform3D tran = new Transform3D();
        tran.rotX(Math.PI/2);
        tran.setTranslation(new Vector3d(0.0,0.0,height/2));
        TransformGroup cylinderGroup = new TransformGroup(tran);
        cylinderGroup.addChild(cylinder);
        
        //Create shadow
        Transform3D trans = new Transform3D();
//        tran.rotX(Math.PI/2);
//        tran.setTranslation(new Vector3d(0.0,0.0,height/2));
        TransformGroup shadowGroup = new TransformGroup(trans);
        FakeShadow shadow = new FakeShadow((GeometryArray) getGeometry(), new Color3f(0.0f,0.0f,0.0f));
        
        shadowGroup.addChild(shadow);
        
        BranchGroup group = new BranchGroup();
        group.addChild(cylinderGroup);
        group.addChild(shadowGroup);
        
        
        
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
    
    
}
