import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ON2_Dictionary implements Dictionary {
    private ON2 On2_dictionary;
    private Hashing h;

    public ON2_Dictionary(int n) {
        String[] arr = new String[2];
        arr[0] = "dummy1";
        arr[1] = "dummy2";
        h = new Hashing(n);
        On2_dictionary = new ON2(h);
    }

    @Override
    public String insert(String insertString) {
        On2_dictionary.insert(insertString);
        // On2_dictionary = new ON2(h);
        On2_dictionary.print();
        // return insertString + " inserted successfully";
        System.out.println("Size = " + countLines());
        return "";
    }

    @Override
    public String search(String searchString) {
        return On2_dictionary.lookUp(searchString);
    }

    @Override
    public boolean delete(String deleteString) {
        if (On2_dictionary.delete(deleteString)) {
            // On2_dictionary = new ON2(h);
            On2_dictionary.print();
            System.out.println("Size = " + countLines());
            System.out.println(deleteString + " was found and deleted");
            return true;
        } else {
            System.out.println(deleteString + " was not found");
            return false;
        }

    }

    @Override
    public String batchInsert(String path) throws IOException {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (Exception e) {
            return "File not found";
        }
        String[] array = lines.toArray(new String[0]);
        if (h.n * h.n < array.length) {
            return "No more space";
        }
        int size = On2_dictionary.batchInsertion(array);
        if (size > h.n * h.n) {
            return "No more space" + " N= " + h.n + ", Size= " + On2_dictionary.batchInsertion(array);
        }
        On2_dictionary = new ON2(h);
        On2_dictionary.print();
        System.out.println("Size = " + countLines());
        return "Batch insertion finished, Total Number of elements= " + size;
    }

    @Override
    public String batchDelete(String path) throws IOException {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (Exception e) {
            return "File not found";
        }
        String[] array = lines.toArray(new String[0]);
        int deletions = 0;
        for (int i = 0; i < array.length; i++) {
            if (On2_dictionary.delete(array[i])) {
                deletions++;
            }
            ;
        }
        // On2_dictionary = new ON2(h);
        On2_dictionary.print();
        System.out.println("Size = " + countLines());
        return "Batch deletion finished, Deleted: " + deletions;
    }




    public static int countLines() {
        int lineCount = 0;
        String filePath = "src/Output.txt";
        System.out.println("Number of lines in the file: " + lineCount);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineCount;
    }
}


