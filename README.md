# DRAMA-Compiler
Compiles C code to DRAMA assembly

## Getting Started

### Prerequisites

* [Eclipse](http://www.eclipse.org/downloads/eclipse-packages/)
* [Java](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
* [JUnit](http://junit.org/junit4/)

### Installing

* **Basic Install:** Only install the executable.
```
1. Navigate to DRAMA-Compiler/DRAMA-Compiler-Project/Work Directory/
2. Right click DRAMA-Compiler.jar
3. Click save link as...
4. Save as Executable Jar File in your preferred destination
```

* **Full Install:** Install the entire project.
```
1. Click "Clone or download"
2. Click "Download ZIP"
3. Extract the ZIP file in your preferred destination
```

## Running/Testing

* **Run the application:**
```
1. Navigate to the directory where you saved DRAMA-Compiler.jar
2. Open the terminal
3. Type "java -jar DRAMA-Compiler.jar [inputfile] [outputfile]
```

* **Run the test suite:**
```
You must have a Full Install to run the test suite.
The step-by-step procedure assumes you are using eclipse and that the project is already imported.
1. Open the project
2. Open tests
3. Navigate to the specific component you wish to test
4. Right click the chosen test file
5. Click Run As -> JUnit Test
```

### Test suites overview

```
1. **TestProgram.java:** Tests the internal representation of a DRAMA Program.
## Deployment
```

## Deployment

* **Importing the Project to eclipse:**
```
You must have a Full Install to import the Project to eclipse.
1. Open eclipse
2. Click file
3. Click import
4. General -> Existing Projects into Workspace
5. Navigate to the folder in which you saved the project
6. Click the project folder
```

* **Setting up a full test suite:**
```
A full test suite allows you to run all the JUnit tests in one time.
You must have a Full Install to run a full test suite.
The step-by-step procedure assumes you are using eclipse and that the project is already imported.
1. Open eclipse
2. Click "Run"
3. Click "Run Configurations..."
4. Click "JUnit"
5. Click the 'New' button (top left)
6. Click "Run all tests in the selected project, package or source folder"
7. Click "Search..."
8. Select the tests directory
9. Name the configuration (top)
10. Click apply
To Run the full test suite, simply navigate to the "Run Configurations..." window,
select the right configuration and click "Run" (bottom right).
You can also reach the "Run Configurations..." window by clicking the arrow next to the Run icon.
```

## Contributing

Please read [CONTRIBUTING.md]() for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

* **Mathijs Hubrechtsen** - *Majority Contributor*
* **Jianing Li** - *Helped with initial work*

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* [DRAMA Assembly](https://nl.wikipedia.org/wiki/Drama_(assembleertaal)): The assembly language that made this project possible.
* [A template to make good README.md](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2): Provided inspiration for [README.md](README.md).
