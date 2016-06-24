/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core 

	Copyright (c) 2006 Morozko

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Apache License v2.0
	which accompanies this distribution, and is available at
	http://www.apache.org/licenses/
	(txt version : http://www.apache.org/licenses/LICENSE-2.0.txt
	html version : http://www.apache.org/licenses/LICENSE-2.0.html)

   This product includes software developed at
   The Apache Software Foundation (http://www.apache.org/).
</copyright>
*****************************************************************/
/*
 * @(#)SortUtils.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.util.sort
 * @creation	: 29-dic-2004 8.21.52
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.util.sort;

import java.util.List;

import org.morozko.java.core.util.sort.rule.NumberCompareRule;
import org.morozko.java.core.util.sort.rule.StringCompareRule;
import org.morozko.java.core.util.sort.sorter.MergeSorter;
import org.morozko.java.core.util.sort.swap.ArraySwapable;
import org.morozko.java.core.util.sort.swap.ByteArraySwapable;
import org.morozko.java.core.util.sort.swap.DoubleArraySwapable;
import org.morozko.java.core.util.sort.swap.FloatArraySwapable;
import org.morozko.java.core.util.sort.swap.IntArraySwapable;
import org.morozko.java.core.util.sort.swap.ListSwapable;
import org.morozko.java.core.util.sort.swap.LongArraySwapable;
import org.morozko.java.core.util.sort.swap.ShortArraySwapable;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class SortUtils {
    
    public final static Sorter DEFAULT_SORTER = new MergeSorter();
    
    public final static CompareRule RULE_STRING = new StringCompareRule();
    
    public final static CompareRule RULE_NUMBER = new NumberCompareRule();
    
    public static void sort(Number[] data) throws SortException {
        sort(data, RULE_NUMBER);
    }
    
    public static void sort(String[] data) throws SortException {
        sort(data, RULE_STRING);
    }
    
    public static void sortReverse(Number[] data) throws SortException {
        sortReverse(data, RULE_NUMBER);
    }
    
    public static void sortReverse(String[] data) throws SortException {
        sortReverse(data, RULE_STRING);
    }
    
    public static void sortReverse(Swapable data, CompareRule rule) throws SortException {
        DEFAULT_SORTER.sortReverse(data, rule);
    }
    
    public static void sortReverse(List data, CompareRule rule) throws SortException {
        DEFAULT_SORTER.sortReverse(data, rule);
    }
    
    public static void sortReverse(Object[] data, CompareRule rule) throws SortException {
        DEFAULT_SORTER.sortReverse(data, rule);
    }
    
    public static void sort(Swapable data, CompareRule rule) throws SortException {
        DEFAULT_SORTER.sort(data, rule);
    }
    
    public static void sort(List data, CompareRule rule) throws SortException {
        DEFAULT_SORTER.sort(data, rule);
    }
    
    public static void sort(Object[] data, CompareRule rule) throws SortException {
        DEFAULT_SORTER.sort(data, rule);
    }

    public static void sortReverse(float[] data) throws SortException {
        sortReverse(wrap(data), RULE_NUMBER);
    }
    
    public static void sortReverse(double[] data) throws SortException {
        sortReverse(wrap(data), RULE_NUMBER);
    }
    
    public static void sortReverse(byte[] data) throws SortException {
        sortReverse(wrap(data), RULE_NUMBER);
    }
    
    public static void sortReverse(short[] data) throws SortException {
        sortReverse(wrap(data), RULE_NUMBER);
    }
    
    public static void sortReverse(int[] data) throws SortException {
        sortReverse(wrap(data), RULE_NUMBER);
    }
    
    public static void sortReverse(long[] data) throws SortException {
        sortReverse(wrap(data), RULE_NUMBER);
    }    
    
    public static void sort(float[] data) throws SortException {
        sort(wrap(data), RULE_NUMBER);
    }
    
    public static void sort(double[] data) throws SortException {
        sort(wrap(data), RULE_NUMBER);
    }
    
    public static void sort(byte[] data) throws SortException {
        sort(wrap(data), RULE_NUMBER);
    }
    
    public static void sort(short[] data) throws SortException {
        sort(wrap(data), RULE_NUMBER);
    }
    
    public static void sort(int[] data) throws SortException {
        sort(wrap(data), RULE_NUMBER);
    }
    
    public static void sort(long[] data) throws SortException {
        sort(wrap(data), RULE_NUMBER);
    }
    
    public static void reverse(List data) {
        reverse(new ListSwapable(data));
    }
    
    public static void reverse(Object[] data) {
        reverse(new ArraySwapable(data));
    }
    
    public static void reverse(Swapable data) {
        int size = data.size();
        int last = size-1;
        for (int k=0; k<size/2; k++) {
            data.swap(k, (last-k));
        }
    }

    public static FloatArraySwapable wrap(float[] data) {
        return (new FloatArraySwapable(data));
    }
    
    public static DoubleArraySwapable wrap(double[] data) {
        return (new DoubleArraySwapable(data));
    }
    
    public static ByteArraySwapable wrap(byte[] data) {
        return (new ByteArraySwapable(data));
    }
    
    public static ShortArraySwapable wrap(short[] data) {
        return (new ShortArraySwapable(data));
    }
    
    public static IntArraySwapable wrap(int[] data) {
        return (new IntArraySwapable(data));
    }
    
    public static LongArraySwapable wrap(long[] data) {
        return (new LongArraySwapable(data));
    }
    
    private SortUtils() {
        super();
    }

}
