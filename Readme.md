<b>Shimeji English Enhanced</b>

Shimeji-ee is a Windows desktop mascot that freely wanders and plays around the screen.  The mascot is very configurable; its actions are defined through xml and its animations/images can be (painstakingly) customized.  Shimeji was originally created by Yuki Yamada of Group Finity (http://www.group-finity.com/Shimeji/).  This branch of the original Shimeji project not only translates the program/source to English, but adds additional enhancements to Shimeji.

## Homepage (with wiki) ##

Homepage: http://code.google.com/p/shimeji-ee/

## Requirements ##

1. Windows XP or higher

2. Java 6, must be 32-bit

## How to Start ##

Double Click the Shimeji-ee icon (Shimeji-ee.exe).  Right click the tray icon or the individual Shimeji for options.  Left click the tray icon to create another Shimeji.

## Basic Configuration ##

If you want multiple Shimeji types, you must have multiple image sets.  Basically, you put different folders with the correct Shimeji images under the img directory.

For example, if you want to add, say, a new Batman Shimeji:

1. Create an img/Batman folder.

2. You must have an image set that mimicks the contents of img/Shimeji.  Create and put new versions of shime1.png - shime46.png (with Batman images of course) in the img/Batman folder.  The filenames must be the same as the img/Shimeji files.  Refer to img/Shimeji for the proper character positions.

3. Start Shimeji-ee.  Now Shimeji and Batman will drop.  Right click Batman to perform Batman specific options.  Adding "Another One!" from the tray icon will randomly create add either Shimeji or Batman.

When Shimeji-ee starts, one Shimeji for every image set in the img folder will be created.  If you have too many image sets, a lot of your computer's memory will be used... so be careful.  Shimeji-ee can eat up to 60% of your system's free memory.

Shimeji-ee will ignore all the image sets that are in the img/unused folder, so you can hide image sets in there.  There is also a tool, Image Set Chooser, that will let you select image sets at run time.  It remembers previous options via the ActiveShimeji file.  Don't choose too many at once.

For more information, read through the configuration files in conf/.  Most options are somewhat complicated, but it's not too hard to limit the total number of Shimeji or to turn off certain behaviors (hint: set frequency to 0.)

## Advanced Configuration ##

All configuration files are located in the in the conf folders.  In general, none of these should need to be touched.

The logging.properties file defines how logging errors is done.

The actions.xml file specifies the different actions Shimeji can do.  When listing images, only include the file name.  More detail on this file will hopefully be added later.

The behaviors.xml file specifies when Shimeji performs each action.  More detail on this file will hopefully be added later.

Each type of Shimeji is configured through:

1. An image set.  This is located in img/_NAME_.  The image set must contain all image files specified in the actions file.

2. An actions file.  Unless img/_NAME_/conf/actions.xml or conf/_NAME_/actions.xml exists, conf/actions.xml will be used.

3. A behaviors file.  Unless img/_NAME_/conf/behaviors.xml or conf/_NAME_/behaviors.xml exists, conf/behaviors.xml will be used.

When Shimeji-ee starts, one Shimeji for every image set in the img folder will be created.  If you have too many image sets, a lot of your computer's memory will be used... so be careful.  Shimeji-ee can eat up to 60% of your system's free memory.

Shimeji-ee will ignore all the image sets that are in the img/unused folder, so you can hide image sets in there.  There is also a tool, Image Set Chooser, that will let you select image sets at run time.  It remembers previous options via the ActiveShimeji file.  Don't choose too many at once.

The Image Set Chooser looks for the shime1.png image.  If it's not found, no image set preview will be shown.  Even if you're not using an image named shime1.png in your image set, you should include one for the Image Set Chooser's sake.

Editing an existing configuration is fairly straightforward.  But writing a brand new configuration file is very time consuming and requires a lot of trial and error.  Hopefully someone will write a guide for it someday, but until then, you'll have to look at the existing conf files to figure it out.  Basically, for every Behavior, there must be a corresponding action.  Actions and Behaviors can be a sequence of other actions or behaviors.

The following actions must be present for the actions.xml to be valid:

ChaseMouse, Fall, Dragged, Thrown

The following behaviors must be present for the behaviors.xml to be valid:

ChaseMouse, Fall, Dragged, Thrown

The icon used for the system tray is img/icon.png

## How to Quit ##

Right-click the tray icon of Shimeji, Select "Bye Everyone!"

## How to Uninstall ##

Delete the unzipped folder.

## Source ##

Programmers may feel free to use the source.  The Shimeji-ee source is under the New BSD license.

Follow the zlib/libpng licenses.

## Library ##

lib / jna.jar and lib / examples.jar of the JNA library.

JNA follows the LGPL.

lib / AbsoluteLayout.jar from Netbeans.

## Trouble Shooting ##

Shimeji-ee takes a LOT of time to start if you have a lot of image sets, so give it some time.  Try moving all but one image set from the img folder to the img/unused folder to see if you have a memory problem.  If Shimeji is running out of memory, try editing Shimeji-ee.bat and change "-Xmx1000m" to a higher number.

If the Shimeji-ee icon appears, but no Shimeji appear:

1. Make sure you have the newest version of Shimeji-ee.

2. Make sure you only have image set folders in your img directory.

3. Make sure you have 32-bit Java on your system.  If you have both 32-bit Java and 64-bit Java installed, try double clicking Shimeji-ee.bat or Shimeji-ee.jar (your computer may be defaulting to the 64 bit version.)  Shimeji-ee requires 32-bit Windows DLLs so can not currently work with 64-bit Java.

4. If you're somewhat computer savvy, you can try running Shimeji-ee from the command line.  Navigate to the Shimeji-ee directory and run this command: "C:\Program Files (x86)\Java\jre6\bin\java" -classpath Shimeji-ee.jar -Xmx512m com.group\_finity.mascot.Main -Djava.util.logging.config.file=./conf/logging.properties

5. Try checking the log (ShimejiLogX.log) for errors.  If you find a bug (which is very likely), post it up on the Shimeji-ee homepage in the issues section.