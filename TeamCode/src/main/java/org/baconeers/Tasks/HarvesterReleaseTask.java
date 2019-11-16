package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import SkystoneDrive.SkystoneConfiguration;

public class HarvesterReleaseTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private double HarvesterPower;

    public HarvesterReleaseTask(BaconOpMode opMode, double time, SkystoneConfiguration config, double HarvesterPower) {
        super(opMode, time);
        this.config = config;
        this.HarvesterPower = HarvesterPower;

    }

    void update() {
         config.HarvesterServoRight.setPosition(HarvesterPower);
         config.HarvesterServoLeft.setPosition(HarvesterPower);
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
