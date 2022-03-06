
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "DriveCode_Player1", group = "Robot 2")
public class DriveCode_Player1 extends DriveCodeCommon_Teleop {

    @Override
    public void runOpMode() {
        teleop.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        Telemetry();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.update();
            Telemetry();
            Player_1_Drive();
            Toggles1P();
            SetServoPosition();
            sensors();
            autoWarehouse();
            screw();
            intake();
            teleop.cappingServoY.setPosition(0.15);
            teleop.cappingServoX.setPosition(0.5);
            telemetry.update();
        }

    }

}




