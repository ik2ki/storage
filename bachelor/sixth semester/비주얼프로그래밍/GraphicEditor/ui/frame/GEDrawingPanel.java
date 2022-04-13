package ui.frame;

import java.awt.BasicStroke;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Vector;

import javax.swing.JPanel;

import shapes.GEPolygon;
import shapes.GEShape;
import shapes.GEShape.EDrawingType;
import shapes.GEShape.EShapeState;

public class GEDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	// component
	private MouseEventHandler eventHandler;
	private Vector<GEShape> shapes;
	
	// working variables
	private GEShape currentTool;
	private GEShape currentSelectedShape;
	
	//drawingPanel State
	public enum EDrawingState {Idle, NPDrawing, TPDrawing, Trasnforming};
	private EDrawingState drawingState;
	private final static int DEFAULT_DASH_OFF_SET =4;
	private final static int DEFAULT_DASHLINE_WIDTH=1;
	
	
	//setter and getter
	public void setCurrentTool(GEShape currentTool) { this.currentTool = currentTool; }
	
	public GEShape getCurrentSelectedShape() { return currentSelectedShape;	}
	public void setCurrentSelectedShape(GEShape currentSelectedShape) {	this.currentSelectedShape = currentSelectedShape; }

	public EDrawingState getDrawingState() { return drawingState; }
	public void setDrawingState(EDrawingState drawingState) { this.drawingState = drawingState; }
	//constructor
	public GEDrawingPanel() {
		super();
		
		eventHandler = new MouseEventHandler();
		this.addMouseListener(eventHandler);
		this.addMouseMotionListener(eventHandler);
		
		shapes = new Vector<GEShape>();
		
		currentTool = null;
		currentSelectedShape = null;
		drawingState = EDrawingState.Idle;
	}
	
	//methods
	//initialize
	public void initialize() {
		
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		for( GEShape shape : shapes ) {
			shape.draw(g);
		}
	}
	
	private void selectShape(GEShape shape) {
		currentSelectedShape.setSelected(true);
		currentSelectedShape.draw(getGraphics());
	}
	
	private void deSelectShape(){
		if(currentSelectedShape != null){
			currentSelectedShape.setSelected(false);
		}
		this.updateUI();
	}
	
	private void changeCursor(int x, int y){
		GEShape shape = onShape(x, y);
		if(shape == null){
			setCursor(new Cursor((Cursor.DEFAULT_CURSOR)));
		}else{
			setCursor(shape.getCursor());
		}
	}

	public GEShape onShape(int x, int y){
		for(GEShape shape : shapes){
			if(shape.contains(x, y)){
				return shape;
			}
		}
		return null;
	}

	public void initDrawing(int x, int y) {
		currentSelectedShape = currentTool.clone();
		currentSelectedShape.setPos1(x, y);
	}
	
	public void keepDrawing(int x, int y) {
		Graphics2D g2d =(Graphics2D) getGraphics();
		g2d.setXORMode(getBackground());
		float dashes[] = {DEFAULT_DASH_OFF_SET};
		BasicStroke dashedLineStroke = new BasicStroke(DEFAULT_DASHLINE_WIDTH,
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,10,dashes,0);
		
		Stroke currentStroke = g2d.getStroke();
		g2d.setStroke(dashedLineStroke);
		
		currentSelectedShape.draw(g2d);
		currentSelectedShape.movePosN(x, y);
		currentSelectedShape.draw(g2d);
	}
	
	public void contDrawing(int x, int y){
		currentSelectedShape.setPosN(x, y);
	}
	
	public void finishDrawing(int x, int y) {
		currentSelectedShape.setPosN(x, y);
		shapes.add(currentSelectedShape);
		selectShape(currentSelectedShape);
	}
	
	public void initTransforming(GEShape shape, int x, int y) {
		currentSelectedShape = shape;
		if (currentSelectedShape.getState().equals(EShapeState.Moving)) {
			currentSelectedShape.initMove(x, y);
		}
		else if (currentSelectedShape.getState().equals(EShapeState.Resizing)) {
			currentSelectedShape.initResize(x, y);
		}
		else if (currentSelectedShape.getState().equals(EShapeState.Rotating)) {
			currentSelectedShape.initRotate(x, y);
		}
	}
	
	public void keepTransforming(int x, int y) {
		Graphics g = getGraphics();
		g.setXORMode(getBackground());
		currentSelectedShape.draw(g);
		if (currentSelectedShape.getState().equals(EShapeState.Moving)) {
			currentSelectedShape.setSelected(true);
			currentSelectedShape.move(x, y);
		}
		else if (currentSelectedShape.getState().equals(EShapeState.Resizing)) {
			currentSelectedShape.resize(x, y);
		}
		else if (currentSelectedShape.getState().equals(EShapeState.Rotating)) {
			currentSelectedShape.rotate(x, y);
		}
		currentSelectedShape.draw(g);
		this.updateUI();
	}
	
	public void finishTransforming(int x, int y) {
		selectShape(currentSelectedShape);
	}	
	
	//event handler
	private class MouseEventHandler implements  MouseListener, MouseMotionListener , MouseWheelListener {
		
		public void mouseClicked(MouseEvent e) {
			if (drawingState.equals(EDrawingState.NPDrawing)){
				finishDrawing(e.getX(),e.getY());
				setDrawingState(EDrawingState.Idle);
			}
			if(drawingState.equals(EDrawingState.Idle))
				changeCursor(e.getX(),e.getY());
			else if(drawingState.equals(EDrawingState.NPDrawing)) {
				contDrawing(e.getX(),e.getY());
			}
		}
		
		public void mousePressed(MouseEvent e) {
			if(drawingState.equals(EDrawingState.Idle)){
				GEShape shape = onShape(e.getX(), e.getY()); 
				if( shape != null ) {
					deSelectShape();
					initTransforming(shape, e.getX(), e.getY());
					setDrawingState(EDrawingState.Trasnforming);
				} else {
					deSelectShape();
					initDrawing(e.getX(),e.getY());
					if (currentSelectedShape.getDrawingType().equals(EDrawingType.NPRDrawing)){
						setDrawingState(EDrawingState.NPDrawing);
					}  else {
						setDrawingState(EDrawingState.TPDrawing);
					}
				}
			} else if(drawingState.equals(EDrawingState.NPDrawing)) {
				contDrawing(e.getX(),e.getY());
			}
		}

		public void mouseDragged(MouseEvent e) {
			if( drawingState.equals(EDrawingState.Trasnforming)) {
				keepTransforming(e.getX(), e.getY());
			} else if(drawingState.equals(EDrawingState.Drawing)
					&&pointState.equals(PDrawingState.TPDrawing)){
				keepTPDrawing(e.getX(),e.getY());
			} else if(drawingState.equals(EDrawingState.Drawing)
					&&pointState.equals(PDrawingState.NPDrawing)
					&&lineState.equals(PLDrawingState.OddLineDrawing)){
				keepNPDrawing(e.getX(),e.getY());
			}
		}
		
		public void mouseReleased(MouseEvent e) {
			if( drawingState.equals(EDrawingState.Trasnforming)) {
				finishTransforming(e.getX(), e.getY());
				setDrawingState(EDrawingState.Idle);
			} else if(drawingState.equals(EDrawingState.Drawing)
					&&pointState.equals(PDrawingState.TPDrawing)){
				finishTPDrawing(e.getX(),e.getY());
				setDrawingState(EDrawingState.Idle);
			} else if(drawingState.equals(EDrawingState.Drawing)
					&&pointState.equals(PDrawingState.NPDrawing)
					&&lineState.equals(PLDrawingState.OddLineDrawing)){
				addNPoint(e.getX(),e.getY());
				setLineState(PLDrawingState.EvenLineDrawing);
			}
		}
		
		public void mouseMoved(MouseEvent e) {
			if( drawingState.equals(EDrawingState.Idle))
				changeCursor(e.getX(), e.getY());
			else if(drawingState.equals(EDrawingState.Drawing)
					&&pointState.equals(PDrawingState.NPDrawing)
					&&lineState.equals(PLDrawingState.EvenLineDrawing)){
				keepNPDrawing(e.getX(),e.getY());
			}
		}
		
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mouseWheelMoved(MouseWheelEvent e){}
	}
}
