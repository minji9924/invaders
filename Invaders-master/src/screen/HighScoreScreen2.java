package screen;

import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import engine.Core;
import engine.Score;
import engine.Score2;

/**
 * Implements the high scores screen, it shows player records.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class HighScoreScreen2 extends Screen {

	/** List of past high scores. */
	private List<Score2> highScores;

	/**
	 * Constructor, establishes the properties of the screen.
	 * 
	 * @param width
	 *            Screen width.
	 * @param height
	 *            Screen height.
	 * @param fps
	 *            Frames per second, frame rate at which the game is run.
	 */
	public HighScoreScreen2(final int width, final int height, final int fps) {
		super(width, height, fps);

		this.returnCode = 1;

		try {
			this.highScores = Core.getFileManager().loadHighScores2();
		} catch (NumberFormatException | IOException e) {
			logger.warning("Couldn't load high scores!");
		}
	}

	/**
	 * Starts the action.
	 * 
	 * @return Next screen code.
	 */
	public final int run() {
		super.run();

		return this.returnCode;
	}

	/**
	 * Updates the elements on screen and checks for events.
	 */
	protected final void update() {
		super.update();

		draw();
		if (inputManager.isKeyDown(KeyEvent.VK_SPACE)
				&& this.inputDelay.checkFinished())
			this.isRunning = false;
		if (inputManager.isKeyDown(KeyEvent.VK_ESCAPE)){
			BufferedWriter file = null;
			try {
				file = new BufferedWriter(new FileWriter("scores2"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.isRunning = false;
		}
	}

	/**
	 * Draws the elements associated with the screen.
	 */
	private void draw() {
		drawManager.initDrawing(this);

		drawManager.drawHighScoreMenu(this);
		drawManager.drawHighScores2(this, this.highScores);

		drawManager.completeDrawing(this);
	}
}
