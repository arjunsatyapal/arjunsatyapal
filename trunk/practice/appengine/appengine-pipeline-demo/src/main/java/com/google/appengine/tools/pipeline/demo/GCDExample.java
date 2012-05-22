// Copyright 2011 Google Inc.
// 
// Licensed under the Apache License, Version 2.0 (the "License"); you may not
// use this file except in compliance with the License. You may obtain a copy of
// the License at
// 
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
// WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
// License for the specific language governing permissions and limitations under
// the License.
package com.google.appengine.tools.pipeline.demo;

import com.google.appengine.tools.pipeline.FutureValue;
import com.google.appengine.tools.pipeline.Job1;
import com.google.appengine.tools.pipeline.Job2;
import com.google.appengine.tools.pipeline.Value;
import com.google.appengine.tools.pipeline.util.Pair;

/**
 * 
 * @author rudominer@google.com (Mitch Rudominer)
 * 
 */
public class GCDExample {

  /**
   * Performs the Euclidean algorithm on a pair of non-negative integers (a,b).
   * Returns GCD(a,b)
   */
  @SuppressWarnings({ "serial" })
  public static class GCDJob extends Job2<Integer, Integer, Integer> {
    @Override
    public Value<Integer> run(Integer a, Integer b) {
      checkPositive(a, "a");
      checkPositive(b, "b");
      FutureValue<Pair<Integer, Integer>> orderedPair =
          futureCall(new OrderIntsJob(), immediate(a), immediate(b));
      return futureCall(new EuclAlgJob(), orderedPair);
    }

    private void checkPositive(Integer x, String name) {
      if (null == x) {
        throw new IllegalArgumentException(name + " is null.");
      }
      if (x <= 0) {
        throw new IllegalArgumentException(name + " is not positive: " + x);
      }
    }
  }

  /**
   * Performs the Euclidean algorithm on a pair of non-negative integers (a,b)
   * assuming the work has already been done to assure that a<=b Returns the GCD
   * of (a,b)
   */
  @SuppressWarnings({ "serial" })
  public static class EuclAlgJob extends Job1<Integer, Pair<Integer, Integer>> {
    @Override
    public Value<Integer> run(Pair<Integer, Integer> intPair) {
      try {
        int a = intPair.first;
        int b = intPair.second;
        // Assume a<=b
        if (a == b) {
          return immediate(a);
        } else {
          // Else a<b
          FutureValue<Integer> difference = futureCall(new DiffJob(), immediate(b), immediate(a));
          FutureValue<Integer> gcd = futureCall(new GCDJob(), immediate(a), difference);
          return gcd;
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * Given two integers a and b returns the pair (a,b) if a<=b else returns the
   * pair (b,a).
   */
  @SuppressWarnings({ "serial", "unused" })
  public static class OrderIntsJob extends Job2<Pair<Integer, Integer>, Integer, Integer> {

    @Override
    public Value<Pair<Integer, Integer>> run(Integer a, Integer b) {
      Pair<Integer, Integer> pair;
      if (a < b) {
        return immediate(new Pair<Integer, Integer>(a, b));
      } else {
        return immediate(new Pair<Integer, Integer>(b, a));
      }
    }
  }

  /**
   * Given two integers b and a returns b - a.
   */
  @SuppressWarnings({ "serial" })
  public static class DiffJob extends Job2<Integer, Integer, Integer> {
    @Override
    public Value<Integer> run(Integer b, Integer a) {
      return immediate(b - a);
    }
  }

}