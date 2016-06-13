package ravensproject.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ProblemGenerator {
    public static final String inputPath = "Problems" + File.separatorChar + "AllGivenFigures";
    public static String outputPath = "Problems" + File.separatorChar + "MyGeneratedProblems";

    // For a 2x2, the first arg should be "2x2".
	// The next 1 should be the name of the problem I'm generating
	// The next 3 are the figures to use as A, B, C.
	// The next 6 are the figures to use as the answer choices.
    // The last 1 is the correct answer.
	
	// For a 3x3, the first arg should be "3x3".
	// The next 1 should be the name of the problem I'm generating
	// The next 8 are the figures to use as A through H.
	// The next 8 are the figures to use as the answer choices.
    // The last 1 is the correct answer.
    public static void main(String[] args) {
    	String type = args[0];
    	String problemName = args[1];

    	outputPath = outputPath + File.separatorChar + problemName;
    	File outputDir = new File(outputPath);
    	
    	if (!outputDir.exists()) {
    		outputDir.mkdirs();
    	}

        boolean hasVisual = true; // Always true
        boolean hasVerbal = new File(inputPath + File.separatorChar + args[2] + ".txt").exists();
        if (hasVerbal) {
            PrintWriter problemData = createPrintWriter(outputPath + File.separator + "ProblemData.txt");
            problemData.println(type);
            problemData.println(hasVisual);
            problemData.println(hasVerbal); // Assume if verbal exists for A, it exists for all!
			copyFigureVerbal(problemData, inputPath + File.separatorChar + args[2] + ".txt", "A");
			copyFigureVerbal(problemData, inputPath + File.separatorChar + args[3] + ".txt", "B");
			copyFigureVerbal(problemData, inputPath + File.separatorChar + args[4] + ".txt", "C");
	    	if (type.equals("2x2")) {
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[5] + ".txt", "1");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[6] + ".txt", "2");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[7] + ".txt", "3");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[8] + ".txt", "4");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[9] + ".txt", "5");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[10] + ".txt", "6");
	    	} else if (type.equals("3x3")) {
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[5] + ".txt", "D");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[6] + ".txt", "E");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[7] + ".txt", "F");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[8] + ".txt", "G");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[9] + ".txt", "H");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[10] + ".txt", "1");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[11] + ".txt", "2");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[12] + ".txt", "3");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[13] + ".txt", "4");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[14] + ".txt", "5");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[15] + ".txt", "6");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[16] + ".txt", "7");
				copyFigureVerbal(problemData, inputPath + File.separatorChar + args[17] + ".txt", "8");
	    	}
			problemData.close();
        }
        
		// args[2] becomes "A", etc.
		try {
			copyFigureImage(args[2], "A");
			copyFigureImage(args[3], "B");
			copyFigureImage(args[4], "C");

	    	if (type.equals("2x2")) {
				copyFigureImage(args[5], "1");
				copyFigureImage(args[6], "2");
				copyFigureImage(args[7], "3");
				copyFigureImage(args[8], "4");
				copyFigureImage(args[9], "5");
				copyFigureImage(args[10], "6");
				makeAnswerFile(args[11]);
	    	} else if (type.equals("3x3")) {
				copyFigureImage(args[5], "D");
				copyFigureImage(args[6], "E");
				copyFigureImage(args[7], "F");
				copyFigureImage(args[8], "G");
				copyFigureImage(args[9], "H");
				copyFigureImage(args[10], "1");
				copyFigureImage(args[11], "2");
				copyFigureImage(args[12], "3");
				copyFigureImage(args[13], "4");
				copyFigureImage(args[14], "5");
				copyFigureImage(args[15], "6");
				copyFigureImage(args[16], "7");
				copyFigureImage(args[17], "8");
				makeAnswerFile(args[18]);
	    	} else {
	    		System.out.println("First arg should be 2x2 or 3x3. If 2x2, follow it with 9 figures. If 3x3, follow it with 16 figures.");
	    	}
		} catch (IOException e) {
			System.out.println("Caught exception: " + e.getMessage());
		}
    }

	private static void makeAnswerFile(String answer) {
        PrintWriter problemAnswer = createPrintWriter(outputPath + File.separator + "ProblemAnswer.txt");
        problemAnswer.println(answer);
        problemAnswer.close();
	}

	private static void copyFigureVerbal(PrintWriter problemData, String filename, String newFigureName) {
		problemData.println(newFigureName);
		Scanner scanner = createScanner(filename);
		if (scanner.hasNext()) {
			scanner.nextLine(); // Figure name like "Figure0001"
		}
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            problemData.println(line);
        }
        scanner.close();
	}

	private static void copyFigureImage(String filename, String newFigureName) throws IOException {
		copy(inputPath + File.separatorChar + filename + ".png", outputPath + File.separatorChar + newFigureName + ".png");
	}

	// Citation: http://stackoverflow.com/questions/2520305/java-io-to-copy-one-file-to-another
    public static void copy(String sourcePath, String destinationPath) throws IOException {
        Files.copy(Paths.get(sourcePath), new FileOutputStream(destinationPath));
    }

    // From RavensProject.java
    private static PrintWriter createPrintWriter(String filename){
        PrintWriter p = null;

        try {
            p = new PrintWriter(new File(filename));
        } catch(Exception ex){
            System.out.println(ex);
        }

        return p;
    }

    // From RavensProject.java
    private static Scanner createScanner(String filename){
        Scanner r = null;

        try {
            r = new Scanner(new File(filename));
        } catch(Exception ex){
            System.out.println(ex);
        }

        return r;
    }


}
