import java.util.*;

import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int dimension = input.nextInt();
        ArrayList<Integer> pixelMatrix = new ArrayList<>(dimension * dimension);
        for (int i = 0; i < dimension * dimension; i++)
            pixelMatrix.add(input.nextInt());
        int limitOfNumbers = input.nextInt();
        ArrayList<Integer> pixelMatrixElements = new ArrayList<>();
        for (Integer element : pixelMatrix)
            if (!pixelMatrixElements.contains(element))
                pixelMatrixElements.add(element);
        Hashtable<Integer, Integer> frequency = new Hashtable<>(pixelMatrixElements.size());
        for (Integer element : pixelMatrixElements)
            frequency.put(element, Collections.frequency(pixelMatrix, element));
        sorterByFrequency(pixelMatrixElements, frequency);
        ArrayList<Integer> chosen = new ArrayList<>(limitOfNumbers);
        for (int i = 0; i < limitOfNumbers; i++)
            chosen.add(i, pixelMatrixElements.get(pixelMatrixElements.size() - 1 - i));
        chosen.sort(Collections.reverseOrder());
        for (int i = 0; i < dimension * dimension; i++) {
            int difference = 1000000;
            int index = 0;
            for (int j = 0; j < limitOfNumbers; j++)
                if (difference > abs(pixelMatrix.get(i) - chosen.get(j))) {
                    difference = abs(pixelMatrix.get(i) - chosen.get(j));
                    index = j;
                }
            pixelMatrix.remove(i);
            pixelMatrix.add(i, chosen.size() - index - 1);
        }
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++)
                System.out.print(pixelMatrix.get(i * dimension + j) + " ");
            System.out.println();
        }
    }

    public static void sorterByFrequency(ArrayList<Integer> pixelMatrixElements, Hashtable<Integer, Integer> frequency) {
        for (int i = 0; i < pixelMatrixElements.size(); i++)
            for (int j = i + 1; j < pixelMatrixElements.size(); j++)
                if (i != j) {
                    if (frequency.get(pixelMatrixElements.get(i)) > frequency.get(pixelMatrixElements.get(j))) {
                        int temp1 = pixelMatrixElements.get(i);
                        int temp2 = pixelMatrixElements.get(j);
                        pixelMatrixElements.remove(i);
                        pixelMatrixElements.add(i, temp2);
                        pixelMatrixElements.remove(j);
                        pixelMatrixElements.add(j, temp1);
                    } else if (Objects.equals(frequency.get(pixelMatrixElements.get(i)), frequency.get(pixelMatrixElements.get(j))) &&
                            pixelMatrixElements.get(i) > pixelMatrixElements.get(j)) {
                        int temp1 = pixelMatrixElements.get(i);
                        int temp2 = pixelMatrixElements.get(j);
                        pixelMatrixElements.remove(i);
                        pixelMatrixElements.add(i, temp2);
                        pixelMatrixElements.remove(j);
                        pixelMatrixElements.add(j, temp1);
                    }
                }
    }
}