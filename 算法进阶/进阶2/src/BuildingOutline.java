import java.util.*;

/*
        输出轮廓线 输入为N行 a(左位置) b(右位置) c(高度)
    高建筑会遮住低的建筑的轮廓
 */
public class BuildingOutline {

    public static class Node {
        public boolean isUp;
        public int pos;
        public int h;

        public Node(boolean isUp, int pos, int h) {
            this.isUp = isUp;
            this.pos = pos;
            this.h = h;
        }
    }

    //排序  位置小的排前  同条件下上升靠后
    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.pos != o2.pos) {
                return o1.pos - o2.pos;
            }
            if (o1.isUp != o2.isUp) {
                return o1.isUp ? -1 : 1;
            }
            return 0;
        }
    }

    //核心
    public static List<List<Integer>>  buildingOutline(int[][] buildings) {
        Node[] nodes = new Node[buildings.length * 2]; //信息拆分成上下两份
        for (int i = 0; i < buildings.length; i++) {
            nodes[i * 2] = new Node(true, buildings[i][0], buildings[i][2]);
            nodes[i * 2 + 1] = new Node(false, buildings[i][1], buildings[i][2]);
        }
        Arrays.sort(nodes,new NodeComparator());
        TreeMap<Integer, Integer> htMap = new TreeMap<>(); //高度信息
        TreeMap<Integer, Integer> pmMap = new TreeMap<>(); //位置信息

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].isUp) {
                if (!htMap.containsKey(nodes[i].h)) {
                    htMap.put(nodes[i].h, 1);
                } else {
                    htMap.put(nodes[i].h, htMap.get(nodes[i].h) + 1);
                }
            } else {
                if (htMap.containsKey(nodes[i].h)) {
                    if (htMap.get(nodes[i].h) == 1) {
                        htMap.remove(nodes[i].h);
                    } else {
                        htMap.put(nodes[i].h, htMap.get(nodes[i].h) - 1);
                    }
                }
            }
            if (htMap.isEmpty()) {
                pmMap.put(nodes[i].pos, 0);
            } else {
                pmMap.put(nodes[i].pos, htMap.lastKey());
            }
        }
        List<List<Integer>> res = new ArrayList<>(); // 装房轮廓信息
        int start = 0;
        int height = 0;
        for (Map.Entry<Integer, Integer> entry : pmMap.entrySet()) {
            int curPos = entry.getKey();
            int curMaxH = entry.getValue();
            if (height != curMaxH) {
                if (height != 0) {
                    List<Integer> newRecord = new ArrayList<>();
                    newRecord.add(start);
                    newRecord.add(curPos);
                    newRecord.add(height);
                    res.add(newRecord);
                }
                start = curPos;
                height = curMaxH;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] buildings = {
                {1,3,3},
                {2,3,2}
        };
        List<List<Integer>> lists = buildingOutline(buildings);

        for (int i = 0; i < lists.size(); i++) {
            List<Integer> list = lists.get(i);
            for (Integer a : list) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }
}
