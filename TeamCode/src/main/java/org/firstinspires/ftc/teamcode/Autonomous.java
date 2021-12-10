package org.firstinspires.ftc.teamcode;



@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous", group = "Robot_2")
//@Disabled
public class Autonomous extends Robot_2_DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        ResetWheelEncoders();
        while(!opModeIsActive()) {
            button_a_is_pressed = gamepad1.a;
            button_b_is_pressed = gamepad1.b;
            button_x_is_pressed = gamepad1.x;
            if (button_a_is_pressed && !button_a_was_pressed) {
                team++;
                button_a_was_pressed = true;
            } else if (!button_a_is_pressed && button_a_was_pressed) {
                button_a_was_pressed = false;
            }
            if (button_x_is_pressed && !button_x_was_pressed) {
            side++;
                button_x_was_pressed = true;
            } else if (!button_x_is_pressed && button_x_was_pressed) {
                button_x_was_pressed = false;
            }
            if (button_b_is_pressed && !button_b_was_pressed) {
            mode++;
                button_b_was_pressed = true;
            } else if (!button_b_is_pressed && button_b_was_pressed) {
                button_b_was_pressed = false;
            }
            if (Team() == 0 && Mode() == 0 && Side() == 0) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Nothing");
                telemetry.update();
            }
            if (Team() == 1 && Mode() == 0 && Side() == 0) {
                telemetry.addData("Team", "Blue");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Nothing");
                telemetry.update();
            }

            if (Team() == 0 && Mode() == 1 && Side() == 0) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Right");
                telemetry.addData("Mode", "Nothing");
                telemetry.update();
            }
            if (opModeIsActive()){ // ONLY MOVE AT 0.1!!!
                if (Team() == 0 && Mode() == 0 && Side() == 0) {
                    telemetry.addData("Team", "Red");
                    telemetry.addData("Side", "Left");
                    telemetry.addData("Mode", "Nothing");
                    telemetry.update();
                    verticalDrive(10,0.1);
                }
                if (Team() == 1 && Mode() == 0 && Side() == 0) {
                    telemetry.addData("Team", "Blue");
                    telemetry.addData("Side", "Left");
                    telemetry.addData("Mode", "Nothing");
                    telemetry.update();
                    verticalDrive(5,0.1);
                }







            }
        }
    }
}