package album;
import java.util.*;

public abstract class Page {
	private String date;
	private ArrayList<String> keywords=new ArrayList<String>();
	private String type;
	
	public Page(String date, String type,ArrayList<String> keywords){
		this.date=date;
		this.type=type;
		this.keywords=keywords;
	}
	
	public Page(String date, String type){
		this.date=date;
		this.type=type;
	}	
	
	public abstract void changeContents();
	
	public String getDate(){
		return date;
	}
	
	public ArrayList<String> getKeywords(){
		return keywords;
	}
	
	public String getType(){
		return type;
	}
	
	public void setDate(String newDate){
		this.date=newDate;
	}
	

	public void setKeywords(){
		Scanner reader = new Scanner(System.in);
		String answer ="yes";
		while(answer.equals("yes")){
			System.out.println("Do you want to add or to delete keywords?");
			String input = reader.next();
			while(!(input.equals("add")) && !(input.equals("delete")) ){
				System.out.println("Invalid input. Type add or delete");
				input = reader.next();
			}
			if (input.equals("add")){
				String answer1="yes";
				while(answer1.equals("yes")){
					System.out.println("Type the keyword you want to add: ");
					String input1=reader.next();
					if (!keywords.contains(input1)){
						keywords.add(input1);
					}
					System.out.println("Do you want to continue typing keywords?");
					answer1=reader.next();
					while(!answer1.equals("yes") && !answer1.equals("no")){
						System.out.println("Type yes or no");
						answer1=reader.next();
					}
				}
			}
			else if(input.equals("delete")){
				String answer2="yes";
				while(answer2.equals("yes")){
					System.out.println("There is the list of keywords:");
					System.out.println(toString());
					System.out.println("Type the keyword you want to delete:");
					String input2=reader.next();
					if (keywords.contains(input2) && keywords.size()>0 ){
						keywords.remove(input2);
					}
					System.out.println("Do you want to continue deleting keywords?");
					answer2=reader.next();
					if(!answer2.equals("yes") && !answer2.equals("no")){
						System.out.println("Type yes or no");
						answer2=reader.next();
					}	
				}	
					
			}
			System.out.println("Do you want to continue adding or deleting keywords?");
			answer=reader.next();
			if(!answer.equals("yes") && !answer.equals("no")){
				System.out.println("Type yes or no");
				answer=reader.next();
			}
		}	
		reader.close();
	}

	
	public String toString(){
		String temp = "";
		for (String x:keywords){
			temp += x;
		}
		return temp;
	}

	public abstract void setText(String newText);

	public abstract void setPath(String newPath);
	
	public abstract void setTitle(String newTitle);
}
