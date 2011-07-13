package models;

import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

public class Animation3DText extends BranchGroup{

	public Animation3DText(BranchGroup targetObject)
	{
		TransformGroup targetTransformGroup = new TransformGroup();
		targetTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		targetTransformGroup.addChild(targetObject);
		addChild(targetTransformGroup);
		
		//Rotation interpolation (default) around y-axis from 0 to 2xPi radians
		Alpha a = new Alpha(-1, 3000); //-1=infinity and a rotation takes 3000ms

		Transform3D rotationTransform = new Transform3D();
		Vector3f vectorRotate = new Vector3f(1, 0 ,0);
		rotationTransform.rotX(360);
		rotationTransform.setTranslation(vectorRotate);
		
	
		targetTransformGroup.setTransform(rotationTransform);
		
		RotationInterpolator rotationInterpolator = new	RotationInterpolator(a, targetTransformGroup );


		// A rotation that generates a wrong shadow
//		Transform3D ttt = new Transform3D();
//		Vector3d vec = new Vector3d(1.0, 1.0, 0.0);
//		AxisAngle4d aaa = new AxisAngle4d(vec, Math.PI/3);
//		ttt.setRotation(aaa);
//		RotationInterpolator rotationInterpolator = new	RotationInterpolator(a, targetTransformGroup, ttt, 0.0f, (float) Math.PI*2);

		BoundingSphere bounds = new BoundingSphere();
		bounds.setRadius(40.0);
		rotationInterpolator.setSchedulingBounds(bounds);
		
		addChild(rotationInterpolator);
	}
}
