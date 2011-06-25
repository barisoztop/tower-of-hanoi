/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.media.j3d.Switch;
import javax.media.j3d.TransformGroup;
import utils.TransformableObject;

/**
 *
 * @author gokhan
 */
public class SwitchArrow extends TransformableObject
{
    private Switch arrowSwitch;

    private RedArrow red;
    private BlueArrow blue;

    public RedArrow getRed() {
        return red;
    }

    public BlueArrow getBlue() {
        return blue;
    }
    
    public SwitchArrow(BlueArrow barr,RedArrow rarr) 
    {
        blue=barr;
        red = rarr;
        TransformGroup group1 = new TransformGroup();
        TransformGroup group2 = new TransformGroup();
        
        group1.addChild(barr);
        group2.addChild(rarr);
        arrowSwitch= new Switch();
        arrowSwitch.setCapability(Switch.ALLOW_SWITCH_WRITE);
        arrowSwitch.addChild(group1);
        arrowSwitch.addChild(group2);
        transGroup.addChild(arrowSwitch);
    }
    public void selectArrow(boolean status)
    {
        if (status==false)
            arrowSwitch.setWhichChild(0);
        else
            arrowSwitch.setWhichChild(1);
    }
    
}
