package models;

import java.awt.Color;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;

public class RedAppearance extends Appearance{
	
	// Create the blue appearance node
	public RedAppearance()
	{
		Color3f m_black = new Color3f(0.f,0.f,0.f);
		Color3f m_white = new Color3f(1.0f,1.0f,1.0f);
		Color3f m_red = new Color3f(Color.red);
	
		// Ambient,emissive,diffuse,specular,shininess
		/*Material blueMat = new Material
		(blue, black, blue, specular,25.0f);*/
	
		Material redMat = new Material(m_red, m_black, m_red, m_white, 100f);
	
		//Switch on light
		redMat.setLightingEnable(true);
	
		setMaterial(redMat);
	}

}
