package shapes;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GEShape {
	
	protected int px, py;
	protected boolean selected;
	protected GEAnchors anchors;
	
	public enum EShapeState {Idle, Moving, Rotating, Resizing };	
	protected EShapeState state;
	
	public enum EDrawingType {TPDrawing, NClickDrawing, NPRDrawing};
	protected EDrawingType drawingType;
	
	public EDrawingType getDrawingType() {
		return drawingType;
	}

	public void setDrawingType(EDrawingType drawingType) {
		this.drawingType = drawingType;
	}

	// constructor
	public GEShape() {
		selected = false;
		state = EShapeState.Idle;
	}
	
	// getters and setters	
	public EShapeState getState() { return state;}
	public void setState(EShapeState state) {this.state = state;}
	

	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected){
		this.selected = selected;
		if( selected ) {
			anchors = new GEAnchors(getFrame());
		}
	}

	// drawing methods
	public void drawAnchors(Graphics g) {
		if( selected ) {
			anchors.draw(g);
		}
	}
	
	public Cursor getCursor() {
		if (state.equals(EShapeState.Idle))
			return new Cursor(Cursor.DEFAULT_CURSOR);
		else if (state.equals(EShapeState.Moving))
			return new Cursor(Cursor.MOVE_CURSOR);
		else
			return anchors.getCursor();				
	}

	
	// abstract methods

	public abstract void setPos1(int x, int y);
	public abstract void setPosN(int x, int y);
	public abstract void movePosN(int x, int y);
	public abstract int getCx();
	public abstract int getCy();
	public abstract GEShape clone();
	public abstract boolean contains(int x, int y);
	
	public abstract void draw(Graphics g);	

	public abstract void initMove(int x, int y);
	public abstract void initResize(int x, int y);
	public abstract void initRotate(int x, int y);
	
	public abstract void move(int x, int y);
	public abstract void resize(int x, int y);
	public abstract void rotate(int x, int y);
	public abstract Rectangle getFrame();
}
