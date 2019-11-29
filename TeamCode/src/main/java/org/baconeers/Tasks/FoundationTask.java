package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;

public class FoundationTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private double position;

    public FoundationTask(BaconOpMode opMode, double time, SkystoneConfiguration config, double position) {
        super(opMode, time);
        this.config = config;
        this.position = position;

    }

    void update() {
         config.FoundationServo.setPosition(position);
        // Position 1.0 = up
        // Position 0.0 = down

    }

    @Override
    public void run() {
        if (isFinished()) {
            return;
        }
        update();
    }

}
