package MediaBrowser.Model.Dto;

/** 
 Class LibrarySummary
*/
public class ItemCounts
{
	/** 
	 Gets or sets the movie count.
	 
	 <value>The movie count.</value>
	*/
	private int privateMovieCount;
	public final int getMovieCount()
	{
		return privateMovieCount;
	}
	public final void setMovieCount(int value)
	{
		privateMovieCount = value;
	}
	/** 
	 Gets or sets the series count.
	 
	 <value>The series count.</value>
	*/
	private int privateSeriesCount;
	public final int getSeriesCount()
	{
		return privateSeriesCount;
	}
	public final void setSeriesCount(int value)
	{
		privateSeriesCount = value;
	}
	/** 
	 Gets or sets the episode count.
	 
	 <value>The episode count.</value>
	*/
	private int privateEpisodeCount;
	public final int getEpisodeCount()
	{
		return privateEpisodeCount;
	}
	public final void setEpisodeCount(int value)
	{
		privateEpisodeCount = value;
	}
	/** 
	 Gets or sets the game count.
	 
	 <value>The game count.</value>
	*/
	private int privateGameCount;
	public final int getGameCount()
	{
		return privateGameCount;
	}
	public final void setGameCount(int value)
	{
		privateGameCount = value;
	}
	/** 
	 Gets or sets the game system count.
	 
	 <value>The game system count.</value>
	*/
	private int privateGameSystemCount;
	public final int getGameSystemCount()
	{
		return privateGameSystemCount;
	}
	public final void setGameSystemCount(int value)
	{
		privateGameSystemCount = value;
	}
	/** 
	 Gets or sets the trailer count.
	 
	 <value>The trailer count.</value>
	*/
	private int privateTrailerCount;
	public final int getTrailerCount()
	{
		return privateTrailerCount;
	}
	public final void setTrailerCount(int value)
	{
		privateTrailerCount = value;
	}
	/** 
	 Gets or sets the song count.
	 
	 <value>The song count.</value>
	*/
	private int privateSongCount;
	public final int getSongCount()
	{
		return privateSongCount;
	}
	public final void setSongCount(int value)
	{
		privateSongCount = value;
	}
	/** 
	 Gets or sets the album count.
	 
	 <value>The album count.</value>
	*/
	private int privateAlbumCount;
	public final int getAlbumCount()
	{
		return privateAlbumCount;
	}
	public final void setAlbumCount(int value)
	{
		privateAlbumCount = value;
	}
	/** 
	 Gets or sets the music video count.
	 
	 <value>The music video count.</value>
	*/
	private int privateMusicVideoCount;
	public final int getMusicVideoCount()
	{
		return privateMusicVideoCount;
	}
	public final void setMusicVideoCount(int value)
	{
		privateMusicVideoCount = value;
	}
	/** 
	 Gets or sets the box set count.
	 
	 <value>The box set count.</value>
	*/
	private int privateBoxSetCount;
	public final int getBoxSetCount()
	{
		return privateBoxSetCount;
	}
	public final void setBoxSetCount(int value)
	{
		privateBoxSetCount = value;
	}
	/** 
	 Gets or sets the book count.
	 
	 <value>The book count.</value>
	*/
	private int privateBookCount;
	public final int getBookCount()
	{
		return privateBookCount;
	}
	public final void setBookCount(int value)
	{
		privateBookCount = value;
	}
	/** 
	 Gets or sets the unique types.
	 
	 <value>The unique types.</value>
	*/
	private java.util.ArrayList<String> privateUniqueTypes;
	public final java.util.ArrayList<String> getUniqueTypes()
	{
		return privateUniqueTypes;
	}
	public final void setUniqueTypes(java.util.ArrayList<String> value)
	{
		privateUniqueTypes = value;
	}

	public ItemCounts()
	{
		setUniqueTypes(new java.util.ArrayList<String>());
	}
}