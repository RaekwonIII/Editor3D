package it.polimi.aasi;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.PointLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

public class CreatePointLight {
	private BranchGroup lightBG;
	private static final String name = new String("PointLight");
	private static int count = 1;
	
	public CreatePointLight(Color3f color, Point3f position, Point3f attenuation){
		lightBG = new BranchGroup();
		lightBG.setCapability(BranchGroup.ALLOW_DETACH);
		
		PointLight light = new PointLight(color, position, attenuation);
		light.setName(CreatePointLight.name + CreatePointLight.count);
		CreatePointLight.count++;
		
		light.setInfluencingBounds(Editor3D.bounds);
		
		light.setCapability(PointLight.ALLOW_COLOR_READ);
		light.setCapability(PointLight.ALLOW_COLOR_WRITE);
		light.setCapability(PointLight.ALLOW_STATE_READ);
		light.setCapability(PointLight.ALLOW_STATE_WRITE);
		light.setCapability(PointLight.ALLOW_ATTENUATION_READ);
		light.setCapability(PointLight.ALLOW_ATTENUATION_WRITE);
		light.setCapability(PointLight.ALLOW_POSITION_READ);
		light.setCapability(PointLight.ALLOW_POSITION_WRITE);
		
		lightBG.addChild(light);
	}
	
	public BranchGroup getBG(){
		return lightBG;
	}

}
