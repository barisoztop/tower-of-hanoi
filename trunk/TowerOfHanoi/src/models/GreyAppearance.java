package models;

import java.awt.Color;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;

public class GreyAppearance extends Appearance{
	
	public GreyAppearance() {
		
		Color3f black = new Color3f(0.f,0.f,0.f);
		Color3f white = new Color3f(1.0f,1.0f,1.0f);
		Color3f gray = new Color3f(Color.gray);
	
		// Ambient,emissive,diffuse,specular,shininess
		/*Material blueMat = new Material
		(blue, black, blue, specular,25.0f);*/
	
		Material grayMat = new Material(gray, black, gray, white, 100f);
	
		//Switch on light
		grayMat.setLightingEnable(true);
	
		setMaterial(grayMat);
	}

}
