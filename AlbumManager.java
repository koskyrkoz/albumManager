package album;
import java.util.*;


public class AlbumManager {
	private ArrayList<Album> Albums=new ArrayList<Album>();
	
	public AlbumManager(){
		
	}
	
	public void ShowListOfAlbums(){
		if(Albums.isEmpty()){
			System.out.println("There aren't any existing albums!");
		}else{
			for(Album index: Albums){
				System.out.println("Album name :"+index.getName()+"  Album type:"+index.getType()+"/n");
			}
		}
		
	}
	
	/*public void createNewPage(){
		Scanner reader = new Scanner(System.in);
		System.out.println("Type the date of the new page");
		String inputDate = reader.next();
		System.out.println("Type the type of the new page");
		String inputType = reader.next();
		if (inputType=="EmptyPage"){
			EmptyPage page = new EmptyPage(inputDate,inputType);
			Pages.add(page);
		}
	}*/
	
	public void createEmptyAlbum(){
		Album test = new Album("empty");
		Albums.add(test);
	}
	
	public void sortByDate(){
		
	}
	
	public void sortByCreation(){
		
	}
	
	
	
	public void ShowAlbumWContentsByCreation(String name){
		if (Albums.isEmpty())
		{
			System.out.println("There are not any existing albums");
		}
		else{
			
		}
	}
	
	public void showAlbumWContentsByDate(String name){
		if (Albums.isEmpty())
		{
			System.out.println("There are not any existing albums");
		}
		else{
			
		}
	}
	
	public Album searchAlbums(String keyword){
		Album temp = new Album(keyword);
		for (Album x:Albums){
			for (Page y:x.getPages()){
				if(y.getKeywords().contains(keyword)){
					temp.addPage(y);
				}
			}
		}
		return temp;
	}
	
	public void createAlbum(String name){
		Album created = new Album(name);
		Albums.add(created);
	}
	
	public boolean existingAlbum(String name){
		boolean temp=false;
		for(Album x:Albums){
			if(name.equals(x.getName())){
				temp = true;
			}
		}
		return temp;
	}
	
	public Album getAlbum(String name){
		for (Album x:Albums){
			if(name.equals(x.getName())){
				return x;
			}
		}
		return null;
		
	}

}
