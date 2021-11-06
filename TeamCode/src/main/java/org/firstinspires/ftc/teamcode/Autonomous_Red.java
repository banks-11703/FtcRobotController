package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous(name = "Autonomous_Red", group = "Linear Opmode")
//@Disabled
public class Autonomous_Red extends DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.runtime.reset();
        robot.SpinnerMotor.setPower(0.3);
        sleep(1000);
    }
}