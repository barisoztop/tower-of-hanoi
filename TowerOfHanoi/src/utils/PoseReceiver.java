package utils;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;


import ubitrack.SimplePose;
import ubitrack.SimplePoseReceiver;

public class PoseReceiver extends SimplePoseReceiver 
{
    protected TransformGroup markerTransGroup = null;
    protected Vector3d transVec;
    protected Quat4d rotQ;

    public Quat4d getRotation() 
    {
        return rotQ;
    }

    public Vector3d getTransition() 
    {
        return transVec;
    }
        
    public void setTransformGroup(TransformGroup markerTransGroup) 
    {
		this.markerTransGroup = markerTransGroup;
    }

    public TransformGroup getMarkerTransGroup() 
    {
        return markerTransGroup;
    }
    
    @Override
    public void receivePose(SimplePose pose) 
    {
        if (markerTransGroup == null)
        {
                return;
        }
        transVec = new Vector3d(pose.getTx(), pose.getTy(), pose.getTz());
        rotQ = new Quat4d(pose.getRx(), pose.getRy(), pose.getRz(), pose.getRw());

        Transform3D markerTransform = new Transform3D();
        markerTransform.set(rotQ, transVec, 1);
        markerTransGroup.setTransform(markerTransform);
        
        
    }
}
