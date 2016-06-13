package ravensproject.test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.imageio.ImageIO;

import ravensproject.ProblemSet;
import ravensproject.RavensFigure;
import ravensproject.RavensObject;
import ravensproject.RavensProblem;

/**
 * Makes a collection of all figures, and their descriptions if applicable, that were given in the problem sets.
 * Much of the code copied from RavensProject.java
 */
public class FigureCollector {

    private static Scanner createScanner(String filename){
        Scanner r = null;

        try {
            r = new Scanner(new File(filename));
        } catch(Exception ex){
            System.out.println(ex);
        }

        return r;
    }

    private static PrintWriter createPrintWriter(String filename){
        PrintWriter p = null;

        try {
            p = new PrintWriter(new File(filename));
        } catch(Exception ex){
            System.out.println(ex);
        }

        return p;
    }

    public static void collect() {
        ArrayList<ProblemSet> sets = new ArrayList<ProblemSet>();       // The variable 'sets' stores multiple problem sets.

        // Load each set in order.
        Scanner r = createScanner("Problems" + File.separator + "ProblemSetList.txt");        
        while(r.hasNext()) {                                            
            String line = r.nextLine();
            sets.add(new ProblemSet(line));
        }

        List<String> seenFigureDigests = new ArrayList<>();
        
        int counter = 1;
        byte[] buffer = new byte[1024];
        for(ProblemSet set : sets) {
            for(RavensProblem problem : set.getProblems()) {
            	for (RavensFigure fig : problem.getFigures().values()) {
            		String newFigureName = "Figure" + String.format("%04d", counter);
        		    String newFigurePath = "Problems" + File.separatorChar + "AllGivenFigures" + File.separatorChar + newFigureName;
            		if (problem.hasVisual()) {
                		String originalFileName = fig.getVisual();
                		
                		/*
                		 * Begin check to see if figure is unique
                		 * The code to generate the digest was adapted from
                		 * http://stackoverflow.com/questions/304268/getting-a-files-md5-checksum-in-java
                		 */
            			MessageDigest md;
						try {
							md = MessageDigest.getInstance("MD5");
							InputStream is = Files.newInputStream(Paths.get(originalFileName));
							DigestInputStream dis = new DigestInputStream(is, md);
							
 					        int numRead;
					        do {
					            numRead = dis.read(buffer);
					        } while (numRead != -1);
					        dis.close();
					        
						    byte[] digest = md.digest();
	            			String strDigest = new String(digest);
	                		if (seenFigureDigests.contains(strDigest)) {
	                			continue;
	            	    	}
	                		seenFigureDigests.add(strDigest);
						} catch (Exception e) {
							e.printStackTrace();
						}
                		/*
                		 * End check to see if figure is unique
                		 */

                		String newFileName = newFigurePath + ".png";

                		try {
                    		copy(originalFileName, newFileName);
                		} catch (IOException e) {
                			System.out.println("Failed to copy " + originalFileName + " to " + newFileName + " because: " + e.getMessage());
                		}
            		}

            		if (problem.hasVerbal()) {
                		writeVerbal(fig, newFigurePath + ".txt", newFigureName);
            		}
            		++counter;
            	}
            }
        }

        r.close();
    }
    
    /**
     * Just writes the shape descriptions only.
     * For instance, for figure G in Basic Problems C 01, it would write:
Figure1234
	j
		shape:square
		size:huge
		fill:no
	k
		shape:square
		size:large
		fill:no
		inside:j
	l
		shape:square
		size:small
		fill:no
		inside:j,k
     */
    private static void writeVerbal(RavensFigure fig, String filename, String newFigureName) {
		HashMap<String, RavensObject> objects = fig.getObjects();
        PrintWriter pw = createPrintWriter(filename);
        pw.println(newFigureName);
        int counter = 1;
        Map<String,String> map = new HashMap<>();
    	for (Entry<String, RavensObject> entry: objects.entrySet()) {
    		// Map old object name to new object name
    		String oldObjectName = entry.getKey();
    		String newObjectName = newFigureName + "." + counter++;
    		map.put(oldObjectName, newObjectName);
    	}
    	for (Entry<String, RavensObject> entry: objects.entrySet()) {
    		String objectName = map.get(entry.getKey());
    		pw.println("\t" + objectName);
    		RavensObject ro = entry.getValue();
    		for (Entry<String,String> attrAndValue : ro.getAttributes().entrySet()) {
    			String key = attrAndValue.getKey();
    			String value = attrAndValue.getValue();
    			if (value.indexOf(',') == -1) {
        			if (map.keySet().contains(value)) {
        				value = map.get(value);
        			}
    			} else {
    				String[] values = value.split(",");
    				value = "";
    				for (String oneValue : values) {
            			if (map.keySet().contains(oneValue)) {
            				oneValue = map.get(oneValue);
            			}
            			value += oneValue + ",";
    				}
    				value = value.substring(0, value.length()-1);
    			}
    			pw.println("\t\t" + key + ":" + value);
    		}
    	}
    	pw.close();
	}

	// Citation: http://stackoverflow.com/questions/2520305/java-io-to-copy-one-file-to-another
    public static void copy(String sourcePath, String destinationPath) throws IOException {
        Files.copy(Paths.get(sourcePath), new FileOutputStream(destinationPath));
    }

    static Image getImage(RavensProblem problem, String figureName) {
        RavensFigure figure = problem.getFigures().get(figureName);
        String filename = figure.getVisual();

        // Citation: Begin code adapted from https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filename));
        } catch (IOException e) {
            // Citation: End code adapted from https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
        	e.printStackTrace();
        }
        return img;
    }

    public static void main(String[] args) {
    	collect();
    }
}
