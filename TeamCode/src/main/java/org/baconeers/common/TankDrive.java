package org.baconeers.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Shaun on 2/07/2017.
 */

public class TankDrive extends BaconComponent {

    final private DcMotor leftMotor;
    final private DcMotor rightMotor;
    final private Gamepad gamepad;
    final private Telemetry.Item leftPowerItem ;
    final private Telemetry.Item rightPowerItem;

    private double leftPower;
    private double rightPower;


    public TankDrive(BaconOpMode opMode, Gamepad gamepad, DcMotor leftMotor, DcMotor rightMotor) {
        super(opMode);

        this.gamepad = gamepad;
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;

        leftPower = 0;
        rightPower = 0;

        leftPowerItem = getOpMode().telemetry.addData("Left power", "%.2f", 0.0f);
        leftPowerItem.setRetained(true);
        rightPowerItem = getOpMode().telemetry.addData("Right power", "%.2f", 0.0f);
        rightPowerItem.setRetained(true);
    }

    public void setPower(double left, double right) {
        leftPower = left;
        rightPower = right;
    }

    /*
     * Update the motor power based on the gamepad state
     */
    public void update() {
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);

        leftPowerItem.setValue("%.2f", leftPower);
        rightPowerItem.setValue("%.2f", rightPower);
    }

    private static float[] power_curve =
            {0.00f, 0.1f, 0.3f, 0.7f, 1.0f, 1.0f };

    /**
     * The  DC motors are scaled to make it easier to control them at slower speeds
     * The clip method guarantees the value never exceeds the range 0-1.
     */
    private float scaleTriggerPower(float power) {

        // Ensure the values are legal.
        float clipped_power = Range.clip(power, -1, 1);

        // Remember if this is positive or negative
        float sign = Math.signum(clipped_power);

        // Work only with positive numbers for simplicity
        float abs_power = Math.abs(clipped_power);

        // Map the power value [0..1.0] to a power curve index
        int index = (int) (abs_power * (power_curve.length - 1));

        float scaled_power = sign * power_curve[index];

        return scaled_power;
    }


    private static float[] steer_curve =
            {0.00f, 0.2f, 0.2f, 0.4f, 0.6f, 0.7f, 0.8f, 1.0f};

    private float scaleSteerPower(float p_power) {

        // Ensure the values are legal.
        float clipped_power = Range.clip(p_power, -1, 1);

        // Remember if this is positive or negative
        float sign = Math.signum(clipped_power);

        // Work only with positive numbers for simplicity
        float abs_power = Math.abs(clipped_power);

        // Map the power value [0..1.0] to a power curve index
        int index = (int) (abs_power * (steer_curve.length - 1));

        float scaled_power = sign * steer_curve[index];

        return scaled_power;

    }

}
