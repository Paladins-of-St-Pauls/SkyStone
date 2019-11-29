package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;

public class LiftTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private double liftPower;

    public LiftTask(BaconOpMode opMode, double time, SkystoneConfiguration config, double liftPower) {
        super(opMode, time);
        this.config = config;
        this.liftPower = liftPower;

    }

    void update() {
        /* config.liftMotor.setPower(liftPower); */
    }

    @Override
    public void run() {
        if (isFinished()) {
            liftPower = 0.0;
            update();
            return;
        }
        update();
    }

}
