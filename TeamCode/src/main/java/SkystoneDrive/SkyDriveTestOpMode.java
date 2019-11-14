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

    private boolean dpad_up_last = false;
    private boolean dpad_down_last = false;
    private boolean dpad_left_last = false;
    private boolean dpad_right_last = false;
    private boolean right_bumper_last = false;
    private boolean left_bumper_last = false;
    private boolean a_last = false;
    private boolean b_last = false;


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
        ROTATE_RIGHT(0.0, 0.0, 1.0),
        ROTATE_LEFT(0.0, 0.0, -1.0);

        double forward;
        double right;
        double rotate;

        Direction(double f, double l, double r) {
            forward = f;
            right = l;
            rotate = r;
        }
    }

    double[] speeds = {0.3, 0.5, 0.7, 0.8, 1.0};
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
        if (!dpad_up_last && gamepad1.dpad_up) {
            speed_index++;
        }
        dpad_up_last = gamepad1.dpad_up;

        if (!dpad_down_last && gamepad1.dpad_down) {
            speed_index--;
        }
        dpad_down_last = gamepad1.dpad_down;
        speed_index = Range.clip(speed_index, 0, speeds.length - 1);

        // Adjust the time index using dpad right/right
        if (!dpad_right_last && gamepad1.dpad_right) {
            time_index++;
        }
        dpad_right_last = gamepad1.dpad_right;
        if (!dpad_left_last && gamepad1.dpad_left) {
            time_index--;
        }
        dpad_left_last = gamepad1.dpad_left;
        time_index = Range.clip(time_index, 0, times.length - 1);

        // Adjust the direction index using right/right shoulder buttons
        if (!right_bumper_last && gamepad1.right_bumper) {
            direction_index++;
        }
        right_bumper_last = gamepad1.right_bumper;
        if (!left_bumper_last && gamepad1.left_bumper) {
            direction_index--;
        }
        left_bumper_last = gamepad1.left_bumper;
        direction_index = Range.clip(direction_index, 0, Direction.values().length - 1);

        if (!a_last && gamepad1.a) {
            running = true;
            start_time_secs = System.nanoTime() / NANOS_IN_SECONDS;
        }
        a_last = gamepad1.a;

        if (!b_last && gamepad1.b) {
            running = false;
            start_time_secs = 0.0;
        }
        b_last = gamepad1.b;

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
            mecanumDrive.setSpeedXYR(-gamepad1.left_stick_y, gamepad1.left_stick_x , gamepad1.right_stick_x );

        }

        directionItem.setValue(direction);
        speedItem.setValue("%.2f", speed_factor);
        timeItem.setValue("%.2f", time);
        mecanumDrive.update();
    }
}
