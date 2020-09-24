package hw3.hash;

import java.util.LinkedList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int N = oomages.size();
        LinkedList<Oomage>[] buckets = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            buckets[i] = new LinkedList<Oomage>();
        }
        for (int i = 0; i < N; i++) {
            int bucketNum = (oomages.get(i).hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum].add(oomages.get(i));
        }
        for (int i = 0; i < M; i++) {
            if (buckets[i].size() < N / 50 || buckets[i].size() > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
