package models;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;

public class RedAppearance extends Appearance{
	
	// Create the blue appearance node
	public RedAppearance()
	{
		Color3f m_black = new Color3f(0.f,0.f,0.f);
		Color3f m_white = new Color3f(0.5f,0.5f,0.5f);
		Color3f m_red = new Color3f(0.8f,0.3f,0.3f);
	
		// Ambient,emissive,diffuse,specular,shininess
		/*Material blueMat = new Material
		(blue, black, blue, specular,25.0f);*/
	
		Material redMat = new Material(m_red, m_black, m_red, m_white, 100f);
	
		//Switch on light
		redMat.setLightingEnable(true);
	
		setMaterial(redMat);
	}

}
