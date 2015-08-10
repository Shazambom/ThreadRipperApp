import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by Shazambom on 7/22/2015.
 */
public class Main extends Application {
    private static final int MIN_TEXT_WIDTH = 225;
    private static int stickyNum = 0;

    /**
     * The start method that runs the application
     * @param stage The stage object for JavaFX
     */
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
        ta.setOpacity(0.75);
        Console console = new Console(ta);
        PrintStream ps = new PrintStream(console, true);
        System.setOut(ps);
        System.setErr(ps);


        TextField otherFilePath = new TextField("File Path");
        otherFilePath.setMinWidth(MIN_TEXT_WIDTH);
        Button wButton = new Button("/w/");
        wButton.setOnAction(event -> {
            ripBoard(otherFilePath.getText(), "http://boards.4chan.org/w/");
        });
        Button fitButton = new Button("/fit/");
        fitButton.setOnAction(event -> {
            ripBoard(otherFilePath.getText(), "http://boards.4chan.org/fit/");
        });
        Button r9kButton = new Button("/r9k/");
        r9kButton.setOnAction(event -> {
            ripBoard(otherFilePath.getText(), "http://boards.4chan.org/r9k/");
        });
        Button aButton = new Button("/a/");
        aButton.setOnAction(event -> {
            ripBoard(otherFilePath.getText(), "http://boards.4chan.org/a/");
        });
        Button cButton = new Button("/c/");
        cButton.setOnAction(event -> {
            ripBoard(otherFilePath.getText(), "http://boards.4chan.org/c/");
        });
        Button bButton = new Button("/b/");
        bButton.setOnAction(event -> {
            ripBoard(otherFilePath.getText(), "http://boards.4chan.org/b/");
        });
        Button polButton = new Button("/pol/");
        polButton.setOnAction(event -> {
            ripBoard(otherFilePath.getText(), "http://boards.4chan.org/pol/");
        });
        Button kButton = new Button("/k/");
        kButton.setOnAction(event -> {
            ripBoard(otherFilePath.getText(), "http://boards.4chan.org/k/");
        });
        Button vButton = new Button("/v/");
        vButton.setOnAction(event -> {
            ripBoard(otherFilePath.getText(), "http://boards.4chan.org/v/");
        });
        Button vpButton = new Button("/vp/");
        vpButton.setOnAction(event -> {
            ripBoard(otherFilePath.getText(), "http://boards.4chan.org/vp/");
        });
        HBox left = new HBox(10, wButton, fitButton, r9kButton, aButton, cButton);
        HBox right = new HBox(10, bButton, polButton, kButton, vButton, vpButton);
        VBox buttonList = new VBox(10, otherFilePath, left, right);

        TextField filePath = new TextField("C:\\Downloads\\");
        filePath.setMinWidth(MIN_TEXT_WIDTH);
        TextField optionalUrl = new TextField("Put the 4chan board url to rip here");
        optionalUrl.setMinWidth(MIN_TEXT_WIDTH);
        Button goButton = new Button("Begin Board Rip");
        goButton.setOnAction(event -> {
            ripBoard(filePath.getText(), optionalUrl.getText());
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


        try {
            Image image = new Image(new FileInputStream(new File("f.jpg")));
            Background background = new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(1080, 720, false, false, false, false)));
            frame.setBackground(background);
        } catch (Exception e) {
            System.out.println("Background error");
        }

        Scene scene = new Scene(frame);
        stage.setScene(scene);
        stage.setTitle("4chan Image Ripper");
        stage.show();
        System.out.println("___________________________It is advised to disable the sticky if the board you are ripping has one____________________________");
        System.out.println("________________________If you are on Linux/Mac make sure to start file paths from the root directory_________________________");
        System.out.println("______________________If you are on PC make sure to start file paths from whichever drive you wish to use_____________________");
    }

    /**
     * Runs the board rip with all of the appropriate print statements
     * @param filePath String of the file path where the images will be downloaded to
     * @param url String of the url of the 4chan board to be ripped
     */
    private void ripBoard(String filePath, String url) {
        System.out.println("Finding threads...");
        ThreadRipper ripper = new ThreadRipper(filePath);
        ArrayList<String> threadUrls = ripper.getThreads(url);
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
    }
}
