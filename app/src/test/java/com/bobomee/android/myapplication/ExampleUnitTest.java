/*
 * Copyright (C) 2016.  BoBoMEe(wbwjx115@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.bobomee.android.myapplication;

import java.util.ArrayDeque;
import java.util.Queue;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
  @Test public void addition_isCorrect() throws Exception {

    //int query = query("abcdacadb", "af");
    //
    //System.out.println("" + query);

    String abaaabbaa = count("abaaabbaa");

    System.out.println(abaaabbaa);
  }

  public static int query(String s1, String s2) {
    for (int i = 0, len1 = s1.length(); i < len1; i++) {
      int j = 0;//用j去扫子串
      int k = i;//用这个临时变量保存i往后扫和子串比较
      while (j < s2.length() && s1.charAt(k) == s2.charAt(j)) {
        j++;
        k++;
      }
      if (j == s2.length()) {//子串扫到尾了，说明找到了
        return k - s2.length();
      }
    }

    return -1;
  }

  public static String count(String input) {

    if (input == null || input.length() == 0) return input;
    char[] chars = input.toCharArray();
    Queue<String> queue = new ArrayDeque<>();
    for (char aChar : chars) {
      queue.add(String.valueOf(aChar));
    }

    String lastS = null;
    String s;
    int count = 1;
    StringBuilder stringBuilder = new StringBuilder();

    while ((s = queue.poll()) != null) {

      if (lastS != null) {

        if (lastS.equals(s)) {
          count++;
        } else {
          if (count > 1) stringBuilder.append(count);
          stringBuilder.append(lastS);
          count = 1;
        }
      }
      lastS = s;
    }

    if (count > 1) stringBuilder.append(count);
    stringBuilder.append(lastS);

    return stringBuilder.toString();
  }
}