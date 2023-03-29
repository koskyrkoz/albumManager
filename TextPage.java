package album;
import java.util.ArrayList;

public class TextPage extends Page{
	private String title;
	private String text;

	public TextPage(String date,String type,ArrayList<String> keywords,String text, String title) {
		super(date,"TextPage",keywords);
		this.text=text;
		this.title=title;
	}
	
	public void changeContents(){
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setPath(String newPath) {}

}
