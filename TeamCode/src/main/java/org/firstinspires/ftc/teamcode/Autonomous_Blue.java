package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Autonomous_Blue", group = "Linear Opmode")
//@Disabled
public class Autonomous_Blue extends DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        verticalDrive(10,0.5);
        turn(360,0.5);
        robot.SpinnerMotor.setPower(0.3);
        sleep(1000);
    }
}