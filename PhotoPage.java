package album;
import java.util.ArrayList;

public class PhotoPage extends Page {
	private String title;
	private String path;


	public PhotoPage(String date,String type,ArrayList<String> keywords, String path,String title) {
		super(date,"PhotoPage",keywords);
		this.path=path;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setText(String newText) {}	
	
	
}
