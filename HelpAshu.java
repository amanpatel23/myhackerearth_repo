import java.io.*;
import java.util.StringTokenizer;

public class HelpAshu {

    static FastScanner fs;
    static FastWriter fw;
    static boolean checkOnlineJudge = System.getProperty("ONLINE_JUDGE") == null;

    private static final int mod = (int) (1e9 + 7);

    private static int[] arr;

    public static void main(String[] args) throws IOException {

        fs = new FastScanner();
        fw = new FastWriter();

        int t = 1;
        //t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }

        fw.out.close();
    }

    static void solve() {

        int n = fs.nextInt();
        arr = new int[n + 5];
        for (int i = 1; i <= n; i++)
            arr[i] = (fs.nextInt() & 1);

        SegmentTree st = new SegmentTree(n);

        int q = fs.nextInt();
        while (q-- > 0) {
            int qt = fs.nextInt(), x = fs.nextInt(), y = fs.nextInt();
            if (qt == 0) {
                arr[x] = (y & 1);
                st.update(1, 1, n, x);
            } else if (qt == 1) {
                int result = st.query(1, 1, n, x, y, 0);
                fw.out.println(result);
            } else {
                int result = st.query(1, 1, n, x, y, 1);
                fw.out.println(result);
            }
        }

    }

    private static class SegmentTree {

        private final int[][] st;

        SegmentTree(int n) {
            st = new int[(4 * n) + 5][2];
            buildTree(1, 1, n);
        }

        private void buildTree(int si, int ss, int se) {
            if (ss == se) {
                st[si] = new int[]{0, 0};
                st[si][arr[ss]] += 1;
                return;
            }

            int mid = ss + ((se - ss) / 2);
            buildTree(2 * si, ss, mid);
            buildTree((2 * si) + 1, mid + 1, se);
            st[si] = new int[]{
                    st[2 * si][0] + st[(2 * si) + 1][0],
                    st[2 * si][1] + st[(2 * si) + 1][1]
            };
        }

        private int query(int si, int ss, int se, int qs, int qe, int qt) {
            if (qs > se || qe < ss)
                return 0;
            if (ss >= qs && se <= qe)
                return st[si][qt];

            int mid = ss + ((se - ss) / 2);
            int l = query(2 * si, ss, mid, qs, qe, qt);
            int r = query((2 * si) + 1, mid + 1, se, qs, qe, qt);

            return (l + r);
        }

        private void update(int si, int ss, int se, int qi) {
            if (ss == se) {
                st[si] = new int[]{0, 0};
                st[si][arr[ss]]++;
                return;
            }

            int mid = ss + ((se - ss) / 2);
            if (qi <= mid)
                update(2 * si, ss, mid, qi);
            else
                update((2 * si) + 1, mid + 1, se, qi);

            st[si] = new int[]{
                    st[2 * si][0] + st[(2 * si) + 1][0],
                    st[2 * si][1] + st[(2 * si) + 1][1]
            };
        }
    }

    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() throws IOException {
            if (checkOnlineJudge)
                this.br = new BufferedReader(new FileReader("src/input.txt"));
            else
                this.br = new BufferedReader(new InputStreamReader(System.in));

            this.st = new StringTokenizer("");
        }

        public String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public String nextLine() {
            if (st.hasMoreTokens()) {
                return st.nextToken("").trim();
            }
            try {
                return br.readLine().trim();
            } catch (IOException err) {
                err.printStackTrace();
            }
            return "";
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    private static class FastWriter {
        PrintWriter out;

        FastWriter() throws IOException {
            if (checkOnlineJudge)
                out = new PrintWriter(new FileWriter("src/output.txt"));
            else
                out = new PrintWriter(System.out);
        }
    }
}
