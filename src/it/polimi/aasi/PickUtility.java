package it.polimi.aasi;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;

public class PickUtility extends MouseAdapter {
	public static final String clicked = new String("clicked");
	public static final String mouseOver = new String("mouseover");
	private PickCanvas pickCanvas;
	public PickedObjects picked;


	public PickUtility(Canvas3D canvas, BranchGroup scene) {
		pickCanvas = new PickCanvas(canvas, scene);
		pickCanvas.setMode(PickCanvas.GEOMETRY);
		picked = new PickedObjects();
	}

	public void mouseMoved(MouseEvent e) {

		pickCanvas.setShapeLocation(e);
		
		System.out.println("mosso");
		
		PickResult result = pickCanvas.pickClosest();
		//TODO non funziona ma la strada è quella, solo che la modifica del colore non è reversibile...
		if (result != null) {
			
			Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);
			
			if (p != null) {
				picked.setMouseOver(p.getName());
				//ObjectArray.get(p.getName()).setEmissiveColor(new Color3f(0.8f, 0.0f, 0.0f));
			}
		}
	}

	public void mouseClicked(MouseEvent e){
		
		pickCanvas.setShapeLocation(e);

		PickResult result = pickCanvas.pickClosest();

		if (result == null) {

			System.out.println("Nothing picked");

		} else {

			Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);

			if (p != null) {
				picked.setPicked(p.getName());
			}
		}
	}
	
	public class PickedObjects extends Observable{
		private Map<String,String> picked;
		
		public PickedObjects(){
			picked = new HashMap<String,String>(2);
		}
		
		public void setPicked(String string){
			picked.put(PickUtility.clicked, string);
			setChanged();
			notifyObservers(this.picked);
		}
		
		public void setMouseOver(String string){
			picked.put(PickUtility.mouseOver, string);
			setChanged();
			notifyObservers(this.picked);
		}
	}
}
