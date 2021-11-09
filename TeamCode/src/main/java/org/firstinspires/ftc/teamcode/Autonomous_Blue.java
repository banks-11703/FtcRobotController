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

        horizontalDrive(-33,0.5);

        turn(-41,0.5);

        robot.SpinnerMotor.setPower(0.33);
        sleep(3000);

        turn(100,0.5);

    }
}