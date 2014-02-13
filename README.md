Modules
=======

Slight changes between module system built by @gcflames5 and @skipperguy12

Uses @gcflames5's CustomEvents system to emulate an Event system similar to Bukkit's.

In the example provided, a PlayerBuildEvent is fired multiple times, during and after MaxBuildHeightModule is registered.

In a normal listener (not affected by the registering or unregistering of modules), all the PlayerBuildEvents are caught and a debug message shows that.
In the MaxBuildHeightModuleListener, if the build height is greater than defined in XML, another message appears.

A system like this can be used in minigames and game managers to easily create games with the ability to expand in the future.
