package album;

import java.util.ArrayList;

public class EmptyPage extends Page {

	public EmptyPage(String date, String type,ArrayList<String> keywords) {
		super(date,"EmptyPage",keywords);
	}

	public void setText(String newText) {}

	public void setPath(String newPath) {}

	public void setTitle(String newTitle) {}

	public String getTitle() {
		return null;
	}

	public String getText() {
		return null;
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}
}
