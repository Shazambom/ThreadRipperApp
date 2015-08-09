import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by Shazambom on 7/22/2015.
 */
public class Main extends Application {
    private static final int MIN_TEXT_WIDTH = 225;
    private static int stickyNum = 0;

    public void start(Stage stage) {


        Button stickyButton = new Button("Sticky Thread");
        stickyButton.setOnAction(event -> {
            if (stickyNum == 0) {
                stickyNum = 1;
                System.out.println("Sticky disabled");
            } else {
                stickyNum = 0;
                System.out.println("Sticky enabled");
            }
        });

        TextArea ta = TextAreaBuilder.create()
                .prefWidth(800)
                .prefHeight(400)
                .wrapText(true)
                .build();

        Console console = new Console(ta);
        PrintStream ps = new PrintStream(console, true);
        System.setOut(ps);
        System.setErr(ps);


        TextField otherFilePath = new TextField("File Path");
        otherFilePath.setMinWidth(MIN_TEXT_WIDTH);
        Button wButton = new Button("/w/");
        wButton.setOnAction(event -> {
            System.out.println("Finding threads...");
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/w/");
            System.out.println(threadUrls.size() + " threads found");
            System.out.println("Ripping threads now...");
            for (int i = stickyNum; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            System.out.println("\nBoard Rip complete");
            System.out.println(ripper.getTotal() + " images Ripped");
            System.out.println("Cleaning up folder...");
            ripper.cleanUp();
            System.out.println("Cleanup complete");
        });
        Button fitButton = new Button("/fit/");
        fitButton.setOnAction(event -> {
            System.out.println("Finding threads...");
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/fit/");
            System.out.println(threadUrls.size() + " threads found");
            System.out.println("Ripping threads now...");
            for (int i = stickyNum; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            System.out.println("\nBoard Rip complete");
            System.out.println(ripper.getTotal() + " images Ripped");
            System.out.println("Cleaning up folder...");
            ripper.cleanUp();
            System.out.println("Cleanup complete");
        });
        Button r9kButton = new Button("/r9k/");
        r9kButton.setOnAction(event -> {
            System.out.println("Finding threads...");
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/r9k/");
            System.out.println(threadUrls.size() + " threads found");
            System.out.println("Ripping threads now...");
            for (int i = stickyNum; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            System.out.println("\nBoard Rip complete");
            System.out.println(ripper.getTotal() + " images Ripped");
            System.out.println("Cleaning up folder...");
            ripper.cleanUp();
            System.out.println("Cleanup complete");
        });
        Button aButton = new Button("/a/");
        aButton.setOnAction(event -> {
            System.out.println("Finding threads...");
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/a/");
            System.out.println(threadUrls.size() + " threads found");
            System.out.println("Ripping threads now...");
            for (int i = stickyNum; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            System.out.println("\nBoard Rip complete");
            System.out.println(ripper.getTotal() + " images Ripped");
            System.out.println("Cleaning up folder...");
            ripper.cleanUp();
            System.out.println("Cleanup complete");
        });
        Button cButton = new Button("/c/");
        cButton.setOnAction(event -> {
            System.out.println("Finding threads...");
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/c/");
            System.out.println(threadUrls.size() + " threads found");
            System.out.println("Ripping threads now...");
            for (int i = stickyNum; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            System.out.println("\nBoard Rip complete");
            System.out.println(ripper.getTotal() + " images Ripped");
            System.out.println("Cleaning up folder...");
            ripper.cleanUp();
            System.out.println("Cleanup complete");
        });
        Button bButton = new Button("/b/");
        bButton.setOnAction(event -> {
            System.out.println("Finding threads...");
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/b/");
            System.out.println(threadUrls.size() + " threads found");
            System.out.println("Ripping threads now...");
            for (int i = stickyNum; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            System.out.println("\nBoard Rip complete");
            System.out.println(ripper.getTotal() + " images Ripped");
            System.out.println("Cleaning up folder...");
            ripper.cleanUp();
            System.out.println("Cleanup complete");
        });
        Button polButton = new Button("/pol/");
        polButton.setOnAction(event -> {
            System.out.println("Finding threads...");
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/pol/");
            System.out.println(threadUrls.size() + " threads found");
            System.out.println("Ripping threads now...");
            for (int i = stickyNum; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            System.out.println("\nBoard Rip complete");
            System.out.println(ripper.getTotal() + " images Ripped");
            System.out.println("Cleaning up folder...");
            ripper.cleanUp();
            System.out.println("Cleanup complete");
        });
        Button kButton = new Button("/k/");
        kButton.setOnAction(event -> {
            System.out.println("Finding threads...");
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/k/");
            System.out.println(threadUrls.size() + " threads found");
            System.out.println("Ripping threads now...");
            for (int i = stickyNum; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            System.out.println("\nBoard Rip complete");
            System.out.println(ripper.getTotal() + " images Ripped");
            System.out.println("Cleaning up folder...");
            ripper.cleanUp();
            System.out.println("Cleanup complete");
        });
        Button vButton = new Button("/v/");
        vButton.setOnAction(event -> {
            System.out.println("Finding threads...");
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/v/");
            System.out.println(threadUrls.size() + " threads found");
            System.out.println("Ripping threads now...");
            for (int i = stickyNum; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            System.out.println("\nBoard Rip complete");
            System.out.println(ripper.getTotal() + " images Ripped");
            System.out.println("Cleaning up folder...");
            ripper.cleanUp();
            System.out.println("Cleanup complete");
        });
        Button vpButton = new Button("/vp/");
        vpButton.setOnAction(event -> {
            System.out.println("Finding threads...");
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/vp/");
            System.out.println(threadUrls.size() + " threads found");
            System.out.println("Ripping threads now...");
            for (int i = stickyNum; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            System.out.println("\nBoard Rip complete");
            System.out.println(ripper.getTotal() + " images Ripped");
            System.out.println("Cleaning up folder...");
            ripper.cleanUp();
            System.out.println("Cleanup complete");
        });
        HBox left = new HBox(10, wButton, fitButton, r9kButton, aButton, cButton);
        HBox right = new HBox(10, bButton, polButton, kButton, vButton, vpButton);

        VBox buttonList = new VBox(10, otherFilePath, left, right);
        TextField filePath = new TextField("F:\\RippedImages\\");
        filePath.setMinWidth(MIN_TEXT_WIDTH);
        TextField optionalUrl = new TextField("Put the 4chan board url to rip here");
        optionalUrl.setMinWidth(MIN_TEXT_WIDTH);
        Button goButton = new Button("Begin Board Rip");
        goButton.setOnAction(event -> {
            System.out.println("Finding threads...");
            ThreadRipper ripper = new ThreadRipper(filePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads(optionalUrl.getText());
            System.out.println(threadUrls.size() + " threads found");
            System.out.println("Ripping threads now...");
            for (int i = stickyNum; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            System.out.println("\nBoard Rip complete");
            System.out.println(ripper.getTotal() + " images Ripped");
            System.out.println("Cleaning up folder...");
            ripper.cleanUp();
            System.out.println("Cleanup complete");
        });
        VBox textFields = new VBox(5, filePath, optionalUrl, goButton);

        TextField filePathTR = new TextField("/Users/USER_NAME/Pictures/");
        filePathTR.setMinWidth(MIN_TEXT_WIDTH);
        TextField optionalUrlTR = new TextField("Put the 4chan thread url to rip here");
        optionalUrlTR.setMinWidth(MIN_TEXT_WIDTH);
        Button goButtonTR = new Button("Begin Thread Rip");
        goButtonTR.setOnAction(event -> {
            System.out.println("Starting thread rip...");
            ThreadRipper threadRipper = new ThreadRipper(filePathTR.getText());
            threadRipper.RipThread(optionalUrlTR.getText());
            System.out.println("Thread rip complete");
            System.out.println("Cleaning up file...");
            threadRipper.cleanUp();
            System.out.println("Cleanup complete");
        });

        VBox threadRipperBox = new VBox(5, filePathTR, optionalUrlTR, goButtonTR);


        HBox horizontalBox = new HBox(20, buttonList, textFields, threadRipperBox);

        VBox frame = new VBox(horizontalBox, ta, stickyButton);

        Scene scene = new Scene(frame);
        stage.setScene(scene);
        stage.setTitle("4chan Image Ripper");
        stage.show();
        System.out.println("___________________________It is advised to disable the sticky if the board you are ripping has one____________________________");
        System.out.println("________________________If you are on Linux/Mac make sure to start file paths from the root directory_________________________");
        System.out.println("_____________If you are on PC make sure to start file paths from whichever drive you wish to use ex.(C:\\Downloads\\)____________");
    }
}
