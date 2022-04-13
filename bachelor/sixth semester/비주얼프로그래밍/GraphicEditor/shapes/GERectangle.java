package shapes;

import java.awt.Graphics;
import java.awt.Rectangle;

import shapes.GEAnchors.EAnchorIndex;

public class GERectangle extends GEShape {
	
	protected Rectangle rectangle;
	
	public GERectangle() {
		super();
		rectangle = new Rectangle();
	}
	
	public GERectangle(int x, int y, int width, int height) {
		super();
		rectangle = new Rectangle(x,y,width,height);
	}
	public int getCx(){ return rectangle.x; }
	public int getCy(){ return rectangle.y; }
	public Rectangle getFrame(){return rectangle.getBounds();};
	
	public void setPos1(int x, int y) { 
		rectangle.x=x; 
		rectangle.y=y;
	}
	public void addPosN(int x, int y) {
		rectangle.width=x-rectangle.x;
		rectangle.height=y-rectangle.y;
	}
	
	public void movePosN(int x, int y){
		rectangle.width=x-rectangle.x;
		rectangle.height=y-rectangle.y;
	}
	
	public GEShape clone() {
		return new GERectangle();
	}

	
	public void draw(Graphics g){
		g.drawRect(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
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
		if(rectangle.contains(x, y)){
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
		rectangle.x += dx;
		rectangle.y += dy;
		px = x;
		py = y;
	}
	
	@Override
	public void resize(int x, int y) {
	}

	@Override
	public void rotate(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
