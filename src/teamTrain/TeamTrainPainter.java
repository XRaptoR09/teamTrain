package teamTrain;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.geom.Line2D;
public class TeamTrainPainter extends Frame{		//Розширяє або наслідує клас Frame
//			 subclass			 superclass
	public static void main(String[] args) throws Exception{
		TeamTrainPainter autoFirst = new TeamTrainPainter("cayZuzana");
		autoFirst.setSize(frameLength, 600);
		autoFirst.setVisible(true);
		autoFirst.go();
	}
	TeamTrainPainter(String title){
		super(title);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});
	}

	int animationShift = 0;
	static int frameLength = 1200;

	boolean isMovingToLeft = false;

	//!Declarating variables
			//*Locomotive variables
			int locMainY = 100, locMainWidth = 120, locMainHeight = 60;
			int locLeftConnectX = 50;
			int locCabinWidth = 35;
			
			int connectWidth = 11, connectHeight = 6, connectPaddingFromBottom = 3;
			
			int wheelDiameter = 16, wheelPadding = 20, amountOfBlocks = 2;
			int wheelConnectorWidth = 10;
			//*Locomotive

			int	locCabinX = (locLeftConnectX + connectWidth);
			int locCabinHeight = (locMainHeight / 3);
			int locCabinY = (locMainY + locMainHeight) - locCabinHeight;
			int locMainX = (locCabinX + locCabinWidth),		
			connectY = (((locMainY + locMainHeight) - connectPaddingFromBottom) - connectHeight),
			locRightConnectX = (locMainX + locMainWidth),
			wheelsY = (locMainY + locMainHeight),
			locWheelX = (locCabinX + wheelPadding),
			wheelConnectorX = locWheelX + wheelDiameter,
			wheelConnectorY = wheelsY + (wheelDiameter / 3),
			wheelConnectorHeight = (wheelDiameter / 3),
			glassX1 = locCabinX,
			glassY1 = locCabinY,
			glassX2 = locMainX,
			glassY2 = locMainY,
			blockShift = ((locCabinWidth + locMainWidth) - (((wheelDiameter * 2) * 2) + (wheelPadding * 2) + (wheelConnectorWidth * 2)));
			//*Platform variables
			int amountOfWheels = 2, amountOfPairs = 2;

			
			int platMainY = 145, platMainWidth = 120, platMainHeight = 15;
			
			int amountOfPlatforms = 2;

			//*Tank variables

			int bodyWidth = 80;

			int dullDiameter = 2;

			int flagstockWidth = 2;

			int amountOfColors = 2;

			int caterpillarPadding = 4;

			int platLeftConnectX = (locRightConnectX + connectWidth);

			int	platMainX = (platLeftConnectX + connectWidth),
			
			platRightConnectX = (platMainX + platMainWidth);
			int  wheelX = (platMainX + wheelPadding),

			platformWidth = (connectWidth * 2) + platMainWidth,

			platPairShift = ((platMainWidth) - (((wheelDiameter * amountOfWheels) * amountOfPairs) + (wheelPadding * 2)));
	
	public void go() throws Exception{
		while(true) {
			if(!isMovingToLeft) {
				repaint();                    // invoke update
				Thread.sleep(4);
				if(animationShift < (frameLength - (connectWidth*2 + locCabinWidth + locMainWidth) - locLeftConnectX - 10)) {
					animationShift++;
				}else {
					isMovingToLeft = true;
				}
			}else {
				repaint();                    // invoke update
				Thread.sleep(4);
				if (animationShift > (10 - locLeftConnectX)) {
					animationShift--;
				}else {
					isMovingToLeft = false;
				}
			}
		}
	}

	
	
	public void update(Graphics g) {
		int w = getSize().width, h = getSize().height;
		BufferedImage bi = (BufferedImage) createImage(w, h);
		Graphics2D big = bi.createGraphics();
		//!
		Rectangle locMain = new Rectangle(locMainX + animationShift, locMainY, locMainWidth, locMainHeight);
		Rectangle cabine = new Rectangle(locCabinX + animationShift, locCabinY, locCabinWidth, locCabinHeight);
		Rectangle leftConnect = new Rectangle(locLeftConnectX + animationShift, connectY, connectWidth, connectHeight);
		Rectangle rightConnect = new Rectangle(locRightConnectX + animationShift, connectY, connectWidth, connectHeight);
		Line2D.Double glass = new Line2D.Double (glassX1 + animationShift, glassY1, glassX2 + animationShift, glassY2);
		

		Ellipse2D.Double locWheel1 = new Ellipse2D.Double (locWheelX + animationShift, wheelsY, wheelDiameter, wheelDiameter);
		Ellipse2D.Double locWheel2 = new Ellipse2D.Double (locWheelX + wheelDiameter + animationShift, wheelsY, wheelDiameter, wheelDiameter);
		locWheelX = locWheelX + wheelDiameter;
		int wheelShift = ((locMainWidth + locCabinWidth) - ((wheelPadding * 2) + (wheelDiameter * amountOfBlocks) + (wheelConnectorWidth * 2)));
		locWheelX = locWheelX + animationShift + wheelShift;
		Ellipse2D.Double locWheel3 = new Ellipse2D.Double (locWheelX + animationShift, wheelsY, wheelDiameter, wheelDiameter);
		Ellipse2D.Double locWheel4 = new Ellipse2D.Double (locWheelX + wheelDiameter + animationShift, wheelsY, wheelDiameter, wheelDiameter);
		Rectangle wheelConnector1 = new Rectangle(wheelConnectorX + animationShift, wheelConnectorY, wheelConnectorWidth, wheelConnectorHeight);
		Rectangle wheelConnector2 = new Rectangle(wheelConnectorX + wheelShift + animationShift, wheelConnectorY, wheelConnectorWidth, wheelConnectorHeight);
		
		
		GeneralPath locomotive = new GeneralPath(locMain); 
		locomotive.append(cabine, false);
		locomotive.append(leftConnect, false);
		locomotive.append(rightConnect, false);
		locomotive.append(glass, false);
		locomotive.append(wheelConnector1, false);
		locomotive.append(wheelConnector2, false);
		locomotive.append(locWheel1, false);
		locomotive.append(locWheel2, false);
		locomotive.append(locWheel3, false);
		locomotive.append(locWheel4, false);
		big.draw(locomotive);
		//!
		g.drawImage(bi, 0, 0, this);
	}
}
