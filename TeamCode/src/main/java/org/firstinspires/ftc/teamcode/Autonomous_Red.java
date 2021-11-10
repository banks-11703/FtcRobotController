package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Autonomous_Red", group = "Linear Opmode")
//@Disabled
public class Autonomous_Red extends DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.runtime.reset();
        verticalDrive(-3,0.3);
        robot.PivotMotor.setTargetPosition(144);
        robot.PivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.PivotMotor.setPower(0.3);
        while (robot.PivotMotor.isBusy()){}
        robot.PivotMotor.setPower(0);
        horizontalDrive(20,0.5);
    }
}