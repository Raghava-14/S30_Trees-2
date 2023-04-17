//Time = O(n)
//Space = O(n) 

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int postIndex; // instance variable to keep track of the index in postorder array

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // set postIndex to last index of postorder, which is the index of the root node
        postIndex = postorder.length - 1;
        // call helper method with initial values
        return buildTreeHelper(inorder, postorder, 0, inorder.length - 1);
    }

    public TreeNode buildTreeHelper(int[] inorder, int[] postorder, int inStart, int inEnd) {
        // base case: no more nodes to be constructed
        if (inStart > inEnd) {
            return null;
        }
        // create new TreeNode with value at current postIndex
        TreeNode root = new TreeNode(postorder[postIndex]);
        // decrement postIndex to move to next root node
        postIndex--;
        // if inStart is equal to inEnd, there are no more children to be processed for this node
        if (inStart == inEnd) {
            return root;
        }
        // search for the index of the current root value in inorder array
        int inIndex = search(inorder, inStart, inEnd, root.val);
        // use inIndex to split inorder array into left and right subtrees
        // recursively construct right and left subtrees
        root.right = buildTreeHelper(inorder, postorder, inIndex + 1, inEnd);
        root.left = buildTreeHelper(inorder, postorder, inStart, inIndex - 1);
        // return the root
        return root;
    }

    public int search(int[] inorder, int start, int end, int val) {
        // linear search for index of val in inorder array from start to end (inclusive)
        for (int i = start; i <= end; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        // val not found in subarray
        return -1;
    }
}
