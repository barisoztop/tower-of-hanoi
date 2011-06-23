package models;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TriangleFanArray;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

public class CylinderFakeShadow extends BranchGroup 
{
    private float height = 0.0f;
    private final TransformGroup tg;
    public CylinderFakeShadow(GeometryArray geom, Color3f col) 
    {
            tg = new TransformGroup();
            tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            FlatShape flatShape = new FlatShape(geom, col);
            tg.addChild(flatShape);
            addChild(tg);

            setCapability(ALLOW_DETACH);
            setCapability(ALLOW_CHILDREN_WRITE);
    }

    public TransformGroup getTransformGroup() 
    {
            return tg;
    }
    // ******************
    private class FlatShape extends Shape3D 
    {
        FlatShape(GeometryArray geom, Color3f col) 
        {
            int vCount = geom.getVertexCount();
            int[] stripVertexArray = new int[((TriangleFanArray)geom).getNumStrips()];
            ((TriangleFanArray)geom).getStripVertexCounts(stripVertexArray);
            TriangleFanArray poly = new TriangleFanArray(vCount, GeometryArray.COORDINATES | GeometryArray.COLOR_3 | GeometryArray.NORMALS, stripVertexArray);

            Point3f vertex = new Point3f();
            Point3f shadow = new Point3f();
            for (int v = 0; v < vCount; v++) 
            {
                    geom.getCoordinate(v, vertex);
                    shadow.set(vertex.x, height + 0.0001f, vertex.z);
                    poly.setCoordinate(v, shadow);
                    poly.setColor(v, col);
            }
            this.setGeometry(poly);
        }
    }
}
