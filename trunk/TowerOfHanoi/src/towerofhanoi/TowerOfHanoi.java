/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package towerofhanoi;

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
    public static final String TowerOfHanoiTitle = "Exercise 4";
    private UbitrackFacade ubitrackFacade;
    private PoseReceiver rodPoseReceiver1;
    private PoseReceiver rodPoseReceiver2;
    private PoseReceiver rodPoseReceiver3;
    private PoseReceiver cursorPoseReceiver;
    private ImageReceiver imageReceiver;
    private AdvancedViewer viewer;
    
    
    public TowerOfHanoi() 
    {
        
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
        imageReceiver = new ImageReceiver();
        if (!ubitrackFacade.setImageCallback("imgsink", imageReceiver)) 
        {
                return;
        }
        ubitrackFacade.startDataflow();
        
        BackgroundObject backgroundObject = new BackgroundObject();        
        viewer.addObject(backgroundObject);
        imageReceiver.setBackground(backgroundObject.getBackground());
        
        rodPoseReceiver1.setTransformGroup(null);
        rodPoseReceiver2.setTransformGroup(null);
        rodPoseReceiver3.setTransformGroup(null);
        
    }
    
    
    

   
}
