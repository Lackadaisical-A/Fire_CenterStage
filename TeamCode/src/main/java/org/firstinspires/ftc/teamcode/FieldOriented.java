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

@TeleOp(name="Field Oriented Teleop", group="Linear Opmode")
public class FieldOriented extends LinearOpMode {

    //ServoImplEx servo;
    //PwmControl.PwmRange range = new PwmControl.PwmRange(533,2425);
    // Declare OpMode members for each of the 4 motors.
    private ElapsedTime runtime = new ElapsedTime();
    private FireHardwareMap robot = null;
    private ActiveLocation activeLocation = null;
    @Override

    public void runOpMode() {
        robot = new FireHardwareMap(this.hardwareMap);
        activeLocation = new ActiveLocation(robot);

        double drive;
        double strafe;
        double turn;

        double frontLeftPower;
        double frontRightPower;
        double backLeftPower;
        double backRightPower;

        double axial1;

        double currentAngle;

        double maxMotorSpeed = 0.9;

        double maxPower;
        double axial2;
        int filler = 0;

        int heightDifferential = 0;

        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            currentAngle = activeLocation.getTrimmedAngleInRadians();

            drive = -gamepad1.left_stick_y * Math.cos(currentAngle) +
                    gamepad1.left_stick_x * Math.sin(currentAngle);
            strafe = gamepad1.left_stick_x * Math.cos(currentAngle) -
                    -gamepad1.left_stick_y * Math.sin(currentAngle);
            turn = gamepad1.right_stick_x;

            axial1   = -gamepad2.left_stick_y;

            axial2 = -gamepad2.right_stick_y;

            frontLeftPower = drive + strafe + turn;
            frontRightPower = drive - strafe - turn;
            backLeftPower = drive - strafe + turn;
            backRightPower = drive + strafe - turn;


            if (Math.abs(frontLeftPower) > 1 || Math.abs(frontRightPower) > 1 || Math.abs(backLeftPower) > 1 || Math.abs(backRightPower) > 1){
                maxPower = Math.max(Math.abs(frontLeftPower), Math.max(Math.abs(frontRightPower), Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))));

                //fix problem
                frontLeftPower /= maxPower;
                frontRightPower /= maxPower;
                backLeftPower /= maxPower;
                backRightPower /= maxPower;
            }

            if(gamepad1.right_bumper){
                filler=0;
            }
            else{
                frontLeftPower /= 1.8;
                frontRightPower /= 1.8;
                backLeftPower /= 1.8;
                backRightPower /= 1.8;
            }

            robot.FrontLeftMotor.setPower(frontLeftPower * maxMotorSpeed);
            robot.FrontRightMotor.setPower(frontRightPower * maxMotorSpeed);
            robot.BackRightMotor.setPower(backRightPower * maxMotorSpeed);
            robot.BackLeftMotor.setPower(backLeftPower * maxMotorSpeed);




            //TODO: Telemetry
            telemetry.addData("Version: ", "FieldOriented TeleOp 2.0.1");

            telemetry.addData("Front Left Motor Power: ", robot.FrontLeftMotor.getPower());
            telemetry.addData("Front Right Motor Power: ", robot.FrontRightMotor.getPower());
            telemetry.addData("Back Left Motor Power: ", robot.BackLeftMotor.getPower());
            telemetry.addData("Back Right Motor Power: ", robot.BackRightMotor.getPower());

            telemetry.addData("Current frontLeftMotor Encoder Position: ", robot.FrontLeftMotor.getCurrentPosition());
            telemetry.addData("frontLeftMotor Operational: ", robot.FrontLeftMotor.isBusy());
            telemetry.addData("Current frontRightMotor Encoder Position: ", robot.FrontRightMotor.getCurrentPosition());
            telemetry.addData("frontRightMotor Operational: ", robot.FrontRightMotor.isBusy());
            telemetry.addData("Current backLeftMotor Encoder Position: ", robot.BackLeftMotor.getCurrentPosition());
            telemetry.addData("backLeftMotor Operational: ", robot.BackLeftMotor.isBusy());
            telemetry.addData("Current backRightMotor Encoder Position: ", robot.BackRightMotor.getCurrentPosition());
            telemetry.addData("backRightMotor Operational: ", robot.BackRightMotor.isBusy());
            telemetry.update();

        }
    }
}