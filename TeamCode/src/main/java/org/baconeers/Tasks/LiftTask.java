package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import SkystoneDrive.NormalisedMecanumDrive;

public class LiftTask extends BaseTask implements Task {


    public LiftTask(BaconOpMode opMode, double time, NormalisedMecanumDrive drive, double speedX, double speedY, double speedR) {
        super(opMode, time);


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
