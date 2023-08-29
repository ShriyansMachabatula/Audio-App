//Shriyans Machabautla
//Student ID: 501180685

import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist {
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture

	public Playlist(String title) {
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addContent(AudioContent content) {
		contents.add(content);
	}

	public ArrayList<AudioContent> getContent() {
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents) {
		this.contents = contents;
	}

	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio
	 * content object
	 * Make sure the index starts at 1
	 */
	public void printContents() {
		for (int i = 0; i < contents.size(); i++) { // loop through the contents array list
			int index = i + 1; // set the index to 1 more than the current index
			System.out.print("" + index + ". "); // Print the index of the audio content object first followed by ". "
			contents.get(i).printInfo(); // Then make use of the printInfo() method of each audio content object
			System.out.println(); // Print a blank line after each audio content object is printed
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll() {
		for (int i = 0; i < contents.size(); i++) {
			contents.get(i).play(); // The given index is 1-indexed so convert to 0-indexing before removing
			System.out.println(); // Print a new line after each audio content object is played

		}
	}

	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range.
	public void play(int index) {
		if (index >= 1 || index <= contents.size()) // Make sure the index is in the correct range
			contents.get(index - 1).play(); // The given index is 1-indexed so convert to 0-indexing before removing
	}

	public boolean contains(int index) { // This method is used to check if the index is valid
		return index >= 1 && index <= contents.size(); // The given index is 1-indexed so convert to 0-indexing before
														// removing
	}

	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other) { // This method is used to check if a playlist with the same title already
											// exists
		if (other instanceof Playlist) { // Make sure a playlist with the same title doesn't already exist
			Playlist otherPlaylist = (Playlist) other;
			return title.equals(otherPlaylist.title); // The given index is 1-indexed so convert to 0-indexing before
														// removing
		}
		return false;
	}

	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index) {
		if (!contains(index))
			return;
		contents.remove(index - 1);
	}

}
