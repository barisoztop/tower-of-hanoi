/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.media.j3d.Transform3D;
import javax.vecmath.Vector3d;
import models.Rob;
import models.SwitchArrow;

/**
 *
 * @author gokhan
 */
public class DistanceObserver extends Thread
{
    private Rob rob1;
    private Rob rob2;
    private Rob rob3;
    private SwitchArrow cursor;
    private static final double THRESHOLD = 0.03f;
    
    
    
    
    
    public DistanceObserver(Rob rob1, Rob rob2, Rob rob3, SwitchArrow cursor) 
    {
        this.rob1 = rob1;
        this.rob2 = rob2;
        this.rob3 = rob3;
        this.cursor = cursor;
    }
    
    
    
    
    
    
    
    
    

    @Override
    public void run() 
    {
        
        Vector3d vecDir = new Vector3d(); 
        while(true)
        {
            
            Transform3D cursorTrans=null,rob1Trans=null,rob2Trans=null,rob3Trans=null;
            rob1.peek().getTransformGroup().getTransform(rob1Trans);
            rob2.peek().getTransformGroup().getTransform(rob2Trans);
            rob3.peek().getTransformGroup().getTransform(rob3Trans);
            cursor.getTransformGroup().getTransform(cursorTrans);
            
            if((cursorTrans!=null) && (rob1Trans!=null))
            {
                Vector3d rob1Vec = new Vector3d();
                Vector3d cursorVec = new Vector3d();
                cursorTrans.get(cursorVec);
                rob1Trans.get(rob1Vec);
                vecDir.sub(cursorVec, rob1Vec);
                
                
                if(vecDir.length()< THRESHOLD)
                {
                    cursor.selectArrow(true);
                }
                else
                {
                    cursor.selectArrow(false);
                }
                
                
                
                
                
            }
        }
    
    }
    
}
