package main6;

import java.io.*;
import java.util.InputMismatchException;

public class Main {
    static String className = "Main6";
    static String fileName = "A";

    public static void Execute(FastScanner sc, StringBuilder sb){
        while(true){

        }
    }

    private static void runOne() throws IOException {
        String input = "inputs/" + fileName + ".txt";
        String output = "outputs/" + fileName + "_" + className + ".txt";

        File in_file = new File(input);
        FastScanner sc = new FastScanner(new FileInputStream(in_file));

        StringBuilder sb = new StringBuilder();

        Execute(sc, sb);

        File out_file = new File(output);
        FileWriter fw = new FileWriter(out_file);
        fw.write(sb.toString());
        fw.close();
    }

    private static void runAll() throws IOException {

        File inputFolder = new File("inputs/");

        File[] inputFiles = inputFolder.listFiles();

        if(inputFiles == null)
            return;

        for(File inputFile : inputFiles){
            StringBuilder sb = new StringBuilder();

            FastScanner sc = new FastScanner(new FileInputStream(inputFile));

            Execute(sc, sb);

            String output = "outputs/" + inputFile.getName().split(".")[0] + "_" + className + ".txt";

            File out_file = new File(output);
            FileWriter fw = new FileWriter(out_file);
            fw.write(sb.toString());
            fw.close();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Class name is" + className);
        runOne();
//        runAll();

        System.out.println("Class name is" + className);
    }
}

class FastScanner {
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public FastScanner(InputStream is) {
        stream = is;
    }

    public int read() {
        if (numChars == -1) throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try{
                numChars = stream.read(buf);
            }
            catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0) return -1;
        }
        return buf[curChar++];
    }

    public int nextInt(){
        int c = read();
        while (isSpaceChar(c)) c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9') throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public String next() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public double nextDouble() {
        int c = read();
        while (isSpaceChar(c)) c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        double res = 0;
        while (!isSpaceChar(c) && c != '.') {
            if (c == 'e' || c == 'E') return res * Math.pow(10, nextInt());
            if (c < '0' || c > '9') throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        }
        if (c == '.') {
            c = read();
            double m = 1;
            while (!isSpaceChar(c)) {
                if (c == 'e' || c == 'E') return res * Math.pow(10, nextInt());
                if (c < '0' || c > '9') throw new InputMismatchException();
                m /= 10;
                res += (c - '0') * m;
                c = read();
            }
        }
        return res * sgn;
    }

    public long nextLong() {
        int c = read();
        while (isSpaceChar(c)) c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9') throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public boolean isSpaceChar(int c) {
        if (filter != null) return filter.isSpaceChar(c);
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }
}