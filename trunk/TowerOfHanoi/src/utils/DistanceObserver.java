/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * @author nevzat and gokhan
 */
package utils;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import models.Disc;
import models.Rob;
import models.SwitchArrow;


public class DistanceObserver extends Thread
{
    private final static int ROB1 =2;
    private final static int ROB2 =3;
    private final static int ROB3 =4;
    
    private static Disc tempDisc = null;
    
    private Rob rob1;
    private Rob rob2;
    private Rob rob3;
    private SwitchArrow cursor;
    private static final double THRESHOLD = 0.05;
    private boolean HASDISK = false;
  
    
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
            Transform3D relativeTrans = new Transform3D();
            Transform3D cursorTrans=new Transform3D();
            Transform3D rob1Trans=new Transform3D();
            Transform3D rob2Trans=new Transform3D();
            Transform3D rob3Trans=new Transform3D();
            Vector3d vecDirFirst = new Vector3d();
            Vector3d vecDirSecond = new Vector3d();
            Vector3d vecDirThird = new Vector3d();
            TransformGroup tempTransgroup = new TransformGroup();
            BranchGroup tempBranchgroup =new BranchGroup();
            
            tempTransgroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
            tempTransgroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
            tempTransgroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
            tempTransgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            tempBranchgroup.setCapability(BranchGroup.ALLOW_DETACH);
            tempBranchgroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
            tempBranchgroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
            
            
            while(true)
            {   
                rob1.getTransformGroup().getTransform(rob1Trans);
                rob2.getTransformGroup().getTransform(rob2Trans);
                rob3.getTransformGroup().getTransform(rob3Trans);
                cursor.getTransformGroup().getTransform(cursorTrans);

                if((cursorTrans!=null) && (rob1Trans!=null) && (rob2Trans!=null)&& (rob3Trans!=null))
                {
                    Vector3d rob1Vec = new Vector3d();
                    Vector3d rob2Vec = new Vector3d();
                    Vector3d rob3Vec = new Vector3d();
                    Vector3d cursorVec = new Vector3d();
                    
                    cursorTrans.get(cursorVec);
                    rob1Trans.get(rob1Vec);
                    rob2Trans.get(rob2Vec);
                    rob3Trans.get(rob3Vec);
                    
                    vecDirFirst.sub(cursorVec, rob1Vec);
                    vecDirSecond.sub(cursorVec,rob2Vec);
                    vecDirThird.sub(cursorVec,rob3Vec);
                    
                    if((vecDirFirst.length() < THRESHOLD) && vecDirFirst.length() != 0)
                    {
                        if(HASDISK == false)
                        {
                            tempDisc  = rob1.pop();
                            if(tempDisc == null)
                                continue;
                            
                            relativeTrans.invert(cursorTrans);
                            relativeTrans.mul(relativeTrans,rob1Trans);
                            tempTransgroup.setTransform(relativeTrans);
                            
                            tempTransgroup.addChild(tempDisc);
                                                       
                            tempBranchgroup.addChild(tempTransgroup);
                            
                            cursor.selectArrow(true);
                            cursor.getTransformGroup().addChild(tempBranchgroup);
                            HASDISK=true;
//                           
                            Thread.sleep(2000);                            
                        }   
//                        else
//                        {
//                            HASDISK=false;
//                            
//                            //REMOVE disk from temporary branch group
//                            cursor.getTransformGroup().removeChild(tempBranchgroup);
//                            tempBranchgroup.removeChild(tempTransgroup);
//                            tempTransgroup.removeChild(tempDisc);
//                            
//                            
//                            cursor.selectArrow(false);
//                            rob1.push(tempDisc);
//                            Thread.sleep(2000);
//                        } 
                    }
//                    else if((vecDirSecond.length() < THRESHOLD)&& vecDirSecond.length() != 0)
//                    {
//                    	if(HASDISK == false)
//                        {
//                            cursor.selectArrow(true);
//                            HASDISK=true;
//                            tempDisc  = rob2.pop();
//                            if(tempDisc == null)
//                                continue;
//                            cursor.getTransformGroup().addChild(tempDisc);
//                            Thread.sleep(1000);
//                           
//                        }
//                        else
//                        {
//                            cursor.selectArrow(false);
//                            cursor.getTransformGroup().removeChild(tempDisc);
//                            rob2.push(tempDisc);
//                            HASDISK=false;
//                            Thread.sleep(1000);
//                        }
//                    }
//                    else if((vecDirThird.length() < THRESHOLD)&& vecDirThird.length() != 0)
//                    {
//                    	if(HASDISK == false)
//                        {
//                            cursor.selectArrow(true);
//                            HASDISK=true;
//                            tempDisc  = rob3.pop();
//                            if(tempDisc == null)
//                                continue;
//                            cursor.getTransformGroup().addChild(tempDisc);
//                            Thread.sleep(1000);
//                        }
//                        else
//                        {
//                            cursor.selectArrow(false);
//                            cursor.getTransformGroup().removeChild(tempDisc);
//                            rob3.push(tempDisc);
//                            HASDISK=false;
//                            Thread.sleep(1000);
//                        }
//                    }
                       
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.exit(0);
        }
    }
    
}