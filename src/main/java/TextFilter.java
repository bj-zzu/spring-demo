import java.io.*;
import java.util.*;

public class TextFilter {
    private static final String SRC_FILE = "D:/textfilter_pre/10-Asthma00025.txt";
    private static final String TARGET_FILE = "D:/textfiltertarget.txt";

    public static void main(String[] args) {
        List<CharItem> charItems = null;
        try {
            System.out.println("start read file:" + SRC_FILE);
            charItems = readFileText(new File(SRC_FILE));
            // 
            Collections.sort(charItems, new Comparator<CharItem>() {
                @Override
                public int compare(CharItem o1, CharItem o2) {
                    if (o1.count == o2.count) {
                        return 0;
                    }
                    return o1.count > o2.count ? 1 : -1;
                }
            });
            System.out.println("write result to target file:" + TARGET_FILE);
            writeToTargetFile(TARGET_FILE, charItems);
            System.out.println("finished.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<CharItem> readFileText(File file) throws IOException {
        if (file == null || !file.exists() || file.isDirectory()) {
            throw new IllegalArgumentException("file error");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String s = null;
        Map<Character, Long> map = new HashMap<Character, Long>();
        long countAll = 0L;
        try {
            while ((s = br.readLine()) != null) {
                char[] chars = s.toLowerCase().toCharArray();
                for (char c : chars) {
                    if (c >= 'a' && c <= 'z') {
                        countAll++;
                        if (map.containsKey(c)) {
                            map.put(c, map.get(c) + 1);
                        } else {
                            map.put(c, 1L);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }

        List<CharItem> list = new ArrayList<CharItem>();
        for (Map.Entry<Character, Long> entry : map.entrySet()) {
            double v = entry.getValue() / (countAll + 0.0);
            list.add(new CharItem(entry.getKey(), entry.getValue(), v));
        }
        return list;
    }

    public static void writeToTargetFile(String targetFile, List<CharItem> list) throws IOException {
        File target = new File(targetFile);
        if (target.isDirectory()) {
            throw new IllegalArgumentException("targetFile error");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target)));
        try {
            if (list != null && list.size() > 0) {
                for (CharItem charItem : list) {
                    double shang = -charItem.rate * (Math.log(charItem.rate) / Math.log(2));
                    bw.write(charItem.c + "\t" + charItem.count + "\t" + String.format("%.4f", shang));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bw.close();
        }
    }

    static class CharItem {
        char c;
        long count;
        double rate;

        public CharItem(char c, long count, double rate) {
            this.c = c;
            this.count = count;
            this.rate = rate;
        }
    }
}