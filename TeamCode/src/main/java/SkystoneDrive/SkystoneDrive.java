package SkystoneDrive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Baconeers on 8/11/2018.
 */

public class SkystoneDrive {
    // members:
    private OpMode opmode = null;
    private SkystoneConfiguration config = null;
    public boolean buttonState = false;
    public boolean lastButtonState = false;
    public boolean state = false;
    public boolean toggleFunction = false;

   public double toggle(){
        buttonState = opmode.gamepad1.right_bumper;
        if (buttonState && !lastButtonState) {
            state = !state;
        }

        if (buttonState != lastButtonState) {
            lastButtonState = buttonState;
        }


        if (state) {
            //On state
            return 0.5f;

        }
        else {
            //Off state

            return 1.0f;

        }


   }
       public SkystoneDrive(OpMode opmodeIn, SkystoneConfiguration configIn) {
        super();
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        opmode = opmodeIn;
        config = configIn;

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
    }

    public void update(){
        //Drive not working, strafing works fine, turning is faulty and forward isn't working
        //When going forward the front and back wheels are going opposite directions
        //when turning it seems that some wheel might be going slower then others causing it to NOT turn on the centre


        // Setup a variable for each drive wheel to save power level for telemetry
        double frontLeftPower;
        double frontRightPower;
        double backLeftPower  ;
        double backRightPower;

        // Implement Mecanum drive using drive equations from Internet:
        double left_y = opmode.gamepad1.left_stick_y; //forward
        double strafe_left_x  = opmode.gamepad1.left_stick_x; //strafe
        double right_x = -opmode.gamepad1.right_stick_x; //turning

        if (left_y > 0.1 || left_y < -0.1) {
            frontLeftPower = left_y;
            backLeftPower   = left_y;
            frontRightPower = left_y;
            backRightPower = left_y;
        }
        else if (right_x > 0.1 || right_x < -0.1) {
            frontLeftPower = right_x;
            backLeftPower   = right_x;
            frontRightPower = -right_x;
            backRightPower = -right_x;
        }
        else {
            frontLeftPower = Range.clip(left_y + strafe_left_x + right_x, -1.0, 1.0);
            backLeftPower   = Range.clip(left_y - strafe_left_x + right_x, -1.0, 1.0);
            frontRightPower = Range.clip(left_y - strafe_left_x - right_x, -1.0, 1.0);
            backRightPower = Range.clip(left_y + strafe_left_x - right_x, -1.0, 1.0);
        }

        // Send calculated power to wheels
        config.frontLeftMotor.setPower(-frontLeftPower*toggle());
        config.frontRightMotor.setPower(-frontRightPower*toggle());
        config.backLeftMotor.setPower(-backLeftPower*toggle());
        config.backRightMotor.setPower(-backRightPower*toggle());

        // Show the elapsed game time and wheel power.
        opmode.telemetry.addData("Motors", "front left (%.2f), front right (%.2f)",
                frontLeftPower, frontRightPower);
        opmode.telemetry.addData("Motors", "rear left (%.2f), rear right (%.2f)",
                backLeftPower  , backRightPower);
        opmode.telemetry.update();

    }
}