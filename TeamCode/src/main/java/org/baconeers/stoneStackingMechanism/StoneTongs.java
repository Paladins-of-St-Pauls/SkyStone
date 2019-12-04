package org.baconeers.stoneStackingMechanism;

import org.baconeers.common.BaconComponent;
import org.baconeers.common.BaconOpMode;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;

public class StoneTongs extends BaconComponent {

    private BaconOpMode opmode;
    private SkystoneConfiguration config;

    //private boolean aPrevState = false; // Previous state for the a button
    //private boolean bPrevState = false;// Previous state for the b button

    private String tongStatus = "Up (deactivated)";

    public StoneTongs(BaconOpMode opmodeIn, SkystoneConfiguration configIn) {
        super(opmodeIn);
        opmode = opmodeIn;
        config = configIn;

    }

    public void update() {
        
        // Grab/release the stone
        boolean aCurrentState = opmode.gamepad2.a;
        boolean bCurrentState = opmode.gamepad2.b;

        boolean leftBumperState = opmode.gamepad2.left_bumper;
        boolean rightBumperState = opmode.gamepad2.right_bumper;

//        double gripServoPower = opmode.gamepad2.right_stick_x;
//        config.gripServo.setPower(gripServoPower);

        if (aCurrentState) {
            config.movementServo.setPosition(0.0);
        } else if (bCurrentState) {
            config.movementServo.setPosition(1.0);
        }

        if (leftBumperState) {
            config.gripServo.setPower(-1.0);
        } else if (rightBumperState) {
            config.gripServo.setPower(1.0);
        } else {
            config.gripServo.setPower(0.0);
        }


        opmode.telemetry.update();

    }


}
