package models;


import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;

public class GreenAppereance extends Appearance{

	public GreenAppereance() {

		Color3f black = new Color3f(0.0f,0.0f,0.0f);
		Color3f white = new Color3f(0.5f,0.5f,0.5f);
		Color3f green = new Color3f(0.3f,0.8f,0.3f);
	
		// Ambient,emissive,diffuse,specular,shininess
		/*Material blueMat = new Material
		(blue, black, blue, specular,25.0f);*/
	
		Material greenMat = new Material(green, black, green, white, 100f);
	
		//Switch on light
		greenMat.setLightingEnable(true);
	
		setMaterial(greenMat);
	}
}
