package org.baconeers.jeffs;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.baconeers.common.BaconOpMode;
import org.baconeers.common.GamePadSteerDrive;
import org.baconeers.testbot.JeffsBotConfiguration;

@Autonomous(name = "JeffsAutonomousOpMode")
public class JeffsAutonomousOpMode extends BaconOpMode {
    private JeffsBotConfiguration config;
    private GamePadSteerDrive drive;

    @Override
    protected void onInit() {
       config  = JeffsBotConfiguration.newConfig(hardwareMap, telemetry);

       drive = new GamePadSteerDrive(this, gamepad1, config.leftMotor, config.rightMotor);

    }

    @Override
    protected void activeLoop() throws InterruptedException {
        drive.update();

//        if (config.touchSensor.isPressed()) {
//            telemetry.addLine("Touch pressed");
//            config.leftMotor.setPower(0.3);
//            config.rightMotor.setPower(0.0);
//        } else {
//            config.leftMotor.setPower(0.0);
//            config.rightMotor.setPower(0.3);
//
//        }
    }
}
