package org.baconeers.jeffs;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.baconeers.common.BaconOpMode;
import org.baconeers.common.TankDrive;
import org.baconeers.testbot.JeffsBotConfiguration;

@TeleOp(name = "JeffsTeleOpTankDrive")
public class JeffsTeleOpTankDrive extends BaconOpMode {
    private JeffsBotConfiguration config;
    private TankDrive drive;

    @Override
    protected void onInit() {
        config = JeffsBotConfiguration.newConfig(hardwareMap, telemetry);

        drive = new TankDrive(this, gamepad1, config.leftMotor, config.rightMotor);

    }

    @Override
    protected void activeLoop() throws InterruptedException {
        drive.update();
    }
}