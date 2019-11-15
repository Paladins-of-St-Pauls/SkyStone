package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import SkystoneDrive.SkystoneConfiguration;

public class FoundationTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private double HarvesterPower;

    public FoundationTask(BaconOpMode opMode, double time, SkystoneConfiguration config, double HarvesterPower) {
        super(opMode, time);
        this.config = config;
        this.HarvesterPower = HarvesterPower;

    }

    void update() {
         config.HarvesterServoLeft.setPosition(HarvesterPower);
         config.HarvesterServoRight.setPosition(HarvesterPower);

    }

    @Override
    public void run() {
        if (isFinished()) {
            HarvesterPower = 0;
            update();
            return;
        }
        update();
    }

}
