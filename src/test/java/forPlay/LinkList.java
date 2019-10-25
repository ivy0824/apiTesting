package forPlay;


public class LinkList {
    public Node head;
    public Node current;

    //方法：向链表中添加数据
    public void add(int data) {
        //判断链表为空的时候
        if (head == null) {//如果头结点为空，说明这个链表还没有创建，那就把新的结点赋给头结点
            head = new Node(data);
            current = head;
        } else {
            //创建新的结点，放在当前节点的后面（把新的结点合链表进行关联）
            current.next = new Node(data);
            //把链表的当前索引向后移动一位
            current = current.next;   //此步操作完成之后，current结点指向新添加的那个结点
        }
    }

    //方法：遍历链表（打印输出链表。方法的参数表示从节点node开始进行遍历
    public void print(Node node) {
        if (node == null) {
            return;
        }

        current = node;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    //获取单链表的长度
    public int getLength(Node node){
        if (node == null){
            return 0;
        }

        int length = 0;
        Node current = head;
        while(current.next != null){
            length++;
            current = current.next;
        }
        System.out.println(length);
        return length;
    }

    //获取单链表倒数数第K个值(需要遍历链表)
    public int findNode(int k){
        if (head == null){
            return 0;
        }

        int length = 0;
        Node current = head;
        while(current.next != null){
            length++;
            current = current.next;
        }
        current=head;
        for(int i=0;i<length-k+1;i++){
            current=current.next;
        }
        System.out.println(current.data);
        return current.data;

    }

    //获取单链表倒数数第K个值(不需要遍历链表)
    public int findNode(Node head,int k){
        Node first = head;
        Node second = head;

        if(head == null){
            return -1;
        }

        for (int i=0;i<k-1;i++){
            second=second.next;
            if(second==null){
                return -1;
            }
        }

        while (second.next !=null){
            first=first.next;
            second=second.next;
        }
        System.out.println(first.data);
        return first.data;
    }

    //获取单链表的中间值
    public int findMinNode(Node head){
        Node first = head;
        Node second = head;

        if(head == null){
            return -1;
        }

        while (second!=null && second.next !=null){
            first=first.next;
            second=second.next.next;
        }
        System.out.println(first.data);
        return first.data;
    }

    //合并两个有序的单链表，合并之后依然后序
    public Node mergeLinkList(Node head1,Node head2){
        Node head=null;
        Node current=null;

        if(head1.next==null && head2.next==null){
            return null;
        }
        if(head1.next==null){
            head=head2;
        }
        if(head2.next==null){
            head=head1;
        }


        while (head1.next==null || head2.next==null ) {
            if (head1.next.data < head2.next.data) {
                head = head1;
                current = head1;
                current.next = head1.next;
                head2 = head2.next;
            } else {
                head = head2;
                current = head2;
                current.next = head2.next;
                head1 = head1.next;
            }
        }

//        if(head1.next!=null){
//            current.next=head1.next;
//        }
//        if(head2.next!=null){
//            current.next=head2.next;
//        }
        return head;
    }

    class Node {
        //注：此处的两个成员变量权限不能为private，因为private的权限是仅对本类访问。
        int data; //数据域
        Node next;//指针域

        public Node(int data) {
            this.data = data;
        }
    }


    public static void main(String[] args) {
        LinkList list = new LinkList();
        //向LinkList中添加数据
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

//        list.print(list.head);// 从head节点开始遍历输出
//        list.getLength(list.head);//从head节点开始计算长度
//        list.findNode(list.head,2);
//        list.findNode(2);
//        list.findMinNode(list.head);
        LinkList list1 = new LinkList();
        LinkList list2 = new LinkList();
        for (int i = 0; i < 10; i++) {
            list1.add(i);
        }
        list2.head = list2.mergeLinkList(list.head,list1.head);
        list2.print(list2.head);
    }

}