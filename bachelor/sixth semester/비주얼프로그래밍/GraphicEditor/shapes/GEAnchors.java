package shapes;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

//숙제 : code 그대로 사용해서 anchor draw (수요일 날은 anchor 다는..)
public class GEAnchors {
	
	private static final double ANCHOR_SIZE = 8;
	private static final double RR_OFFSET = 20;
	private static final double ANCHOR_RADIUS = ANCHOR_SIZE/2;
	
	public enum EAnchorIndex { NW, NN, NE, WW, EE, SW, SS, SE, RR};
	public int[] cursors = {
			Cursor.NW_RESIZE_CURSOR, 
			Cursor.N_RESIZE_CURSOR,
			Cursor.NE_RESIZE_CURSOR,
			Cursor.W_RESIZE_CURSOR,
			Cursor.E_RESIZE_CURSOR,
			Cursor.SW_RESIZE_CURSOR,
			Cursor.S_RESIZE_CURSOR,
			Cursor.SE_RESIZE_CURSOR,
			Cursor.HAND_CURSOR
	};

	private Rectangle frame;

	private EAnchorIndex state;
	private Vector<Ellipse2D> anchors;

	public GEAnchors(Rectangle r) {
		frame = r;

		anchors = new Vector<Ellipse2D>();
		for (EAnchorIndex e: EAnchorIndex.values() ) {
			anchors.add( new Ellipse2D.Double() );
		}
	}

	//getter and setter
	public EAnchorIndex getState() { return state; }
	public void setAState(EAnchorIndex state) { this.state = state; }
	
	// corresponding cursors
	public Cursor getCursor() {	return new Cursor(cursors[state.ordinal()]);	}	

	public void setAnchors() {
		int x = frame.x;
		int w = frame.width;
		int y = frame.y;
		int h = frame.height;
		
		Rectangle2D r ;
		r = new Rectangle2D.Double(x-ANCHOR_RADIUS, y-ANCHOR_RADIUS, ANCHOR_SIZE, ANCHOR_SIZE);
		anchors.get(EAnchorIndex.NW.ordinal()).setFrame( r );		
		r = new Rectangle2D.Double(x+w/2, y-ANCHOR_RADIUS, ANCHOR_SIZE, ANCHOR_SIZE);
		anchors.get(EAnchorIndex.NN.ordinal()).setFrame( r );		
		r = new Rectangle2D.Double(x+w-ANCHOR_RADIUS, y-ANCHOR_RADIUS, ANCHOR_SIZE, ANCHOR_SIZE);
		anchors.get(EAnchorIndex.NE.ordinal()).setFrame( r );		
		r = new Rectangle2D.Double(x-ANCHOR_RADIUS, y+h/2-ANCHOR_RADIUS, ANCHOR_SIZE, ANCHOR_SIZE);
		anchors.get(EAnchorIndex.WW.ordinal()).setFrame( r );		
		r = new Rectangle2D.Double(x+w-ANCHOR_RADIUS, y+h/2-ANCHOR_RADIUS, ANCHOR_SIZE, ANCHOR_SIZE);
		anchors.get(EAnchorIndex.EE.ordinal()).setFrame( r );		
		r = new Rectangle2D.Double(x-ANCHOR_RADIUS, y+h-ANCHOR_RADIUS, ANCHOR_SIZE, ANCHOR_SIZE);
		anchors.get(EAnchorIndex.SW.ordinal()).setFrame( r );		
		r = new Rectangle2D.Double(x+w/2, y+h-ANCHOR_RADIUS, ANCHOR_SIZE, ANCHOR_SIZE);
		anchors.get(EAnchorIndex.SS.ordinal()).setFrame( r );		
		r = new Rectangle2D.Double(x+w-ANCHOR_RADIUS, y+h-ANCHOR_RADIUS, ANCHOR_SIZE, ANCHOR_SIZE);
		anchors.get(EAnchorIndex.SE.ordinal()).setFrame( r );		
		r = new Rectangle2D.Double(x+w/2, y - RR_OFFSET, ANCHOR_SIZE, ANCHOR_SIZE);
		anchors.get(EAnchorIndex.RR.ordinal()).setFrame( r );
	}
	
	public void draw(Graphics g) {
		setAnchors(); // initialize anchors position

		Graphics2D g2 = (Graphics2D) g;
		g2.draw(frame); //draw boundary rectangle
		for (EAnchorIndex e: EAnchorIndex.values() ) {
			g2.draw( anchors.get(e.ordinal()) );
		}
	}
	
	public boolean contains(int x, int y){
		for (EAnchorIndex aIndex: EAnchorIndex.values() ) {
			if(anchors.get(aIndex.ordinal()).contains(x, y)){
				state = aIndex;
				return true;
			}
		}
		return false;
	}
}








