package SkystoneDrive;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.baconeers.common.BaconOpMode;
import org.baconeers.common.ButtonControl;
import org.baconeers.common.GamePadCRServo;
import org.baconeers.common.GamePadSteerDrive;
import org.baconeers.common.GamePadToggle2positionServo;
import org.baconeers.common.GamePadToggleDualMotor;
import org.baconeers.stoneStackingMechanism.StoneTongsClass;
import org.baconeers.stoneStackingMechanism.TongsLiftClass;
import org.baconeers.testbot.TestBotConfiguration;

@TeleOp(name = "SkyDrive")
public class SkyDriveOpMode extends BaconOpMode {
    private SkystoneConfiguration config;
    private StoneTongsClass tongs;
    private TongsLiftClass lift;
    private NormalisedMecanumDrive mecanumDrive = null;
    GamePadToggleDualMotor harvester = null;
    GamePadCRServo foundationServo = null;

    GamePadToggle2positionServo capstoneMech = null;
    GamePadToggle2positionServo harvesterServoLeft = null;
    GamePadToggle2positionServo harvesterServoRight = null;

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

        try {
            harvester = new GamePadToggleDualMotor(this, gamepad1,
                    config.HarvesterLeft, config.HarvesterRight,
                    ButtonControl.Y, ButtonControl.RIGHT_BUMPER, 1.0f);
        } catch (Exception e) {
            telemetry.addLine("Failed to initialise mecanum drive");
        }

        try {
            capstoneMech = new GamePadToggle2positionServo(this ,gamepad2, config.CapStoneServo,
                    ButtonControl.DPAD_LEFT, ButtonControl.DPAD_RIGHT, 0.2, 0.5);
        } catch (Exception e) {
            telemetry.addLine("Failed to initialise capstoneMech");
        }

        try {
            harvesterServoLeft = new GamePadToggle2positionServo(this ,gamepad2, config.HarvesterServoLeft,
                    ButtonControl.DPAD_UP, ButtonControl.DPAD_DOWN, 0.0, 1.0);
        } catch (Exception e) {
            telemetry.addLine("Failed to initialise capstoneMech");
        }

        try {
            harvesterServoRight = new GamePadToggle2positionServo(this ,gamepad2, config.HarvesterServoRight,
                    ButtonControl.DPAD_UP, ButtonControl.DPAD_DOWN, 1.0, 0.0);
        } catch (Exception e) {
            telemetry.addLine("Failed to initialise capstoneMech");
        }

        try {
            foundationServo = new GamePadCRServo(this, gamepad1, config.FoundationServo,
                    ButtonControl.A, ButtonControl.B, 0.5f);
        } catch (Exception e) {
            telemetry.addLine("Failed to initialise foundationServo");

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

        if (harvester != null) {
            harvester.update();
        }

        if (capstoneMech != null) {
            capstoneMech.update();
        }

        if (foundationServo != null) {
            foundationServo.update();
        }

        if (harvesterServoLeft != null) {
            harvesterServoLeft.update();
        }

        if (harvesterServoRight != null) {
            harvesterServoRight.update();
        }
    }
}
