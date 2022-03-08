package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.drive.DriveConstants.MAX_ACCEL;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.MAX_ANG_ACCEL;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.MAX_ANG_VEL;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.MAX_VEL;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.MOTOR_VELO_PID;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.RUN_USING_ENCODER;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.TRACK_WIDTH;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.encoderTicksToInches;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.kA;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.kStatic;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.kV;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.acmerobotics.roadrunner.followers.HolonomicPIDVAFollower;
import com.acmerobotics.roadrunner.followers.TrajectoryFollower;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceRunner;
import org.firstinspires.ftc.teamcode.util.AxisDirection;
import org.firstinspires.ftc.teamcode.util.BNO055IMUUtil;
import org.firstinspires.ftc.teamcode.util.LynxModuleUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Simple mecanum drive hardware implementation for REV hardware.
 */
@Config
public class DriveCodeCommon_Auto extends MecanumDrive {
    public static PIDCoefficients TRANSLATIONAL_PID = new PIDCoefficients(8, 0, 0);
    public static PIDCoefficients HEADING_PID = new PIDCoefficients(8.2, 0, 0);

    public static double LATERAL_MULTIPLIER = 1.2327;

    public static double VX_WEIGHT = 1;
    public static double VY_WEIGHT = 1;
    public static double OMEGA_WEIGHT = 1;

    private TrajectorySequenceRunner trajectorySequenceRunner;

    private static final TrajectoryVelocityConstraint VEL_CONSTRAINT = getVelocityConstraint(MAX_VEL, MAX_ANG_VEL, TRACK_WIDTH);
    private static final TrajectoryAccelerationConstraint ACCEL_CONSTRAINT = getAccelerationConstraint(MAX_ACCEL);

    private TrajectoryFollower follower;
    private DcMotorEx leftFront, leftRear, rightRear, rightFront;
    private List<DcMotorEx> motors;
    public Servo HighGoal;
    public Servo LowGoal;
    public DcMotor Screw_Motor;
    private BNO055IMU imu;
    private VoltageSensor batteryVoltageSensor;

    public DriveCodeCommon_Auto(HardwareMap hardwareMap) {

        super(kV, kA, kStatic, TRACK_WIDTH, TRACK_WIDTH, LATERAL_MULTIPLIER);

        follower = new HolonomicPIDVAFollower(TRANSLATIONAL_PID, TRANSLATIONAL_PID, HEADING_PID,
                new Pose2d(0.5, 0.5, Math.toRadians(5.0)), 0.5);

        LynxModuleUtil.ensureMinimumFirmwareVersion(hardwareMap);

        batteryVoltageSensor = hardwareMap.voltageSensor.iterator().next();

        for (LynxModule module : hardwareMap.getAll(LynxModule.class)) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        // TODO: adjust the names of the following hardware devices to match your configuration
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        // TODO: If the hub containing the IMU you are using is mounted so that the "REV" logo does
        // not face up, remap the IMU axes so that the z-axis points upward (normal to the floor.)
        //
        //             | +Z axis
        //             |
        //             |
        //             |
        //      _______|_____________     +Y axis
        //     /       |_____________/|__________
        //    /   REV / EXPANSION   //
        //   /       / HUB         //
        //  /_______/_____________//
        // |_______/_____________|/
        //        /
        //       / +X axis
        //
        // This diagram is derived from the axes in section 3.4 https://www.bosch-sensortec.com/media/boschsensortec/downloads/datasheets/bst-bno055-ds000.pdf
        // and the placement of the dot/orientation from https://docs.revrobotics.com/rev-control-system/control-system-overview/dimensions#imu-location
        //
        // For example, if +Y in this diagram faces downwards, you would use AxisDirection.NEG_Y.
        BNO055IMUUtil.remapZAxis(imu, AxisDirection.NEG_X);

        leftFront = hardwareMap.get(DcMotorEx.class, "fl");
        leftRear = hardwareMap.get(DcMotorEx.class, "bl");
        rightRear = hardwareMap.get(DcMotorEx.class, "br");
        rightFront = hardwareMap.get(DcMotorEx.class, "fr");
        HighGoal = hardwareMap.get(Servo.class, "hg");
        LowGoal = hardwareMap.get(Servo.class, "lg");
        Screw_Motor = hardwareMap.get(DcMotor.class, "sm");
        motors = Arrays.asList(leftFront, leftRear, rightRear, rightFront);

        for (DcMotorEx motor : motors) {
            MotorConfigurationType motorConfigurationType = motor.getMotorType().clone();
            motorConfigurationType.setAchieveableMaxRPMFraction(1.0);
            motor.setMotorType(motorConfigurationType);
        }

        if (RUN_USING_ENCODER) {
            setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        if (RUN_USING_ENCODER && MOTOR_VELO_PID != null) {
            setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, MOTOR_VELO_PID);
        }

        // TODO: reverse any motors using DcMotor.setDirection()
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.FORWARD);
        rightRear.setDirection(DcMotor.Direction.REVERSE);
        // TODO: if desired, use setLocalizer() to change the localization method
        // for instance, setLocalizer(new ThreeTrackingWheelLocalizer(...));

        trajectorySequenceRunner = new TrajectorySequenceRunner(follower, HEADING_PID);
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose) {
        return new TrajectoryBuilder(startPose, VEL_CONSTRAINT, ACCEL_CONSTRAINT);
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose, boolean reversed) {
        return new TrajectoryBuilder(startPose, reversed, VEL_CONSTRAINT, ACCEL_CONSTRAINT);
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose, double startHeading) {
        return new TrajectoryBuilder(startPose, startHeading, VEL_CONSTRAINT, ACCEL_CONSTRAINT);
    }

    public TrajectorySequenceBuilder trajectorySequenceBuilder(Pose2d startPose) {
        return new TrajectorySequenceBuilder(
                startPose,
                VEL_CONSTRAINT, ACCEL_CONSTRAINT,
                MAX_ANG_VEL, MAX_ANG_ACCEL
        );
    }

    public void turnAsync(double angle) {
        trajectorySequenceRunner.followTrajectorySequenceAsync(
                trajectorySequenceBuilder(getPoseEstimate())
                        .turn(angle)
                        .build()
        );
    }

    public void turn(double angle) {
        turnAsync(angle);
        waitForIdle();
    }

    public void followTrajectoryAsync(Trajectory trajectory) {
        trajectorySequenceRunner.followTrajectorySequenceAsync(
                trajectorySequenceBuilder(trajectory.start())
                        .addTrajectory(trajectory)
                        .build()
        );
    }

    public void followTrajectory(Trajectory trajectory) {
        followTrajectoryAsync(trajectory);
        waitForIdle();
    }

    public void followTrajectorySequenceAsync(TrajectorySequence trajectorySequence) {
        trajectorySequenceRunner.followTrajectorySequenceAsync(trajectorySequence);
    }

    public void followTrajectorySequence(TrajectorySequence trajectorySequence) {
        followTrajectorySequenceAsync(trajectorySequence);
        waitForIdle();
    }

    public Pose2d getLastError() {
        return trajectorySequenceRunner.getLastPoseError();
    }

    public void update() {
        updatePoseEstimate();
        DriveSignal signal = trajectorySequenceRunner.update(getPoseEstimate(), getPoseVelocity());
        if (signal != null) setDriveSignal(signal);
    }

    public void waitForIdle() {
        while (!Thread.currentThread().isInterrupted() && isBusy())
            update();
    }

    public boolean isBusy() {
        return trajectorySequenceRunner.isBusy();
    }

    public void setMode(DcMotor.RunMode runMode) {
        for (DcMotorEx motor : motors) {
            motor.setMode(runMode);
        }
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        for (DcMotorEx motor : motors) {
            motor.setZeroPowerBehavior(zeroPowerBehavior);
        }
    }

    public void setPIDFCoefficients(DcMotor.RunMode runMode, PIDFCoefficients coefficients) {
        PIDFCoefficients compensatedCoefficients = new PIDFCoefficients(
                coefficients.p, coefficients.i, coefficients.d,
                coefficients.f * 12 / batteryVoltageSensor.getVoltage()
        );

        for (DcMotorEx motor : motors) {
            motor.setPIDFCoefficients(runMode, compensatedCoefficients);
        }
    }

    public void setWeightedDrivePower(Pose2d drivePower) {
        Pose2d vel = drivePower;

        if (Math.abs(drivePower.getX()) + Math.abs(drivePower.getY())
                + Math.abs(drivePower.getHeading()) > 1) {
            // re-normalize the powers according to the weights
            double denom = VX_WEIGHT * Math.abs(drivePower.getX())
                    + VY_WEIGHT * Math.abs(drivePower.getY())
                    + OMEGA_WEIGHT * Math.abs(drivePower.getHeading());

            vel = new Pose2d(
                    VX_WEIGHT * drivePower.getX(),
                    VY_WEIGHT * drivePower.getY(),
                    OMEGA_WEIGHT * drivePower.getHeading()
            ).div(denom);
        }

        setDrivePower(vel);
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        List<Double> wheelPositions = new ArrayList<>();
        for (DcMotorEx motor : motors) {
            wheelPositions.add(encoderTicksToInches(motor.getCurrentPosition()));
        }
        return wheelPositions;
    }

    @Override
    public List<Double> getWheelVelocities() {
        List<Double> wheelVelocities = new ArrayList<>();
        for (DcMotorEx motor : motors) {
            wheelVelocities.add(encoderTicksToInches(motor.getVelocity()));
        }
        return wheelVelocities;
    }

    @Override
    public void setMotorPowers(double v, double v1, double v2, double v3) {
        leftFront.setPower(v);
        leftRear.setPower(v1);
        rightRear.setPower(v2);
        rightFront.setPower(v3);
    }

    @Override
    public double getRawExternalHeading() {
        return imu.getAngularOrientation().firstAngle;
    }

    @Override
    public Double getExternalHeadingVelocity() {
        // To work around an SDK bug, use -zRotationRate in place of xRotationRate
        // and -xRotationRate in place of zRotationRate (yRotationRate behaves as 
        // expected). This bug does NOT affect orientation. 
        //
        // See https://github.com/FIRST-Tech-Challenge/FtcRobotController/issues/251 for details.
        return (double) -imu.getAngularVelocity().xRotationRate;
    }

    public static TrajectoryVelocityConstraint getVelocityConstraint(double maxVel, double maxAngularVel, double trackWidth) {
        return new MinVelocityConstraint(Arrays.asList(
                new AngularVelocityConstraint(maxAngularVel),
                new MecanumVelocityConstraint(maxVel, trackWidth)
        ));
    }

    public static TrajectoryAccelerationConstraint getAccelerationConstraint(double maxAccel) {
        return new ProfileAccelerationConstraint(maxAccel);
    }
    Vector2d vectorBlueRightStart = new Vector2d(-41.5,63);
    Pose2d blueRightStart = new Pose2d(-41.5,63,Math.toRadians(-90));
    Pose2d redRightStart = new Pose2d(7,-63,Math.toRadians(90));
    Pose2d blueLeftStart = new Pose2d(7,63,Math.toRadians(-90));
    Pose2d redLeftStart = new Pose2d(-41.5,-63,Math.toRadians(90));
    Pose2d blueHubScore = new Pose2d(-12,44, Math.toRadians(-90));
    Pose2d redHubScore = new Pose2d(-12,-44, Math.toRadians(90));
    Pose2d blueHubScoreSide = new Pose2d(-33,24.3, Math.toRadians(0));
    Pose2d redHubScoreSide = new Pose2d(-33,-24.3, Math.toRadians(0));
    Pose2d blueWarehouseOutsideOpening = new Pose2d(12,66.5, Math.toRadians(0));
    Pose2d redWarehouseOutsideOpening = new Pose2d(12,-66.5, Math.toRadians(0));
    Pose2d blueWarehouseInsideOpening = new Pose2d(38,66.5, Math.toRadians(0));
    Pose2d redWarehouseInsideOpening = new Pose2d(38,-66.5, Math.toRadians(0));
    Pose2d blueWarehouseFinal = new Pose2d(65.5,36, Math.toRadians(-90));
    Pose2d redWarehouseFinal = new Pose2d(65.5,-36, Math.toRadians(90));
    Pose2d blueStorage = new Pose2d(65.5,36, Math.toRadians(-90));
    Pose2d redStorage = new Pose2d(65.5,-36, Math.toRadians(90));
    int team = 0;// 0 = red 1 = blue
    int side = 0;// 0 = left 1 = right
    int mode = 0;//0 = nothing
    int barcode;
    public int Team() {
        return team % 2;
    }

    public int Side() {
        return side % 2;
    }

    public int Mode() {
        return mode % 3;
    }
    final double HHold = 0.7; //
    final double HScore = 0.175; //
    final double HHub = 0.015; //
    final double LHold = 0.8; //
    final double LScore = 0.6; //
    final double LRelease = 0.4; //
    public void HighHold() {
        HighGoal.setPosition(HHold);
        LowGoal.setPosition(LHold);
    }

    public void ScoreHub() {
        HighGoal.setPosition(HHub);
        LowGoal.setPosition(LHold);
    }

    public void ScoreTop() {
        HighGoal.setPosition(HScore);
        LowGoal.setPosition(LHold);
    }

    public void HoldMid() {
        HighGoal.setPosition(HScore);
        LowGoal.setPosition(LHold);
    }

    public void ScoreMid() {
        HighGoal.setPosition(HScore);
        LowGoal.setPosition(LScore);
    }

    public void ScoreLow() {
        HighGoal.setPosition(HScore);
        LowGoal.setPosition(LRelease);
    }
    public void BlueScoreLeftWarehouse(){
        if (barcode == 0){
            HoldMid();
        } else if (barcode == 1){
            HoldMid();
        }else if (barcode == 2){
            HighHold();
        }
        Screw_Motor.setPower(-0.7);
        Pose2d startPose = new Pose2d(7,63,Math.toRadians(-90));
        setPoseEstimate(startPose);
        Trajectory traj1 = trajectoryBuilder(startPose)
                .splineToConstantHeading(new Vector2d(-12, 40.5), Math.toRadians(-90))
                .build();
        Trajectory traj2 = trajectoryBuilder(traj1.end())
                .lineToLinearHeading(new Pose2d(12, 66.5,Math.toRadians(0)))
                .build();

        Trajectory traj3 = trajectoryBuilder(traj2.end())
                .lineToSplineHeading(new Pose2d(38,66.5, Math.toRadians(0)))
                .build();
        Trajectory traj4 = trajectoryBuilder(traj3.end())
                .splineToLinearHeading(new Pose2d(38,38), Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(63,42), Math.toRadians(-90))
                .build();
        followTrajectory(traj1);
        Screw_Motor.setPower(0);
        if (barcode == 0){
            ScoreLow();
        } else if (barcode == 1){
            ScoreMid();
        }else if (barcode == 2){
            ScoreTop();
        }
        followTrajectory(traj2);
        followTrajectory(traj3);
        followTrajectory(traj4);
        turn(Math.toRadians(-90));
    }
    public void RedScoreRightWarehouse(){
        if (barcode == 0){
            HoldMid();
        } else if (barcode == 1){
            HoldMid();
        }else if (barcode == 2){
            HighHold();
        }
        Screw_Motor.setPower(-0.7);
        Pose2d startPose = new Pose2d(7,-63,Math.toRadians(90));
        setPoseEstimate(startPose);
        Trajectory traj1 = trajectoryBuilder(startPose)
                .splineToConstantHeading(new Vector2d(-12, -40.5), Math.toRadians(90))
                .build();
        Trajectory traj2 = trajectoryBuilder(traj1.end())
                .lineToLinearHeading(new Pose2d(12, -66.5,Math.toRadians(0)))
                .build();

        Trajectory traj3 = trajectoryBuilder(traj2.end())
                .lineToSplineHeading(new Pose2d(38,-66.5, Math.toRadians(0)))
                .build();
        Trajectory traj4 = trajectoryBuilder(traj3.end())
                .splineToLinearHeading(new Pose2d(38,-38), Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(63,-42), Math.toRadians(90))
                .build();
        followTrajectory(traj1);
        Screw_Motor.setPower(0);
        if (barcode == 0){
            ScoreLow();
        } else if (barcode == 1){
            ScoreMid();
        }else if (barcode == 2){
            ScoreTop();
        }
        followTrajectory(traj2);
        followTrajectory(traj3);
        followTrajectory(traj4);
        turn(Math.toRadians(-90));
    }
}
