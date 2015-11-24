package Data;

import java.util.Iterator;
import java.util.TreeMap;

import jsat.linear.IndexValue;
import jsat.linear.Vec;

/**
 * @author Karlson Pfannschmidt
 *
 */
public class SparseVector extends Vec {

	private static final long serialVersionUID = 1L;

	private TreeMap<Integer, Double> tm;
	private int N;

	public SparseVector(int length) {
		tm = new TreeMap<Integer, Double>();
		N = length;
	}

	public SparseVector(AVPair[] pairs, int length) {
		this(length);
		for (final AVPair pair : pairs) {
			this.set(pair.index, pair.value);
		}
	}

	public SparseVector(double[] dense) {
		this(dense.length);
		for (int i=0; i < dense.length; i++) {
			this.set(i, dense[i]);
		}
	}

	@Override
	public Vec clone() {
		Vec result = new SparseVector(N);
		for (Integer key : tm.keySet()){
			result.set(key, tm.get(key));
		}
		return result;
	}

	@Override
	public double get(int i) {
		if (tm.containsKey(i)) {
			return tm.get(i);
		} else return 0.0;
	}

	@Override
	public boolean isSparse() {
		return true;
	}

	@Override
	public int length() {
		return this.N;
	}

	@Override
	public void set(int i, double value) {
		if (value == 0.0) tm.remove(i);
		else tm.put(i, value);
	}

	@Override
	public void mutableAdd(double c, Vec b) {
        if(b.isSparse())
            for(IndexValue iv : b) {
                increment(iv.getIndex(), c*iv.getValue());
            }
        else
            for(int i = 0; i < length(); i++)
                increment(i, c*b.get(i));
	}

	@Override
	public void mutableMultiply(double c) {
		for (Integer key : tm.keySet()) {
			this.set(key, tm.get(key) * c);
		}
	};

	@Override
	public Iterator<IndexValue> getNonZeroIterator(int start) {
		final Integer fnz = tm.isEmpty() ? null : tm.firstKey();
		Iterator<IndexValue> iter = new Iterator<IndexValue>() {
			Integer nextnz = fnz;
			IndexValue iv = new IndexValue(-1, Double.NaN);

			@Override
			public boolean hasNext() {
				return nextnz != null && nextnz >= 0;
			}

			@Override
			public IndexValue next() {
				if (nextnz == null)
					return null;
				iv.setIndex(nextnz);
				iv.setValue(get(nextnz));
				nextnz = tm.higherKey(nextnz);
				return iv;
			}

			@Override
			public void remove() {
				set(iv.getIndex(), 0.0);
			}

		};
		return iter;
	}

	public Vec sqrt() {
		Vec result = new SparseVector(N);
		for (Integer key : tm.keySet()) {
			result.set(key, Math.sqrt(tm.get(key)));
		}
		return result;
	}

}
