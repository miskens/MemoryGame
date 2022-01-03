# MemoryGame
Memory game

1. git clone git@github.com:RenasSaid/MemoryGame.git
2. Download your OS version of JavaFX SDK from https://gluonhq.com/products/javafx/
3. Put the unpacked javafx files into a folder of your choice.
        !! If "JAVA PROJECTS" is not showing in the bottom left side under project, click a ".java"-file to load it(e.g Game.java).
4. In VSCode under "JAVA PROJECTS", add javafx files to "referenced libraries"
5. In VSCode under "JAVA PROJECTS", add "junit-platform-console-standalone-1.8.2.jar" from the project lib folder to "referenced libraries"
6. In VScode, go to "run" and press "Add configuration..". Choose "Java".
7. In VScode, in VScode/launch.json add to configuration with name: "Launch Game":
        "vmArgs": "--module-path <path to javafx lib folder> --add-modules javafx.controls,javafx.fxml",
                * replace path with your own javafx lib folder path e.g: C:/JavaFX/lib/
                * Remember to use normal slashes in path (not backslashes in launch.json file)
8. Run.

Tests

1. In VSCode open "Testing" in the left side pane.
2. Run tests.