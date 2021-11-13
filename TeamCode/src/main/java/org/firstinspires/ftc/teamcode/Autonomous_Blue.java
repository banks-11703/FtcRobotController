package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Autonomous_Blue", group = "Linear Opmode")
//@Disabled
public class Autonomous_Blue extends DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        ResetWheelEncoders();
        waitForStart();
        verticalDrive(-3,0.3);
        robot.PivotMotor.setTargetPosition(144);
        robot.PivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.PivotMotor.setPower(0.3);
        while (robot.PivotMotor.isBusy()){}
        robot.PivotMotor.setPower(0);
        horizontalDrive(-33,0.5);

        turn(-41,0.5);

        robot.SpinnerMotor.setPower(0.33);
        sleep(3000);

        turn(100,0.5);

    }
}