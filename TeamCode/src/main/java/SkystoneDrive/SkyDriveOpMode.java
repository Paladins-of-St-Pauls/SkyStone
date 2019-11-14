package SkystoneDrive;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.baconeers.common.BaconOpMode;
import org.baconeers.common.GamePadSteerDrive;
import org.baconeers.testbot.TestBotConfiguration;

@TeleOp(name = "SkyDrive")
public class SkyDriveOpMode extends BaconOpMode {
    private SkystoneConfiguration config;
    private SkystoneDrive drive;
    private NormalisedMecanumDrive mecanumDrive = null;

    @Override
    protected void onInit() {
       config  = SkystoneConfiguration.newConfig(hardwareMap, telemetry);

       drive = new SkystoneDrive(this, config);

    }

    @Override
    protected void activeLoop() throws InterruptedException {
        drive.update();
        mecanumDrive.setSpeedXYR(-gamepad1.left_stick_y, gamepad1.left_stick_x , gamepad1.right_stick_x );

        mecanumDrive.update();

//        if (config.touchSensor.isPressed()) {
//            telemetry.addLine("Touch pressed");
//        }
    }
}
