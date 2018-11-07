# Java Test 
## How to build jar
* run command `mvn clean compile assembly:single`, to get jar with all dependencies. Jar will create in folder target.

## How to run
`java -jar test*.jar`*`command additional_flags`*
  * where *command* is: **--readDrives, --findFile, --editFile**
  * *additional_flags* are required for commands **--findFile, --editFile**: **--path *\<absolute path to dir\>*, --file *\<file name\>***
  
# Attention
* Linux and MacOS users must install **lshw** util to work with command **--readDrives**, because standard java methods cannot get list of drives, usb flash, cd/dvd-rom on these OS, unlike **lshw** (test on ext4 File system).
* run command **--readDrives** as superuser (`sudo java -jar test*.jar --readDrives`)
