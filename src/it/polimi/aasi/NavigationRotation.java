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
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class NavigationRotation extends Behavior{

	private static final float mThreshold = 2;
	private static final double mIncrement = 0.01;
	private double alfa = 0;
	private double beta = 0;
	private static final Point3d mOrigin = new Point3d(0,0,0);
	private static final Vector3d mUp =  new Vector3d(0,1,0);
    private TransformGroup targetTG;
    private Transform3D t3d = new Transform3D();
    private Vector3d v3d = new Vector3d();
    private Point old_point = new Point();
    private Point3d mUsrPos = new Point3d();
    private double x, y, z, rxz, ryz, new_x, new_y, new_z;

	// create SimpleBehavior
    public NavigationRotation(TransformGroup targetTG){
        this.targetTG = targetTG;
        this.targetTG.getTransform(t3d);
		t3d.get(v3d);
		mUsrPos.set(v3d);
		
		old_point.setLocation(v3d.getX(), v3d.getY());
		
    	x = v3d.getX();
    	y = v3d.getY();
    	z = v3d.getZ();
    	
    	rxz = Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));
    	ryz = Math.sqrt(Math.pow(y, 2) + Math.pow(z, 2));
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
    					
    					
    					if ((new_point.getX() - old_point.getX()) > mThreshold){

    						alfa-=mIncrement;
    						if(alfa<0)
    							alfa += 2*Math.PI;
    						    						
    						new_x = rxz*Math.sin(alfa);
    						new_z = rxz*Math.cos(alfa);
    										
    						mUsrPos.setX(new_x);
    						mUsrPos.setZ(new_z);
    					}
    					else if((new_point.getX() - old_point.getX()) < -mThreshold){
    										
    						alfa+=mIncrement;
    						if(alfa>=2*Math.PI)
    							alfa -= 2*Math.PI;

    						new_x = rxz*Math.sin(alfa);
    						new_z = rxz*Math.cos(alfa);
    										
    						mUsrPos.setX(new_x);
    						mUsrPos.setZ(new_z);
    					}
    					
    					if ((new_point.getY() - old_point.getY()) > mThreshold){
    						
    						beta+=mIncrement;
    						if(beta>=2*Math.PI)
    							beta -= 2*Math.PI;

    						new_y = ryz*Math.sin(beta);
    						new_z = ryz*Math.cos(beta);
    						
    						mUsrPos.setY(new_y);
    						mUsrPos.setZ(new_z);
    					}
    					else if ((new_point.getY() - old_point.getY()) < -mThreshold){
    						beta-=mIncrement;
    						if(beta<0)
    							beta += 2*Math.PI;

    						new_y = ryz*Math.sin(beta);
    						new_z = ryz*Math.cos(beta);

    						mUsrPos.setY(new_y);
    						mUsrPos.setZ(new_z);
    					}
    					
						t3d.lookAt(mUsrPos , mOrigin , mUp);
						t3d.invert();
    					old_point = new_point;
    					targetTG.setTransform(t3d);
    				}
    			}
    		}
    	}
    	// do what is necessary
    	this.wakeupOn(new WakeupOnAWTEvent(MouseEvent.MOUSE_DRAGGED));
    }
} // end of class SimpleBeh