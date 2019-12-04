package org.baconeers.stoneStackingMechanism;

import org.baconeers.common.BaconComponent;
import org.baconeers.common.BaconOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;

public class TongsLift extends BaconComponent {

    private BaconOpMode opmode;
    private SkystoneConfiguration config;
    private double liftPower = 0.0;
    private Telemetry telemetry;


    public TongsLift(BaconOpMode opmodeIn, SkystoneConfiguration configIn, Telemetry telemetryIn) {
        super(opmodeIn);
        opmode = opmodeIn;
        config = configIn;
        telemetry = telemetryIn;


    }

    public void update() {

        liftPower = opmode.gamepad2.left_stick_y;
        telemetry.addData("Lift Power: ",  liftPower);
        telemetry.update();
        config.liftMotor.setPower(liftPower);

    }


}
