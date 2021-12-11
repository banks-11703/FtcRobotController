package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Auto_Red_Park", group = "Linear Opmode")
@Disabled
public class Auto_Red_Park extends DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        ResetWheelEncoders();
        waitForStart();
        verticalDrive(-3,0.3);
        robot.PivotMotor.setTargetPosition(Raised);
        robot.PivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.PivotMotor.setPower(0.3);
        while (robot.PivotMotor.isBusy()){}
        robot.PivotMotor.setPower(0);
        horizontalDrive(24,0.5);
        turn(-50,0.5);
        robot.SpinnerMotor.setPower(0.25);
        sleep(3000);
        turn(50,0.5);
        verticalDrive(-10, 0.3);
        robot.PivotMotor.setTargetPosition(Intake);
        robot.PivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.PivotMotor.setPower(0.3);
        while (robot.PivotMotor.isBusy()){}
        robot.PivotMotor.setPower(0);
        //Should work, maybe
    }
}