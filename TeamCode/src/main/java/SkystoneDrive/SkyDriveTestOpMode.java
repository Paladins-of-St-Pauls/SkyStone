package SkystoneDrive;



import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.baconeers.common.BaconOpMode;

@TeleOp(name = "Drive Test")
public class SkyDriveTestOpMode extends BaconOpMode {
    private SkystoneConfiguration config;
    private SkystoneDrive drive;
public enum Direction{
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NW,
    NE,
    SW,
    SE,
    ROTATIONRIGHT,
    ROTATIONLEFT

}

double [] speeds = {0.3,0.7,1.0};
double [] time = {1.0,3.0,5.0};

    @Override
    protected void onInit() {
       config  = SkystoneConfiguration.newConfig(hardwareMap, telemetry);

       drive = new SkystoneDrive(this, config);

    }

    @Override
    protected void activeLoop() throws InterruptedException {
        drive.update();

//        if (config.touchSensor.isPressed()) {
//            telemetry.addLine("Touch pressed");
//        }
    }
}
