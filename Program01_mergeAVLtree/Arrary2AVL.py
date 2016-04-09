import logging
import random

count_insert = 0
count_update_balance = 0
count_update_height = 0
count_l_rotate = 0
count_r_rotate = 0
count_inorder_traverse = 0
count_fill_AVL = 0
#Create AVL node object
class Node():
    def __init__(self, value):
        self.left = None
        self.right = None
        self.key = value

#Create object AVL tree, default AVL tree is blank.

class AVL:
    def __init__(self):
        #height shows the height of a substree rooted at node.

        #balance is an indicator of whether the left and right
        #substrees of the node are balanced or not, it is height of 
        #left subtree minus the height of right subtree.

        self.node = None
        self.height = -1
        self.balance = 0


    def insert(self, key):
        global count_insert
        
        #Create new node
        n = Node(key)

        #Insert node as leaf
        if self.node == None:
            self.node = n
            
            self.node.left = AVL()
            self.node.right = AVL()

        # Insert key to the left subtree
        elif key < self.node.key:
            self.node.left.insert(key)
        # Insert key to the right subtree
        elif key > self.node.key:
            self.node.right.insert(key)
            
        count_insert += 1
        logging.info("In the %dth insert step, I inserted node with key %d", count_insert, key)

        #balance the tree to hold AVL property
        
        self.rebalance()

    def build(self, key):
        #Create new node
        n = Node(key)

        #Insert node as leaf
        if self.node == None:
            self.node = n
            self.node.left = AVL()
            self.node.right = AVL()

        # Insert key to the left subtree
        elif key < self.node.key:
            self.node.left.build(key)
        # Insert key to the right subtree
        elif key > self.node.key:
            self.node.right.build(key)

        #balance the tree to hold AVL property
        self.rebalance()

    def fill_AVL(self, Array, Astart, Aend):
        global count_fill_AVL
        count_fill_AVL += 1
        if Aend < Astart:
            return AVL()
            logging.info("In the %dth fill_AVL step, I did not insert any node", count_fill_AVL)
        else:
            mid = (Aend + Astart) / 2 
            self.node = Node(array[mid])
            self.node.left = AVL()
            self.node.right = AVL()
            logging.info("In the %dth fill_AVL step, I inserted node with key %d", count_fill_AVL, array[mid])

        
        self.node.left.fill_AVL(Array, Astart, mid-1) 
        self.node.right.fill_AVL(Array, mid+1, Aend)

        self.rebalance()

        


    #rebalance the subtree to maintain the property of AVL
    def rebalance(self):
        #This step is to update the height and balance values of
        #the AVL node. As I don't want to update height and balance 
        #values of its subtree, I set the recursive parameter as false.
        self.update_heights(recursive = False)
        self.update_balance(recursive = False)

        #if balance is not among -1, 0 or 1, then it means this subtree
        # is not balanced.
        while self.balance < -1 or self.balance > 1:
            #falls into the case when left subtree has higher height 
            #than right one
            if self.balance > 1:
                #falls into the case when left subtree's left subtree 
                #has loIr height than its right one, in this case, I 
                #perform the left rotation, which makes the left child 
                #as original root.
                if self.node.left.balance < 0:
                    self.node.left.leftRotate()
                #falls into the case when left subtree's left subtree 
                #has higher height than its right one, in this case, I 
                #perform the right rotation, which makes the node 
                #as original root. 
                self.rightRotate()
                self.update_heights()
                self.update_balance()
            #falls into the case when left subtree has loIr height 
            #than right one
            if self.balance < -1:
                #falls into the case when right subtree's left subtree 
                #has higher height than its right one, in this case, I 
                #perform the right rotation, which makes the right
                # child as original root.
                if self.node.right.balance > 0:
                    self.node.right.rightRotate()
                #falls into the case when right subtree's left subtree 
                #has loIr height than its right one, in this case, I 
                #perform the left rotation, which makes the node
                # as original root.
                self.leftRotate()
                self.update_heights()
                self.update_balance()

    #Update the balance value of each node
    def update_balance(self, recursive = True):
        global count_update_balance
        count_update_balance +=1
        #if the node is not null
        if self.node:
            #recursive is true only when I want to update balance 
            #value of all nodes in subtree
            if recursive:
                if self.node.left:
                    self.node.left.update_balance()
                if self.node.right:
                    self.node.right.update_balance()
            self.balance = self.node.left.height - self.node.right.height
            
            logging.info("In the %dth update balance step, I updated balance value of a node with key %d", count_update_balance, self.node.key)
        else:
            self.balance = 0
            ogging.info("In the %dth update balance step, I didnt update the balance value as node is null", count_update_balance)

    #Update the height value of each node
    def update_heights(self, recursive = True):
        global count_update_height
        count_update_height +=1
        if self.node:
            if recursive:
                if self.node.left: 
                    self.node.left.update_heights()
                if self.node.right:
                    self.node.right.update_heights()
            
            self.height = 1 + max(self.node.left.height, self.node.right.height)
            
            logging.info("In the %dth update height step, I updated height value of a node with key %d", count_update_height, self.node.key)

        else: 
            self.height = -1
            logging.info("In the %dth update height step, I didnt update the height value as node is null", count_update_height)


    #Define an generator to walkthrough all the nodes in AVL tree
    def inorder_traverse(self):
        global count_inorder_traverse
        if self.node.left.node != None:
            for x in self.node.left.inorder_traverse():
                yield x
        count_inorder_traverse +=1
        yield self.node.key
        if self.node.right.node != None:
            for x in self.node.right.inorder_traverse():
                yield x

    def rightRotate(self):
        global count_r_rotate
        newRoot = self.node.left.node
        newLeftChild = newRoot.right.node
        oldRoot = self.node
 
        self.node = newRoot
        oldRoot.left.node = newLeftChild
        newRoot.right.node = oldRoot
        count_r_rotate +=1
        logging.info("right rotate %d ", count_r_rotate)
 
    def leftRotate(self):
        global count_l_rotate
        newRoot = self.node.right.node
        newLeftChild = newRoot.left.node
        oldRoot = self.node
 
        self.node = newRoot
        oldRoot.right.node = newLeftChild
        newRoot.left.node = oldRoot
        count_l_rotate+=1
        logging.info("left rotate %d ", count_l_rotate)



if __name__ == "__main__":
    
    logging.basicConfig(filename='Array2AVL.log',level=logging.DEBUG)
    #I would like to randomly sample 10, 100, 1000, 10000 numbers from 1 to 50,000 respectively. 10, 100, 1000, 10000 would be total number of nodes in the trees.
    nodes_num = [10, 100]
    for num in nodes_num:
        logging.info("********************************")
        logging.info("Now lets build Tree1 with %d elements sorted array ", num)
        array = sorted(random.sample(range(1,50000),num))
        logging.info("Input sorted array is "+ str(array))
        Tree1 = AVL()
        Tree1.fill_AVL(array, 0, len(array)-1)
        logging.info( "when Array has %d values, takes %d fill_avl steps to fill each element into an AVL trees", num, count_fill_AVL)
        #Refresh the fill_array counter for next loop
        count_fill_AVL = 0
    logging.info( "From the implementation above, I can tell that as the size of ordered array (num) grows, the empirical time complexity is always linear with the size of the array when insert each element into a AVL tree")











