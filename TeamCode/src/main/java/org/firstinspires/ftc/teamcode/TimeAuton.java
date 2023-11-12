package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous
public class TimeAuton extends LinearOpMode {
    private FireHardwareMap hwMap = null;
    private ElapsedTime runtime = new ElapsedTime();
    final double mecanumWheelCircumference = Math.PI*3.5; // note that this is slightly lower, adjusting for rolling w/ slipping

    long millisecondsPerTile = 1000; // at 0.9 power

    DcMotor frontRightMotor = null;
    DcMotor frontLeftMotor = null;
    DcMotor backRightMotor = null;
    DcMotor backLeftMotor = null;
    public void runOpMode() {
        hwMap = new FireHardwareMap(this.hardwareMap);
        frontRightMotor = hwMap.frontRightMotor;
        frontLeftMotor = hwMap.frontLeftMotor;
        backRightMotor = hwMap.backRightMotor;
        backLeftMotor = hwMap.backLeftMotor;

        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // starting sequence
        telemetry.addData("Robot Status: ", "ready");
        telemetry.addData("Version: ", "Auton 1.4");
        telemetry.update();

        waitForStart();
        runtime.reset();
    }

    public void drive(int numOfTiles) {
        frontLeftMotor.setPower(0.9);
        frontRightMotor.setPower(0.9);
        backLeftMotor.setPower(0.9);
        backRightMotor.setPower(0.9);

        sleep(millisecondsPerTile*numOfTiles);

        frontRightMotor.setPower(0.0);
        frontLeftMotor.setPower(0.0);
        backLeftMotor.setPower(0.0);
        backRightMotor.setPower(0.0);
    }

    public void strafe(int numOfTiles, boolean right) {
        int dir = right ? -1 : 1;
        frontLeftMotor.setPower(-1*dir*0.9);
        frontRightMotor.setPower(dir*0.9);
        backLeftMotor.setPower(dir*0.9);
        backRightMotor.setPower(-1*dir*0.9);

        sleep(millisecondsPerTile*numOfTiles);

        frontRightMotor.setPower(0.0);
        frontLeftMotor.setPower(0.0);
        backLeftMotor.setPower(0.0);
        backRightMotor.setPower(0.0);
    }
}
