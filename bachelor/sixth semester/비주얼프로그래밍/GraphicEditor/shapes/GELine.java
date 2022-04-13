package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import shapes.GEAnchors.EAnchorIndex;
import shapes.GEShape.EShapeState;

public class GELine extends GEShape {

	protected Line2D line;
	
	public GELine() {
		super();
		line = new Line2D.Double();
	}
	
	public GELine(int x1, int y1, int x2, int y2) {
		super();
		line = new Line2D.Double(x1,y1,x2,y2);
	}
	public int getCx(){ return (int)line.getX1(); }
	public int getCy(){ return (int)line.getY1(); }
	public Rectangle getFrame() { return line.getBounds();}
	
	public void setPos1(int x, int y) {
		line.setLine(x, y, x, y);
	}
	public void addPos2(int x, int y) {
		line.setLine(line.getX1(),line.getY1(), x, y);
	}
	
	public GEShape clone() {
		return new GELine();
	}

	
	public void draw(Graphics g){
		Graphics2D g2d;
		g2d = (Graphics2D)g;
		g2d.draw(line);
		drawAnchors(g);
	}
	
	public boolean contains(int x, int y){
		if(anchors.contains(x, y)){
			if(anchors.getState().equals(EAnchorIndex.RR)){
				state = EShapeState.Rotating;
			} else {
				state = EShapeState.Resizing;
			}
			return true;
		}
		if(getFrame().contains(x, y)){
			state = EShapeState.Moving;
			return true;
		}
		return false;
	}
	
	@Override
	public void initMove(int x, int y) {
		// TODO Auto-generated method stub
		px = x;
		py = y;
	}

	@Override
	public void initResize(int x, int y) {
		// TODO Auto-generated method stub
		px = x;
		py = y;
	}

	@Override
	public void initRotate(int x, int y) {
		// TODO Auto-generated method stub
		px = x;
		py = y;		
	}
	
	public void move(int x, int y) {
		int dx = x - px;
		int dy = y - py;
		line.setLine(line.getX1()+dx,line.getY1()+dy,line.getX2()+dx,line.getY2()+dy);
		px = x;
		py = y;
	}
	
	@Override
	public void resize(int x, int y) {
	}

	@Override
	public void rotate(int x, int y) {
	}
}
