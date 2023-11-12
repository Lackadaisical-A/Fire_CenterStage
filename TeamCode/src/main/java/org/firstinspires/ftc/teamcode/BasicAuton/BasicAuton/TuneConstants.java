package org.firstinspires.ftc.teamcode.BasicAuton.BasicAuton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BasicAuton.BasicAutoDriving;
import org.firstinspires.ftc.teamcode.FireHardwareMap;

@Autonomous(name="TuneConstants", group="Auton")
public class TuneConstants extends LinearOpMode {

    FireHardwareMap robot = new FireHardwareMap(hardwareMap);

    @Override
    public void runOpMode() {
        org.firstinspires.ftc.teamcode.BasicAuton.BasicAutoDriving autoDriving = new BasicAutoDriving(robot.frontLeftMotor, robot.frontRightMotor, robot.backLeftMotor, robot.backRightMotor);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        if (opModeIsActive()){

            //Should go forward 90 cm
            autoDriving.drive(90);
            sleep(3000);
            telemetry.addData("fl ticks", autoDriving.frontLeftMotor.getCurrentPosition());
            telemetry.addData("fr ticks", autoDriving.frontRightMotor.getCurrentPosition());
            telemetry.addData("bl ticks", autoDriving.backLeftMotor.getCurrentPosition());
            telemetry.addData("br ticks", autoDriving.backRightMotor.getCurrentPosition());
            telemetry.update();
            sleep(3000);




            //should strafe to the right 90 cm
            autoDriving.strafe(90);
            sleep(2500);
            telemetry.addData("fl ticks", autoDriving.frontLeftMotor.getCurrentPosition());
            telemetry.addData("fr ticks", autoDriving.frontRightMotor.getCurrentPosition());
            telemetry.addData("bl ticks", autoDriving.backLeftMotor.getCurrentPosition());
            telemetry.addData("br ticks", autoDriving.backRightMotor.getCurrentPosition());
            telemetry.update();
            sleep(3000);
            //If it goes to far lower the value of ticksPerCentimeterStrafe in the Basic Auto Driving file
            //If it doesn't go far enough than increase the value of ticksPerCentimeterStrafe


            autoDriving.turn(360);
            telemetry.addData("fl ticks", autoDriving.frontLeftMotor.getCurrentPosition());
            telemetry.addData("fr ticks", autoDriving.frontRightMotor.getCurrentPosition());
            telemetry.addData("bl ticks", autoDriving.backLeftMotor.getCurrentPosition());
            telemetry.addData("br ticks", autoDriving.backRightMotor.getCurrentPosition());
            telemetry.update();
            sleep(3000);
            //should turn 360 degrees clockwise
            //If it goes to far lower the value of ticksPerDegree in the Basic Auto Driving file
            //If it doesn't go far enough than increase the value of ticksPerDegree


        }

    }
}
