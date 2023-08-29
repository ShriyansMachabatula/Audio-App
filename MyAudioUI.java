//Shriyans Machabatula
//Student ID: 501180685

import java.util.ArrayList;
import java.util.Scanner;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI {
	public static void main(String[] args) {
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your
		// mylibrary
		AudioContentStore store = new AudioContentStore();
		// Create my music mylibrary
		Library mylibrary = new Library();
		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine()) {
			String action = scanner.nextLine();
			if (action == null || action.equals("")) {
				System.out.print("\n>");
				continue;
			} else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("STORE")) // List all content in the store
			{
				store.listAll();
			} else if (action.equalsIgnoreCase("SONGS")) // List all songs
			{
				mylibrary.listAllSongs();
			} else if (action.equalsIgnoreCase("BOOKS")) // List all books
			{
				mylibrary.listAllAudioBooks();

			} else if (action.equalsIgnoreCase("ARTISTS")) // List all artists
			{
				mylibrary.listAllArtists();
			} else if (action.equalsIgnoreCase("PLAYLISTS")) // List all playlists
			{
				mylibrary.listAllPlaylists();
			}

			else if (action.equalsIgnoreCase("DOWNLOAD")) {
				// Print error message if the song doesn't exist in the store
				int fromIndex = 0; // index of the song in the store
				int toIndex = 0; // index of the song in the store

				System.out.print("From Store Content #: "); // Prompt for the index
				if (scanner.hasNextInt()) { // Check if the input is an integer
					fromIndex = scanner.nextInt(); // Get the index
					scanner.nextLine();

				}
				System.out.print("To Store Content #: "); // Prompt for the index
				if (scanner.hasNextInt()) { // Check if the input is an integer
					toIndex = scanner.nextInt(); // Get the index
					scanner.nextLine();
				}
				for (int i = fromIndex; i <= toIndex; i++) {
					try {
						mylibrary.download(store.getContent(i));
					} catch (SongAlreadyDownlaodedException e) {
						System.out.println(e.getMessage());
					} catch (AudioBookAlreadDownlaodedException e) {
						System.out.println(e.getMessage());
					} catch (InvalidAudioContentException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song
			else if (action.equalsIgnoreCase("PLAYSONG")) {
				// Print error message if the song doesn't exist in the library
				int index = 0; // index of the song in the library

				System.out.print("Song Number: "); // Prompt for the index
				if (scanner.hasNextInt()) { // Check if the input is an integer
					index = scanner.nextInt();// Get the index
					scanner.nextLine(); // Input the newline character
				}
				try {
					mylibrary.playSong(index); // Play the song
				} catch (InvalidSongIndex e) { // Print error message if the song doesn't exist in the library
					System.out.println(e.getMessage()); // Print error message if the song doesn't exist in the library
				}
			}
			// Print the table of contents (TOC) of an
			// that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) {
				int index = 0; // index of the book in the library
				System.out.print("Audio Book Number: "); // Prompt for the index
				if (scanner.hasNextInt()) { // Check if the input is an integer
					index = scanner.nextInt(); // Get the index
					scanner.nextLine(); // Input the newline character
				}
				try { // Print error message if the book doesn't exist in the library
					mylibrary.printAudioBookTOC(index); // Print the TOC
				} catch (InvalidAudioBookIndex e) { // Print error message if the book doesn't exist in the library
					System.out.println(e.getMessage()); // Print error message if the book doesn't exist in the library
				}
			}
			// Similar to playsong above except for audio book
			// In addition to the book index, read the chapter
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) {
				// Print error message if the book doesn't exist in the library
				int index = 0; // index of the book in the library
				int chapter = 0; // chapter number of the book

				System.out.print("Audio Book Number: ");// Prompt for the index
				if (scanner.hasNextInt()) { // Check if the input is an integer
					index = scanner.nextInt(); // Get the index
					scanner.nextLine(); // Input the newline character
				}
				System.out.print("Chapter: "); // Prompt for the chapter number
				if (scanner.hasNextInt()) { // Check if the input is an integer
					chapter = scanner.nextInt(); // Get the chapter number
					scanner.nextLine(); // Input the newline character
				}
				try {
					mylibrary.playAudioBook(index, chapter);
				} catch (InvalidAudioBookIndex e) {
					System.out.println(e.getMessage());
				} catch (InvalidChapterNumber e) {
					System.out.println(e.getMessage());
				}
			}
			// Specify a playlist title (string)
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) {
				// Specify a playlist title (string)
				// Play all the audio content (songs, audiobooks, podcasts) of the playlist
				// see class Library for the method to call
				String title = ""; // title of the playlist
				System.out.print("Playlist Title: "); // Prompt for the playlist title
				if (scanner.hasNext()) { // Check if the input is a string
					title = scanner.nextLine(); // Get the playlist title
				}
				try {
					mylibrary.playPlaylist(title);
				} catch (PlaylistNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
			// Specify a playlist title (string)
			// Read the index of a song/audiobook/podcast in the playist from the keyboard
			// Play all the audio content
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) {
				// Specify a playlist title (string)
				// Read the index of a song/audiobook/podcast in the playist from the keyboard
				// Play all the audio content
				// see class Library for the method to call
				String title = ""; // title of the playlist
				int index = 0; // index of the song/audiobook/podcast in the playlist
				System.out.print("Playlist Title: "); // Prompt for the playlist title
				if (scanner.hasNext()) { // Check if the input is a string
					title = scanner.nextLine(); // Get the playlist title
				}
				System.out.print("Content Number: "); // Prompt for the index
				if (scanner.hasNextInt()) { // Check if the input is an integer
					index = scanner.nextInt(); // Get the index
					scanner.nextLine(); // Input the newline character
				}
				try {
					mylibrary.playPlaylist(title, index);
				} catch (PlaylistNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (InvalidAudioContentException e) {
					System.out.println(e.getMessage());
				}
			}
			// Delete a song from the list of songs in mylibrary and any play lists it
			// belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELSONG")) { // Delete a song from the list of songs in mylibrary and any
															// play lists it belongs to
				int index = 0; // index of the song in the library

				System.out.print("Library Song #: "); // Prompt for the index
				if (scanner.hasNextInt()) { // Check if the input is an integer
					index = scanner.nextInt(); // Get the index
					scanner.nextLine(); // Input the newline character
				}
				try {
					mylibrary.deleteSong(index);
				} catch (PlaylistNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) { // Read a title string from the keyboard and make a playlist
				String playlistTitle = ""; // title of the playlist

				System.out.print("Playlist Title: "); // Prompt for the playlist title
				if (scanner.hasNext()) { // Check if the input is a string
					playlistTitle = scanner.next(); // Get the playlist title
					scanner.nextLine(); // Input the newline character
				}
				try {
					mylibrary.makePlaylist(playlistTitle);
				} catch (PlaylistAlreadyExistsException e) {
					System.out.println(e.getMessage());
				}

			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL")) // print playlist content
			{
				String playlistTitle = ""; // title of the playlist

				System.out.print("Playlist Title: "); // Prompt for the playlist title
				if (scanner.hasNext()) { // Check if the input is a string
					playlistTitle = scanner.next(); // Get the playlist title
					scanner.nextLine(); // Input the newline character
				}
				try {
					mylibrary.printPlaylist(playlistTitle);
				} catch (PlaylistNotFoundException e) {
					System.out.println(e.getMessage());
				}

			}
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a
			// playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from
			// the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) { // Add content (song, audiobook, podcast) from mylibrary (via
															// index) to a playlist
				String type = ""; // type of content
				int index = 0; // index of the content in the library
				String playlistTitle = ""; // title of the playlist

				System.out.print("Playlist Title: "); // Prompt for the playlist title
				if (scanner.hasNext()) { // Check if the input is a string
					playlistTitle = scanner.next(); // Get the playlist title
					scanner.nextLine(); // Input the newline character
				}
				System.out.print("Content Type [SONG, AUDIOBOOK]: "); // Prompt for the content type
				if (scanner.hasNext()) { // Check if the input is a string
					type = scanner.next(); // Get the content type
					scanner.nextLine(); // Input the newline character
				}
				System.out.print("Library Content #: "); // Prompt for the index
				if (scanner.hasNextInt()) { // Check if the input is an integer
					index = scanner.nextInt(); // Get the index
					scanner.nextLine(); // Input the newline character
				}
				try {
					mylibrary.addContentToPlaylist(type, index, playlistTitle);
				} catch (PlaylistNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (InvalidSongIndex e) {
					System.out.println(e.getMessage());
				} catch (InvalidAudioBookIndex e) {
					System.out.println(e.getMessage());
				} catch (UnknownContentType e) {
					System.out.println(e.getMessage());
				}
			}
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) { // Delete content from play list based on index from the
																// playlist
				String playlistTitle = ""; // title of the playlist
				int index = 0; // index of the content in the playlist

				System.out.print("Playlist Title: "); // Prompt for the playlist title
				if (scanner.hasNext()) { // Check if the input is a string
					playlistTitle = scanner.next(); // Get the playlist title
					scanner.nextLine(); // Input the newline character
				}
				System.out.print("Playlist Content #: "); // Prompt for the index
				if (scanner.hasNextInt()) { // Check if the input is an integer
					index = scanner.nextInt(); // Get the index
					scanner.nextLine(); // Input the newline character
				}
				try { // Delete the content from the playlist
					mylibrary.delContentFromPlaylist(index, playlistTitle); // Delete the content from the playlist
				} catch (PlaylistNotFoundException e) { // Catch the exception
					System.out.println(e.getMessage()); // Print the exception message
				} catch (InvalidSongIndex e) { // Catch the exception
					System.out.println(e.getMessage()); // Print the exception message
				}

			}

			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			} else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			} else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			} else if (action.equalsIgnoreCase("SEARCH")) { // search for a song by title
				String title = ""; // title of the song
				String output = "Title: "; // output string
				System.out.print(output); // Prompt for the title

				if (scanner.hasNextLine()) { // Check if the input is a string
					title = scanner.nextLine(); // Get the title
				}

				try { // Search for the song
					ArrayList<AudioContent> indexes = store.getStoreTitles(title); // Get the indexes of the songs
					mylibrary.SEARCH(title, indexes, store.getContents()); // Print the songs
				} catch (TitleNotFoundException e) { // Catch the exception
					System.out.println(e.getMessage()); // Print the exception message
				}
			} else if (action.equalsIgnoreCase("SEARCHA")) { // search for a song by artist
				String art = ""; // artist
				System.out.print("Artist: "); // Prompt for the artist
				if (scanner.hasNextLine()) { // Check if the input is a string
					art = scanner.nextLine(); // Get the artist
				}
				try { // Search for the song
					ArrayList<Integer> indexes = store.getStoreArtists(art); // Get the indexes of the songs
					mylibrary.SEARCHA(art, indexes, store.getContents()); // Print the songs
				} catch (ArtistNotFoundException e) { // Catch the exception
					System.out.println(e.getMessage()); // Print the exception message
				}
			} else if (action.equalsIgnoreCase("SEARCHG")) { // search for a song by genre
				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: "); // Prompt for the genre
				String genre = ""; // genre of the song
				if (scanner.hasNextLine()) { // Check if the input is a string
					genre = scanner.nextLine(); // Get the genre
				}
				try { // Search for the song
					ArrayList<Integer> indexes = store.getStoreGenres(genre); // Get the indexes of the songs
					mylibrary.SEARCHG(genre, indexes, store.getContents()); // Print the songs
				} catch (GenreNotFoundException e) { // Catch the exception
					System.out.println(e.getMessage()); // Print the exception message
				}
			} else if (action.equalsIgnoreCase("DOWNLOADA")) { // download all songs by an artist
				System.out.print("Artist: "); // Prompt for the artist
				String art = ""; // artist
				if (scanner.hasNextLine()) { // Check if the input is a string
					art = scanner.nextLine(); // artist of the song
				}
				try { // Download the songs
					ArrayList<Integer> indexes = store.getStoreArtists(art); // Get the indexes of the songs
					for (int i : indexes) { // Download the songs
						try {
							mylibrary.DOWNLOADA(indexes, store.getContents().get((i))); // Download the songs
						} catch (SongAlreadyDownlaodedException e) { // Catch the exception
							System.out.println(e.getMessage()); // Print the exception message
						} catch (AudioBookAlreadDownlaodedException e) { // Catch the exception
							System.out.println(e.getMessage()); // Print the exception message
						} catch (ArtistNotFoundException e) { // Catch the exception
							System.out.println(e.getMessage()); // Print the exception message
						}
					}
				} catch (ArtistNotFoundException e) { // Catch the exception
					System.out.println(e.getMessage()); // Print the exception message
				}

			} else if (action.equalsIgnoreCase("DOWNLOADG")) { // download all songs by a genre
				System.out.print("Genre: "); // Prompt for the genre
				String genre = ""; // genre
				if (scanner.hasNextLine()) { // Check if the input is a string
					genre = scanner.nextLine(); // Get the genre
				}
				try { // Download the songs
					ArrayList<Integer> indexes = store.getStoreGenres(genre); // Get the indexes of the songs
					for (int index : indexes) { // Download the songs
						try {
							mylibrary.DOWNLOADG(genre, indexes, store.getContents().get((index))); // Download the songs
						} catch (SongAlreadyDownlaodedException e) { // Catch the exception
							System.out.println(e.getMessage()); // Print the exception message
						} catch (AudioBookAlreadDownlaodedException e) { // Catch the exception
							System.out.println(e.getMessage()); // Print the exception message
						}
					}
				} catch (GenreNotFoundException e) { // Catch the exception
					System.out.println(e.getMessage()); // Print the exception message
				}
			}
			System.out.print("\n>"); // Prompt for the next action
		}
	}
}
