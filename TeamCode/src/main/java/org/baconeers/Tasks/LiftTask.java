package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import SkystoneDrive.NormalisedMecanumDrive;
import SkystoneDrive.SkystoneConfiguration;

public class LiftTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private double liftPower;

    public LiftTask(BaconOpMode opMode, double time, SkystoneConfiguration config, double liftPower) {
        super(opMode, time);
        this.config = config;
        this.liftPower = liftPower;

    }

    void update(double power){
//        config.liftMotor.setPower(power);
    }


    @Override
    public void run() {
        if (isFinished()) {
            liftPower = 0.0;
            update(liftPower);
            return;
        }
        update(liftPower);
    }

}
