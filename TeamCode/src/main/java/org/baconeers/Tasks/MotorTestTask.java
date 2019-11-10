package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import SkystoneDrive.NormalisedMecanumDrive;
import SkystoneDrive.SkystoneConfiguration;

public class MotorTestTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private final double frontLeftSpeed;
    private final double frontRightSpeed;
    private final double backLeftSpeed;
    private final double backRightSpeed;

    public MotorTestTask(BaconOpMode opMode, double time, SkystoneConfiguration config, double frontLeftSpeed, double frontRightSpeed, double backLeftSpeed, double backRightSpeed) {
        super(opMode, time);
        this.config = config;
        this.frontLeftSpeed = frontLeftSpeed;
        this.frontRightSpeed = frontRightSpeed;
        this.backLeftSpeed = backLeftSpeed;
        this.backRightSpeed = backRightSpeed;

    }

    @Override
    public void run() {
        if (isFinished()) {
            return;
        }
        config.frontLeftMotor.setPower(frontLeftSpeed);
        config.frontRightMotor.setPower(frontRightSpeed);
        config.backLeftMotor.setPower(backLeftSpeed);
        config.backRightMotor.setPower(backRightSpeed);
    }

}
