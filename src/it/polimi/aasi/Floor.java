package it.polimi.aasi;
// CheckerFloor.java
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* The floor is a blue and green chessboard, with a small red square
   at the (0,0) position on the (X,Z) plane, and with numbers along
   the X- and Z- axes.
*/

import java.util.ArrayList;

import javax.media.j3d.BranchGroup;

import javax.vecmath.Color3f;
import javax.vecmath.Point3f;


public class Floor
{
  private final static int FLOOR_LEN = 40;  // should be even

  // colours for floor, etc
  private final static Color3f dGrey = new Color3f(0.5f, 0.5f, 0.5f);
  private final static Color3f lGrey = new Color3f(0.7f, 0.7f, 0.7f);

  private BranchGroup floorBG;


  @SuppressWarnings("unchecked")
public Floor()
  // create tiles, add origin marker, then the axes labels
  {
    ArrayList dGreyCoords = new ArrayList();
    ArrayList lGreyCoords = new ArrayList();
    floorBG = new BranchGroup();

    boolean isBlue;
    for(int z=-FLOOR_LEN/2; z <= (FLOOR_LEN/2)-1; z++) {
      isBlue = (z%2 == 0)? true : false;    // set colour for new row
      for(int x=-FLOOR_LEN/2; x <= (FLOOR_LEN/2)-1; x++) {
        if (isBlue)
          createCoords(x, z, dGreyCoords);
        else 
          createCoords(x, z, lGreyCoords);
        isBlue = !isBlue;
      }
    }
    floorBG.addChild( new ColouredTiles(dGreyCoords, dGrey) );
    floorBG.addChild( new ColouredTiles(lGreyCoords, lGrey) );
  }


  @SuppressWarnings("unchecked")
private void createCoords(int x, int z, ArrayList coords)
  // Coords for a single square, 
  // its left hand corner at (x,0,z)
  {
    // points created in counter-clockwise order
    Point3f p1 = new Point3f(x, 0.0f, z+1.0f);
    Point3f p2 = new Point3f(x+1.0f, 0.0f, z+1.0f);
    Point3f p3 = new Point3f(x+1.0f, 0.0f, z);
    Point3f p4 = new Point3f(x, 0.0f, z);   
    coords.add(p1); coords.add(p2); 
    coords.add(p3); coords.add(p4);
  }  // end of createCoords()

  public BranchGroup getBG()
  {  return floorBG;  }


}  // end of CheckerFloor class

