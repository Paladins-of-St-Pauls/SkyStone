package org.baconeers.stoneStackingMechanism;

import org.baconeers.common.BaconComponent;
import org.baconeers.common.BaconOpMode;

import SkystoneDrive.SkystoneConfiguration;

public class TongsLiftClass extends BaconComponent {

    private BaconOpMode opmode;
    private SkystoneConfiguration config;
    private double liftPower = 0.0;

    public TongsLiftClass(BaconOpMode opmodeIn, SkystoneConfiguration configIn) {
        super(opmodeIn);
        opmode = opmodeIn;
        config = configIn;

    }

    public void update() {

        liftPower = opmode.gamepad1.left_stick_y;
        config.liftMotor.setPower(liftPower);

    }


}
