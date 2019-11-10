package org.baconeers.stoneStackingMechanism;

import org.baconeers.common.BaconComponent;
import org.baconeers.common.BaconOpMode;

import SkystoneDrive.SkystoneConfiguration;

public class StoneTongsClass extends BaconComponent {

    private BaconOpMode opmode;
    private SkystoneConfiguration config;

    private boolean aPrevState = false; // Previous state for the a button
    private boolean bPrevState = false;// Previous state for the b button

    private String tongStatus = "Up (deactivated)";

    public StoneTongsClass(BaconOpMode opmodeIn, SkystoneConfiguration configIn) {
        super(opmodeIn);
        opmode = opmodeIn;
        config = configIn;

    }

    public void update() {
        // Move the stone tongs
        double servoPower = opmode.gamepad2.left_stick_x;
        config.movementServo.setPower(servoPower);

        // Grab/release the stone
        boolean aCurrentState = opmode.gamepad2.a;
        boolean bCurrentState = opmode.gamepad2.b;

        double servoPos;
        if (aCurrentState && !aPrevState) {
            servoPos = 0.0;
            config.gripServo.setPosition(servoPos);
            tongStatus = "Down (activated)";
        } else if (bCurrentState && !bPrevState){
            servoPos = 1.0;
            config.gripServo.setPosition(servoPos);
            tongStatus = "Up (deactivated)";
        }

        aPrevState = aCurrentState;
        bPrevState = bCurrentState;

        opmode.telemetry.addData("Servo", "Power: " + servoPower);
        opmode.telemetry.addData("Tongs Status", "Stone Tongs: " + tongStatus);
        opmode.telemetry.update();

    }


}