
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DLL {

	/*
	 * readAndAssemble performs the following operations: (1) Reads from file
	 * fileName (e.g. Mystery.txt) line by line (2) stores the content of each
	 * line in a DLLNode (3) places each DLLNode in DLL according to DLLNode’s
	 * packetID number
	 */
	private DLLNode header;
	private DLLNode trailer;

	public DLL() {
	}

	public void readAndAssemble(String fileName)
	{
		String word = "";
		//String originally set to null, before being read in from the file
		try 
		{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//Reading in file, assuming it is in the project file
			while ((word = bufferedReader.readLine()) != null) 
			{
				//Keep reading in as long as there is stuff to read in from the file
				String[] content = word.split("\t");
				//Split each line into an array of size 2, delimited by a tab using ASCII \t
				int number = Integer.parseInt(content[0]);
				//Checks for the two rare cases of empty nodes as in the first case
				if (header == null) 
				{
					header = new DLLNode(number, content[1], null, null);
				} else if (trailer == null)
				{
					trailer = new DLLNode(number, content[1], header, null);
					header.setNextNode(trailer);
				} 
				
				else {
					if (number > trailer.getPacketID()) {
						trailer.setNextNode(new DLLNode(number, content[1], trailer, null));
						trailer = trailer.getNextNode();
					}
					else if (number < header.getPacketID()) {
						header.setPrevNode(new DLLNode(number, content[1], null, header));
						header = header.getPrevNode();
					}  else 
					{
						//Main case checking section
						DLLNode node = header;
						while (number < node.getPacketID() || number > node.getNextNode().getPacketID()) {
							node = node.getNextNode();
						}
						node.setNextNode(new DLLNode(number, content[1], node, node.getNextNode()));
					}
				}
			}

			bufferedReader.close();
		} catch (FileNotFoundException e) {
			//In case the file doesn't exist
			System.out.println("File Does Not Exist");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * printContent traverses DLL and prints out the content of its nodes so as
	 * to recreate the original message
	 */
	void printContent() {
		DLLNode node = header;
		DLLNode node2 = trailer;
		while (node.getPacketID() < node2.getPacketID()) {
			System.out.print(node.getPacketContent() + " ");
			node = node.getNextNode();
		}
		System.out.print(node.getPacketContent());
	}
}
