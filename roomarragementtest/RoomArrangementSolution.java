import java.util.HashMap;

public class RoomArrangementSolution {

    private static boolean[][] seatMap = new boolean[100][100];
    private static HashMap<String, Node> seats=new HashMap<String, Node>();

    public static void main(String[] args) {

        String testData = "[5,2,8]-> 7 [3,5,14]-> 18 [1,16,1]-> 0 [3,5,1]-> 0 [8,2,12]-> 10 [16,1,1]-> 0 [3,3,6]-> 3 [2,6,12]-> 16 [15,1,0]-> 0 [5,3,7]-> 0 [4,3,5]-> 0 [3,5,11]-> 8 [7,2,13]-> 16 [15,1,6]-> 0 [15,1,15]-> 14 [4,4,9]-> 2 [5,3,8]-> 0 [3,5,6]-> 0 [16,1,7]-> 0 [1,15,7]-> 0 [4,3,12]-> 17 [5,3,13]-> 14 [2,4,5]-> 2 [5,3,5]-> 0 [16,1,16]-> 15 [2,5,8]-> 7 [5,3,4]-> 0 [5,3,10]-> 6 [4,4,7]-> 0 [3,5,9]-> 3 [4,2,2]-> 0 [4,4,15]-> 20 [2,2,4]-> 4 [5,3,11]-> 8 [4,4,8]-> 0 [1,16,9]-> 1 [4,4,16]-> 24 [1,15,6]-> 0 [15,1,8]-> 0 [5,3,6]-> 0 [16,1,9]-> 1 [3,5,15]-> 22 [1,15,1]-> 0 [1,15,0]-> 0 [2,5,9]-> 10 [3,5,10]-> 6 [1,15,15]-> 14 [3,2,0]-> 0 [5,3,2]-> 0 [5,3,1]-> 0 [5,2,4]-> 0 [3,5,4]-> 0 [2,7,13]-> 16 [3,3,0]-> 0 [7,2,11]-> 10 [4,4,0]-> 0 [1,1,0]-> 0 [2,6,9]-> 7 [3,5,3]-> 0 [5,3,15]-> 22 [5,2,6]-> 2 [3,4,12]-> 17 [2,3,6]-> 7 [1,1,1]-> 0 [15,1,1]-> 0 [1,16,16]-> 15 [2,2,2]-> 0 [3,3,9]-> 12 [16,1,8]-> 0 [9,1,6]-> 2 [5,3,12]-> 11 [2,2,3]-> 2 [3,5,7]-> 0 [7,2,0]-> 0 [4,3,6]-> 0 [2,3,4]-> 2 [1,15,8]-> 0 [16,1,0]-> 0 [5,3,9]-> 3 [15,1,7]-> 0 [2,4,6]-> 4 [1,16,7]-> 0 [3,5,12]-> 11 [1,16,8]-> 0 [4,4,1]-> 0 [3,5,0]-> 0 [3,5,8]-> 0 [1,16,0]-> 0 [5,3,3]-> 0 [5,3,0]-> 0 [1,13,9]-> 4 [3,5,2]-> 0 [1,9,6]-> 2 [6,2,12]-> 16 [4,3,8]-> 4 [3,5,5]-> 0 [5,3,14]-> 18 [4,3,7]-> 2 [6,2,4]-> 0 [3,5,1]-> 0";
        String[] testItems = testData.split(" ");
        for (int i = 0; i < testItems.length; i++) {
            int expectedAnswer = Integer.parseInt(testItems[i + 1]);
            final String firstData = testItems[i].split("->")[0].substring(1,testItems[i].split("->")[0].length() -1);
            final int x = Integer.parseInt(firstData.split(",")[0]);
            final int y = Integer.parseInt(firstData.split(",")[1]);
            final int width = Integer.parseInt(firstData.split(",")[2]);
            int answer1 = arrangeMeetingRoom(x,y,width);
            int answer2 = arrangeMeetingRoom(x,y,width);
            int answer = answer1;
            if (answer2 < answer1) {
                answer = answer2;
            }
            if (expectedAnswer != answer) {
                System.out.println("X_X Wrong Answer, expectedAnswer:" + expectedAnswer + ", But Your Answer is: " + answer1 + answer2);
                break;
            }
            else {
                System.out.println("Success");
            }
            ++i;
        }

    }

    private static class Node {
        int neighbor;
        int x;
        int y;

        public Node(int neighbor, int x, int y) {
            this.neighbor = neighbor;
            this.x = x;
            this.y = y;
        }

        public Node setNeighbor(int neighbor) {
            this.neighbor = neighbor;
            return this;
        }
    }

    private static int getSeatStateAfterTaking(int x, int y, int width, int height) {
        int numberOfNeighbor = 0;
        // check neighbor
        if (x - 1 >= 0) {
            if (seatMap[x - 1][y]) {
                numberOfNeighbor += 1;
            }
        }

        if (x + 1 < width) {
            if (seatMap[x + 1][y]) {
                numberOfNeighbor += 1;
            }
        }

        if (y - 1 >= 0) {
            if (seatMap[x][y - 1]) {
                numberOfNeighbor += 1;
            }
        }

        if (y + 1 < height) {
            if (seatMap[x][y + 1]) {
                numberOfNeighbor += 1;
            }
        }
        return numberOfNeighbor;
    }

    private static void emptySeatMap(int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                String coordinate = i + "," + j;
                seats.put(coordinate, new Node(0, i, j));
                seatMap[i][j] = false;
            }
        }
    }

    private static int arrangeMeetingRoom(int width,
                                           int height,
                                           int numberOfUsers) {

        emptySeatMap(width, height);
        int minNumberOfNeighbor;
        int posX = -1;
        int posY = -1;
        int checkingCounter = 0;
        for (int k = 0; k < numberOfUsers; k++) {
            minNumberOfNeighbor = 5;

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (!seatMap[i][j]) {
                        checkingCounter += 1;
                        int newNumberOfNeighbor = getSeatStateAfterTaking(i, j, width, height);
                        if ((checkingCounter % 2 == 1) && newNumberOfNeighbor == minNumberOfNeighbor || newNumberOfNeighbor < minNumberOfNeighbor) {
                            minNumberOfNeighbor = newNumberOfNeighbor;
                            posX = i;
                            posY = j;
                        }
                    }
                }
            }

            seatMap[posX][posY] = true;
            final String coordinate = posX + "," + posY;
            final Node updatedNode = seats.get(coordinate).setNeighbor(minNumberOfNeighbor);
            seats.put(coordinate, updatedNode);

        }

        int count = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (j + 1 < height && seatMap[i][j] && seatMap[i][j + 1]) {
                    count += 1;
                }
            }
        }

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (i + 1 < width && seatMap[i][j] && seatMap[i + 1][j]) {
                    count += 1;
                }
            }
        }

        return count;
    }
}