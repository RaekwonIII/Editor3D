package it.polimi.aasi;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;

public class CreateSphere {
	private BranchGroup sphereBG;
	private static final String name = new String("Sphere");
	private static int count = 1;

	public CreateSphere(float r, Color3f emissive, Color3f realColor, Color3f specular, float shininess){
		sphereBG = new BranchGroup();
		sphereBG.setCapability(BranchGroup.ALLOW_DETACH);

		Material material = new Material(realColor, emissive, realColor, specular, shininess);
		// sets ambient, emissive, diffuse, specular, shininess
		material.setCapability(Material.ALLOW_COMPONENT_READ);
		material.setCapability(Material.ALLOW_COMPONENT_WRITE);
		material.setLightingEnable(true);

		Appearance appearance = new Appearance();
		appearance.setMaterial(material);
		appearance.setCapability(Appearance.ALLOW_MATERIAL_READ);
		appearance.setCapability(Appearance.ALLOW_MATERIAL_WRITE);

		Sphere sphere = new Sphere(r, Sphere.GENERATE_NORMALS/* | Sphere.GEOMETRY_NOT_SHARED*/, 100, appearance);
		sphere.setName(CreateSphere.name + CreateSphere.count);
		CreateSphere.count++;
		sphere.setCapability(Box.ENABLE_PICK_REPORTING);
		
		// position the sphere
		Transform3D t3d = new Transform3D();
		t3d.set( new Vector3f(0,1,0));
		TransformGroup tg = new TransformGroup(t3d);
		tg.addChild(sphere);   // set its radius and appearance
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
		tg.setPickable(true);
		
		sphereBG.addChild(tg);
	}

	public BranchGroup getBG(){
		return sphereBG;
	}

}
