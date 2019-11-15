package SkystoneDrive;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.baconeers.common.BaconOpMode;
import org.baconeers.common.GamePadSteerDrive;
import org.baconeers.stoneStackingMechanism.StoneTongsClass;
import org.baconeers.stoneStackingMechanism.TongsLiftClass;
import org.baconeers.testbot.TestBotConfiguration;

@TeleOp(name = "SkyDrive")
public class SkyDriveOpMode extends BaconOpMode {
    private SkystoneConfiguration config;
    private StoneTongsClass tongs;
    private TongsLiftClass lift;
    private NormalisedMecanumDrive mecanumDrive = null;

    @Override
    protected void onInit() {
        config = SkystoneConfiguration.newConfig(hardwareMap, telemetry);

        try {
            tongs = new StoneTongsClass(this, config);
        } catch (Exception e) {
            telemetry.addLine("Failed to initialise tongs");
        }

        try {
            lift = new TongsLiftClass(this, config);
        } catch (Exception e) {
            telemetry.addLine("Failed to initialise tong lift");
        }

        try {
            mecanumDrive = new NormalisedMecanumDrive(this,
                    config.frontLeftMotor, config.frontRightMotor,
                    config.backLeftMotor, config.backRightMotor,
                    false);
        } catch (Exception e) {
            telemetry.addLine("Failed to initialise mecanum drive");
        }
    }

    @Override
    protected void activeLoop() throws InterruptedException {

        if (mecanumDrive != null) {
            mecanumDrive.setSpeedXYR(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            mecanumDrive.update();
        }

        if (lift != null) {
            lift.update();
        }

        if (tongs != null) {
            tongs.update();
        }

//        if (config.touchSensor.isPressed()) {
//            telemetry.addLine("Touch pressed");
//        }
    }
}
