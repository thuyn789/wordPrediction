// DO NOT MODIFY THIS FILE
// DO NOT MODIFY THIS FILE
// DO NOT MODIFY THIS FILE
// DO NOT MODIFY THIS FILE
import java.util.*;

public class TreeNode<T> {
	private T value;
	private List<TreeNode<T>> children;

	public TreeNode(T value) {
		this.value = value;
		this.children = new ArrayList<>();
	}

	public TreeNode(T value, List<TreeNode<T>> children) {
		assert children != null : "children cannot be null";
		this.value = value;
		this.children = children;
	}

	public T getValue() {
		return value;
	}

	public List<TreeNode<T>> getChildren() {
		return children;
	}

	public boolean isLeaf() {
		return children == null || children.size() == 0;
	}
}
