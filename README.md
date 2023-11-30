# Welcome to the Practice Bot Swerve Refactor!
This is where we are experimenting around with a slightly different robot code structure for next year.

## General Structure

The general structure of stuff should look like the layout of files in this current program. Any exceptions will be noted.

### Subsystems

Subsystems are going to be moving from single files that contain all the surface level IO to folders that contain a subsystem class and IO classes that contain the interfaces for this surface level IO and implementations of those interfaces.

Here is an example of what the layout of a shooter subsystem might look like.

```
shooter
├── ShooterSubsystem.java
├── TurretIO.java
├── TurretIOFalcon500.java
├── TurretIOSim.java
├── FlywheelIO.java
├── FlywheelIOKrakenX60.java
├── FlywheelIOFalcon500.java
├── FlywheelIOSim.java
```

Continuing our example, what do each of the files do? `ShooterSubsystem.java` would be the most similar to what we currently have as our subsystem files. The main difference would be that any time the subsystem file would usually interact with a motor or sensor, it interacts with an interface for that instead. The an implementation to the interface is passed into the subsystem in its constructor for flexibility.

The interface is composed of methods that do the output (setters), and one method that updates a class of inputs. Take the `FlywheelIO.java` interface. This would likely have a `setDesiredVelocity()` method that sets the velocity the flywheel is aiming for. It would also have a `updateInputs()` method that takes as an argument an instance of `FlywheelIOInputs`. This class has only public fields and is defined as an inner class of the `FlywheelIO` interface.