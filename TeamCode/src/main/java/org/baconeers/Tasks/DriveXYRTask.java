package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import SkystoneDrive.NormalisedMecanumDrive;

public class DriveXYRTask extends BaseTask implements Task {

    private final NormalisedMecanumDrive drive;
    private final double speedX;
    private final double speedY;
    private final double speedR;

    public DriveXYRTask(BaconOpMode opMode, double time, NormalisedMecanumDrive drive, double speedX, double speedY, double speedR) {
        super(opMode, time);
        this.drive = drive;
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedR = speedR;

    }

    @Override
    public void run() {
        if (isFinished()) {
            drive.setSpeedXYR(0, 0, 0);
            drive.update();
            return;
        }
        drive.setSpeedXYR(speedX, speedY, speedR);
        drive.update();
    }

}
