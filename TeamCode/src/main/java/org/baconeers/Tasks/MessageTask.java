package org.baconeers.Tasks;

import org.baconeers.common.BaconOpMode;

public class MessageTask implements Task {

    private String message;
    private double time;
    private boolean isFinished = false;
    private double startTime = 0;
    private BaconOpMode opMode;


    /**
     * the number of nanoseconds in a second
     */
    public static final double NANOS_IN_SECONDS = 1000000000.0;


    public MessageTask(BaconOpMode opMode, String message, double time) {

        this.message = message;
        this.time = time;
        this.opMode = opMode;

    }

    @Override
    public void run() {
        if (startTime == 0) {
            startTime = getCurrentTime();
        }
        if (getCurrentTime() > (startTime + time)) {
            isFinished = true;
            return;
        }
        opMode.telemetry.addLine(String.format("%s %.2f", message, time));
    }


    @Override
    public boolean isFinished() {
        return isFinished;
    }

    private double getCurrentTime() {
        return System.nanoTime() / NANOS_IN_SECONDS;
    }

}
