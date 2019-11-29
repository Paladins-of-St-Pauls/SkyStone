package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;

public class HarvesterServoTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private double harvesterPosition;

    public HarvesterServoTask(BaconOpMode opMode, double time, SkystoneConfiguration config, double harvesterPosition) {
        super(opMode, time);
        this.config = config;
        this.harvesterPosition = harvesterPosition;

    }

    void update() {
         config.HarvesterServoRight.setPosition(harvesterPosition);
         config.HarvesterServoLeft.setPosition(-harvesterPosition);

    }

    @Override
    public void run() {
        if (isFinished()) {
            harvesterPosition = 0;
            update();
            return;
        }
        update();
    }

}
