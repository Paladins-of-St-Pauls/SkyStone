package org.baconeers.Tasks;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;
import org.baconeers.common.BaconOpMode;

public class WaitTask extends BaseTask implements Task {


    public WaitTask(BaconOpMode opMode, double time) {
        super(opMode, time);

    }

    void update() {

    }

    @Override
    public void run() {
        if (isFinished()) {
            return;
        }

    }

}
