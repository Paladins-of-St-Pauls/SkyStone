package org.baconeers.stoneStackingMechanism;

import org.baconeers.common.BaconComponent;
import org.baconeers.common.BaconOpMode;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;

public class StoneTongsClass extends BaconComponent {

    private BaconOpMode opmode;
    private SkystoneConfiguration config;

    //private boolean aPrevState = false; // Previous state for the a button
    //private boolean bPrevState = false;// Previous state for the b button

    private String tongStatus = "Up (deactivated)";

    public StoneTongsClass(BaconOpMode opmodeIn, SkystoneConfiguration configIn) {
        super(opmodeIn);
        opmode = opmodeIn;
        config = configIn;

    }

    public void update() {
        // Move the stone tongs
//        double servoPower = opmode.gamepad2.right_stick_x / 4;
//        config.movementServo.setPower(servoPower);

        // Grab/release the stone
        boolean aCurrentState = opmode.gamepad2.a;
        boolean bCurrentState = opmode.gamepad2.b;
        boolean xCurrentState = opmode.gamepad2.x;
        boolean yCurrentState = opmode.gamepad2.y;

        double grabberServoPos;
        if (aCurrentState) {
            grabberServoPos = 0.7;
            config.gripServo.setPosition(grabberServoPos);
//            config.gripServo.setPower(0.5);
            tongStatus = "Down (activated)";
        } else if (bCurrentState){
            grabberServoPos = 0.0;
            config.gripServo.setPosition(grabberServoPos);
//            config.gripServo.setPower(-0.5);
            tongStatus = "Up (deactivated)";
        } else {
//            config.gripServo.setPower(0.0);
        }

        double movementServoPos;
        if (xCurrentState) {
            movementServoPos = 0.;
            config.movementServo.setPosition(movementServoPos);
            tongStatus = "Ready to drop! (activated)";
        } else if (yCurrentState){
            movementServoPos = 0.;
            config.movementServo.setPosition(movementServoPos);
            tongStatus = "Turn to drop! (deactivated)";
        } else {
        }


        //aPrevState = aCurrentState;
        //bPrevState = bCurrentState;

//        opmode.telemetry.addData("Servo", "Power: " + servoPower);
        opmode.telemetry.addData("Tongs Status", "Stone Tongs: " + tongStatus);
        opmode.telemetry.update();

    }


}
