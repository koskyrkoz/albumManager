package album;
import java.util.ArrayList;

public class TitlePage extends Page{
	private String title;

	public TitlePage(String date,String type,ArrayList<String> keywords,String title) {
		super(date,"TitlePage",keywords);
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

	public void setText(String newText) {}


	public void setPath(String newPath) {}
}
