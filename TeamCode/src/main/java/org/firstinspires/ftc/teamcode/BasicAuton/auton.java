package org.firstinspires.ftc.teamcode.BasicAuton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FireHardwareMap;

@Autonomous(name="auton", group="Auton")
public class auton extends LinearOpMode {

    FireHardwareMap robot = new FireHardwareMap(hardwareMap);

    @Override
    public void runOpMode() {
        BasicAutoDriving autoDriving = new BasicAutoDriving(robot.frontLeftMotor, robot.frontRightMotor, robot.backLeftMotor, robot.backRightMotor);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        if (opModeIsActive()){
            autoDriving.drive(10);
            //If it doesn't go far enough than increase the value of ticksPerDegree


        }

    }
}
