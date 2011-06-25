/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.sun.j3d.utils.geometry.Cylinder;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

/**
 *
 * @author gokhan
 */
public class Disc extends BranchGroup
{

    private float radius;
    private float height;
    
    private final TransformGroup GlobaltransGroup;
    private final Cylinder cylinder;
    private Transform3D trans;
    private TransformGroup group;
    
    public Disc(float radius, float height,int order ,Appearance app)
    {
        this.radius=radius;
        this.height=height;
        
        
        
        GlobaltransGroup = new TransformGroup();
        GlobaltransGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        GlobaltransGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        GlobaltransGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        GlobaltransGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        
        
        
        trans = new Transform3D();
        trans.rotX(Math.PI/2.0);
        trans.setTranslation(new Vector3f(0.0f,0.0f,(height/2.0f)+(height*order)));
        group = new TransformGroup(trans);
        cylinder = new Cylinder(radius,height,app);
        group.addChild(cylinder);
        
        
        GlobaltransGroup.addChild(group);
        
        
        
        
        setCapability(ALLOW_DETACH);
        addChild(GlobaltransGroup);
    }
    
    
    public void updatePosition(int order)
    {
        trans.setTranslation(new Vector3f(0.0f,0.0f,(height/2.0f)+(height*order)));
        group.setTransform(trans);
    }

    public float getRadius() {
        return radius;
    }

    public float getHeight() {
        return height;
    }

    public TransformGroup getTransformGroup() {
        return GlobaltransGroup;
    }
    
    
    
    
    
}
