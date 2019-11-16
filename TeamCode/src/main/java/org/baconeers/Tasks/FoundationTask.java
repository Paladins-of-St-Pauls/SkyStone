package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import SkystoneDrive.SkystoneConfiguration;

public class FoundationTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private double FoundationPower;

    public FoundationTask(BaconOpMode opMode, double time, SkystoneConfiguration config, double FoundationPower) {
        super(opMode, time);
        this.config = config;
        this.FoundationPower = FoundationPower;

    }

    void update() {
         config.FoundationServo.setPower(FoundationPower);


    }

    @Override
    public void run() {
        if (isFinished()) {
            FoundationPower = -0.2;
            update();
            return;
        }
        update();
    }

}
