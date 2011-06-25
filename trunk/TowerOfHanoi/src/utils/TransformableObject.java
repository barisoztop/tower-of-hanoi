package utils;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;

public class TransformableObject extends BranchGroup {

	protected TransformGroup transGroup;
	
	public TransformableObject() {
		transGroup = new TransformGroup();
		transGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
                transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
                transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
		addChild(transGroup);
                this.setCapability(ALLOW_DETACH);
                this.setCapability(ALLOW_CHILDREN_WRITE);
            this.setCapability(ALLOW_CHILDREN_EXTEND);
	}
	
	public TransformGroup getTransformGroup() {
		return transGroup;
	}

    public void setTransGroup(TransformGroup transGroup) {
        this.transGroup = transGroup;
    }
        
        
        
       
}
