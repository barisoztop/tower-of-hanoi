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
    private static final double THRESHOLD = 0.03;
    private boolean HASDISK = false;
    private int state;
    private final Transform3D offset3D = new Transform3D();
  
    
    
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
            Vector3d vecDirFirst = new Vector3d();
            Vector3d vecDirSecond = new Vector3d();
            Vector3d vecDirThird = new Vector3d();
            
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
                    //burda cursor ile rob aras›ndaki uzakl›€› hesapl›o
                    
                    vecDirFirst.sub(cursorVec, rob1Vec);
                    vecDirSecond.sub(cursorVec,rob2Vec);
                    vecDirThird.sub(cursorVec,rob3Vec);
                    //vecDir.sub(cursorVec,rob3Vec);
                    
                     //Buralar falanda
                    
                    //e€er belli threasholddan küçükse //al›cak
                    if((vecDirFirst.length() < THRESHOLD) && vecDirFirst.length() != 0)// || (vecDirSecond.length() < THRESHOLD) || (vecDirThird.length() < THRESHOLD))
                    {
                        if(HASDISK == false)
                        {
                           //buray› doldurucaks›n

                        	//cursor selectArrow methodu renk de€iﬂtirtio true verirsen k›rm›z› false verirsen mavi oluo galiba
                            cursor.selectArrow(true);
                            HASDISK=true;
                            Thread.sleep(1000);
                            if(tempDisc == null)
                            {
                            	tempDisc  = rob1.pop();
                            	cursor.getTransformGroup().addChild(tempDisc);
                            	//cursor.addChild(tempDisc);
                 
                            	System.out.println("Here");
                				//cursor.getTransformGroup().setTransform(targetTransform3D);  
                            	
                            }else
                            {

                            	cursor.getTransformGroup().removeChild(tempDisc);
                            	rob1.push(tempDisc);
                            	tempDisc = null;
                            } 
                        }             
                    }
                    else if((vecDirSecond.length() < THRESHOLD)&& vecDirSecond.length() != 0)
                    {
                    	if(HASDISK == false)
                        {
                           //buray› doldurucaks›n

                        	//cursor selectArrow methodu renk de€iﬂtirtio true verirsen k›rm›z› false verirsen mavi oluo galiba
                            cursor.selectArrow(true);
                            HASDISK=true;
                            Thread.sleep(1000);
                            if(tempDisc == null)
                            {
                            	tempDisc  = rob2.pop();
                            	cursor.getTransformGroup().addChild(tempDisc);
                            }else
                            {
                            	cursor.getTransformGroup().removeChild(tempDisc);
                            	rob2.push(tempDisc);
                            	
                            	tempDisc = null;
                            } 
                        }
                    }
                    else if((vecDirThird.length() < THRESHOLD)&& vecDirThird.length() != 0)
                    {
                    	if(HASDISK == false)
                        {
                            cursor.selectArrow(true);
                            HASDISK=true;
                            Thread.sleep(1000);
                            if(tempDisc == null)
                            {
                            	tempDisc  = rob3.pop();
                            	cursor.getTransformGroup().addChild(tempDisc);
                            }else
                            {
                            	cursor.getTransformGroup().removeChild(tempDisc);
                            	rob3.push(tempDisc);                     
                            	tempDisc = null;
                            } 
                        }
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
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
}
