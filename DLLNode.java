

class DLLNode
{
	private int packetID;
	private String packetContent;
	private DLLNode prev;
	private DLLNode next;
     DLLNode(int packetID, String packetContent, DLLNode prev, DLLNode next)
     {
         this.packetID = packetID;
         this.packetContent = packetContent;
         this.prev = prev;
         this.next = next;
         
     }
     int getPacketID() 
     {
         return this.packetID;
     }
     String getPacketContent() 
     {
    	 return this.packetContent;
     }
     DLLNode getNextNode() { 
    	 return this.next;
     }
     DLLNode getPrevNode() { 
    	 return this.prev;
     }
     void setNextNode(DLLNode n)
     {
         this.next = n;
     }
     void setPrevNode(DLLNode p) 
     {
    	 this.prev = p;
      }
}