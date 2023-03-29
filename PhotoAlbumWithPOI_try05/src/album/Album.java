package album;
import java.util.*;
import java.text.*;

import src.poiExtractor.*;

public class Album {
	private String name;
	private ArrayList<Page> Pages= new ArrayList<Page>();
	private boolean albumType=true;
	
	
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
	
	public void changeType(){
		albumType=!albumType;
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
		System.out.println("Type the date of the new page(e.g. 21/12/2016)");
		String inputDate = reader.next();
		System.out.println("Type the type of the new page");
		String inputType = reader.next();
		String answer="yes";
		while(answer.equals("yes")){
			System.out.println("Type a keyword:");
			String keyword = reader.next();
			keywords.add(keyword);
			System.out.println("Do you want to continue typing keywords?(yes or no)");
			answer =reader.next();
			while(!answer.equals("yes") && !answer.equals("no")){
				System.out.println("Type yes or no to coninue");
				answer =reader.next();
			}
		}
		if (inputType.equals("EmptyPage")){
			EmptyPage page = new EmptyPage(inputDate,inputType,keywords);
			Pages.add(page);
		}
		else if(inputType.equals("PhotoPage")){	
			System.out.println("Type the path of photo: ");
			String path = reader.next();
			System.out.println("Type the title for this page: ");
			String title = reader.next();
			PhotoPage page = new PhotoPage(inputDate,inputType,keywords,path,title);
			Pages.add(page);
			
		}
		else if(inputType.equals("TextPage")){
			System.out.println("Type the text for this page: ");
			String text = reader.next();
			System.out.println("Type the title for this page: ");
			String title = reader.next();
			TextPage page = new TextPage(inputDate,inputType,keywords,text,title);
			Pages.add(page);
		}
		else if(inputType.equals("TitlePage")){
			System.out.println("Type the title for this page: ");
			String title = reader.next();
			TitlePage page = new TitlePage(inputDate,inputType,keywords,title);
			Pages.add(page);
		}
		else{
			System.out.println("The type of page is invalid \n");
			createNewPage();
		}
		//reader.close();
	}
	
	
	public void sortContentsCreation(){
		ArrayList<IPageExtractorToPoi> PageExtr= new ArrayList<IPageExtractorToPoi>();
		for (Page x: Pages){
			if (x.getType().equals("EmptyPage")){
				BlankPageExtractor empty = new BlankPageExtractor();
				PageExtr.add(empty);
			}else if(x.getType().equals("TitlePage")){
				TitlePageExtractor title = new TitlePageExtractor(x.getTitle());
				PageExtr.add(title);
			}else if(x.getType().equals("TextPage")){
				ContentPageExtractor content = new ContentPageExtractor(x.getTitle(),x.getText());
				PageExtr.add(content);
			}else if(x.getType().equals("PhotoPage")){
				PicturePageWResizablePicExtractor picture = new PicturePageWResizablePicExtractor(x.getTitle(),x.getPath());
				PageExtr.add(picture);
			}
		}
		AlbumExtractor albumExtractor = new AlbumExtractor(name);
		for (IPageExtractorToPoi x: PageExtr){
			albumExtractor.addSlideExtractor(x);
			
		}
		albumExtractor.exportPOISlideShow();
	}
	
	public void sortContentsDate() throws ParseException {
		ArrayList<IPageExtractorToPoi> PageExtr= new ArrayList<IPageExtractorToPoi>();
		
		String DateRN[];
		SimpleDateFormat dateIndex=new SimpleDateFormat("dd-MM-yyyy");
		Date[] dateFormat=new Date[Pages.size()];
		int i=0;
		for(Page index:Pages){
			DateRN=splitDate(index.getDate());
			dateFormat[i]=dateIndex.parse(DateRN[0]+"-"+DateRN[1]+"-"+DateRN[2]);
			i++;
		}
		Page PagesArray[]=Pages.toArray(new Page[Pages.size()]);
		int j;
		boolean flag=true;
		Date temp;
		Page tempPage;
		while(flag){             //bubble sort
			flag=false;
			for(j=0;j<Pages.size()-1;j++){
				if(dateFormat[j].compareTo(dateFormat[j+1])>=0){
					temp=dateFormat[j];
					tempPage=PagesArray[j];
					PagesArray[j]=PagesArray[j+1];
					PagesArray[j+1]=tempPage;
					dateFormat[j]=dateFormat[j+1];
					dateFormat[j+1]=temp;
					flag=true;
				}
			}
		}
		
		for (int x=0;x<PagesArray.length;x++){
			if (PagesArray[x].getType().equals("EmptyPage")){
				BlankPageExtractor empty = new BlankPageExtractor();
				PageExtr.add(empty);
			}else if(PagesArray[x].getType().equals("TitlePage")){
				TitlePageExtractor title = new TitlePageExtractor(PagesArray[x].getTitle());
				PageExtr.add(title);
			}else if(PagesArray[x].getType().equals("TextPage")){
				ContentPageExtractor content = new ContentPageExtractor(PagesArray[x].getTitle(),PagesArray[x].getText());
				PageExtr.add(content);
			}else if(PagesArray[x].getType().equals("PhotoPage")){
				PicturePageWResizablePicExtractor picture = new PicturePageWResizablePicExtractor(PagesArray[x].getTitle(),PagesArray[x].getPath());
				PageExtr.add(picture);
			}
		}
		AlbumExtractor albumExtractor = new AlbumExtractor(name);
		for (IPageExtractorToPoi x: PageExtr){
			albumExtractor.addSlideExtractor(x);
			
		}
		albumExtractor.exportPOISlideShow();
		
	}
	
	public String invalidContentToChange(String tempType,String change){
		Scanner reader = new Scanner(System.in);
		while(tempType.equals("EmptyPage") && (change.equals("text") || change.equals("title") || change.equals("path"))){
			System.out.println("You can't change the "+ change +" of this page because it doesn't have one \n . Try another content");
			change = reader.next();
		}
		while(tempType.equals("PhotoPage") && change.equals("text")){
			System.out.println("You can't change the "+ change +" of this page because it doesn't have one \n . Try another content");
			change = reader.next();				
		}
		while(tempType.equals("TextPage") && change.equals("path")){
			System.out.println("You can't change the "+ change +" of this page because it doesn't have one \n . Try another content");
			change = reader.next();
		}
		while(tempType.equals("TitlePage") && (change.equals("text") || change.equals("path"))){
			System.out.println("You can't change the "+ change +" of this page because it doesn't have one \n . Try another content");
			change = reader.next();
		}
		//reader.close();
		return change;
	}
	
	public String [] splitDate(String Date){
		String [] dateparts = Date.split("-");
		/*int [] splitted = new int[dateparts.length];
		for(int i=0; i< dateparts.length; i++){
			splitted[i]=Integer.parseInt(dateparts[i]);
		}
		return splitted;*/
		return dateparts;
	}
	
	
}
