package org.baconeers.Tasks;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;
import org.baconeers.common.BaconOpMode;

public class SkystoneServoTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private double skystoneServoPosition;

    public SkystoneServoTask(BaconOpMode opMode, double time, SkystoneConfiguration config, double skystoneServoPosition) {
        super(opMode, time);
        this.config = config;
        this.skystoneServoPosition = skystoneServoPosition;

    }

    void update() {
         config.harvesterServoRight.setPosition(skystoneServoPosition);

    }

    @Override
    public void run() {
        if (isFinished()) {
            update();
            return;
        }
        update();
    }

}
