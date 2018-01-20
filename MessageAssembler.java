
public class MessageAssembler {
	public static void main(String[] args) {
		DLL myDLL = new DLL();
		myDLL.readAndAssemble("Mystery.txt");
		myDLL.printContent();
	}
}