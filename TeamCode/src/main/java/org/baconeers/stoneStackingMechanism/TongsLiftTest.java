package org.baconeers.stoneStackingMechanism;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.baconeers.common.BaconOpMode;
import org.baconeers.common.ButtonControl;
import org.baconeers.common.GamePadCRServo;
import org.baconeers.common.GamePadToggle2positionServo;
import org.baconeers.common.GamePadToggleDualMotor;

import SkystoneDrive.NormalisedMecanumDrive;
import SkystoneDrive.SkystoneConfiguration;

@TeleOp(name = "LiftTest")
public class TongsLiftTest extends BaconOpMode {
    private SkystoneConfiguration config;
    private TongsLiftClass lift;

    @Override
    protected void onInit() {
        config = SkystoneConfiguration.newConfig(hardwareMap, telemetry);
        lift = new TongsLiftClass(this, config, telemetry);
    }

    @Override
    protected void activeLoop() throws InterruptedException {
            lift.update();
    }
}
