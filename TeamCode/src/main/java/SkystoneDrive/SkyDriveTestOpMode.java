package SkystoneDrive;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.baconeers.common.BaconOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "Drive Speed Test")
public class SkyDriveTestOpMode extends BaconOpMode {
    private SkystoneConfiguration config;
    private NormalisedMecanumDrive mecanumDrive = null;

    private Telemetry.Item directionItem = null;
    private Telemetry.Item speedItem = null;
    private Telemetry.Item timeItem = null;


    /**
     * the number of nanoseconds in a second
     */
    public static final double NANOS_IN_SECONDS = 1000000000.0;

    public enum Direction {
        NORTH(1.0, 0.0, 0.0),
        NORTHEAST(1.0, 1.0, 0.0),
        EAST(0.0, 1.0, 0.0),
        SOUTHEAST(-1.0, 1.0, 0.0),
        SOUTH(-1.0, 0.0, 0.0),
        SOUTHWEST(-1.0, -1.0, 0.0),
        WEST(0.0, -1.0, 0.0),
        NORTHWEST(1.0, -1.0, 0.0),
        ROTATE_RIGHT(1.0, 0.0, 1.0),
        ROTATE_LEFT(1.0, 0.0, -1.0);

        double forward;
        double right;
        double rotate;

        Direction(double f, double l, double r) {
            forward = f;
            right = l;
            rotate = r;
        }
    }

    double[] speeds = {0.3, 0.7, 1.0};
    double[] times = {1.0, 3.0, 5.0};

    int speed_index = 0;
    int time_index = 0;
    int direction_index = 0;

    boolean running = false;
    double start_time_secs = 0.0;

    @Override
    protected void onInit() {
        config = SkystoneConfiguration.newConfig(hardwareMap, telemetry);

        directionItem = telemetry.addData("Direction: ", Direction.values()[direction_index]);
        speedItem = telemetry.addData("Speed: ", speeds[speed_index]);
        timeItem = telemetry.addData("Time: ", times[time_index]);

        directionItem.setRetained(true);
        speedItem.setRetained(true);
        timeItem.setRetained(true);

        mecanumDrive = new NormalisedMecanumDrive(this,
                config.frontLeftMotor, config.frontRightMotor,
                config.backLeftMotor, config.backRightMotor,
                true);
    }

    @Override
    protected void activeLoop() throws InterruptedException {

        // Adjust the speed index using dpad up/down
        if (gamepad1.dpad_up) {
            speed_index++;
        }
        if (gamepad1.dpad_down) {
            speed_index--;
        }
        speed_index = Range.clip(speed_index, 0, speeds.length - 1);

        // Adjust the time index using dpad right/right
        if (gamepad1.dpad_right) {
            time_index++;
        }
        if (gamepad1.dpad_left) {
            time_index--;
        }
        time_index = Range.clip(time_index, 0, times.length - 1);

        // Adjust the direction index using right/right shoulder buttons
        if (gamepad1.right_bumper) {
            direction_index++;
        }
        if (gamepad1.left_bumper) {
            direction_index--;
        }
        direction_index = Range.clip(direction_index, 0, Direction.values().length - 1);

        if (gamepad1.a) {
            running = true;
            start_time_secs = System.nanoTime() / NANOS_IN_SECONDS;
        }

        if (gamepad1.b) {
            running = false;
            start_time_secs = 0.0;
        }

        Direction direction = Direction.values()[direction_index];
        double speed_factor = speeds[speed_index];
        double time = times[time_index];

        double current_time = System.nanoTime() / NANOS_IN_SECONDS;
        if (current_time > time + start_time_secs) {
            running = false;
            start_time_secs = 0.0;
        }

        if (running) {
            mecanumDrive.setSpeedXYR(direction.forward * speed_factor,
                    direction.right * speed_factor,
                    direction.rotate * speed_factor);

        } else {
            mecanumDrive.setSpeedXYR(0.0,0.0,0.0);
        }

        directionItem.setValue(direction);
        speedItem.setValue("%.2f", speed_factor);
        timeItem.setValue("%.2f", time);
        mecanumDrive.update();
    }
}
