package album;
import java.util.*;

public class Album {
	private String name;
	private ArrayList<Page> Pages= new ArrayList<Page>();
	private boolean albumType; //False gia paragwgo, alliws true
	
	public Album(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String newName){
		this.name=newName;
	}
	
	public String getType(){
		String temp;
		if(albumType){
			temp = "Generic";
					
		}else{
			temp =  "Produced";
		}
		return temp;
		
	}
	
	public ArrayList<Page> getPages(){
		return Pages;
	}
	
	public Page getPage(int x){
		return Pages.get(x);
	}
	
	
	

	public void addPage(Page page){
		Pages.add(page);
	}
	
	public void createNewPage(){
		ArrayList<String> keywords = new ArrayList<String>();
		Scanner reader = new Scanner(System.in);
		System.out.println("Type the date of the new page");
		String inputDate = reader.next();
		System.out.println("Type the type of the new page");
		String inputType = reader.next();
		if (inputType.equals("EmptyPage")){
			String answer="yes";
			while(answer.equals("yes")){
				System.out.println("Type a keyword:");
				keywords.add(reader.next());
				System.out.println("Do you want to continue typing keywords?(yes or no)");
				answer =reader.next();
			}
			EmptyPage page = new EmptyPage(inputDate,inputType,keywords);
			Pages.add(page);
		}
		else if(inputType.equals("PhotoPage")){
			String answer="yes";
			while(answer.equals("yes")){
				System.out.println("Type a keyword:");
				keywords.add(reader.next());
				System.out.println("Do you want to continue typing keywords?(yes or no)");
				answer =reader.next();
			}	
			System.out.println("Type the path of photo: ");
			String path = reader.next();
			System.out.println("Type the title for this page: ");
			String title = reader.next();
			PhotoPage page = new PhotoPage(inputDate,inputType,keywords,path,title);
			Pages.add(page);
			
		}
		else if(inputType.equals("TextPage")){
			String answer="yes";
			while(answer.equals("yes")){
				System.out.println("Type a keyword:");
				keywords.add(reader.next());
				System.out.println("Do you want to continue typing keywords?(yes or no)");
				answer =reader.next();
			}
			System.out.println("Type the text for this page: ");
			String text = reader.next();
			System.out.println("Type the title for this page: ");
			String title = reader.next();
			TextPage page = new TextPage(inputDate,inputType,keywords,text,title);
			Pages.add(page);
		}
		else if(inputType.equals("TitlePage")){
			String answer="yes";
			while(answer.equals("yes")){
				System.out.println("Type a keyword:");
				keywords.add(reader.next());
				System.out.println("Do you want to continue typing keywords?(yes or no)");
				answer =reader.next();
			}
			System.out.println("Type the title for this page: ");
			String title = reader.next();
			TitlePage page = new TitlePage(inputDate,inputType,keywords,title);
			Pages.add(page);
		}
		else{
			System.out.println("The type of page is invalid");
			createNewPage();
		}
		reader.close();
	}
	
	public void showContents(){
		//logika edw mpainei to pptx
		
			
		
	}
	
	public void sortContentsCreation(){
		
	}
	
	public void sortContentsDate() {
		
	}
	
	public String invalidContentToChange(String tempType,String change){
		Scanner reader = new Scanner(System.in);
		if (tempType.equals("EmptyPage") && (change.equals("text") || change.equals("title") || change.equals("path"))){
			System.out.println("You can't change the "+ change +" of this page because it doesn't have one \n . Try another content");
			change = reader.next();
		}
		else if(tempType.equals("PhotoPage") && change.equals("text")){
			System.out.println("You can't change the "+ change +" of this page because it doesn't have one \n . Try another content");
			change = reader.next();				
		}
		else if(tempType.equals("TextPage") && change.equals("path")){
			System.out.println("You can't change the "+ change +" of this page because it doesn't have one \n . Try another content");
			change = reader.next();
		}
		else if(tempType.equals("TitlePage") && (change.equals("text") || change.equals("path"))){
			System.out.println("You can't change the "+ change +" of this page because it doesn't have one \n . Try another content");
			change = reader.next();
		}
		reader.close();
		return change;
	}

}
