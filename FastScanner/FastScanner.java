import java.io.*;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;

public class FastScanner implements AutoCloseable {
    private InputStreamReader inStream;
    private boolean isClosed = false;
    private char[] buffer = new char[8192];
    private int ptr = 0;
    private int size = 0;
    private String currentWord = null;
    private Checker checker;

    public interface Checker {
        boolean check(char ch); // What should be included
    }

    public void resetBufferSize(int size) {
        if (size > 0) {
            buffer = new char[size];
        } else {
            System.err.println("Incorrect buffer size");
        }
    }

    public FastScanner(File file, Charset charset, Checker checker) throws FileNotFoundException {
        this(new FileInputStream(file), charset, checker);
    }

    public FastScanner(InputStream source, Charset charset, Checker checker) {
        inStream = new InputStreamReader(source, charset);
        this.checker = checker;
    }

    private void CheckNotClosed() throws IllegalStateException {
        if (isClosed) {
            throw new IllegalStateException("Scanner closed");
        }
    }

    private void updateBuffer() throws IOException, IllegalStateException {
        CheckNotClosed();
        size = inStream.read(buffer);
        ptr = 0;
    }

    public boolean inputNotEmpty() throws IOException, IllegalStateException {
        CheckNotClosed();
        if (ptr == size) {
            updateBuffer();
        }
        return ptr < size;
    }

    public boolean LineEndReached() throws IOException, IllegalStateException {
        while (ptr < size && !checker.check(buffer[ptr]) && buffer[ptr] != '\n' && buffer[ptr] != '\r') {
            ptr++;
            if (ptr == size) {
                updateBuffer();
            }
        }
        return buffer[ptr] == '\r' || buffer[ptr] == '\n';
    }

    public void newLine() throws IOException, IllegalStateException {
        CheckNotClosed();
        if (LineEndReached()) {
            ptr++;
            if (ptr == buffer.length) {
                updateBuffer();
            }
            if (buffer[ptr] == '\n') {
                ptr++;
            }
        }
    }

    private int beginNext() throws IOException, IllegalStateException {
        CheckNotClosed();
        if (size == 0) {
            updateBuffer();
        }
        while (ptr < size && !checker.check(buffer[ptr])) {
            ptr++;
            if (ptr == size) {
                updateBuffer();
            }
        }
        return ptr;
    }


    private String next() throws NoSuchElementException, IOException, IllegalStateException {
        CheckNotClosed();
        if (!hasNext()) {
            throw new NoSuchElementException("Next element not found");
        }
        StringBuilder nextSubstr = new StringBuilder();
        while (ptr < size && checker.check(buffer[ptr])) {
            nextSubstr.append(buffer[ptr]);
            ptr++;
            if (ptr == size) {
                updateBuffer();
            }
        }
        return nextSubstr.toString().toLowerCase();
    }

    public boolean hasNext() {
        try {
            return beginNext() < size;
        } catch (IOException | IllegalStateException e) {
            return false;
        }
    }
/*
    public boolean hasNextInt() {
        boolean result = true;
        if (result) {
            if (currentWord == null) {
                try {
                    currentWord = next();
                } catch (IOException | IllegalStateException | NoSuchElementException e) {
                    result = false;
                }
            }
            try {
                Integer.parseInt(currentWord);
            } catch (NumberFormatException e) {
                result = false;
            }
        }
        return result;
    }

    public boolean hasNextHex() {
        boolean result = true;
        if (currentWord == null) {
            try {
                currentWord = next();
            } catch (IOException | IllegalStateException | NoSuchElementException e) {
                result = false;
            }
        }
        if (result) {
            if (currentWord.length() > 2 && currentWord.charAt(0) == '0' && currentWord.charAt(1) == 'x') {
                try {
                    Integer.parseUnsignedInt(currentWord.substring(2).toUpperCase(), 16);
                } catch (NumberFormatException e) {
                    result = false;
                }
            } else {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < currentWord.length(); i++) {
                    if (currentWord.charAt(i) >= 'a' && currentWord.charAt(i) <= 'j') {
                        builder.append(currentWord.charAt(i) - 'a');
                    } else {
                        builder.append(currentWord.charAt(i));
                    }
                }
                try {
                    Integer.parseInt(builder.toString());
                } catch (NumberFormatException e) {
                    result = false;
                }
            }
        }
        return result;
    }
*/
    public String nextWord() throws NoSuchElementException, IOException, IllegalStateException {
        String result;
        if (currentWord == null) {
            result = next();
        } else {
            result = currentWord;
            currentWord = null;
        }
        return result;
    }

    public int nextInt() throws NumberFormatException, IOException, IllegalStateException, NoSuchElementException {
        if (currentWord == null) {
            currentWord = next();
        }
        int result = Integer.parseInt(currentWord);
        currentWord = null;
        return result;
    }

    public int nextHex() throws NumberFormatException, IOException, IllegalStateException, NoSuchElementException {
        if (currentWord == null) {
            currentWord = next();
        }
        if (currentWord.length() > 2 && currentWord.charAt(0) == '0' && currentWord.charAt(1) == 'x') {
            int result = Integer.parseUnsignedInt(currentWord.substring(2).toUpperCase(), 16);
            currentWord = null;
            return result;
        } else {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < currentWord.length(); i++) {
                if (currentWord.charAt(i) >= 'a' && currentWord.charAt(i) <= 'j') {
                    builder.append(currentWord.charAt(i) - 'a');
                } else {
                    builder.append(currentWord.charAt(i));
                }
            }
            currentWord = null;
            return Integer.parseInt(builder.toString());
        }
    }

    public void close() throws IOException, IllegalStateException {
        if (!isClosed) {
            inStream.close();
            isClosed = true;
        } else {
            throw new IllegalStateException("Scanner closed");
        }
    }
}