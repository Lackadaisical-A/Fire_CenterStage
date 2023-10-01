package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**

 *
 * 1) Axial:    Driving forward and backward               Left-joystick Forward/Backward
 * 2) Lateral:  Strafing right and left                     Left-joystick Right and Left
 * 3) Yaw:      Rotating Clockwise and counter clockwise    Right-joystick Right and Left
 *

 */

@TeleOp(name="Robot Oriented TeleOp", group="Linear Opmode")
public class LinearTeleOp extends LinearOpMode {

    //ServoImplEx servo;
    //PwmControl.PwmRange range = new PwmControl.PwmRange(533,2425);
    // Declare OpMode members for each of the 4 motors.
    private ElapsedTime runtime = new ElapsedTime();
    private FireHardwareMap HW = null;

    @Override

    public void runOpMode() {
        HW = new FireHardwareMap(this.hardwareMap);

        //servo = hardwareMap.get(ServoImplEx.class, "left_hand");
        //servo.setPwmRange(range);


        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double max;
            double i =0.0;


            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.left_stick_x * 1.1;
            double yaw     =  gamepad1.right_stick_x;

            double axial1   = -gamepad2.left_stick_y;
            double axial2 = -gamepad2.right_stick_y;
            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double leftFrontPower  = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower   = axial - lateral + yaw;
            double rightBackPower  = axial + lateral - yaw;


            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1) {
                leftFrontPower  /= max;
                rightFrontPower /= max;
                leftBackPower   /= max;
                rightBackPower  /= max;
                axial1 /=max;
                axial2 /=max;
            }
            if(gamepad1.right_bumper){
                i = 0.8;
            }
            else{
                leftFrontPower  /= 2;
                rightFrontPower /= 2;
                leftBackPower   /= 2;
                rightBackPower  /= 2;
            }


            axial2 = axial2/1.5;

            // Send calculated power to wheels
            HW.FrontLeftMotor.setPower(leftFrontPower);
            HW.FrontRightMotor.setPower(rightFrontPower);
            HW.BackLeftMotor.setPower(leftBackPower);
            HW.BackRightMotor.setPower(rightBackPower);


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.addData("Servo  left/Right", "%4.2f, %4.2f", axial1, axial1);

            telemetry.addData("Current frontLeftMotor Encoder Position: ", HW.FrontLeftMotor.getCurrentPosition());
            telemetry.addData("frontLeftMotor Operational: ", HW.FrontLeftMotor.isBusy());
            telemetry.addData("Current frontRightMotor Encoder Position: ", HW.FrontRightMotor.getCurrentPosition());
            telemetry.addData("frontRightMotor Operational: ", HW.FrontRightMotor.isBusy());
            telemetry.addData("Current backLeftMotor Encoder Position: ", HW.BackLeftMotor.getCurrentPosition());
            telemetry.addData("backLeftMotor Operational: ", HW.BackLeftMotor.isBusy());
            telemetry.addData("Current backRightMotor Encoder Position: ", HW.BackRightMotor.getCurrentPosition());
            telemetry.addData("backRightMotor Operational: ", HW.BackRightMotor.isBusy());
            telemetry.update();

            telemetry.update();
        }
    }}