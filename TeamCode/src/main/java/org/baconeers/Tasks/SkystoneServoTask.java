package org.baconeers.Tasks;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;
import org.baconeers.common.BaconOpMode;

public class SkystoneServoTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private double position;

    public SkystoneServoTask(BaconOpMode opMode, double time, SkystoneConfiguration config, double position) {
        super(opMode, time);
        this.config = config;
        this.position = position;

    }

    void update() {
         config.skystoneServo.setPosition(position);

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
