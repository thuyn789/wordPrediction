import java.util.*;

public class Autocomplete {

	public static List<String> candidates(TreeNode<Character> root, String prefix) {
		List<String> results = new ArrayList();

		if(prefix.length() == 0) {
			//if empty string passed in, print every words
			collectCandidates(root, "", results);
		} else {
			TreeNode<Character> nodeFound = findNode(root, prefix, 0);
			collectCandidates(nodeFound, "", results);
			//printTree(nodeFound);
		}

		return results;
	}

	private static TreeNode<Character> findNode(TreeNode<Character> root, String prefix, int index) {
		int prefixLength = prefix.length();

		for(TreeNode<Character>child : root.getChildren()) {
			char childValue = child.getValue();
			char rootValue = root.getValue();

			if(index < prefixLength) {
				if(childValue == prefix.charAt(index)) {
					return new TreeNode(rootValue, Arrays.asList(findNode(child, prefix, ++index)));
				}
			}
		}

		if(index == 0 || index >= prefixLength && root.getValue() == '$') {
			return new TreeNode(null);
		}

		return root;
	}

	private static void collectCandidates(TreeNode<Character> root, String wordSoFar, List<String> results) {
		if(root.getValue() == null) {
			return;
		}

		if(root.getValue() == '$') { //base case
			results.add(wordSoFar);
		} else {
			for(TreeNode<Character>child : root.getChildren()) {
				char rootValue = root.getValue();

				if(rootValue == '$' || rootValue == '*') {
					collectCandidates(child, wordSoFar, results);
				} else {
					collectCandidates(child, wordSoFar + rootValue, results);
				}
			}
		}
	}

	//*******************************
	//*******Testing methods*********
	//******************************* 
	private static void printTree(TreeNode<Character> tree) {
		char treeValue = tree.getValue();
		if(treeValue != '*' && treeValue != '$') {
			System.out.println(treeValue);
		}

		for (TreeNode<Character> child : tree.getChildren()) {
			printTree(child);
		}
	}

	private static void allWords(TreeNode<Character> root, List<String> list) {
		allWords(root, list, "");
	}

	private static void allWords(TreeNode<Character>root, List<String> list, String candidate) {
		if(root.getValue() == '$') { //base case
			list.add(candidate);
		} else {
			for(TreeNode<Character>child : root.getChildren()) {
				char rootValue = root.getValue();
				if(rootValue == '$' || rootValue == '*') {
					allWords(child, list, candidate);
				} else {
					allWords(child, list, candidate + rootValue);
				}
			}
		}
	}
	//******************************
	//******************************
	//******************************

	public static void main(String[] args) {
		TreeNode<Character> example = makeExample();

		//List<String> testList = new ArrayList();
		//allWords(example, testList);
		//printTree(example);
		//System.out.println(testList);
		//System.out.println(candidates(example, "bean")); 
		
		System.out.println(candidates(example, "c")); // Outputs ["cat", "cow", "cut"]
		System.out.println(candidates(example, "ca")); // Outputs ["cat"]
		System.out.println(candidates(example, "an")); // Outputs ["and", "andrew"]

		// Outputs ["ace", "acne", "and", "andrew", "beam", "beef", "cat", "cow", "cut"]
		System.out.println(candidates(example, ""));
		System.out.println(candidates(example, "deer")); // Outputs []
		System.out.println(candidates(example, "bean")); // Outputs []
		

	}

	private static TreeNode<Character> makeExample() {
		return
				new TreeNode('*', Arrays.asList(
						new TreeNode('a', Arrays.asList(
								new TreeNode('c', Arrays.asList(
										new TreeNode('e', Arrays.asList(
												new TreeNode('$')
												)),
										new TreeNode('n', Arrays.asList(
												new TreeNode('e', Arrays.asList(
														new TreeNode('$')
														))
												))
										)),
								new TreeNode('n', Arrays.asList(
										new TreeNode('d', Arrays.asList(
												new TreeNode('$'),
												new TreeNode('r', Arrays.asList(
														new TreeNode('e', Arrays.asList(
																new TreeNode('w', Arrays.asList(
																		new TreeNode('$')
																		))
																))
														))
												))
										))
								)),
						new TreeNode('b', Arrays.asList(
								new TreeNode('e', Arrays.asList(
										new TreeNode('a', Arrays.asList(
												new TreeNode('m', Arrays.asList(
														new TreeNode('$')
														))
												)),
										new TreeNode('e', Arrays.asList(
												new TreeNode('f', Arrays.asList(
														new TreeNode('$')
														))
												))
										))
								)),
						new TreeNode('c', Arrays.asList(
								new TreeNode('a', Arrays.asList(
										new TreeNode('t', Arrays.asList(
												new TreeNode('$')
												))
										)),
								new TreeNode('o', Arrays.asList(
										new TreeNode('w', Arrays.asList(
												new TreeNode('$')
												))
										)),
								new TreeNode('u', Arrays.asList(
										new TreeNode('t', Arrays.asList(
												new TreeNode('$')
												))
										))
								))
						));
	}

}