public class Main {
    public static void main(String[] args) {
        System.out.println("Question 1 - Advantages of CircularList");
        System.out.println("---------------------------------------");
        for (int i = 1; i <= 10; i++) {
            CircularList cList = new CircularList(10000 * i);
            long cStart = System.nanoTime();
            for (int m = 0; m < 10000 * i; m++) {
                cList.insert(m, m, "hello");
            }
            long cEnd = System.nanoTime();
            long cTime = (cEnd - cStart) / (10000 * i);

            TreeList tList = new TreeList();
            long tStart = System.nanoTime();
            for (int m = 0; m < 10000 * i; m++) {
                tList.insert(m, m, "hello");
            }
            long tEnd = System.nanoTime();
            long tTime = (tEnd - tStart) / (10000 * i);

            double avgLeftRotations = (double) (tList.tree.numLeftRotations) / (10000 * i);
            double avgRightRotations = (double) (tList.tree.numRightRotations) / (10000 * i);

            System.out.println("i=" + i + ", CircularList avg time=" + cTime + ", TreeList avg time=" + tTime + ".");
            System.out.println("i=" + i + ", avg left rotations=" + avgLeftRotations + ", avg right rotations=" + avgRightRotations + ".");
        }
        System.out.println();

        System.out.println("Question 2 - Advantages of TreeList");
        System.out.println("-----------------------------------");
        for (int i = 1; i <= 10; i++) {
            CircularList cList = new CircularList(10000 * i);
            long cStart = System.nanoTime();
            for (int m = 0; m < 10000 * i; m++) {
                cList.insert(m / 2, m, "hello");
            }
            long cEnd = System.nanoTime();
            long cTime = (cEnd - cStart) / (10000 * i);

            TreeList tList = new TreeList();
            long tStart = System.nanoTime();
            for (int m = 0; m < 10000 * i; m++) {
                tList.insert(m / 2, m, "hello");
            }
            long tEnd = System.nanoTime();
            long tTime = (tEnd - tStart) / (10000 * i);

            double avgLeftRotations = (double) (tList.tree.numLeftRotations) / (10000 * i);
            double avgRightRotations = (double) (tList.tree.numRightRotations) / (10000 * i);

            System.out.println("i=" + i + ", CircularList avg time=" + cTime + ", TreeList avg time=" + tTime + ".");
            System.out.println("i=" + i + ", avg left rotations=" + avgLeftRotations + ", avg right rotations=" + avgRightRotations + ".");
        }
        System.out.println();

        System.out.println("Question 3 - Random inserts");
        System.out.println("-----------------------------------");
        for (int i = 1; i <= 10; i++) {
            CircularList cList = new CircularList(10000 * i);
            long cStart = System.nanoTime();
            for (int m = 0; m < 10000 * i; m++) {
                int index = (int) (Math.random() * (m + 1));
                cList.insert(index, m, "hello");
            }
            long cEnd = System.nanoTime();
            long cTime = (cEnd - cStart) / (10000 * i);

            TreeList tList = new TreeList();
            long tStart = System.nanoTime();
            for (int m = 0; m < 10000 * i; m++) {
                int index = (int) (Math.random() * (m + 1));
                tList.insert(index, m, "hello");
            }
            long tEnd = System.nanoTime();
            long tTime = (tEnd - tStart) / (10000 * i);

            double avgLeftRotations = (double) (tList.tree.numLeftRotations) / (10000 * i);
            double avgRightRotations = (double) (tList.tree.numRightRotations) / (10000 * i);

            System.out.println("i=" + i + ", CircularList avg time=" + cTime + ", TreeList avg time=" + tTime + ".");
            System.out.println("i=" + i + ", avg left rotations=" + avgLeftRotations + ", avg right rotations=" + avgRightRotations + ".");
        }
        System.out.println();
    }
}
