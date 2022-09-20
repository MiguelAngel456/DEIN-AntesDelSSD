module TANDA1 {
	requires javafx.controls;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens ejerciciob to javafx.graphics, javafx.fxml;
	opens model to javafx.graphics, javafx.fxml;
}
