package it.polimi.aasi;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;

public class CreateBox {
	private BranchGroup boxBG;
	private static final String name = new String("Box");
	private static int count = 1;
	
	public CreateBox(float x, float y, float z, Color3f emissive, Color3f realColor, Color3f specular, float shininess){
		boxBG = new BranchGroup();
		boxBG.setCapability(BranchGroup.ALLOW_DETACH);

		Material material = new Material(realColor, emissive, realColor, specular, shininess);
		// sets ambient, emissive, diffuse, specular, shininess
		material.setCapability(Material.ALLOW_COMPONENT_READ);
		material.setCapability(Material.ALLOW_COMPONENT_WRITE);
		material.setLightingEnable(true);
		
		Appearance appearance = new Appearance();
		appearance.setMaterial(material);
		appearance.setCapability(Appearance.ALLOW_MATERIAL_READ);
		appearance.setCapability(Appearance.ALLOW_MATERIAL_WRITE);

		Box box = new Box(x, y, z, /*Box.GEOMETRY_NOT_SHARED, */appearance);
		box.setName(CreateBox.name + CreateBox.count);
		CreateBox.count++;
		box.setCapability(Box.ENABLE_PICK_REPORTING);
		box.setCapability(Box.ENABLE_COLLISION_REPORTING);
		box.setCollidable(true);
		box.setBoundsAutoCompute(true);
		
		// position the sphere
		Transform3D t3d = new Transform3D();
		t3d.set( new Vector3f(0,y,0));
		TransformGroup tg = new TransformGroup(t3d);
		tg.addChild(box);   // set its radius and appearance
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
		tg.setPickable(true);
		
		boxBG.addChild(tg);
	}

	public BranchGroup getBG(){
		return boxBG;
	}
}
