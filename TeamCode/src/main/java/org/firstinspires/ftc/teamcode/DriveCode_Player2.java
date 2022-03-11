package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@TeleOp(name = "DriveCode_Player2", group = "Robot 2")
public class DriveCode_Player2 extends DriveCodeCommon_Teleop {

    @Override
    public void runOpMode() {
        teleop.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        Telemetry();
        teleop.cappingServoY.scaleRange(0.55, 0.75);
        waitForStart();
        teleop.runtime.reset();
        teleop.redLED.setMode(DigitalChannel.Mode.OUTPUT);
        teleop.greenLED.setMode(DigitalChannel.Mode.OUTPUT);
        teleop.redLED1.setMode(DigitalChannel.Mode.OUTPUT);
        teleop.greenLED1.setMode(DigitalChannel.Mode.OUTPUT);
        while (opModeIsActive()) {
            telemetry.update();
            Telemetry();
            if (gamepad1.left_stick_button){ speed = 1;} else {speed = 0;}
             if (Override == 1) {
                Player_2_Override();
            }else if(speed == 1){
                Player_2_FastDrive();
            }else{
                 Player_2_Drive();
             }
            Toggles_2P();
            SetServoPosition();
            capping();
            sensors();
            autoWarehouse();
            screw();
            intake();
            duckSpinner(RobotConstants.initDuckSpeed,RobotConstants.duckrate);
            //showdown is when button a,b,y all pressed
            if (shutdown) {
                screwtoggle = 0;
                intaketoggle = 0;
                teleop.Bottom_Intake_Motor.setPower(0);
            }
            telemetry.update();
        }
    }
}




