/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.sun.j3d.utils.geometry.Cylinder;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;

/**
 *
 * @author gokhan
 */
public class Disc extends BranchGroup
{

    private float radius;
    private float height;
    
    private final TransformGroup transGroup;
    private final Cylinder cylinder;
    
    public Disc()
    {
        transGroup = new TransformGroup();
        cylinder = new Cylinder();
    }
    
    
}
