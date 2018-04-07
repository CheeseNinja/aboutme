package Splash_2_package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.prefs.Preferences;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.text.*;
import javafx.scene.control.*;

public class Splash_Application extends Application {

	private String[] account = { "darren05", "splashadmin" }, weather_arr;
	private final String img_loc = "C:/Users/Wendy/Desktop/eclipse/Splash_2/images/";
	private TextField username_field;
	private PasswordField password_field;
	private Preferences [] prefs_array;
	private Preferences remember;
	private int bacon_rate = -540, minibacon_rate = -540, paus_res_counter = 0;
	private FileInputStream fis_miniicon = null, fis_largeicon = null, pause_button_ic = null, resume_button_ic = null;
	private Image miniicon_img, largeicon_img;
	private ImageView iv_miniicon, iv_largeicon;
	private Timeline timeline_bacon, timeline_minibacon;
	/*
	 * (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 * https://docs.oracle.com/javafx/2/text/jfxpub-text.htm
	 */
	@Override
	public void start(Stage primaryStage) {
		// Initiating containers
		Group root = new Group();
		Scene login_scene = new Scene(root, Color.FORESTGREEN);
		Login_Info loginInformation = new Login_Info();
		login_scene.getStylesheets().add("login_style.css");
		//Initiating preferences
		
		// Images
		FileInputStream fis_yolk = null, fis_bg = null, fis_bacon_bg = null, fis_egg_white = null;
		try {
			fis_yolk = new FileInputStream(img_loc + "yolk.png");
			fis_bg = new FileInputStream(img_loc + "screenshot.png");
			fis_bacon_bg = new FileInputStream(img_loc + "bacon_background.png");
			fis_miniicon = new FileInputStream(img_loc + "minibacon_background.png");
			fis_egg_white = new FileInputStream(img_loc + "egg_white.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image yolk_image = new Image(fis_yolk);
		ImageView iv_yolk = new ImageView(yolk_image);
		iv_yolk.setId("yolk-image");
		iv_yolk.setX(45);			iv_yolk.setFitWidth(205);
		iv_yolk.setY(140);		iv_yolk.setFitHeight(200);
		
		Image egg_white_image = new Image(fis_egg_white);
		ImageView iv_egg_white = new ImageView(egg_white_image);
		iv_egg_white.setFitWidth(430);			iv_egg_white.setFitHeight(530);
		
		Image bg = new Image(fis_bg);
		ImageView iv_bg = new ImageView(bg);
		iv_bg.setFitHeight(540);
		iv_bg.setFitWidth(875);
		
		largeicon_img = new Image(fis_bacon_bg);
		iv_largeicon = new ImageView(largeicon_img); 
		iv_largeicon.setFitHeight(1080);			iv_largeicon.setFitWidth(500);
		timeline_bacon = new Timeline(new KeyFrame(
		        Duration.millis(10),
		        ae -> iv_largeicon.setY(bacon_position())));
		timeline_bacon.setCycleCount(Animation.INDEFINITE);
		timeline_bacon.play();		
		
		miniicon_img = new Image(fis_miniicon);
		iv_miniicon = new ImageView(miniicon_img);
		iv_miniicon.setFitHeight(1080);			iv_miniicon.setFitWidth(500);
		timeline_minibacon = new Timeline(new KeyFrame(
		        Duration.millis(40),
		        ae -> iv_miniicon.setY(minibacon_position())));
		timeline_minibacon.setCycleCount(Animation.INDEFINITE);
		timeline_minibacon.play();	
		

		// Extracting loginInformation information
		Text dotw = new Text(loginInformation.getDOTW());
		dotw.setFont(Font.font("Century Gothic", 20));		
		dotw.setX(400);			dotw.setY(20);
		
		Text time_phrase = new Text(loginInformation.getTimePhrase());	
		time_phrase.setX(610);	time_phrase.setY(230);
		time_phrase.setFont(Font.font("Century Gothic", FontPosture.ITALIC, 30));
		
		Text time = new Text(loginInformation.getTime());
		time.setX(524);			time.setY(154);
		time.setFont(Font.font("Century Gothic" ,FontWeight.MEDIUM,60)); 
		time.setFill(Color.WHITESMOKE);
		
		Timeline timeline = new Timeline(new KeyFrame(
		        Duration.millis(1000),
		        ae -> time.setText(loginInformation.getTime())));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();		
		
		// Login features
		username_field = new TextField();
		username_field.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				if (arg0.getCode().equals(KeyCode.ENTER)) {
					if (username_field.getText().equals(account[0]) && password_field.getText().equals(account[1]))
						primaryStage.setScene(menu_scene());
					password_field.setText("");
				}
			}
		});
		username_field.setPrefSize(226, 21);
		username_field.setId("user-pass-field");
		username_field.setLayoutX(542.35);
		username_field.setLayoutY(246.75);
		
		password_field = new PasswordField();
		password_field.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				if (arg0.getCode().equals(KeyCode.ENTER)) {
					if (username_field.getText().equals(account[0]) && password_field.getText().equals(account[1]))
						primaryStage.setScene(menu_scene());
					password_field.setText("");
				}
			}
		});
		password_field.setPrefSize(226, 21);
		password_field.setId("user-pass-field");
		password_field.setLayoutX(599);
		password_field.setLayoutY(281.75);
		
		//Food button
		FileInputStream bacon_button_ic = null, ramen_button_ic = null;
		try {
			bacon_button_ic = new FileInputStream(img_loc + "bacon_button_icon.png");
			ramen_button_ic = new FileInputStream(img_loc + "ramen_button_icon.png");
			pause_button_ic = new FileInputStream(img_loc + "pause_button.png");
			resume_button_ic = new FileInputStream(img_loc + "resume_button.png");
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ImageView iv_bacon_icon = new ImageView(new Image(bacon_button_ic));
		ImageView iv_ramen_icon = new ImageView(new Image(ramen_button_ic));
		ImageView iv_resume_icon = new ImageView(new Image(resume_button_ic));
		
		Button bacon_button = new Button("", iv_bacon_icon);
		bacon_button.setId("bacon-button");
		bacon_button.setLayoutX(570);		bacon_button.setLayoutY(50);
		bacon_button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					fis_largeicon = new FileInputStream(img_loc + "bacon_background.png");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				largeicon_img = new Image(fis_largeicon);
				iv_largeicon.setImage(largeicon_img);
			}	
		});
		
		Button ramen_button = new Button("", iv_ramen_icon);
		ramen_button.setId("ramen-button");
		ramen_button.setLayoutX(645);		ramen_button.setLayoutY(50);
		ramen_button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					fis_largeicon = new FileInputStream(img_loc + "miniramen_background.png");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				largeicon_img = new Image(fis_largeicon);
				iv_largeicon.setImage(largeicon_img);
			}	
		});
		
		Button pas_res = new Button("", iv_resume_icon);
		pas_res.setId("pause-button");
		pas_res.setLayoutX(812);			pas_res.setLayoutY(450);
		
		pas_res.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				paus_res_counter++;
				if (paus_res_counter%2 == 1) {
					try {
						pause_button_ic = new FileInputStream(img_loc + "pause_button.png");
					}catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					iv_resume_icon.setImage(new Image(pause_button_ic));
					timeline_bacon.pause();
					timeline_minibacon.pause();
				}
				else {
					try {
						resume_button_ic = new FileInputStream(img_loc + "resume_button.png");
					}catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					iv_resume_icon.setImage(new Image(resume_button_ic));
					timeline_bacon.play();
					timeline_minibacon.play();
				}
			}
		});
		
		//Weather
		Text weather_txt = new Text("Waiting for connection...");
		weather_txt.setX(576);			weather_txt.setY(350);
		weather_arr = loginInformation.getWeather();
		weather_txt.setText("Temperature: " + weather_arr[0] + "\nApparent Temperature: " + weather_arr[1] +"\nHigh: " 
								+ weather_arr[2] + "\nLow: " + weather_arr[3]);

		// Adding
		root.getChildren().addAll(iv_bg, iv_miniicon, iv_egg_white, iv_yolk, iv_largeicon, dotw, time_phrase, 
				username_field, password_field, time, weather_txt, ramen_button, bacon_button, pas_res);
		
		//primaryStage properties 
		primaryStage.setTitle("Yolk");
		primaryStage.setScene(login_scene);
		primaryStage.setWidth(875);
		primaryStage.setHeight(540);
	//	primaryStage.setAlwaysOnTop(true);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	private int bacon_position() {
		if (bacon_rate<=0) bacon_rate+=1;
		else bacon_rate = -540;
		return bacon_rate;
	}
	private int minibacon_position() {
		if (minibacon_rate<=0) minibacon_rate+=1;
		else minibacon_rate = -540;
		return minibacon_rate;
	}
	private Scene menu_scene() {
		Group root = new Group();
		Scene menu_scene = new Scene(root);
		
		Button testing = new Button("Testing");
		root.getChildren().addAll(testing);
		return menu_scene;	
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}