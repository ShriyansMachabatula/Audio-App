//Shriyans Machabatula
//Student ID: 501180685

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */

public class Library {

	private ArrayList<Song> songs;
	private ArrayList<AudioBook> audiobooks;
	private ArrayList<Playlist> playlists;

	public Library() {
		songs = new ArrayList<Song>();
		audiobooks = new ArrayList<AudioBook>();
		playlists = new ArrayList<Playlist>();
	}

	public void download(AudioContent content) { // content is a song, audiobook, or podcast
		if (content == null) {
			throw new InvalidAudioContentException("Invalid Audio Content");
		}
		if (content.getType().equals(Song.TYPENAME)) { // if the content is a song
			if (songs.contains(content)) { // if the song is already in the library
				throw new SongAlreadyDownlaodedException("Song " + content.getTitle() + " already downloaded");
			} else { // if the song is not in the library
				songs.add((Song) content); // add the song to the library
				System.out.println(content.getType() + " " + content.getTitle() + " " + "Added to Library");
			}
		} else if (content.getType().equals(AudioBook.TYPENAME)) { // if the content is an audiobook
			if (audiobooks.contains(content)) { // if the audiobook is already in the library
				throw new AudioBookAlreadDownlaodedException("AudioBook " + content.getTitle() + " already downloaded");
			} else { // if the audiobook is not in the library
				audiobooks.add((AudioBook) content); // add the audiobook to the library
				System.out.println(content.getType() + " " + content.getTitle() + " " + "Added to Library");
			}
		}
	}

	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs() { // print all songs in the library
		for (int i = 0; i < songs.size(); i++) { // for each song in the library
			int index = i + 1; // set the index to the current song
			System.out.print("" + index + ". "); // print the index
			songs.get(i).printInfo(); // print the song information
			System.out.println(); // print a new line
		}
	}

	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks() { // print all audiobooks in the library
		for (int i = 0; i < audiobooks.size(); i++) { // for each audiobook in the library
			int index = i + 1; // set the index to the current audiobook
			System.out.print("" + index + ". "); // print the index
			audiobooks.get(i).printInfo(); // print the audiobook information
			System.out.println(); // print a new line
		}
	}

	// Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	// Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	{
		for (int i = 0; i < playlists.size(); i++) { // for each playlist in the library
			int index = i + 1; // set the index to the current playlist
			System.out.print("" + index + ". "); // print the index
			System.out.println(playlists.get(i).getTitle()); // print the playlist information
		}
	}

	// Print the name of all artists.
	public void listAllArtists() {
		// First create a new (empty) array list of string
		// Go through the songs array list and add the artist name to the new arraylist
		// only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists
		// names
		ArrayList<String> artists = new ArrayList<String>(); // create a new array list of strings
		for (int i = 0; i < songs.size(); i++) { // for each song in the library
			if (!(artists.contains(songs.get(i).getArtist()))) { // if the artist is not in the list
				artists.add(songs.get(i).getArtist()); // add the artist to the list
			}
		}
		for (int i = 0; i < artists.size(); i++) { // for each artist in the list
			System.out.print(i + 1 + ". ");
			System.out.println(artists.get(i)); // print the artist
		}
	}

	// Delete a song from the library (i.e. the songs list) -
	// also go through all playlists and remove it from any playlist as well if it
	// is part of the playlist
	public void deleteSong(int index) { // delete a song from the library
		if (index < 1 || index > songs.size()) { // if the index is out of bounds
			throw new InvalidSongIndex("Invalid Song Index"); // throw an exception
		} else { // if the index is in bounds
			for (int i = 0; i < playlists.size(); i++) { // for each playlist in the library
				if (playlists.get(i).getContent().contains(songs.get(index - 1))) {
					playlists.get(i).deleteContent(playlists.get(i).getContent().indexOf(songs.get(index - 1)) + 1);
				}
			}
			songs.remove(index - 1); // remove the song from the library
		}
	}

	// Sort songs in library by year
	public void sortSongsByYear() {
		// Use Collections.sort());
		Collections.sort(songs, new SongYearComparator()); // sort the songs by year
	}

	// Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song> { // create a new comparator
		public int compare(Song s1, Song s2) { // compare two songs
			if (s1.getYear() > s2.getYear()) { // if the first song is newer
				return 1; // return 1
			} else if (s1.getYear() == s2.getYear()) { // if the songs are the same year
				return 0; // return 0
			} else { // if the second song is newer
				return -1; // return -1
			}
		}
	}

	// Sort songs by length
	public void sortSongsByLength() { // sort the songs by length
		// Use Collections.sort()
		Collections.sort(songs, new SongLengthComparator()); // sort the songs by length

	}

	private class SongLengthComparator implements Comparator<Song> { // create a new comparator
		public int compare(Song s1, Song s2) { // compare two songs
			if (s1.getLength() > s2.getLength()) { // if the first song is longer
				return 1; // return 1
			} else if (s1.getLength() == s2.getLength()) { // if the songs are the same length
				return 0; // return 0
			} else { // if the second song is longer
				return -1; // return -1
			}
		}

	}

	// Sort songs by title
	public void sortSongsByName() {
		// Use Collections.sort()
		class SongNameComparator implements Comparator<Song> { // create a new comparator
			public int compare(Song s1, Song s2) { // compare two songs
				return s1.getTitle().compareTo(s2.getTitle()); // compare the titles
			}
		}
		Collections.sort(songs, new SongNameComparator()); // sort the songs by name
	}

	/*
	 * Play Content
	 */

	// Play song from songs list
	public void playSong(int index) { // play a song
		if (index < 1 || index > songs.size()) { // if the index is out of bounds
			throw new InvalidSongIndex("Song Not Found"); // throw an exception
		}
		songs.get(index - 1).play(); // play the song
	}

	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter) { // play an audiobook
		if (index < 1 || index > audiobooks.size()) { // if the index is out of bounds
			throw new InvalidAudioBookIndex("AudioBook Not Found"); // throw an exception
		}
		if (chapter < 1 || chapter > audiobooks.get(index - 1).getChapters().size()) { // if the chapter is out of
																						// bounds
			throw new InvalidChapterNumber("Chapter Not Found"); // throw an exception
		}
		audiobooks.get(index - 1).selectChapter(chapter); // select the chapter
		audiobooks.get(index - 1).play(); // play the audiobook

	}

	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index) { // print the table of contents of an audiobook
		if (index < 1 || index > audiobooks.size()) { // if the index is out of bounds
			throw new InvalidAudioBookIndex("AudioBook Not Found"); // throw an exception
		}
		audiobooks.get(index - 1).printTOC(); // print the table of contents
		System.out.println(); // print a new line
	}

	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title) { // make a new playlist
		for (int i = 0; i < playlists.size(); i++) { // go through all the playlists
			if (playlists.get(i).getTitle().equals(title)) { // if the playlist already exists
				throw new PlaylistAlreadyExistsException("Playlist " + playlists.get(i).getTitle() + " Already Exists");
			}
		}
		Playlist newPlaylist = new Playlist(title); // create a new playlist
		playlists.add(newPlaylist); // add the playlist to the list of playlists
	}

	// Print list of content information (songs, audiobooks etc) in playlist named
	// title from list of playlists
	public void printPlaylist(String title) { // print a playlist
		for (int i = 0; i < playlists.size(); i++) { // go through all the playlists
			if (playlists.get(i).getTitle().equals(title)) { // if the playlist is found
				playlists.get(i).printContents(); // print the contents of the playlist
			} else {
				throw new PlaylistNotFoundException("Playlist Not Found"); // throw an exception
			}
		}
	}

	// Play all content in a playlist
	public void playPlaylist(String playlistTitle) { // play a playlist
		for (int i = 0; i < playlists.size(); i++) { // go through all the playlists
			if (playlists.get(i).getTitle().equals(playlistTitle)) { // if the playlist is found
				playlists.get(i).playAll(); // play the playlist
			} else {
				throw new PlaylistNotFoundException("Playlist Not Found"); // throw an exception
			}
		}
	}

	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL) { // play a playlist
		System.out.println(playlistTitle); // print the playlist title
		for (int i = 0; i < playlists.size(); i++) { // go through all the playlists
			// If the playlist is found and the index is out of bounds throw an exception
			if (playlists.get(i).getTitle().equals(playlistTitle) && indexInPL > playlists.size()) {
				throw new InvalidAudioContentException("Playlist Index Out Of Bounds");
			}
			if (playlists.get(i).getTitle().equals(playlistTitle)) { // if the playlist is found
				playlists.get(i).play(indexInPL); // play the playlist
			} else {
				throw new PlaylistNotFoundException("Playlist Not Found"); // throw an exception
			}
		}
	}

	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle) { // add content to a playlist
		boolean found = false;
		if (type.equalsIgnoreCase(Song.TYPENAME)) { // if the type is a song
			for (int i = 0; i < playlists.size(); i++) { // go through all the playlists
				if (playlists.get(i).getTitle().equals(playlistTitle)) { // if the playlist is found
					if (index < 1 || index > songs.size()) { // if the index is out of bounds
						throw new InvalidSongIndex("Song Not Found"); // throw an exception
					}
					playlists.get(i).addContent(songs.get(index - 1)); // add the song to the playlist
					found = true;
				}
			}
		} else if (type.equalsIgnoreCase(AudioBook.TYPENAME)) { // if the type is an audiobook
			for (int i = 0; i < playlists.size(); i++) { // go through all the playlists
				if (playlists.get(i).getTitle().equals(playlistTitle)) { // if the playlist is found
					if (index < 1 || index > audiobooks.size()) { // if the index is out of bounds
						throw new InvalidAudioBookIndex("AudioBook Not Found"); // throw an exception
					}
					playlists.get(i).addContent(audiobooks.get(index - 1)); // add the audiobook to the playlist
					found = true;
				}
			}
		}
		if (!found) {
			throw new PlaylistNotFoundException("Playlist Not Found");
		}
	}

	// Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is
	// valid
	public void delContentFromPlaylist(int index, String title) { // delete content from a playlist
		for (int i = 0; i < playlists.size(); i++) { // go through all the playlists
			if (playlists.get(i).getTitle().equals(title)) { // if the playlist is found
				playlists.get(i).deleteContent(index); // delete the content from the playlist
			} else {
				throw new PlaylistNotFoundException("Playlist Not Found"); // throw an exception
			}
		}
	}

	public void SEARCH(String title, ArrayList<AudioContent> indexes, ArrayList<AudioContent> contents) { // search for
																											// a
																											// song/audiobook/podcast
		boolean found = false; // boolean to check if the song/audiobook/podcast is found
		for (int i = 0; i < contents.size(); i++) { // go through all the songs/audiobooks/podcasts
			if (contents.get(i).getTitle().equals(title)) { // if the song/audiobook/podcast is found
				found = true; // set the boolean to true
				System.out.print((i + 1) + ". "); // print the index of the song/audiobook/podcast
				contents.get(i).printInfo(); // print the information of the song/audiobook/podcast
			}
		}
		if (!found) { // if the song/audiobook/podcast is not found
			throw new TitleNotFoundException("No Matches for " + title); // throw an exception
		}
	}

	public void SEARCHA(String artist, ArrayList<Integer> indexes, ArrayList<AudioContent> contents) { // search for a
																										// song/audiobook
		boolean found = false; // boolean to check if the song/audiobook is found
		if (indexes != null) { // if the indexes are not null
			for (int i = 0; i < contents.size(); i++) { // go through all the songs/audiobooks
				if (indexes.contains(i)) { // if the song/audiobook is found
					found = true; // set the boolean to true
					System.out.print((i + 1) + ". "); // print the index of the song/audiobook
					contents.get(i).printInfo(); // print the information of the song/audiobook
					System.out.println(); // print a new line
				}
			}
		}
		if (!found) { // if the song/audiobook/podcast is not found
			throw new ArtistNotFoundException("No matches for " + artist); // throw an exception
		}
	}

	public void SEARCHG(String genre, ArrayList<Integer> indexes, ArrayList<AudioContent> contents) { // search for a
																										// song/audiobook
																										// based on
																										// genre
		boolean found = false; // boolean to check if the song/audiobook is found
		if (indexes != null) { // if the indexes are not null
			for (int i = 0; i < contents.size(); i++) { // go through all the songs/audiobooks
				if (indexes.contains(i)) { // if the song/audiobook is found
					found = true; // set the boolean to true
					System.out.print((i + 1) + ". "); // print the index of the song/audiobook
					contents.get(i).printInfo(); // print the information of the song/audiobook
					System.out.println(); // print a new line
				}
			}
		}
		if (!found) { // if the song/audiobook/podcast is not found
			throw new GenreNotFoundException("No matches for " + genre); // throw an exception
		}
	}

	public void DOWNLOADA(ArrayList<Integer> indexes, AudioContent current) { // download a song/audiobook
		if (indexes != null) { // if the indexes are not null
			if (current.getType().equals(Song.TYPENAME)) { // if the type is a song
				if (songs.contains((Song) current)) { // if the song is already downloaded
					throw new SongAlreadyDownlaodedException("Song " + current.getTitle() + " already downloaded"); // throw
																													// exception
				} else {
					songs.add((Song) current); // add the song to the library
					System.out.println(current.getType() + " " + current.getTitle() + " " + "Added to Library"); // print
																													// the
																													// song
																													// added
																													// to
																													// the
																													// library
				}
			} else if (current.getType().equals(AudioBook.TYPENAME)) { // if the type is an audiobook
				if (audiobooks.contains((AudioBook) current)) { // if the audiobook is already downloaded
					throw new AudioBookAlreadDownlaodedException(
							"AudioBook " + current.getTitle() + " already downloaded"); // throw
					// exception
				} else {
					audiobooks.add((AudioBook) current); // add the audiobook to the library
					System.out.println(current.getType() + " " + current.getTitle() + " " + "Added to Library"); // print
																													// the
																													// audiobook
																													// added
																													// to
																													// the
																													// library
				}
			}
		}
	}

	public void DOWNLOADG(String genre, ArrayList<Integer> indexes, AudioContent current) { // download a song/audiobook
																							// based on genre
		if (indexes != null) { // if the indexes are not null
			if (current.getType().equals(Song.TYPENAME)) { // if the type is a song
				if (!(songs.contains((Song) current))) { // if the song is not already downloaded
					songs.add((Song) current); // add the song to the library
					System.out.println(current.getType() + " " + current.getTitle() + " Added to Library"); // print the
																											// song
																											// added to
																											// the
																											// library
				} else { // if the song is already downloaded
					throw new SongAlreadyDownlaodedException("Song " + current.getTitle() + " already downloaded"); // throw
																													// exception
				}
			} else if (current.getType().equals(AudioBook.TYPENAME)) { // if the type is an audiobook
				if (!(audiobooks.contains((AudioBook) current))) { // if the audiobook is not already downloaded
					audiobooks.add((AudioBook) current); // add the audiobook to the library
					System.out.println(current.getType() + " " + current.getTitle() + " Added to Library"); // print the
																											// audiobook
																											// added to
																											// the
																											// library
				} else { // if the audiobook is already downloaded
					throw new AudioBookAlreadDownlaodedException(
							"Audiobook " + current.getTitle() + " already downloaded"); // throw exception
				}
			}
		}
	}
}

class AudioContentNotFoundException extends RuntimeException { // create a new exception for audio content not found
	public AudioContentNotFoundException(String errMsg) { // constructor
		super(errMsg);
	}
}

class SongAlreadyDownlaodedException extends RuntimeException { // create a new exception for song already downloaded
	public SongAlreadyDownlaodedException(String errMsg) {
		super(errMsg);
	}
}

class AudioBookAlreadDownlaodedException extends RuntimeException { // create a new exception for audiobook already
																	// downloaded
	public AudioBookAlreadDownlaodedException(String errMsg) {
		super(errMsg);
	}
}

class UnknownContentType extends RuntimeException { // create a new exception for unknown content type
	public UnknownContentType(String errMsg) {
		super(errMsg);
	}
}

class InvalidSongIndex extends RuntimeException { // create a new exception for invalid song index
	public InvalidSongIndex(String errMsg) {
		super(errMsg);
	}
}

class InvalidAudioBookIndex extends RuntimeException { // create a new exception for invalid audiobook index
	public InvalidAudioBookIndex(String errMsg) {
		super(errMsg);
	}
}

class PlaylistAlreadyExistsException extends RuntimeException { // create a new exception for playlist already exists
	public PlaylistAlreadyExistsException(String errMsg) {
		super(errMsg);
	}
}

class PlaylistNotFoundException extends RuntimeException { // create a new exception for playlist not found
	public PlaylistNotFoundException(String errMsg) {
		super(errMsg);
	}
}

class TitleNotFoundException extends RuntimeException { // create a new exception for title not found
	public TitleNotFoundException(String errMsg) {
		super(errMsg);
	}
}

class ArtistNotFoundException extends RuntimeException { // create a new exception for artist not found
	public ArtistNotFoundException(String errMsg) {
		super(errMsg);
	}
}

class GenreNotFoundException extends RuntimeException { // create a new exception for genre not found
	public GenreNotFoundException(String errMsg) {
		super(errMsg);
	}
}

class InvalidChapterNumber extends RuntimeException { // create a new exception for invalid chapter number
	public InvalidChapterNumber(String errMsg) {
		super(errMsg);
	}
}

class InvalidAudioContentException extends RuntimeException { // create a new exception for invalid audio content
	public InvalidAudioContentException(String errMsg) {
		super(errMsg);
	}
}