/**
 * @author Ahmed Abbas<a href="mailto:ahmed.abbas1@ucalgary.ca">ahmed.abbas1@ucalgary.ca</a>
 * 
 * @version	1.5
 * @since	1.0
 */

class Book{
	
	private String isbn;
	private int publicationYear;
	private int  pages;

	//Constructors
	public Book() {}

	/**
	 * @param isbn
	 * @param pages
	 */
	public Book(String isbn, int pages) {
		this.isbn = isbn;
		this.pages = pages;
	}

	/**
	 * @param isbn
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return isbn
	 */
	public String getIsbn() {
		return this.isbn;
	}

	/**
	 * @param publicationYear
	 */
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	/**
	 * @return publication year as int
	 */
	public int getPublicationYear() {
		return this.publicationYear;
	}

	/**
	 * @param pages
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}

	/**
	 * @return pages
	 */
	public int getPages() {
		return this.pages;
	}
}

class Paperback extends Book{

	//Constructors
	public Paperback(){
		super();
	}

	/**
	 * @param isbn
	 * @param pages
	 */
	public Paperback(String isbn, int pages) {
		super(isbn, pages);
	}

	/**
	 * @return message in String form
	 */
	public String coverArt() {
		return "Method coverArt called from PaperBack";
	};
}

abstract class Hardcover extends Book {

	public abstract void binding();

	//Constructors
	public Hardcover() {
		super();
	}

	/**
	 * @param isbn
	 * @param pages
	 */
	public Hardcover(String isbn, int pages) {
		super(isbn, pages);
	}
}




class Classic extends Hardcover {

	private int origPubYear = 1860;
	private Author theAuthor;
	private Publisher[] bookPublisher;

	//Constructors
	public Classic() {
		super();
	}

	/**
	 * @param isbn
	 * @param pages
	 */
	public Classic(String isbn, int pages) {
		super(isbn, pages);
	}

	/**
	 * @param isbn
	 * @param pages
	 * @param origPubYear
	 * @param theAuthor
	 * @param bookPublisher
	 */
	public Classic(String isbn, int pages, int origPubYear, Author theAuthor, Publisher[] bookPublisher) {
		super(isbn, pages);
		this.origPubYear = origPubYear;
		this.theAuthor = theAuthor;
		this.bookPublisher = bookPublisher;
	}

	/**
	 * @param theAuthor
	 */
	public void setTheAuthor(Author theAuthor) {
		this.theAuthor = theAuthor;
	}

	/**
	 * @return theAuthor
	 */
	public Author getTheAuthor() {
		return this.theAuthor;
	}

	/**
	 * @param origPubYear
	 */
	public void setOrigPubYear(int origPubYear) {
		this.origPubYear = origPubYear;
	}

	/**
	 * @return origPubYear
	 */
	public int getOrigPubYear() {
		return this.origPubYear;
	}

	/**
	 * @param bookPublisher
	 */
	public void setBookPublisher(Publisher[] bookPublisher) {
		if (bookPublisher.length < 1 || bookPublisher.length > 10) {
			System.out.println("Error: there must be 1 to 10 publishers.");
		} else {
			this.bookPublisher = bookPublisher;
		}
	}

	/**
	 * @return bookPublisher
	 */
	public Publisher[] getBookPublisher() {
		return this.bookPublisher;
	}

	/**
	 * @return message in String form
	 */
	public String createNotes() {
		return "Method createNotes called from Classic";
	}

	@Override
	public void binding() {
		// TODO Auto-generated method stub
	}
}

abstract class Fiction extends Paperback{
	
	public abstract String coverArt();

	//Constructors
	public Fiction(){
		super();
	}

	/**
	 * @param isbn
	 * @param pages
	 */
	public Fiction(String isbn, int pages) {
		super(isbn, pages);
	}

	/**
	 * @return message in String form
	 */
	public String genre() {
		return "Method genre called from Fiction";
	}
}

class Nonfiction extends Paperback{
	
	private Category deweyClassification;

	//Constructors
	public Nonfiction(){ super(); }

	/**
	 * @param isbn
	 * @param pages
	 */
	public Nonfiction(String isbn, int pages) { super(isbn, pages); }

	/**
	 * @param isbn
	 * @param pages
	 * @param deweyClassification
	 */
	public Nonfiction(String isbn, int pages, Category deweyClassification) {
		super(isbn, pages);
		this.setDeweyClassification(deweyClassification);
	}

	/**
	 * @param deweyClassification
	 */
	public void setDeweyClassification(Category deweyClassification) {
		this.deweyClassification = deweyClassification;
	}

	/**
	 * @return deweyClassification
	 */
	public Category getDeweyClassification() {
        return this.deweyClassification;
    }

	/**
	 * @return message in String form
	 */
	public String topic() {
		return "Method topic called from Nonfiction";
	}
}

class Novel extends Fiction{
	
	private Author theAuthor;
	private Series mySeries;

	//Constructors
	public Novel(){
		super();
	}

	/**
	 * @param isbn
	 * @param pages
	 */
	public Novel(String isbn, int pages) {
		super(isbn, pages);
	}

	/**
	 * @param isbn
	 * @param pages
	 * @param theAuthor
	 * @param mySeries
	 */
	public Novel(String isbn, int pages, Author theAuthor, Series mySeries) {
		super(isbn, pages);
		this.setTheAuthor(theAuthor);
		this.setMySeries(mySeries);
	}

	/**
	 * @param theAuthor
	 */
	public void setTheAuthor(Author theAuthor) {
		this.theAuthor = theAuthor;
	}

	/**
	 * @return theAuthor
	 */
	public Author getTheAuthor() {
		return this.theAuthor;
	}

	/**
	 * @param mySeries
	 */
	public void setMySeries(Series mySeries) {
		this.mySeries = mySeries;
	}

	/**
	 * @return mySeries
	 */
	public Series getMySeries() {
		return this.mySeries;
	}

	/**
	 * @return message in String form
	 */
	public String theme() {
		return "Method theme called from Novel";
	}

    /**
	 * @return message in String form
	 */
    public String coverArt() {
        return "Method coverArt called from Novel";
    }
}

class Anthology extends Fiction{
	
	private Story[] story;

	//Constructors
	public Anthology(){
		super();
	}

	/**
	 * @param isbn
	 * @param pages
	 */
	public Anthology(String isbn, int pages) {
		super(isbn, pages);
	}

	/**
	 * @param isbn
	 * @param pages
	 * @param story
	 */
	public Anthology(String isbn, int pages, Story[] story) {
		super(isbn, pages);
		this.setStory(story);
	}

	/**
	 * @param story
	 */
	public void setStory(Story[] story) {
		if (story.length < 5) { System.out.println("Error: at least five stories have to be in an Anthology."); }
		else { this.story = story; }
	}

	/**
	 * @return stories list
	 */
	public Story[] getStory() {
		return this.story;
	}

	/**
	 * @return message in String form
	 */
	public String storyOrder() {
		return "Method storyOrder called from Anthology";
	}

    /**
	 * @return message in String form
	 */
    public String coverArt() {
        return "Method coverArt called from Anthology";
    }
}

class Category{
	
	private Category subCategory;
	private Category superCategory;
	private String category;

	/**
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return category
	 */
	public String getCategory() {
		return this.category;
	}

	/**
	 * @param subCategory
	 */
	public void setSubCategory(Category subCategory) {
		this.subCategory = subCategory;
	}

	/**
	 * @return subCategory
	 */
	public Category getSubCategory() {
		return this.subCategory;
	}

	/**
	 * @param superCategory
	 */
	public void setSuperCategory(Category superCategory) {
		this.superCategory = superCategory;
	}

	/**
	 * 
	 * @return superCategory
	 */
	public Category getSuperCategory() {
		return this.superCategory;
	}

	/**
	 * @return message in String form
	 */
	public String sort() {
		return "Method sort called from Category";
	}
}





class Story{
	
	private Author theAuthor;

	/**
	 * @param theAuthor
	 */
	public void setTheAuthor(Author theAuthor) {
		this.theAuthor = theAuthor;
	}

	/**
	 * @return theAuthor
	 */
	public Author getTheAuthor() {
		return this.theAuthor;
	}

	/**
	 * @return message in String form
	 */
	public String plot() {
		return "Method plot called from Story";
	}
}

class Author{
	
	private String name = "Unknown";
	private String address;
	private int age;

	//Default Constructor
	public Author() {}

	/**
	 * @param name
	 * @param address
	 * @param age
	 */
	public Author(String name, String address, int age) {
		this.name = name;
		this.address = address;
		this.age = age;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return age
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 *
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * @return String message
	 */
	public String write() {
		return "Method write called from Author";
	}
}

class Series{
	
	private String seriesName;

	/**
	 * @param seriesName
	 */
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	/**
	 * @return seriesName
	 */
	public String getSeriesName() {
		return this.seriesName;
	}

	/**
	 * @return message in String form
	 */
	public String theme() {
		return "Method theme called from Series";
	}
}

class Publisher{
	
	private String name;
	private String address;
	private Classic[] classicsCatalog;
	
	/**
	 * @param name
	 * @param address
	 */
	public Publisher(String name, String address) {
		this.name = name;
		this.address = address;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 
	 * @return address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * @param classicsCatalog
	 */
	public void setClassicsCatalog(Classic[] classicsCatalog) {
		if (classicsCatalog.length < 0 || classicsCatalog.length > 10){ System.out.println("Error: there must be 0 to 10 Classics."); }
		else { this.classicsCatalog = classicsCatalog; }
	}

	/**
	 * @return Classics list
	 */
	public Classic[] getClassicsCatalog() {
		return this.classicsCatalog;
	}

	/**
	 * @return message in String form
	 */
	public String printLetterhead() {
		return "Method printLetterhead called from Publisher";
	}
}

class Contract{
	
	private String data;
	private Publisher publisherInfo;
	private Author authorInfo;
	
	/**
	 * @param data
	 * @param publisherInfo
	 * @param authorInfo
	 */
	public Contract(String data, Publisher publisherInfo, Author authorInfo) {
		this.data = data;
		this.publisherInfo = publisherInfo;
		this.authorInfo = authorInfo;
	}

	/**
	 * @param data
	 */
	public void setData(String data) {
		this.data = data;
	}
	
	/**
	 * @return data
	 */
	public String getData() {
		return this.data;
	}

	/**
	 * @param publisherInfo
	 */
	public void setPublisherInfo(Publisher publisherInfo) {
		this.publisherInfo = publisherInfo;
	}

	/**
	 * 
	 * @return publisherInfo
	 */
	public Publisher getPublisherInfo() {
		return this.publisherInfo;
	}

	/**
	 * @param authorInfo
	 */
	public void setAuthorInfo(Author authorInfo) {
		this.authorInfo = authorInfo;
	}

	/**
	 * @return authorInfo
	 */
	public Author getAuthorInfo() {
		return this.authorInfo;
	}

	/**
	 * @return message in String form
	 */
	public String printContract() {
		return "Method printContract called from Contract";
	}
}




public class MyBook {}

