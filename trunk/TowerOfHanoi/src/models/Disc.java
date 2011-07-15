/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.sun.j3d.utils.geometry.Cylinder;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Shape3D;
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
    
    private  TransformGroup GlobaltransGroup;
    private final Cylinder cylinder;
    private Transform3D trans;
    private TransformGroup group;

    public void setTransformGroup(TransformGroup group) {
        this.GlobaltransGroup = group;
    }
    
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
        
        BranchGroup bg = new BranchGroup();
        bg.setCapability(ALLOW_DETACH);
        bg.setCapability(ALLOW_CHILDREN_EXTEND);
        bg.setCapability(ALLOW_CHILDREN_WRITE);
        group = new TransformGroup(trans);
        
        group.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        group.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        group.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        
        cylinder = new Cylinder(radius,height,app);
        cylinder.getShape(Cylinder.BODY).setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        cylinder.getShape(Cylinder.BOTTOM).setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        cylinder.getShape(Cylinder.TOP).setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        
        group.addChild(cylinder);
        bg.addChild(group);
        
        GlobaltransGroup.addChild(bg);
        
        
        
        setCapability(ALLOW_DETACH);
        setCapability(ALLOW_CHILDREN_EXTEND);
        setCapability(ALLOW_CHILDREN_WRITE);
        addChild(GlobaltransGroup);
        
    }
    
    public void setAppearance(Appearance app)
    {
        cylinder.setAppearance(app);
    }
    
    public Appearance getAppearance()
    {
        return cylinder.getAppearance();
    }
    public void updatePosition(int order)
    {
        trans.setTranslation(new Vector3f(0.0f,0.0f,(height/2.0f)+(height*order)));
        group.setTransform(trans);
    }

    public float getRadius() 
    {
        return radius;
    }

    public float getHeight() 
    {
        return height;
    }

    public TransformGroup getTransformGroup() 
    {
        return GlobaltransGroup;
    }
    
    public TransformGroup getTransGroup()
    {
        return group;
    }
    
}
