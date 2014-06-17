package it.polimi.aasi;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

public class CreateDirLight {
	private BranchGroup lightBG;
	private static final String name = new String("DirLight");
	private static int count = 1;
	
	public CreateDirLight(Color3f color, Vector3f direction){
		lightBG = new BranchGroup();
		lightBG.setCapability(BranchGroup.ALLOW_DETACH);
		
		DirectionalLight light = new DirectionalLight(color, direction);
		light.setName(CreateDirLight.name + CreateDirLight.count);
		CreateDirLight.count++;
		
		light.setInfluencingBounds(Editor3D.bounds);
		
		light.setCapability(DirectionalLight.ALLOW_COLOR_READ);
		light.setCapability(DirectionalLight.ALLOW_COLOR_WRITE);
		light.setCapability(DirectionalLight.ALLOW_STATE_READ);
		light.setCapability(DirectionalLight.ALLOW_STATE_WRITE);
		light.setCapability(DirectionalLight.ALLOW_DIRECTION_READ);
		light.setCapability(DirectionalLight.ALLOW_DIRECTION_WRITE);
		
		lightBG.addChild(light);
	}
	
	public BranchGroup getBG(){
		return lightBG;
	}

}
