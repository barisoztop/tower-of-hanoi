/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;
import models.Disc;
import models.Rob;
import models.Text3DApp;

/**
 *
 * @author gokhan
 */
public class FinalStateChecker extends Thread
{
    private Viewer viewer;
    private Rob rob1;
    private Rob rob3;
    private BranchGroup bgroup;
    private DistanceObserver dist;
    public FinalStateChecker(Rob start,Rob end,Viewer view,DistanceObserver dist) 
    {
        this.dist=dist;
        bgroup = new BranchGroup();
        this.rob1=start;
        this.rob3=end;
        this.viewer=view;
        Transform3D trans = new Transform3D();
        trans.setTranslation(new Vector3f(0.0f, 0.0f, -0.4f));
        Text3DApp text3DApp = new Text3DApp("Game Over");
        TransformGroup tgroup = new TransformGroup(trans);
        tgroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        tgroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        tgroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        tgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        bgroup.setCapability(BranchGroup.ALLOW_DETACH);
        bgroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        bgroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

        tgroup.addChild(text3DApp);
        bgroup.addChild(tgroup);
    }

    @Override
    public void run() 
    {
        while(true)
        {
            if(rob3.size()==3)
            {
                try
                {
                    
                    viewer.addObject(bgroup);
                    synchronized (dist)
                    {
                       
                    }
                    
                    Disc small = rob3.pop();
                    Disc medium = rob3.pop();
                    Disc big = rob3.pop();
                    
                    rob1.push(big);
                    rob1.push(medium);
                    rob1.push(small);
                    synchronized (dist)
                    {
                        dist.resume();   
                    }
                    
                    Thread.sleep(5000);
                    viewer.removeObject(bgroup);
                    
                    //Reset the game
                    
                }
                catch (Exception e)
                {
//                    viewer.removeObject(bgroup);
                    System.out.println(e);
                     dist.resume();
                }
                
            }
        }
    }
    
    
    
    
}
