package it.polimi.aasi;

import java.awt.AWTEvent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.vecmath.Vector3f;

public class NavigationTranslate extends Behavior{

	private static final float mThreshold = 2;
	private static final float mIncrement = 0.1f;
    private TransformGroup targetTG;
    private Transform3D t3d = new Transform3D();
    private Vector3f v3f = new Vector3f();
    private Point old_point = new Point();

	// create SimpleBehavior
    public NavigationTranslate(TransformGroup targetTG){
        this.targetTG = targetTG;
        this.targetTG.getTransform(t3d);
		t3d.get(v3f);
		old_point.setLocation(v3f.getX(), v3f.getY());
    }

    // initialize the Behavior
    //     set initial wakeup condition
    //     called when behavior becomes live
    public void initialize(){
        // set initial wakeup condition
    	this.wakeupOn(new WakeupOnAWTEvent(MouseEvent.MOUSE_DRAGGED));
    }

    // called by Java 3D when appropriate stimulus occures
    @SuppressWarnings("unchecked")
	public void processStimulus(Enumeration criteria){
        // decode event
    	WakeupCriterion wakeup;
    	AWTEvent[] event;
    	while (criteria.hasMoreElements()) {

    		wakeup = (WakeupCriterion) criteria.nextElement();

    		if (wakeup instanceof WakeupOnAWTEvent) {
    			event = ((WakeupOnAWTEvent)wakeup).getAWTEvent();
    			for (int i=0; i<event.length; i++){

    				if (event[i] instanceof MouseEvent){
    					Point new_point = ((MouseEvent)event[i]).getPoint();
    					targetTG.getTransform(t3d);
    					t3d.get(v3f);

    					if ((new_point.getX() - old_point.getX()) > mThreshold)
    						v3f.setX(v3f.getX() - mIncrement);
    					else if((new_point.getX() - old_point.getX()) < -mThreshold)
    						v3f.setX(v3f.getX() + mIncrement);
    					
    					if ((new_point.getY() - old_point.getY()) > mThreshold)
    						v3f.setY(v3f.getY() + mIncrement);
    					else if ((new_point.getY() - old_point.getY()) < -mThreshold)
    						v3f.setY(v3f.getY() - mIncrement);

    					old_point = new_point;
    					t3d.setTranslation(v3f);
    					targetTG.setTransform(t3d);
    				}
    			}
    		}
    	}
    	// do what is necessary
    	this.wakeupOn(new WakeupOnAWTEvent(MouseEvent.MOUSE_DRAGGED));
    }
} // end of class SimpleBeh