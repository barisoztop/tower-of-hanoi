package models;

import java.awt.Font;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Geometry;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

public class Text3DApp extends BranchGroup{

	
    public Text3DApp(String text)
    {
        Shape3D textShape;
        Color3f eColor = new Color3f(0.1f, 0.1f, 0.1f);
        Color3f sColor = new Color3f(0.5f, 1.0f, 1.0f);
        Color3f objColor = new Color3f(0.8f, 0.8f, 0.8f);
        Material m = new Material(objColor, eColor, objColor, sColor , 1.0f);
        Appearance a = new Appearance();
        m.setLightingEnable(true);
        a.setMaterial(m);
        Font3D font3d = new Font3D(new Font("Helvetica", Font.LAYOUT_LEFT_TO_RIGHT, 1),new FontExtrusion());
        Text3D textGeom = new Text3D(font3d, new String(text),new Point3f(-2.0f, 0.0f, 0.0f));
        textGeom.setCapability(Geometry.ALLOW_INTERSECT);
        TransformGroup transScale = new TransformGroup();
        transScale.setCapability(TransformGroup.ALLOW_PARENT_READ);
        transScale.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        Transform3D t3d = new Transform3D();
        t3d.setIdentity();
        t3d.setScale(0.1d);
        textShape = new Shape3D(textGeom,a);
        TransformGroup transGroup = new TransformGroup(); 
        transGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        transGroup.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
        transGroup.setTransform(t3d);
        transGroup.addChild(textShape);
        addChild(transGroup);
        compile();
    }

}
