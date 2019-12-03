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

        double gripServoPower = opmode.gamepad2.right_stick_y/2;
        config.gripServo.setPower(gripServoPower);

        if (aCurrentState) {
            config.movementServo.setPosition(0.0);
        } else if (bCurrentState) {
            config.movementServo.setPosition(1.0);
        }
        
        //aPrevState = aCurrentState;
        //bPrevState = bCurrentState;

//        opmode.telemetry.addData("Servo", "Power: " + servoPower);
        opmode.telemetry.addData("Tongs Status", "Stone Tongs: " + tongStatus);
        opmode.telemetry.update();

    }


}
