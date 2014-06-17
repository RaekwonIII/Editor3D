package it.polimi.aasi;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.SpotLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

public class CreateSpotLight {
	private BranchGroup lightBG;
	private static final String name = new String("SpotLight");
	private static int count = 1;
	
	public CreateSpotLight(Color3f color, Point3f position, Point3f attenuation, Vector3f direction, float spreadAngle, float concentration){
		lightBG = new BranchGroup();
		lightBG.setCapability(BranchGroup.ALLOW_DETACH);
		
		SpotLight light = new SpotLight(color, position, attenuation, direction, spreadAngle, concentration);
		light.setName(CreateSpotLight.name + CreateSpotLight.count);
		CreateSpotLight.count++;
		
		light.setInfluencingBounds(Editor3D.bounds);

		light.setCapability(SpotLight.ALLOW_COLOR_READ);
		light.setCapability(SpotLight.ALLOW_COLOR_WRITE);
		light.setCapability(SpotLight.ALLOW_STATE_READ);
		light.setCapability(SpotLight.ALLOW_STATE_WRITE);
		light.setCapability(SpotLight.ALLOW_ATTENUATION_READ);
		light.setCapability(SpotLight.ALLOW_ATTENUATION_WRITE);
		light.setCapability(SpotLight.ALLOW_POSITION_READ);
		light.setCapability(SpotLight.ALLOW_POSITION_WRITE);
		light.setCapability(SpotLight.ALLOW_DIRECTION_READ);
		light.setCapability(SpotLight.ALLOW_DIRECTION_WRITE);
		light.setCapability(SpotLight.ALLOW_CONCENTRATION_READ);
		light.setCapability(SpotLight.ALLOW_CONCENTRATION_WRITE);
		light.setCapability(SpotLight.ALLOW_SPREAD_ANGLE_READ);
		light.setCapability(SpotLight.ALLOW_SPREAD_ANGLE_WRITE);
		
		lightBG.addChild(light);
	}
	
	public BranchGroup getBG(){
		return lightBG;
	}


}
