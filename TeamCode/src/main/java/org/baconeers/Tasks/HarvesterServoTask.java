package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import SkystoneDrive.SkystoneConfiguration;

public class HarvesterServoTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private double HarvesterPosition;

    public HarvesterServoTask(BaconOpMode opMode, double time, SkystoneConfiguration config, double HarvesterPosition) {
        super(opMode, time);
        this.config = config;
        this.HarvesterPosition = HarvesterPosition;

    }

    void update() {
         config.HarvesterServoRight.setPosition(HarvesterPosition);
         config.HarvesterServoLeft.setPosition(HarvesterPosition);

    }

    @Override
    public void run() {
        if (isFinished()) {
            HarvesterPosition = 0;
            update();
            return;
        }
        update();
    }

}
