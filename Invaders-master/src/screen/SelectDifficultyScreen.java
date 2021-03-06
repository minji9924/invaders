package screen;

import java.awt.event.KeyEvent;


import engine.Cooldown;
import engine.Core;


public class SelectDifficultyScreen extends Screen{

    private static final int SELECTION_TIME = 200;
    private final Cooldown selectionCooldown;

    public SelectDifficultyScreen(final int width, final int height, final int fps) {
        super(width, height, fps);

        this.returnCode = 4;
        this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
        this.selectionCooldown.reset();
    }

    public final int run() {
        super.run();

        return this.returnCode;
    }

    protected final void update() {
        super.update();

        draw();
        if (this.selectionCooldown.checkFinished()
                && this.inputDelay.checkFinished()) {
            if (inputManager.isKeyDown(KeyEvent.VK_UP)
                    || inputManager.isKeyDown(KeyEvent.VK_W)) {
                previousMenuItem();
                this.selectionCooldown.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_DOWN)
                    || inputManager.isKeyDown(KeyEvent.VK_S)) {
                nextMenuItem();
                this.selectionCooldown.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_SPACE))
                this.isRunning = false;
        }
    }

    private void nextMenuItem() {
        if (this.returnCode == 6)
            this.returnCode = 11;
        else if (this.returnCode == 11)
            this.returnCode = 4;
        else
            this.returnCode++;
    }

    /**
     * Shifts the focus to the previous menu item.
     */

    private void previousMenuItem() {
        if (this.returnCode == 4)
            this.returnCode = 11;
        else if (this.returnCode == 11)
            this.returnCode = 6;
        else
            this.returnCode--;
    }

    /**
     * Draws the elements associated with the screen.
     */
    private void draw() {
        drawManager.initDrawing(this);

        drawManager.drawSelectDifficulty_1(this, this.returnCode);
//        drawManager.drawMenu(this, this.returnCode);

        drawManager.completeDrawing(this);
    }
}
