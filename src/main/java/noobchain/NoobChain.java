package noobchain;

import java.util.ArrayList;


import com.google.gson.GsonBuilder;

public class NoobChain {
	private static ArrayList<Block> blockChain = new ArrayList<Block>();
	public static int difficulty = 7;
	

	public static void main(String[] args) {
////		
////		blockChain.add(new Block("Hi im the first block", "0"));		
////		blockChain.add(new Block("Yo im the second block",blockChain.get(blockChain.size()-1).hash)); 
////		blockChain.add(new Block("Hey im the third block",blockChain.get(blockChain.size()-1).hash));
////		
//		String blockChainJson = new GsonBuilder().
//				setPrettyPrinting().
//				create().
//				toJson(blockChain);
//		
//		System.out.println(blockChainJson);
//		
		
		blockChain.add(new Block("Hi im the first block", "0"));
		System.out.println("Trying to Mine block 1... ");
		blockChain.get(0).mineBlock(difficulty);
		
		blockChain.add(new Block("Yo im the second block",blockChain.get(blockChain.size()-1).hash));
		System.out.println("Trying to Mine block 2... ");
		blockChain.get(1).mineBlock(difficulty);
		
		blockChain.add(new Block("Hey im the third block",blockChain.get(blockChain.size()-1).hash));
		System.out.println("Trying to Mine block 3... ");
		blockChain.get(2).mineBlock(difficulty);	
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
		
	}
	
	public static Boolean isChainValid() {
		Block  currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		for (int i = 1; i < blockChain.size(); i++) {
			currentBlock = blockChain.get(i);
			previousBlock = blockChain.get(i - 1);
			
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes  not equal");
				return false;
			}
			
			if (!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hashes  not equal");
				return false;
			}
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
				
			
			}
		}
		
		return true;
	}

}
