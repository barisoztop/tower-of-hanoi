/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.media.j3d.Transform3D;
import javax.vecmath.Vector3d;
import models.Disc;
import models.Rob;
import models.SwitchArrow;

/**
 *
 * @author gokhan
 */
public class DistanceObserver extends Thread
{
    private final static int ROB1 =2;
    private final static int ROB2 =3;
    private final static int ROB3 =4;
    
    private Rob rob1;
    private Rob rob2;
    private Rob rob3;
    private SwitchArrow cursor;
    private static final double THRESHOLD = 0.05;
    private boolean HASDISK = false;
    private int state;
    
    
    
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
        try
        {
        
            Transform3D cursorTrans=new Transform3D();
            Transform3D rob1Trans=new Transform3D();
            Transform3D rob2Trans=new Transform3D();
            Transform3D rob3Trans=new Transform3D();
            Vector3d vecDir = new Vector3d(); 
            while(true)
            {   
                rob1.getTransformGroup().getTransform(rob1Trans);
                rob2.getTransformGroup().getTransform(rob2Trans);
                rob3.getTransformGroup().getTransform(rob3Trans);
                cursor.getTransformGroup().getTransform(cursorTrans);

                if((cursorTrans!=null) && (rob1Trans!=null))
                {
                    Vector3d rob1Vec = new Vector3d();
                    Vector3d cursorVec = new Vector3d();
                    cursorTrans.get(cursorVec);
                    rob1Trans.get(rob1Vec);
                    vecDir.sub(cursorVec, rob1Vec);

                    if(vecDir.length() < THRESHOLD)
                    {
                        if(HASDISK == false)
                        {
                            //pop the disc 
//                            Disc disc = rob1.pop();
                            
                            //Calculate relative position
//                            Transform3D relative = new Transform3D();
//                            relative.mulInverse(rob1Trans,cursorTrans);
                            
                            
                            
                            
                            cursor.selectArrow(true);
                            HASDISK=true;
                            Thread.sleep(1000);
                            
                        }
                        else
                        {
                            cursor.selectArrow(false);
                            HASDISK=false;
                            Thread.sleep(1000);
                        }
                        
                        
                        
                    }
                    
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
}
