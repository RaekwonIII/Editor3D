package it.polimi.aasi;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;

public class CreateWireFrameCube {
	private BranchGroup cubeBG;
	private static final String name = new String("WFCube");
	
	public CreateWireFrameCube(float dim){
		cubeBG = new BranchGroup();
		cubeBG.setCapability(BranchGroup.ALLOW_DETACH);
		
		Appearance appearance = new Appearance();
		PolygonAttributes polyAttr = new PolygonAttributes();
		//Set them so that the draw mode is polygon line
		polyAttr.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		//Use these in the appearance
		appearance.setPolygonAttributes(polyAttr);

		Box box = new Box(dim, dim, dim,appearance);
		box.setName(CreateWireFrameCube.name);
		box.setCapability(Box.ENABLE_PICK_REPORTING);
		box.setCapability(Box.ENABLE_COLLISION_REPORTING);
		
		// position the sphere
		Transform3D t3d = new Transform3D();
		t3d.set( new Vector3f(0,dim,0));
		TransformGroup tg = new TransformGroup(t3d);
		tg.addChild(box);   // set its radius and appearance
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
		tg.setPickable(true);
		
		cubeBG.addChild(tg);
	}
	
	public BranchGroup getBG(){
		return cubeBG;
	}
}
