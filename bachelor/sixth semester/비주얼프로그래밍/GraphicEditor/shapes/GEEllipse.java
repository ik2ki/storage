package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import shapes.GEAnchors.EAnchorIndex;
import shapes.GEShape.EShapeState;

public class GEEllipse extends GEShape {
	
	protected Ellipse2D ellipse;
	
	public GEEllipse() {
		super();
		ellipse = new Ellipse2D.Double();
	}
	
	public GEEllipse(int x, int y, int width, int height) {
		super();
		ellipse = new Ellipse2D.Double(x,y,width,height);
	}
	public int getCx(){ return (int)ellipse.getX(); }
	public int getCy(){ return (int)ellipse.getY(); }
	public Rectangle getFrame() { return ellipse.getBounds();}
	
	public void setPos1(int x, int y) {
		ellipse.setFrame(x, y, 0, 0);
	}
	public void addPos2(int x, int y) {
		ellipse.setFrame(ellipse.getX(),ellipse.getY(),x-ellipse.getX(),y-ellipse.getY());
	}
	
	public GEShape clone() {
		return new GEEllipse();
	}

	
	public void draw(Graphics g){
		Graphics2D g2d;
		g2d = (Graphics2D)g;
		g2d.draw(ellipse);
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
		if(ellipse.contains(x, y)){
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
		ellipse.setFrame(ellipse.getX()+dx,ellipse.getY()+dy,ellipse.getWidth(),ellipse.getHeight());
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
