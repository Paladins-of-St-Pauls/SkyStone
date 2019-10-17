package SkystoneDrive;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.baconeers.common.BaconOpMode;
import org.baconeers.common.GamePadSteerDrive;
import org.baconeers.testbot.TestBotConfiguration;

@TeleOp(name = "SkyDrive")
public class SkyDriveOpMode extends BaconOpMode {
    private SkystoneConfiguration config;
    private SkystoneDrive drive;

    @Override
    protected void onInit() {
       config  = SkystoneConfiguration.newConfig(hardwareMap, telemetry);

       drive = new SkystoneDrive(this, config);

    }

    @Override
    protected void activeLoop() throws InterruptedException {
        drive.update();

//        if (config.touchSensor.isPressed()) {
//            telemetry.addLine("Touch pressed");
//        }
    }
}
