/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package towerofhanoi;

import models.BlueAppearance;
import models.BlueArrow;
import models.Disc;
import models.GreenAppereance;
import models.GreyAppearance;
import models.RedAppearance;
import models.RedArrow;
import models.Rob;
import models.SwitchArrow;
import utils.AdvancedViewer;
import utils.BackgroundObject;
import utils.ImageReceiver;
import utils.PoseReceiver;
import utils.UbitrackFacade;

/**
 *
 * @author gokhan
 */
public class TowerOfHanoi 
{
    public static final String TowerOfHanoiTitle = "Tower Of Hanoi";
    private UbitrackFacade ubitrackFacade;
    private PoseReceiver rodPoseReceiver1;
    private PoseReceiver rodPoseReceiver2;
    private PoseReceiver rodPoseReceiver3;
    private PoseReceiver cursorPoseReceiver;
    private ImageReceiver imageReceiver;
    private AdvancedViewer viewer;
    private RedArrow redArrow;
    private SwitchArrow switchArrow;
    private BlueArrow blueArrow;
    private Disc SMALL_DISC;
    private Disc MEDIUM_DISC;
    private Disc LARGE_DISC;
    public static final float DISK_HEIGHT =0.005f; 
    
    public static final float LARGE_DISK_RADIUS =0.03f;
    public static final float MEDIUM_DISC_RADIUS =0.02f;
    public static final float SMALL_DISK_RADIUS =0.01f;
    private Rob rob1;
    private Rob rob2;
    private Rob rob3;
    
    public TowerOfHanoi() 
    {
        LARGE_DISC =new Disc(LARGE_DISK_RADIUS,DISK_HEIGHT,0, new GreenAppereance());
        MEDIUM_DISC = new Disc(MEDIUM_DISC_RADIUS, DISK_HEIGHT, 1, new RedAppearance());
        SMALL_DISC = new Disc(SMALL_DISK_RADIUS, DISK_HEIGHT, 2, new BlueAppearance());
        
        redArrow =new RedArrow();
        blueArrow = new BlueArrow();
        switchArrow = new SwitchArrow(blueArrow, redArrow);
        
        GreyAppearance greyApp  = new GreyAppearance();
        rob1 = new Rob(0.005f, 0.1f, greyApp); 
        rob2 = new Rob(0.005f, 0.1f, greyApp); 
        rob3 = new Rob(0.005f, 0.1f, greyApp);
//        
        
        //Ubitrack initilization
        ubitrackFacade = new UbitrackFacade();
        viewer = new AdvancedViewer(TowerOfHanoiTitle, ubitrackFacade);
        ubitrackFacade.initUbitrack();

        rodPoseReceiver1 = new PoseReceiver();
        if (!ubitrackFacade.setPoseCallback("posesink", rodPoseReceiver1)) 
        {
                return;
        }
        rodPoseReceiver2 = new PoseReceiver();
        if (!ubitrackFacade.setPoseCallback("posesink2", rodPoseReceiver2)) 
        {
                return;
        }
        rodPoseReceiver3 = new PoseReceiver();
        if (!ubitrackFacade.setPoseCallback("posesink3", rodPoseReceiver3)) 
        {
                return;
        }
        cursorPoseReceiver = new PoseReceiver();
        if (!ubitrackFacade.setPoseCallback("posesink4", cursorPoseReceiver)) 
        {
                return;
        }
        
        imageReceiver = new ImageReceiver();
        if (!ubitrackFacade.setImageCallback("imgsink", imageReceiver)) 
        {
                return;
        }
        ubitrackFacade.startDataflow();
        
        BackgroundObject backgroundObject = new BackgroundObject();        
        viewer.addObject(backgroundObject);
        imageReceiver.setBackground(backgroundObject.getBackground());
        
        viewer.addObject(rob1);
        viewer.addObject(rob2);
        viewer.addObject(rob3);
        viewer.addObject(switchArrow);
       
        
        rodPoseReceiver1.setTransformGroup(rob1.getTransformGroup());
        rodPoseReceiver2.setTransformGroup(rob2.getTransformGroup());
        rodPoseReceiver3.setTransformGroup(rob3.getTransformGroup());
        
        cursorPoseReceiver.setTransformGroup(switchArrow.getTransformGroup());
        
    }

    public PoseReceiver getRodPoseReceiver1() 
    {
        return rodPoseReceiver1;
    }

    public PoseReceiver getRodPoseReceiver2() 
    {
        return rodPoseReceiver2;
    }

    public PoseReceiver getRodPoseReceiver3() 
    {
        return rodPoseReceiver3;
    }

    public PoseReceiver getCursorPoseReceiver() 
    {
        return cursorPoseReceiver;
    }
    
    
    
    

   
}
