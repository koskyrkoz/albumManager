package album;
import java.util.*;


public class AlbumManager {
	private ArrayList<Album> Albums=new ArrayList<Album>();
		
	public void ShowListOfAlbums(){
		if(Albums.isEmpty()){
			System.out.println("There aren't any existing albums!");
		}else{
			for(Album index: Albums){
				System.out.println("Album name :"+index.getName()+"  Album type:"+index.getType()+"\n");
			}
		}
		
	}
		
	public Album createEmptyAlbum(){
		Album test = new Album("empty");
		return test;
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
		if(temp.getPages().isEmpty()){
			System.out.println("Keyword does not exist.An empty album is created");
			return createEmptyAlbum();
		}else{
			return temp;
		}
		
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
		return createEmptyAlbum();
		
	}
	
	public ArrayList<Album> getAlbums(){
		return Albums;
	}
	
	

}
