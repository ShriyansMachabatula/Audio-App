//Shriyans Machabatula
//Student ID: 501180685

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore {
	private ArrayList<AudioContent> contents; // list of audio content
	private Map<String, Integer> titleMap; // map of titles to indices in contents
	private Map<String, ArrayList<Integer>> artistMap; // map of artists to indices in contents
	private Map<String, ArrayList<Integer>> genreMap; // map of genres to indices in contents

	private ArrayList<AudioContent> ReadTextFile() {
		ArrayList<AudioContent> components = new ArrayList<AudioContent>();
		try { // try to read the file
			FileReader file = new FileReader("store.txt"); // create a file object
			Scanner in = new Scanner(file); // create a scanner object

			// The following code reads the file line by line and creates the appropriate
			// objects.
			while (in.hasNextLine()) { // while there is a next line
				String type = in.nextLine();
				if (type.equals("SONG")) { // if the type is a song
					System.out.println("Loading SONG");
					String id = in.nextLine();
					String title = in.nextLine();
					int year = in.nextInt();
					int length = in.nextInt();
					in.nextLine(); // We have to do in.nextLine() because the nextInt() method does not read the
									// newline character
					String artist = in.nextLine();
					String composer = in.nextLine();
					String genre = in.nextLine();
					Song.Genre genre1 = Song.Genre.valueOf(genre);
					int numLines = in.nextInt();
					in.nextLine();
					String lyrics = "";
					for (int i = 0; i < numLines; i++) {
						lyrics += in.nextLine() + "\r\n";
					}
					components.add(new Song(title, year, id, type, lyrics, length, artist, composer, genre1, lyrics)); // add
																														// the
																														// song
																														// to
																														// the
																														// arraylist
				}
				if (type.equals("AUDIOBOOK")) { // if the type is an audiobook
					System.out.println("Loading AUDIOBOOK");
					String id = in.nextLine();
					String title = in.nextLine();
					int year = in.nextInt();
					int length = in.nextInt();
					in.nextLine(); // We have to do in.nextLine() because the nextInt() method does not read the
									// newline character
					String author = in.nextLine();
					String narrator = in.nextLine();
					int numChapters = in.nextInt();
					in.nextLine();
					ArrayList<String> chapterTitles = new ArrayList<String>(); // create an arraylist of chapter titles
					for (int i = 0; i < numChapters; i++) { // for each chapter
						chapterTitles.add(in.nextLine()); // add the chapter title to the arraylist
					}
					ArrayList<String> chapters = new ArrayList<String>(); // create an arraylist of chapters
					for (int i = 0; i < numChapters; i++) { // for each chapter
						int numLines = in.nextInt(); // get the number of lines in the chapter
						in.nextLine();
						String lyrics = ""; // create a string to store the lyrics
						for (int j = 0; j < numLines; j++) { // for each line
							lyrics += in.nextLine() + "\r\n"; // add the line to the string
						}
						chapters.add(lyrics); // add the chapter to the arraylist
					}
					components.add(new AudioBook(title, year, id, type, narrator, length, author, narrator,
							chapterTitles, chapters)); // add the audiobook to the arraylist
				}
			}
			in.close(); // close the scanner
		} catch (IOException e) { // if there is an error
			System.out.println(e.getMessage()); // print the error message
			System.exit(1); // exit the program
		}
		return components; // return the arraylist
	}

	public AudioContentStore() {
		// contents = new ArrayList<AudioContent>();
		titleMap = new HashMap<String, Integer>(); // create a hashmap for the titles
		artistMap = new HashMap<String, ArrayList<Integer>>(); // create a hashmap for the artists
		genreMap = new HashMap<String, ArrayList<Integer>>(); // create a hashmap for the genres
		// ACCESS THE METHOD HERE
		contents = ReadTextFile(); // call the method to read the file

		for (int i = 0; i < contents.size(); i++) { // for each audio content
			String title = contents.get(i).getTitle(); // get the title
			titleMap.put(title, i); // add the title and the index to the hashmap
		}

		for (int i = 0; i < contents.size(); i++) { // for each audio content
			if (contents.get(i).getType().equals("SONG")) { // if the type is a song
				String artist = ((Song) contents.get(i)).getArtist(); // get the artist
				if (artistMap.containsKey(artist)) { // if the artist is already in the hashmap
					artistMap.get(artist).add(i); // add the index to the arraylist
				} else { // if the artist is not in the hashmap
					ArrayList<Integer> index = new ArrayList<Integer>(); // create an arraylist
					index.add(i); // add the index to the arraylist
					artistMap.put(artist, index); // add the artist and the arraylist to the hashmap
				}
			} else if (contents.get(i).getType().equals("AUDIOBOOK")) { // if the type is an audiobook
				String author = ((AudioBook) contents.get(i)).getAuthor(); // get the author
				if (artistMap.containsKey(author)) { // if the author is already in the hashmap
					artistMap.get(author).add(i); // add the index to the arraylist
				} else { // if the author is not in the hashmap
					ArrayList<Integer> index = new ArrayList<Integer>(); // create an arraylist
					index.add(i); // add the index to the arraylist
					artistMap.put(author, index); // add the author and the arraylist to the hashmap
				}
			}
		}

		for (int i = 0; i < contents.size(); i++) { // for each audio content
			if (contents.get(i).getType().equals("SONG")) { // if the type is a song
				String genre = ((Song) contents.get(i)).getGenre().toString(); // get the genre
				if (genreMap.containsKey(genre)) { // if the genre is already in the hashmap
					genreMap.get(genre).add(i); // add the index to the arraylist
				} else { // if the genre is not in the hashmap
					ArrayList<Integer> index = new ArrayList<Integer>(); // create an arraylist
					index.add(i); // add the index to the arraylist
					genreMap.put(genre, index); // add the genre and the arraylist to the hashmap
				}
			} else if (contents.get(i).getType().equals("AUDIOBOOK")) { // if the type is an audiobook
				String genre = ((AudioBook) contents.get(i)).getType().toString(); // get the genre
				if (genreMap.containsKey(genre)) { // if the genre is already in the hashmap
					genreMap.get(genre).add(i); // add the index to the arraylist
				} else { // if the genre is not in the hashmap
					ArrayList<Integer> index = new ArrayList<Integer>(); // create an arraylist
					index.add(i); // add the index to the arraylist
					genreMap.put(genre, index); // add the genre and the arraylist to the hashmap
				}
			}
		}
	}

	public AudioContent getContent(int index) { // get the audio content at the index
		if (index < 1 || index > contents.size()) { // if the index is out of bounds
			return null; // return null
		}
		return contents.get(index - 1); // return the audio content at the index
	}

	public int getSize() { // get the size of the arraylist
		return contents.size(); // return the size
	}

	public void listAll() { // list all the audio content
		for (int i = 0; i < contents.size(); i++) { // for each audio content
			int index = i + 1; // get the index
			System.out.print("" + index + ". "); // print the index
			contents.get(i).printInfo(); // print the info
			System.out.println(); // print a new line
		}
	}

	public ArrayList<Integer> getStoreArtists(String artist) { // get the audio content by the artist
		if (artistMap.get(artist) == null) { // if the artist is not in the hashmap
			throw new ArtistNotFoundException("No matches for " + artist); // throw an exception
		}
		return artistMap.get(artist); // return the arraylist of indexes
	}

	public ArrayList<AudioContent> getStoreTitles(String title) { // get the audio content by the title
		ArrayList<AudioContent> titles = new ArrayList<AudioContent>(); // create an arraylist
		if (titleMap.containsKey(title)) { // if the title is in the hashmap
			titles.add(contents.get(titleMap.get(title))); // add the audio content to the arraylist
		}
		return titles; // return the arraylist
	}

	public ArrayList<Integer> getStoreGenres(String genre) { // get the audio content by the genre
		if (genreMap.get(genre) == null) { // if the genre is not in the hashmap
			throw new GenreNotFoundException("Genre not found"); // throw an exception
		}
		return genreMap.get(genre); // return the arraylist of indexes
	}

	public ArrayList<AudioContent> getContents() { // get the arraylist of audio content
		return contents; // return the arraylist
	}
}
