import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by Shazambom on 7/22/2015.
 */
public class Main extends Application {
    private static final int MIN_TEXT_WIDTH = 225;

    public void start(Stage stage) {
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

        TextField filePathTR = new TextField("/Users/USER_NAME/Pictures/");
        filePathTR.setMinWidth(MIN_TEXT_WIDTH);
        TextField optionalUrlTR = new TextField("Put the 4chan thread url to rip here");
        optionalUrlTR.setMinWidth(MIN_TEXT_WIDTH);
        Button goButtonTR = new Button("Begin Rip");
        goButtonTR.setOnAction(event -> {
            ThreadRipper threadRipper = new ThreadRipper(filePathTR.getText());
            threadRipper.RipThread(optionalUrlTR.getText());
            threadRipper.cleanUp();
        });

        VBox threadRipperBox = new VBox(5, filePathTR, optionalUrlTR, goButtonTR);


        HBox horizontalBox = new HBox(20, buttonList, textFields, threadRipperBox);

        VBox frame = new VBox(horizontalBox, ta);

        Scene scene = new Scene(frame);
        stage.setScene(scene);
        stage.setTitle("4chan Image Ripper");
        stage.show();
    }
}
