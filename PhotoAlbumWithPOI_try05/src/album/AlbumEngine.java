package album;

import java.text.ParseException;
import java.util.*;

public class AlbumEngine {

	public static void main(String[] args) throws ParseException {
		
		AlbumManager manager = new AlbumManager();
		ArrayList<Album> ProducedAlbums = new ArrayList<Album>();
		Scanner reader = new Scanner(System.in);	
		
		String option;	
		int number;
		String change;
		String newDate;
		String newTitle;
		String newText;
		String newPath;
		String ans;
		String keyword;
		
		System.out.println("Welcome to Album Manager. \n");
		System.out.println("First of all you have to create a new album. \n Enter the name of album: \n");	
		String name = reader.nextLine();
		manager.createAlbum(name);
		
		//String endanswer="yes";
		while(true){
			System.out.println("Choose what you want to do:");
			System.out.println("1.Show list of albums");
			System.out.println("2.Show album contents by creation date");
			System.out.println("3.Show album contents by chronologically shorted");
			System.out.println("4.Add new empty album");
			System.out.println("5.Add new page to a chosen album");
			System.out.println("6.Change contents of an existing page");
			System.out.println("7.Search pages by a keyword");
			
			option = reader.nextLine();
			while(!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4") && !option.equals("5") && !option.equals("6") && !option.equals("7")){
				System.out.println("Invalid input. Type 1-7 to continue: ");
				option = reader.nextLine();
			}
			
			if (option.equals("1")){
				manager.ShowListOfAlbums();
			}
			else if(option.equals("2")){
				System.out.println("Enter the name of album: ");	
				name = reader.nextLine();
				while(!manager.existingAlbum(name)){
					System.out.println("Enter an existing name of album: ");	
					name = reader.nextLine();
				}
				if(manager.getAlbum(name).getPages().size()==0){
					System.out.println("This album is Empty");
				}
				else{
					manager.getAlbum(name).sortContentsCreation();
				}	
			}
			else if(option.equals("3")){
				System.out.println("Enter the name of album: ");	
				name = reader.nextLine();
				while(!manager.existingAlbum(name)){
					System.out.println("Enter an existing name of album: ");	
					name = reader.nextLine();
				}
				manager.getAlbum(name).sortContentsDate();
			}
			else if(option.equals("4")){
				System.out.println("Enter the name of album that you want to create: ");	
				name = reader.nextLine();
				while(manager.existingAlbum(name)){
					System.out.println("This album is already existed. Type another name: ");	
					name = reader.nextLine();
				}
				manager.createAlbum(name);
				
			}
			else if(option.equals("5")){
				System.out.println("Enter the name of album where you want to create the new page: ");	
				name = reader.nextLine();
				if (ProducedAlbums.contains(manager.getAlbum(name))){
					System.out.println("You cannot edit this album because it is produced by search");
				}else{
					while(!manager.existingAlbum(name)){
						System.out.println("This album is not existed. Type another name: ");	
						name = reader.nextLine();
					}
					manager.getAlbum(name).createNewPage();
				}
				
			}
			else if(option.equals("6")){
				System.out.println("Enter the name of the album where the page you want to change its contents is: ");	
				name = reader.nextLine();
				if (ProducedAlbums.contains(manager.getAlbum(name))){
					System.out.println("You cannot edit this album because it is produced by search");
				}else{
					while(!manager.existingAlbum(name)){
						System.out.println("This album is not existed. Type another name: ");	
						name = reader.nextLine();
					}
					System.out.println("Enter the number of page you want to change: ");
					number=reader.nextInt();
					while(manager.getAlbum(name).getPages().size()<=number || number<0){
						System.out.println("Enter a number between 0-"+ (manager.getAlbum(name).getPages().size()-1)+": " );
						number=reader.nextInt();
					}
					Page tempPage = manager.getAlbum(name).getPage(number);
					String tempType = manager.getAlbum(name).getPage(number).getType();
					String t = "yes";
					while(t.equals("yes")){
						System.out.println("What do you want to change? (date,text,keywords,path,title)");
						System.out.println("Note: This page is a "+ tempType);
						change = reader.nextLine();
						manager.getAlbum(name).invalidContentToChange(tempType,change);	
						while(!change.equals("date") && !change.equals("text") && !change.equals("keywords") && !change.equals("path") && !change.equals("title") ){
							System.out.println("There isn't any field to change. Enter a correct word: ");
							change = reader.nextLine();
							manager.getAlbum(name).invalidContentToChange(tempType,change);				
						}
						if(change.equals("date")){
							System.out.println("Enter the new date of the photo: ");
							newDate = reader.nextLine();
							tempPage.setDate(newDate);
						}else if(change.equals("text")){
							System.out.println("Enter the new text of the photo: ");
							newText = reader.nextLine();
							tempPage.setText(newText);
						}else if(change.equals("keywords")){
							tempPage.setKeywords();
						}else if(change.equals("path")){
							System.out.println("Enter the new path of the photo: ");
							newPath = reader.nextLine();
							tempPage.setPath(newPath);
						}else if(change.equals("title")){
							System.out.println("Enter the new title of the photo: ");
							newTitle = reader.nextLine();
							tempPage.setTitle(newTitle);
						}
						
						System.out.println("Do you want to continue changing contents?(yes or no)");
						t =reader.nextLine();
						while(!t.equals("yes") && !t.equals("no")){
							System.out.println("Type yes or no to move on");
							t =reader.nextLine();
						}	
					}	
				}
				
			}
			else if(option.equals("7")){
				System.out.println("Type the keyword you want to search for: ");
				keyword =reader.nextLine();
				Album produced = manager.searchAlbums(keyword);
				manager.getAlbum(keyword).changeType();
				ProducedAlbums.add(produced);
			}
			System.out.println("Do you want to continue Album Manager? (yes or no)");
			//String ans = reader.nextLine();
			try{
			
			ans =reader.nextLine();
			if(!ans.equals("yes")){
				break;
			}
			/*endanswer=reader.nextLine();
			while(!endanswer.equals("yes") && !endanswer.equals("no")){
				System.out.println("You have to type yes or no to continue");
				endanswer=reader.nextLine();
			}*/		
			}catch(NoSuchElementException e){
				System.out.println("MPIKE EDW MESA");
			}
		}
		//reader.close();
		System.out.println("End of Album Manager");
	}

}
