# MemoryGame
Memory game

1. git clone git@github.com:RenasSaid/MemoryGame.git
2. Download your OS version of JavaFX SDK from https://gluonhq.com/products/javafx/
3. Put the unpacked javafx files into a folder of your choice.
4. In VSCode under "JAVA PROJECTS", add javafx files to "referenced libraries"
5. In VSCode under "JAVA PROJECTS", add the junit jar package in the project lib folder to "referenced libraries"
6. In VScode, go to "run" and press "Add configuration..". Choose "Java".
7. In VScode, add to VScode/vmArgs/launch.json:
        "vmArgs": "--module-path <path to javafx lib folder> --add-modules javafx.controls,javafx.fxml",
                * add your own javafx path e.g: C://JavaFX//lib//
8. Run.

Tests

1. In VSCode open "Testing" in the left side pane.
2. Run tests.