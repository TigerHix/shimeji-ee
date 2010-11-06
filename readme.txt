shimeji-ee

Shimeji English Enhanced

Shimeji-ee is a Windows desktop mascot that freely wanders and plays around the screen.  The mascot is very configurable; its actions are defined through xml and its animations/images can be (painstakingly) customized.  Shimeji was originally created by Yuki Yamada of Group Finity (http://www.group-finity.com/Shimeji/).  This branch of the original Shimeji project not only translates the program/source to English, but adds additional enhancements to Shimeji.

==== Homepage (with wiki) ==== 

Homepage: http://code.google.com/p/shimeji-ee/

==== Requirements ==== 

1. Windows XP or higher
2. Java 6, must be 32-bit

==== How to Start ==== 

Double Click the Shimeji-ee icon (Shimeji-ee.exe).  Right click the tray icon or the individual Shimeji for options.  Left click the tray icon to create another Shimeji.

==== Basic Configuration ==== 

If you want multiple Shimeji types, you must have multiple image sets.  Basically, you put different folders with the correct Shimeji images under the img directory.

For example, if you want to add, say, a new Batman Shimeji:

1. Create an img/Batman folder.
2. You must have an image set that mimicks the contents of img/Shimeji.  Create and put new versions of shime1.png - shime46.png (with Batman images of course) in the img/Batman folder.  The filenames must be the same as the img/Shimeji files.  Refer to img/Shimeji for the proper character positions.
3. Start Shimeji-ee.  Now Shimeji and Batman will drop.  Right click Batman to perform Batman specific options.  Adding "Another One!" from the tray icon will randomly create add either Shimeji or Batman.

When Shimeji-ee starts, one Shimeji for every image set in the img folder will be created.  If you have too many image sets, a lot of your computer's memory will be used... so be careful.  Shimeji-ee can eat up to 60% of your system's free memory.  

Shimeji-ee will ignore all the image sets that are in the img/unused folder, so you can hide image sets in there.  If enough people want it, a future release may add some better capabilities (namely a image set selection tool when Shimeji-ee is run) to managing Shimeji image sets.

==== Advanced Configuration ==== 

All configuration files are located in the in the conf folders.  In general, none of these should need to be touched.

The logging.properties file defines how logging errors is done.
The actions.xml file specifies the different actions Shimeji can do.  When listing images, only include the file name.  More detail on this file will be added later.
The behaviors.xml file specifies when Shimeji performs each action.  More detail on this file will be added later.

Each type of Shimeji is configured through:

1. An image set.  This is located in img/[NAME].  The image set must contain all image files specified in the actions file. 
2. An actions file.  Unless conf/[NAME]/actions.xml exists, conf/actions.xml will be used.
3. A behaviors file.  Unless conf/[NAME]/behaviors.xml exists, conf/behaviors.xml will be used.

When Shimeji-ee starts, one Shimeji for every image set in the img folder will be created.  If you have too many image sets, a lot of your computer's memory will be used... so be careful.  Shimeji-ee can eat up to 60% of your system's free memory.  

Shimeji-ee will ignore all the image sets that are in the img/unused folder, so you can hide image sets in there.  If enough people want it, a future release may add some better capabilities (namely a image set selection tool when Shimeji-ee is run) to managing Shimeji image sets.

==== How to Quit ==== 

Right-click the tray icon of Shimeji, Select "Bye Everyone!"

==== How to Uninstall ==== 

Delete the unzipped folder.

==== Source ====

Programmers may feel free to use the source.  The Shimeji-ee source is under the New BSD license.
Follow the zlib/libpng licenses.

==== Library ====

lib / jna.jar and lib / examples.jar of the JNA library.
JNA follows the LGPL.