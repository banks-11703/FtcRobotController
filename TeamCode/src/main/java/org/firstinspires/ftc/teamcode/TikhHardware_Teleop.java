/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.util.ElapsedTime;


public class TikhHardware_Teleop
{
    public DcMotor FrontLeftDrive = null;
    public DcMotor BackLeftDrive = null;
    public DcMotor FrontRightDrive = null;
    public DcMotor BackRightDrive = null;
    public Servo HighGoal;
    public Servo LowGoal;
    public DcMotor Top_Intake_Motor;
    public DcMotor Bottom_Intake_Motor;// also duck spinner
    public DcMotor Screw_Motor;
    public DcMotor cappingMotor;
    public Servo Stopper_Servo;
    public Servo cappingServoX;
    public Servo cappingServoY;
    public TouchSensor ScrewDetector;
    public TouchSensor intakeDetector;
    public DistanceSensor intakeSensor;
    public DigitalChannel redLED;
    public DigitalChannel greenLED;
    public DigitalChannel redLED1;
    public DigitalChannel greenLED1;
    public ElapsedTime runtime = new ElapsedTime();
    HardwareMap hwMap           =  null;


    public TikhHardware_Teleop(){

    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        FrontLeftDrive = hwMap.get(DcMotor.class, "fl");
        FrontRightDrive = hwMap.get(DcMotor.class, "fr");
        BackLeftDrive = hwMap.get(DcMotor.class, "bl");
        BackRightDrive = hwMap.get(DcMotor.class, "br");
        HighGoal = hwMap.get(Servo.class, "hg");
        LowGoal = hwMap.get(Servo.class, "lg");
        Top_Intake_Motor = hwMap.get(DcMotor.class, "ti");
        Bottom_Intake_Motor = hwMap.get(DcMotor.class, "bi");
        Screw_Motor = hwMap.get(DcMotor.class, "sm");
        cappingMotor = hwMap.get(DcMotor.class, "cm");
        Stopper_Servo = hwMap.get(Servo.class, "ss");
        cappingServoX = hwMap.get(Servo.class, "leftrightcappingservo");
        cappingServoY = hwMap.get(Servo.class, "updowncappingservo");
        ScrewDetector = hwMap.get(TouchSensor.class, "ts");
        intakeDetector = hwMap.get(TouchSensor.class, "ID");
        intakeSensor = hwMap.get(DistanceSensor.class,"is");
        redLED= hwMap.get(DigitalChannel.class, "red");
        greenLED = hwMap.get(DigitalChannel.class, "green");
        redLED1 = hwMap.get(DigitalChannel.class, "red1");
        greenLED1 = hwMap.get(DigitalChannel.class, "green1");
        FrontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        FrontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        BackLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        BackRightDrive.setDirection(DcMotor.Direction.REVERSE);
        Bottom_Intake_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
 }

