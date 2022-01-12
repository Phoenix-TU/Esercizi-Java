/*

Copyright 2021 Luca Prigioniero, Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordList implements Comparable<WordList> {

  private final List<String> words = new ArrayList<>();
  private String min = null;

  public void add(final String word) {
    words.add(word);
    if (min == null) min = word;
    else if (min.compareTo(word) > 0) min = word;
  }

  public List<String> words() {
    words.sort(null);
    return Collections.unmodifiableList(words);
  }

  public int size() {
    return words.size();
  }

  @Override
  public int compareTo(WordList o) {
    final int lc = -Integer.compare(words.size(), o.words.size());
    if (lc == 0) return min.compareTo(o.min);
    return lc;
  }
}
