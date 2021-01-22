import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class Scratch {
    public static void main(String[] args) {

        BST bst = new BST();

        bst.insert(20);
        bst.insert(8);
        bst.insert(22);
        bst.insert(4);
        bst.insert(12);
        bst.insert(10);
        bst.insert(14);

        int height = bst.get_height();
//        System.out.println(height);

        int min = bst.get_min();
        int max = bst.get_max();
        boolean valid = bst.is_binary_search_tree();
        System.out.println("min: " + min);
        System.out.println("max: " + max);
        System.out.println("valid: " + valid);

        TreeNode key = bst.root.left.right.right;
        int successor = bst.get_successor(key);
        System.out.println("successor: " + successor);



//        bst.print_values_by_level();
//        bst.print_values();

//        int size = bst.get_node_count();
//        System.out.println(bst.is_in_tree(0));

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class BST {

        TreeNode root;
        Integer size = 0;

        public BST() {
        }


        public void insert(int value) {

            this.root = this.insertHelper(value, this.root);
            this.size++;

        }

        private TreeNode insertHelper(int value, TreeNode root) {
            if (root == null) {
                return new TreeNode(value);
            }
            if (value <= root.val) {
                root.left = insertHelper(value, root.left);
            } else {
                root.right = insertHelper(value, root.right);
            }
            return root;
        }

        public Integer get_node_count() {
            return this.size;
        }


        public void print_values() {
            helper(this.root);
        }

        private void helper(TreeNode root) {

            if (root == null) {
                return;
            }
            helper(root.left);
            System.out.println(root.val);
            helper(root.right);
        }

        public void print_values_by_level() {
            Queue q = new LinkedList<TreeNode>();
            q.add(this.root);

            while (!q.isEmpty()) {
                for (int i = 0; i < q.size(); i++) {
                    TreeNode cur = (TreeNode) q.remove();
                    System.out.print(cur.val);
                    System.out.print(' ');
                    if (cur.left != null) {
                        q.add(cur.left);
                    }
                    if (cur.right != null) {
                        q.add(cur.right);
                    }
                }
                System.out.println();
            }
        }


        public void delete_tree() {
            this.root = null;
        }


        public boolean is_in_tree(int value) {
            return is_in_tree_helper(value, this.root);
        }

        private boolean is_in_tree_helper(int value, TreeNode root) {

            if (root == null) {
                return false;
            }
            if (value == root.val) {
                return true;
            } else if (value > root.val) {
                return is_in_tree_helper(value, root.right);
            } else {
                return is_in_tree_helper(value, root.left);
            }

        }


        public Integer get_height(){
            return get_height_helper(root)-1;
        }
        private Integer get_height_helper(TreeNode root){
            if (root == null){
                return 0;
            }
            return Math.max(get_height_helper(root.left) + 1, get_height_helper(root.right) + 1);
        }

        public Integer get_min(){
            return min(this.root);

        }
        private Integer min(TreeNode root){
            TreeNode cur = root;
            if (cur == null){
                return null;
            }
            while (cur.left != null){
                cur = cur.left;
            }
            return cur.val;
        }

        public Integer get_max(){
            TreeNode cur = this.root;
            if (cur == null){
                return null;
            }
            while (cur.right != null){
                cur = cur.right;
            }
            return cur.val;
        }


        public boolean is_binary_search_tree() {

            boolean valid = valid(this.root, Long.MIN_VALUE, Long.MAX_VALUE);
//            System.out.println("valid:" + valid);
            return valid;

        };

        private boolean valid(TreeNode root, Long min, Long max){

            if (root.val <= min || root.val >= max){
                return false;
            };

            boolean validLeft = true;
            boolean validRight = true;

            if (root.left != null){
                validLeft = valid(root.left, min, (long) root.val);
            }
            if (root.right != null){
                validRight = valid(root.right, (long) root.val, max);
            }
            return validLeft && validRight;

        }

        public void delete_value(int value){
            delete_value_helper(value, this.root);
        }
        private TreeNode delete_value_helper(int value, TreeNode root){
            if (root == null){ // When the value to delete does not exist
                return null;
            } else if (value > root.val){
                root.right = delete_value_helper(value, root.right);
            } else if (value < root.val){
                root.left = delete_value_helper(value, root.left);
            } else { // When root has the value to delete
                if (root.left == null && root.right == null) return null;
                else if (root.left == null) return root.right;
                else if (root.right == null) return root.left;
                else { // has both children case
                    TreeNode cur = root.right;

                    while (cur.left != null){
                        cur = cur.left;
                    } // cur now points to the in order successor or root;

                    root.val = cur.val;
                    root.right = delete_value_helper(cur.val, root.right);
                }
            }
            return root;
        }

        public int get_successor(TreeNode key){
            return get_succssor_helper(key, this.root);
        }
        private int get_succssor_helper(TreeNode key, TreeNode root){
            if (key.right != null){
                return this.min(key.right);
            } else {
                TreeNode successor = null;
                while (root != key && root != null){
                    if (root.val > key.val){
                        successor = root;
                        root = root.left;
                    } else {
                        root = root.right;
                    }
                }
                return successor != null ? successor.val : -1;
            }
        }



    }
}