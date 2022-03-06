package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "DriveCode_Player2", group = "Robot 2")
public class DriveCode_Player2 extends DriveCodeCommon_Teleop {

    @Override
    public void runOpMode() {
        teleop.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        Telemetry();
        waitForStart();
        teleop.runtime.reset();
        while (opModeIsActive()) {
            telemetry.update();
            Telemetry();
             if (Override == 1) {
                Player_2_Override();
            }else if(speed == 1){

            }else{
                 Player_2_Drive();
             }
            Toggles_2P();
            SetServoPosition();
            capping();
            lightSequence();
            sensors();
            autoWarehouse();
            screw();
            intake();
            if (shutdown) {
                screwtoggle = 0;
                intaketoggle = 0;
                teleop.Bottom_Intake_Motor.setPower(0);
            }
            telemetry.update();
        }
    }
}




