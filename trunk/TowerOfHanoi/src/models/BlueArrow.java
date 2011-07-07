/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

/**
 *
 * @author gokhan
 */
public class BlueArrow extends BranchGroup
{
    private final TransformGroup transGroup;
    private Cylinder _cylinder;
    private Cone _cone;
    public BlueArrow() 
    {
        transGroup = new TransformGroup();
        
        transGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        
        BlueAppearance app = new BlueAppearance();
        _cone = new Cone(0.01f, 0.02f,app);
        _cylinder = new Cylinder(0.003f, 0.03f,app);
        
        
        Transform3D  coneTransform= new Transform3D();
//        coneTransform.rotX(Math.PI/2.0d);
        coneTransform.setTranslation(new Vector3d(0, 0.020, 0.010));
        
        TransformGroup coneTransformGroup = new TransformGroup(coneTransform);
        coneTransformGroup.addChild(_cone);
        
        Transform3D  cylinderTransform= new Transform3D();
//        cylinderTransform.rotX(Math.PI/2.0d);
        cylinderTransform.setTranslation(new Vector3d(0, 0, 0.010));
        
        TransformGroup cylinderTransformGroup = new TransformGroup(cylinderTransform);
        cylinderTransformGroup.addChild(_cylinder);
        
        transGroup.addChild(coneTransformGroup);
        transGroup.addChild(cylinderTransformGroup);
        addChild(transGroup);
        
    }
    public TransformGroup getTransformGroup() 
    {
            return transGroup;
    }

}
