package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Auto_Forward", group = "Linear Opmode")
@Disabled
public class Auto_Forward extends DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        ResetWheelEncoders();
        waitForStart();
        verticalDrive(-5, 0.7);
        robot.PivotMotor.setTargetPosition(Raised);
        robot.PivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.PivotMotor.setPower(0.3);
        while (robot.PivotMotor.isBusy()) {
        }
        robot.PivotMotor.setPower(0);
        verticalDrive(-23, 0.5);
        robot.PivotMotor.setTargetPosition(Intake);
        robot.PivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.PivotMotor.setPower(0.3);
        while (robot.PivotMotor.isBusy()) {
        }
        robot.PivotMotor.setPower(0);
    }
}