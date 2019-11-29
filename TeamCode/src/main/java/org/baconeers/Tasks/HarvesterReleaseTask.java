package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;

public class HarvesterReleaseTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private double harvesterPower;

    public HarvesterReleaseTask(BaconOpMode opMode, double time, SkystoneConfiguration config, double harvesterPower) {
        super(opMode, time);
        this.config = config;
        this.harvesterPower = harvesterPower;

    }

    void update() {
         config.HarvesterServoRight.setPosition(harvesterPower);
         config.HarvesterServoLeft.setPosition(harvesterPower);
    }

    @Override
    public void run() {
        if (isFinished()) {
            harvesterPower = 0;
            update();
            return;
        }
        update();
    }

}
