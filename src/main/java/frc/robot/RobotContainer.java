
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // drn -- robot's subsystems and commands are defined here...

  // drn -- drive & intake & arm subsystem declarations
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();

  // drn --- declaring an instance of the XBox controller
  public final XboxController m_xboxController = new XboxController(OIConstants.kDriverControllerPort);
  public final Joystick m_joystick = new Joystick(OIConstants.kDriverControllerPort);

  //Air Compressor
  Compressor pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
  Compressor phCompressor = new Compressor(1, PneumaticsModuleType.REVPH);
  
  boolean enabled = pcmCompressor.enabled();
  boolean pressureSwitch = pcmCompressor.getPressureSwitchValue();
  double current = pcmCompressor.getCurrent();

  // drn -- A chooser for autonomous commands
  private final SendableChooser<Command> m_chooser = new SendableChooser<>();

  // drn -- shuffleboard tabs
  private final ShuffleboardTab sbCamera = Shuffleboard.getTab("Camera");

  // drn -- 

  // Camera
  private UsbCamera camera01;
  private VideoSink videoServer;

  // drn -- while driving diagnostics
  public final PowerDistribution pdp = new PowerDistribution(0,ModuleType.kCTRE);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    // prepare the camera
    cameraInit();

    // drive command to split-stick arcade drive
    // split stick is left and right sticks on the XBox
    m_robotDrive.setDefaultCommand(new RunCommand(() -> m_robotDrive.arcadeDrive(m_xboxController.getLeftY(),-m_xboxController.getRightX()), m_robotDrive));
    //m_robotDrive.setDefaultCommand(new RunCommand(() -> m_robotDrive.arcadeDrive(m_joystick.getZ(), m_joystick.getY()), m_robotDrive));
    // drn -- put power onto shuffleboard
    sbCamera.add("PDP voltage", pdp.getVoltage())
      .withSize(1, 1).withPosition(0, 2);
    // drn -- put camera on shuffleboard
    sbCamera.add(camera01)
      .withSize(6, 4).withPosition(2, 0);

  } // end RobotContainer initialization methods

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

  } // end configureButtonBindins

  // Starting and adjusting camera
  private void cameraInit() {
    camera01 = CameraServer.startAutomaticCapture(0);
    videoServer = CameraServer.getServer();
    camera01.setResolution(320, 240);
    camera01.setFPS(15);
    videoServer.setSource(camera01);
  } // end cameraInit

  /*
  // toggle switch for changing cameras
  private void cameraSwitch() {
    if (Constants.cameraState) {
      videoServer.setSource(camera01);
    } else {
      videoServer.setSource(camera02);
    }
    Constants.cameraState = !Constants.cameraState;
  } // end cameraSwitch
*/
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  } // end getAutonomous

} // end RobotContainer