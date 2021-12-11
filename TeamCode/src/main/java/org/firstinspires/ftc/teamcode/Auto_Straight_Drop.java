package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Auto_Straight_Drop", group = "Linear Opmode")
@Disabled
public class Auto_Straight_Drop extends DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        ResetWheelEncoders();
        waitForStart();
        verticalDrive(-3,0.7);
        robot.PivotMotor.setTargetPosition(Raised);
        robot.PivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.PivotMotor.setPower(0.3);
        while (robot.PivotMotor.isBusy()){}
        robot.PivotMotor.setPower(0);
        verticalDrive(13, 0.3);
        robot.PivotMotor.setTargetPosition(Scoring);
        robot.PivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.PivotMotor.setPower(0.3);
        while (robot.PivotMotor.isBusy()){}
        robot.PivotMotor.setPower(0);
        sleep(1000);
        ScoreMid();
        robot.PivotMotor.setTargetPosition(Intake);
        robot.PivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.PivotMotor.setPower(0.3);
        while (robot.PivotMotor.isBusy()){}
        robot.PivotMotor.setPower(0);
        //Should work, maybe
    }
}