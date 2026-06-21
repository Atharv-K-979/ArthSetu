package com.arthsetu;

import javafx.application.Application;
import javafx.stage.Stage;
import com.arthsetu.Models.Model;

public class ArthsetuApplication extends Application {
	@Override
	public void start(Stage stage) {
		Model.getInstance().getViewFactory().showLoginWindow();
	}

	public static void main(String[] args) {
		launch();
	}

}