package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Autonomous_Red_v2", group = "Linear Opmode")
//@Disabled
public class Autonomous_Red_v2 extends DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        ResetWheelEncoders();
        waitForStart();
        verticalDrive(-5,0.3);
        robot.PivotMotor.setTargetPosition(Raised);
        robot.PivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.PivotMotor.setPower(0.3);
        while (robot.PivotMotor.isBusy()){}
        robot.PivotMotor.setPower(0);
        horizontalDrive(23,0.5);
        robot.PivotMotor.setTargetPosition(Scoring);
        robot.PivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.PivotMotor.setPower(0.3);
        while (robot.PivotMotor.isBusy()){}
        robot.PivotMotor.setPower(0);
        verticalDrive(-3,0.5);
        ScoreMid();
        sleep(2000);
        HoldMid();
        HighHold();
        horizontalDrive(51, 0.5);
        verticalDrive(-19,0.5);
        robot.PivotMotor.setTargetPosition(Intake);
        robot.PivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.PivotMotor.setPower(0.3);
        while (robot.PivotMotor.isBusy()){}
        robot.PivotMotor.setPower(0);

    }
}