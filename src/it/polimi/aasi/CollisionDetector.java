package it.polimi.aasi;

import java.util.Enumeration;

import javax.media.j3d.Appearance;
import javax.media.j3d.Behavior;
import javax.media.j3d.Bounds;
import javax.media.j3d.Material;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.media.j3d.WakeupOnCollisionExit;
import javax.vecmath.Color3f;

import com.sun.j3d.utils.geometry.Primitive;

public class CollisionDetector extends Behavior {

	private static Material highlightMaterial = new Material(new Color3f(0.0f, 0.0f, 0.0f), 
			new Color3f(1.0f, 0.8f, 0.0f), new Color3f(0.0f, 0.0f, 0.0f), new Color3f(0.9f, 0.9f, 0.9f), 25.0f);
	
	 /* private static final ColoringAttributes highlight = new ColoringAttributes(
	      highlightColor, ColoringAttributes.SHADE_GOURAUD);*/

	  private boolean inCollision = false;

	  private Primitive solid;

	  private Material material;

	  private Appearance appearance;

	  private WakeupOnCollisionEntry wEnter;

	  private WakeupOnCollisionExit wExit;

	  public CollisionDetector(Primitive p, Bounds bound) {
		  //setSchedulingBounds(bound);
		  solid = p;
		  appearance = solid.getAppearance();
		  material = appearance.getMaterial();
		  inCollision = false;
		  setBoundsAutoCompute(true);
		  wEnter = new WakeupOnCollisionEntry(solid.getBounds());
		  wExit = new WakeupOnCollisionExit(solid.getBounds());
	  }

	  public void initialize() {
	    wakeupOn(wEnter);
	  }

	  @SuppressWarnings("unchecked")
	public void processStimulus(Enumeration criteria) {
		  System.out.println(inCollision);

		  WakeupCriterion theCriterion = (WakeupCriterion) criteria.nextElement();
		  String name = new String();
		  if (theCriterion instanceof WakeupOnCollisionEntry){
			  inCollision = true;
			  name = ((WakeupOnCollisionEntry) theCriterion)
			  .getTriggeringPath().getObject().getParent().getName();
		  }
		  else if (theCriterion instanceof WakeupOnCollisionExit) inCollision = false;
		  if ((inCollision) && (name != null) && (name.contains("WF"))) {
			  appearance.setMaterial(highlightMaterial);
			  wakeupOn(wExit);
		  } else {
			  appearance.setMaterial(material);
			  wakeupOn(wEnter);
		  }
	  }
}