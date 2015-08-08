import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Shazambom on 7/22/2015.
 * System.out.println("Finding threads...");
 * ArrayList<String> threadUrls = ripper.getThreads(url);
 * System.out.println(threadUrls.size() + " threads found");
 * System.out.println("Ripping threads now...");
 * for (int i = 1; i < threadUrls.size(); i++) {
 * ripper.RipThread(threadUrls.get(i));
 * }
 * System.out.println("\nBoard Rip complete");
 * System.out.println(ripper.getTotal() + " images Ripped");
 * System.out.println("Cleaning up folder...");
 * ripper.cleanUp();
 * System.out.println("Cleanup complete");
 */
public class Main extends Application {
    private static final int MIN_TEXT_WIDTH = 225;

    public void start(Stage stage) {
        TextField otherFilePath = new TextField("File Path");
        otherFilePath.setMinWidth(MIN_TEXT_WIDTH);
        Button wButton = new Button("/w/");
        wButton.setOnAction(event -> {
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/w/");
            for (int i = 1; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            ripper.cleanUp();
        });
        Button fitButton = new Button("/fit/");
        fitButton.setOnAction(event -> {
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/fit/");
            for (int i = 1; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            ripper.cleanUp();
        });
        Button r9kButton = new Button("/r9k/");
        r9kButton.setOnAction(event -> {
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/r9k/");
            for (int i = 1; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            ripper.cleanUp();
        });
        Button aButton = new Button("/a/");
        aButton.setOnAction(event -> {
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/a/");
            for (int i = 1; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            ripper.cleanUp();
        });
        Button cButton = new Button("/c/");
        cButton.setOnAction(event -> {
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/c/");
            for (int i = 1; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            ripper.cleanUp();
        });
        Button bButton = new Button("/b/");
        bButton.setOnAction(event -> {
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/b/");
            for (int i = 1; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            ripper.cleanUp();
        });
        Button polButton = new Button("/pol/");
        polButton.setOnAction(event -> {
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/pol/");
            for (int i = 1; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            ripper.cleanUp();
        });
        Button kButton = new Button("/k/");
        kButton.setOnAction(event -> {
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/k/");
            for (int i = 1; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            ripper.cleanUp();
        });
        Button vButton = new Button("/v/");
        vButton.setOnAction(event -> {
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/v/");
            for (int i = 1; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            ripper.cleanUp();
        });
        Button vpButton = new Button("/vp/");
        vpButton.setOnAction(event -> {
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads("http://boards.4chan.org/vp/");
            for (int i = 1; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            ripper.cleanUp();
        });
        HBox left = new HBox(10, wButton, fitButton, r9kButton, aButton, cButton);
        HBox right = new HBox(10, bButton, polButton, kButton, vButton, vpButton);

        VBox buttonList = new VBox(10, otherFilePath, left, right);
        TextField filePath = new TextField("F:\\\\RippedImages\\\\");
        filePath.setMinWidth(MIN_TEXT_WIDTH);
        TextField optionalUrl = new TextField("Put the 4chan board url to rip here");
        optionalUrl.setMinWidth(MIN_TEXT_WIDTH);
        Button goButton = new Button("Begin Rip");
        goButton.setOnAction(event -> {
            ThreadRipper ripper = new ThreadRipper(otherFilePath.getText());
            ArrayList<String> threadUrls = ripper.getThreads(optionalUrl.getText());
            for (int i = 1; i < threadUrls.size(); i++) {
                ripper.RipThread(threadUrls.get(i));
            }
            ripper.cleanUp();
        });
        VBox textFields = new VBox(5, filePath, optionalUrl, goButton);

        TextField filePathTR = new TextField("~/Desktop/4chan");
        filePathTR.setMinWidth(MIN_TEXT_WIDTH);
        TextField optionalUrlTR = new TextField("Put the 4chan thread url to rip here");
        optionalUrlTR.setMinWidth(MIN_TEXT_WIDTH);
        Button goButtonTR = new Button("Begin Rip");
        goButtonTR.setOnAction(event -> {
            ThreadRipper threadRipper = new ThreadRipper(filePathTR.getText());
            threadRipper.RipThread(optionalUrlTR.getText());
        });

        VBox threadRipperBox = new VBox(5, filePathTR, optionalUrlTR, goButtonTR);


        HBox horizontalBox = new HBox(20, buttonList, textFields, threadRipperBox);

        Scene scene = new Scene(horizontalBox);
        stage.setScene(scene);
        stage.setTitle("4chan Image Ripper");
        stage.show();
    }
}
