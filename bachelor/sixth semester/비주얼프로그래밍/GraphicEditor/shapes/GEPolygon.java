package shapes;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

import shapes.GEAnchors.EAnchorIndex;
import shapes.GEShape.EShapeState;

public class GEPolygon extends GEShape {
	
	protected Polygon polygon;
	
	public GEPolygon() {
		super();
		polygon = new Polygon();
	}
	public int getCx(){ return polygon.xpoints[polygon.npoints-1]; }
	public int getCy(){ return polygon.ypoints[polygon.npoints-1]; }
	public Rectangle getFrame(){return polygon.getBounds();};
	
	public void setPos1(int x, int y) {
		polygon.addPoint(x, y);
		polygon.addPoint(x, y);
	}
	
	public void setPosN(int x, int y) {
		polygon.addPoint(x, y);
	}
	
	public void movePosN(int x, int y){
		polygon.xpoints[polygon.npoints-1] = x;
		polygon.ypoints[polygon.npoints-1] = y;
	}
	
	public GEShape clone() {
		return new GEPolygon();
	}

	
	public void draw(Graphics g){
		g.drawPolygon(polygon);
		drawAnchors(g);
	}
	
	public boolean contains(int x, int y){
		if(selected){
			if(anchors.contains(x, y)){
				if(anchors.getState().equals(EAnchorIndex.RR)){
					state = EShapeState.Rotating;
				} else {
					state = EShapeState.Resizing;
				}
				return true;
			}
		}
		if(polygon.contains(x, y)){
			state = EShapeState.Moving;
			return true;
		}
		return false;
	}
	
	@Override
	public void initMove(int x, int y) {
		px = x;
		py = y;
	}

	@Override
	public void initResize(int x, int y) {
		px = x;
		py = y;
	}

	@Override
	public void initRotate(int x, int y) {
		px = x;
		py = y;		
	}
	
	public void move(int x, int y) {
		int dx = x - px;
		int dy = y - py;
		polygon.translate(dx, dy);
		px = x;
		py = y;
	}
	
	@Override
	public void resize(int x, int y) {
		px=x;
		py=y;
	}

	@Override
	public void rotate(int x, int y) {		
	}
}
