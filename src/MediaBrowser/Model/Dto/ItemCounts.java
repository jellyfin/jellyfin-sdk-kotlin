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
	private int MovieCount;
	public final int getMovieCount()
	{
		return MovieCount;
	}
	public final void setMovieCount(int value)
	{
		MovieCount = value;
	}
	/** 
	 Gets or sets the series count.
	 
	 <value>The series count.</value>
	*/
	private int SeriesCount;
	public final int getSeriesCount()
	{
		return SeriesCount;
	}
	public final void setSeriesCount(int value)
	{
		SeriesCount = value;
	}
	/** 
	 Gets or sets the episode count.
	 
	 <value>The episode count.</value>
	*/
	private int EpisodeCount;
	public final int getEpisodeCount()
	{
		return EpisodeCount;
	}
	public final void setEpisodeCount(int value)
	{
		EpisodeCount = value;
	}
	/** 
	 Gets or sets the game count.
	 
	 <value>The game count.</value>
	*/
	private int GameCount;
	public final int getGameCount()
	{
		return GameCount;
	}
	public final void setGameCount(int value)
	{
		GameCount = value;
	}
	/** 
	 Gets or sets the game system count.
	 
	 <value>The game system count.</value>
	*/
	private int GameSystemCount;
	public final int getGameSystemCount()
	{
		return GameSystemCount;
	}
	public final void setGameSystemCount(int value)
	{
		GameSystemCount = value;
	}
	/** 
	 Gets or sets the trailer count.
	 
	 <value>The trailer count.</value>
	*/
	private int TrailerCount;
	public final int getTrailerCount()
	{
		return TrailerCount;
	}
	public final void setTrailerCount(int value)
	{
		TrailerCount = value;
	}
	/** 
	 Gets or sets the song count.
	 
	 <value>The song count.</value>
	*/
	private int SongCount;
	public final int getSongCount()
	{
		return SongCount;
	}
	public final void setSongCount(int value)
	{
		SongCount = value;
	}
	/** 
	 Gets or sets the album count.
	 
	 <value>The album count.</value>
	*/
	private int AlbumCount;
	public final int getAlbumCount()
	{
		return AlbumCount;
	}
	public final void setAlbumCount(int value)
	{
		AlbumCount = value;
	}
	/** 
	 Gets or sets the music video count.
	 
	 <value>The music video count.</value>
	*/
	private int MusicVideoCount;
	public final int getMusicVideoCount()
	{
		return MusicVideoCount;
	}
	public final void setMusicVideoCount(int value)
	{
		MusicVideoCount = value;
	}
	/** 
	 Gets or sets the box set count.
	 
	 <value>The box set count.</value>
	*/
	private int BoxSetCount;
	public final int getBoxSetCount()
	{
		return BoxSetCount;
	}
	public final void setBoxSetCount(int value)
	{
		BoxSetCount = value;
	}
	/** 
	 Gets or sets the book count.
	 
	 <value>The book count.</value>
	*/
	private int BookCount;
	public final int getBookCount()
	{
		return BookCount;
	}
	public final void setBookCount(int value)
	{
		BookCount = value;
	}
	/** 
	 Gets or sets the unique types.
	 
	 <value>The unique types.</value>
	*/
	private java.util.ArrayList<String> UniqueTypes;
	public final java.util.ArrayList<String> getUniqueTypes()
	{
		return UniqueTypes;
	}
	public final void setUniqueTypes(java.util.ArrayList<String> value)
	{
		UniqueTypes = value;
	}

	public ItemCounts()
	{
		setUniqueTypes(new java.util.ArrayList<String>());
	}
}