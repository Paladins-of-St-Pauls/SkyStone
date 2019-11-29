package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

import java.util.ArrayDeque;

import org.baconeers.SkystoneDrive.SkystoneConfiguration;

public class DetectSkystoneTask extends BaseTask implements Task {

    private final SkystoneConfiguration config;
    private final ArrayDeque<Task> tasks;

    public DetectSkystoneTask(BaconOpMode opMode, double time, SkystoneConfiguration config, ArrayDeque<Task> tasks) {
        super(opMode, time);
        this.config = config;
        this.tasks = tasks;

    }

    void update() {

    }

    @Override
    public void run() {
        if (isFinished()) {

            return;
        }
        // code here
    }

}
