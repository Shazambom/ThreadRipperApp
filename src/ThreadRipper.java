import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadRipper {
    private int total;
    private HashMap<Integer, String> duplicateNames;
    private String filePath;

    /**
     * Initializes Thread Ripper and sets it up to rip images
     * @param filePath String of the file path where the images will be downloaded to
     */
    public ThreadRipper(String filePath) {
        this.filePath = filePath;
        initDuplicates();
        total = 0;
    }

    /**
     * Changes the file path local variable
     * @param filePath String of the file path where the images will be downloaded to
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Rips the images from the thread url and puts them in the file path given
     * @param url String of the thread url where the images will be downloaded from
     */
    public void RipThread(String url) {
        try {
            UserAgent userAgent = new UserAgent();
            userAgent.visit(url);
            System.out.println(userAgent.doc.getUrl());
            Elements fileNames = userAgent.doc.findEach("<a class=\"fileThumb\">");
            System.out.println(fileNames.size() + " images found");
            ArrayList<String> links = new ArrayList<String>();
            ArrayList<String> names = new ArrayList<String>();
            for (Element element : fileNames) {
                links.add(element.toString());
            }
            for (int i = 0; i < links.size(); i++) {
                links.set(i, parseLink(links.get(i)));
                names.add(parseThreadName(userAgent.doc.getUrl()) + "_" + parseFileName(links.get(i)));
            }
            String[] folder = new File(filePath).list();
            HashMap<Integer, String> files = new HashMap<Integer, String>();
            if (folder != null) {
                for (String element : folder) {
                    files.put(element.hashCode(), element);
                }
            }

            System.out.print("[");
            int success = downloadImages(links, names, userAgent, files);
            System.out.println("]");
            System.out.println(success + " unique images successfully downloaded");


        } catch (JauntException e) {
            System.out.println("An error occurred. Try again.");
        }
    }

    /**
     * Downloads the images from the thread
     * @param links links to the images on the thread
     * @param names names of the image files (they will be named this when downloaded)
     * @param userAgent the Jaunt object that can look at websites and download files
     * @param files the files to exclude from the download process because they are already in the file path
     * @return the number of successful downloaded images
     */
    private int downloadImages(List<String> links, List<String> names, UserAgent userAgent, HashMap<Integer, String> files) {
        int success = 0;
        for (int i = 0; i < links.size(); i++) {
            if ((files.size() == 0 || !files.containsValue(names.get(i))) && !duplicateNames.containsValue(names.get(i))) {
                try {
                    userAgent.download(links.get(i), new File(filePath + names.get(i)));
                    success++;
                    total++;
                    System.out.print("∎");
                } catch (JauntException e) {
                    System.out.println("\nFile: " + (i + 1) + " at the url: " + links.get(i) + " failed to download");
                    success += downloadImages(links.subList(i + 1, links.size()), names.subList(i + 1, names.size()), userAgent, files);
                    i = links.size();
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return success;
    }


    /**
     * Parses the url out of a string
     * @param link the String containing a url
     * @return the String of the url that is parsed out of the link
     */
    private String parseLink(String link) {
        String toReturn = "";
        for (int i = 0; i < link.length(); i++) {
            if (link.substring(i, i + 7).equals("http://")) {
                while (link.charAt(i) != '\"') {
                    toReturn += link.charAt(i);
                    i++;
                }
                return (toReturn);
            }
        }
        return toReturn;
    }

    /**
     * Parses the file name out of the link
     * @param link the link to parse the name of the file out of
     * @return the name of the file
     */
    private String parseFileName(String link) {
        int slashCount = 0;
        for (int i = 0; i < link.length(); i++) {
            if (link.charAt(i) == '/') {
                slashCount++;
            }
            if (slashCount == 4) {
                return link.substring(i + 1);
            }
        }
        return "";
    }


    /**
     * Recursively searches through a 4chan board looking for threads
     * @param url the 4chan board you are searching through
     * @return the List of threads that are found
     */
    public ArrayList<String> getThreads(String url) {
        ArrayList<String> threadUrls = new ArrayList<String>();
        try {
            UserAgent userAgent = new UserAgent();
            userAgent.visit(url);
            Elements clickHere = userAgent.doc.findEvery("<a class=\"replylink\">");
            for (Element element : clickHere) {
                threadUrls.add(parseLink(element.toString()));
            }
            removeCopyCats(threadUrls);
            try {
                userAgent.doc.submit("Next");
            } catch (JauntException e) {
                return threadUrls;
            }
            threadUrls.addAll(getThreads(userAgent.doc.getUrl()));

        } catch (JauntException e) {
            System.out.println("An error occurred. Try again.");
        }
        return threadUrls;
    }

    /**
     * Some threads are copyCats (aka the same threads)
     * This is simply a helper method that removes all urls of threads that are not unique
     * @param threadUrls the list of threads to be searched through
     */
    private void removeCopyCats(ArrayList<String> threadUrls) {
        ArrayList<String> copyCatUrls = new ArrayList<String>();
        for (String element : threadUrls) {
            if (element.matches("http://boards\\.4chan\\.org/w/thread/[0-9]+/.+")) {
                String copyCatUrl = "";
                int slashCount = 0;
                for (int i = 0; i < element.length(); i++) {
                    if (element.charAt(i) == '/') {
                        slashCount++;
                    }
                    if (slashCount == 6) {
                        break;
                    }
                    copyCatUrl += element.charAt(i);
                }
                copyCatUrls.add(copyCatUrl);
            }
        }
        for (String element : copyCatUrls) {
            threadUrls.remove(element);
        }
    }

    /**
     * Gets the total number of files downloaded
     * @return total number of files downloaded
     */
    public int getTotal() {
        return total;
    }

    /**
     * Cleans up the folder that the files were downloaded to.
     * It removes all duplicate files or previously downloaded files
     */
    public void cleanUp() {
        ArrayList<File> folder = new ArrayList<File>();
        for (File element : new File(filePath).listFiles()) {
            folder.add(element);
        }
        ArrayList<File> toRemove = new ArrayList<File>();
        try {
            System.out.print("[");
            double percentage = folder.size() / 100;
            for (int i = 0; i < folder.size(); i++) {
                if (!toRemove.contains(folder.get(i))) {
                    for (int j = i + 1; j < folder.size(); j++) {
                        if (folder.get(i).isFile() && folder.get(j).isFile()
                                && FileUtils.contentEquals(folder.get(i), folder.get(j))) {
                            toRemove.add(folder.get(j));
                        }
                    }
                }
                if (i % percentage == 0) {
                    System.out.print("∎");
                }
            }
            System.out.println("]");
            System.out.println(toRemove.size() + " invalid wallpapers found");
            System.out.println("Resolving invalid wallpapers...");
            resolveDuplicates(toRemove);
        } catch (Exception e) {
            System.out.println("An error occurred. Try again.");
        }
    }

    /**
     * Assists cleanUp() by removing files that are unwanted
     * Also writes the names of the removed files to a text file as a reference for later
     * so the files are not downloaded again.
     * @param toRemove the list of files to remove
     */
    private void resolveDuplicates(ArrayList<File> toRemove) {
        try {
            PrintWriter out = new PrintWriter(filePath + "duplicates.txt");
            for (Map.Entry element : duplicateNames.entrySet()) {
                out.println(element.getValue());
            }
            System.out.print("[");
            for (File element : toRemove) {
                out.println(element.getName());
                Files.delete(element.toPath());
                System.out.print("∎");
            }
            System.out.println("]");
            out.close();
        } catch (Exception e) {
            System.out.println("An error occurred. Try again.");
        }
    }

    /**
     * Initializes the duplicates file
     * The file is used to ignore any unwanted files
     */
    private void initDuplicates() {
        try {
            duplicateNames = new HashMap<Integer, String>();
            File duplicates = new File(filePath + "duplicates.txt");
            FileReader fileReader = new FileReader(duplicates);
            BufferedReader in = new BufferedReader(fileReader);

            String line;
            while ((line = in.readLine()) != null) {
                duplicateNames.put(line.hashCode(), line);
            }
            in.close();
            fileReader.close();

        } catch (Exception e) {
            try {
                PrintWriter out = new PrintWriter(new File(filePath + "duplicates.txt"));
                out.println("\n");
                out.close();
            } catch (Exception exception) {
                System.out.println("An error occurred. Try again.");
            }

        }
    }

    /**
     * Parses the thread name out of a url
     * @param threadName the url of the thread
     * @return returns the name of the thread
     */
    private String parseThreadName(String threadName) {
        String name = threadName.substring(33);
        String toReturn = "";
        boolean isName = false;
        for (int i = 0; i < name.length(); i++) {
            if (isName) {
                toReturn += name.charAt(i);
            }
            if (name.charAt(i) == '/') {
                isName = true;
            }
        }
        return toReturn;
    }

}
